package com.pawelcz.wypozyczalnia.uzytkownik;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UzytkownikServiceImpl implements UzytkownikService {

    private final UzytkownikRepozytorium uzytkownikRepozytorium;

    public UzytkownikServiceImpl(UzytkownikRepozytorium uzytkownikRepozytorium) {
        super();
        this.uzytkownikRepozytorium = uzytkownikRepozytorium;
    }

    @Override
    public Uzytkownik zapiszUzytkownika(Uzytkownik uzytkownik) {
        return uzytkownikRepozytorium.save(uzytkownik);
    }

    @Override
    public List<Uzytkownik> wszyscyUzytkownicy() {
        return uzytkownikRepozytorium.findAll();
    }

    @Override
    public Optional<Uzytkownik> znajdzUzytkownika(long id) {
        return uzytkownikRepozytorium.findById(id);
    }
}
