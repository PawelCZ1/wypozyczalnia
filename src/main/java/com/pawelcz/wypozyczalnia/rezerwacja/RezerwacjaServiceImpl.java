package com.pawelcz.wypozyczalnia.rezerwacja;

import com.pawelcz.wypozyczalnia.samochod.Samochod;
import com.pawelcz.wypozyczalnia.samochod.SamochodService;
import com.pawelcz.wypozyczalnia.uzytkownik.Uzytkownik;
import com.pawelcz.wypozyczalnia.uzytkownik.UzytkownikService;


import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class RezerwacjaServiceImpl implements RezerwacjaService {

    private final RezerwacjaRepozytorium rezerwacjaRepozytorium;
    private final UzytkownikService uzytkownikService;
    private final SamochodService samochodService;
    
    public RezerwacjaServiceImpl(RezerwacjaRepozytorium rezerwacjaRepozytorium, UzytkownikService uzytkownikService,
                                 SamochodService samochodService) {
        super();
        this.rezerwacjaRepozytorium = rezerwacjaRepozytorium;
        this.uzytkownikService = uzytkownikService;
        this.samochodService = samochodService;
        
    }


    @Override
    public List<Rezerwacja> aktualneRezerwacje() {
        return archiwumRezerwacji().stream().filter(element -> element.aktualnosc()).toList();
    }

    @Override
    public List<Rezerwacja> rezerwacjeWybranegoUzytkownika(long id) {
        Optional<Uzytkownik> uzytkownik = uzytkownikService.znajdzUzytkownika(id);
        if(uzytkownik.isEmpty()) {
            throw new  RuntimeException("Nie istnieje użytkownik z id:" + id);
        }
        return uzytkownik.get().listaRezerwacji().stream().filter(element -> element.aktualnosc()).toList();
    }

    @Override
    public Rezerwacja dodajRezerwacje(Rezerwacja rezerwacja) {
        return rezerwacjaRepozytorium.save(rezerwacja);
    }

    @Override
    public void dodajRezerwacje(long id_uzytkownika, long id_samochodu, int okres) {
        Optional<Uzytkownik> uzytkownik = uzytkownikService.znajdzUzytkownika(id_uzytkownika);
        Optional<Samochod> samochod = samochodService.znajdzSamochod(id_samochodu);
        if(uzytkownik.isEmpty()) {
            throw new  RuntimeException("Nie istnieje użytkownik z id:" + id_uzytkownika);
        }
        if(samochod.isEmpty()) {
            throw new  RuntimeException("Nie istnieje samochód z id:" + id_samochodu);
        }
        if(samochod.get().dostepnosc()) {
            if(okres * samochod.get().getCenaZaDzien() <= uzytkownik.get().getSaldo()) {
                Rezerwacja rezerwacja = new Rezerwacja(uzytkownik.get(),samochod.get(), LocalDate.now().plusDays(okres));
                
                uzytkownik.get().saldoPoRezerwacji(okres, samochod.get().getCenaZaDzien());

                dodajRezerwacje(rezerwacja);
               
            }else {
                throw new RuntimeException("Użytkownik nie posiada wystarczającej kwoty aby"
                        + " wypożyczyć wybrany samochód");
            }
        }else {
            throw new RuntimeException("Samochód już jest zarezerwowany");
        }
    }

    @Override
    public Optional<Rezerwacja> znajdzRezerwacje(long id){
        return rezerwacjaRepozytorium.findById(id);
    }

    @Override
    public void usunRezerwacje(long id) {
        Optional<Rezerwacja> rezerwacja = znajdzRezerwacje(id);

        if(rezerwacja.isEmpty() || rezerwacja.get().aktualnosc() == false) {
            throw new  RuntimeException("Nie istnieje rezerwacja z id:" + id);
        }

        rezerwacja.get().getUzytkownik().saldoPrzedRezerwacja((int)Period.between(LocalDate.now(), rezerwacja.get().getDataZakonczenia()).getDays()
                , rezerwacja.get().getSamochod().getCenaZaDzien());

        rezerwacja.get().setDataZakonczenia(LocalDate.now());        
        rezerwacja.get().setAktualne(false);
        
        rezerwacjaRepozytorium.save(rezerwacja.get());
 
    }


    @Override
    public List<Rezerwacja> archiwumRezerwacji() {
        
        return rezerwacjaRepozytorium.findAll();
    }


    @Override
    public List<Rezerwacja> archiwumRezerwacjiWybranegoUzytkownika(long id) {
        Optional<Uzytkownik> uzytkownik = uzytkownikService.znajdzUzytkownika(id);
        if(uzytkownik.isEmpty()) {
            throw new  RuntimeException("Nie istnieje użytkownik z id:" + id);
        }
        return uzytkownik.get().listaRezerwacji();
    }


    


}
