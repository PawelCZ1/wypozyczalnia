package com.pawelcz.wypozyczalnia.rezerwacja;

import com.pawelcz.wypozyczalnia.samochod.Samochod;
import com.pawelcz.wypozyczalnia.samochod.SamochodService;
import com.pawelcz.wypozyczalnia.uzytkownik.Uzytkownik;
import com.pawelcz.wypozyczalnia.uzytkownik.UzytkownikService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RezerwacjaServiceImpl implements RezerwacjaService {

    private final RezerwacjaRepozytorium rezerwacjaRepozytorium;
    private final UzytkownikService uzytkownikService;
    private final SamochodService samochodService;
    private final RezerwacjaArchiwumService rezerwacjaArchiwumService;

    public RezerwacjaServiceImpl(RezerwacjaRepozytorium rezerwacjaRepozytorium, UzytkownikService uzytkownikService,
                                 SamochodService samochodService, RezerwacjaArchiwumService rezerwacjaArchiwumService) {
        super();
        this.rezerwacjaRepozytorium = rezerwacjaRepozytorium;
        this.uzytkownikService = uzytkownikService;
        this.samochodService = samochodService;
        this.rezerwacjaArchiwumService = rezerwacjaArchiwumService;
    }


    @Override
    public List<Rezerwacja> wszystkieRezerwacje() {
        return rezerwacjaRepozytorium.findAll();
    }

    @Override
    public List<Rezerwacja> rezerwacjeWybranegoUzytkownika(long id) {
        Optional<Uzytkownik> uzytkownik = uzytkownikService.znajdzUzytkownika(id);
        if(uzytkownik.isEmpty()) {
            throw new  RuntimeException("Nie istnieje użytkownik z id:" + id);
        }
        return uzytkownik.get().listaRezerwacji();
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
        if(samochod.get().rezerwacjaSamochodu() == null) {
            if(okres * samochod.get().getCenaZaDzien() <= uzytkownik.get().getSaldo()) {
                Rezerwacja rezerwacja = new Rezerwacja(uzytkownik.get(), samochod.get(), okres);
                RezerwacjaArchiwum rezerwacjaArchwium = new RezerwacjaArchiwum(uzytkownik.get(), samochod.get(), okres);
                uzytkownik.get().saldoPoRezerwacji(okres, samochod.get().getCenaZaDzien());

                dodajRezerwacje(rezerwacja);
                rezerwacjaArchiwumService.dodajRezerwacjeDoArchiwum(rezerwacjaArchwium);
            }else {
                throw new RuntimeException("Użytkownik nie posiada wystarczającej kwoty aby"
                        + " wypożyczyć wybrany samochód");
            }
        }else {
            throw new RuntimeException("Samochód już jest zarezerwowany");
        }
    }

    @Override
    public void usun(long id) {
        rezerwacjaRepozytorium.deleteById(id);
    }

    @Override
    public Optional<Rezerwacja> znajdzRezerwacje(long id){
        return rezerwacjaRepozytorium.findById(id);
    }

    @Override
    public void usunRezerwacje(long id) {
        Optional<Rezerwacja> rezerwacja = znajdzRezerwacje(id);

        if(rezerwacja.isEmpty()) {
            throw new  RuntimeException("Nie istnieje rezerwacja z id:" + id);
        }

        rezerwacja.get().getUzytkownik().saldoPrzedRezerwacja(rezerwacja.get().getPozostaleDni()
                , rezerwacja.get().getSamochod().getCenaZaDzien());

        usun(id);
    }


}
