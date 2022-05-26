package com.pawelcz.wypozyczalnia.rezerwacja;

import java.util.List;

public interface RezerwacjaArchiwumService {

    RezerwacjaArchiwum dodajRezerwacjeDoArchiwum(RezerwacjaArchiwum rezerwacjaArchiwum);

    List<RezerwacjaArchiwum> archwiumRezerwacjiWybranegoUzytkownika(long id);
}
