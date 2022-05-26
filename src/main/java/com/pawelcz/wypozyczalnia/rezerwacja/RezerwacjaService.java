package com.pawelcz.wypozyczalnia.rezerwacja;



import java.util.List;
import java.util.Optional;

public interface RezerwacjaService {
    List<Rezerwacja> wszystkieRezerwacje();

    List<Rezerwacja> rezerwacjeWybranegoUzytkownika(long id);

    Rezerwacja dodajRezerwacje(Rezerwacja rezerwacja);

    void dodajRezerwacje(long id_uzytkownika, long id_samochodu, int okres );

    void usun(long id);

    Optional<Rezerwacja> znajdzRezerwacje(long id);

    void usunRezerwacje(long id);
}
