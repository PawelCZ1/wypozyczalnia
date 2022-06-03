package com.pawelcz.wypozyczalnia.samochod;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SamochodServiceImpl implements SamochodService {

    private final SamochodRepozytorium samochodRepozytorium;

    public SamochodServiceImpl(SamochodRepozytorium samochodRepozytorium) {
        super();
        this.samochodRepozytorium = samochodRepozytorium;
    }


    @Override
    public Samochod dodajSamochod(Samochod samochod) {
        return samochodRepozytorium.save(samochod);
    }

    @Override
    public List<Samochod> wszystkieSamochody() {
        return samochodRepozytorium.findAll();
    }

    @Override
    public List<Samochod> dostepneSamochody() {
        return wszystkieSamochody().stream().filter(element -> element.Dostepnosc()).toList();
    }

    @Override
    public Optional<Samochod> znajdzSamochod(long id) {
        return samochodRepozytorium.findById(id);
    }
}
