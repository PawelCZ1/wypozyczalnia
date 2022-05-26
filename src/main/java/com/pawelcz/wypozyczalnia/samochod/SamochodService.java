package com.pawelcz.wypozyczalnia.samochod;

import java.util.List;
import java.util.Optional;

public interface SamochodService {
    Samochod dodajSamochod (Samochod samochod);

    List<Samochod> wszystkieSamochody();

    List<Samochod> dostepneSamochody();

    Optional<Samochod> znajdzSamochod(long id);
}
