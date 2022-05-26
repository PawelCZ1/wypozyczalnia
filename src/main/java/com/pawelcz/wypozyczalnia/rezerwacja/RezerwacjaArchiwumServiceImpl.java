package com.pawelcz.wypozyczalnia.rezerwacja;

import com.pawelcz.wypozyczalnia.uzytkownik.Uzytkownik;
import com.pawelcz.wypozyczalnia.uzytkownik.UzytkownikService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RezerwacjaArchiwumServiceImpl implements RezerwacjaArchiwumService {

    private final UzytkownikService uzytkownikService;

    private final RezerwacjaArchiwumRepozytorium rezerwacjaArchiwumRepozytorium;

    public RezerwacjaArchiwumServiceImpl(UzytkownikService uzytkownikService, RezerwacjaArchiwumRepozytorium rezerwacjaArchiwumRepozytorium) {
        this.uzytkownikService = uzytkownikService;
        this.rezerwacjaArchiwumRepozytorium = rezerwacjaArchiwumRepozytorium;
    }

    @Override
    public RezerwacjaArchiwum dodajRezerwacjeDoArchiwum(RezerwacjaArchiwum rezerwacjaArchiwum) {
        return rezerwacjaArchiwumRepozytorium.save(rezerwacjaArchiwum);
    }

    @Override
    public List<RezerwacjaArchiwum> archwiumRezerwacjiWybranegoUzytkownika(long id) {
        Optional<Uzytkownik> uzytkownik = uzytkownikService.znajdzUzytkownika(id);
        if(uzytkownik.isEmpty()) {
            throw new  RuntimeException("Nie istnieje użytkownik z id:" + id);
        }
        return uzytkownik.get().listaRezerwacjiArchiwum();
    }
}
