package com.pawelcz.wypozyczalnia.uzytkownik;

import java.util.List;
import java.util.Optional;


public interface UzytkownikService {

    Uzytkownik zapiszUzytkownika(Uzytkownik uzytkownik);

    List<Uzytkownik> wszyscyUzytkownicy();

    Optional<Uzytkownik> znajdzUzytkownika(long id);




}
