package com.pawelcz.wypozyczalnia.rezerwacja;



import java.util.List;
import java.util.Optional;

public interface RezerwacjaService {
    List<Rezerwacja> aktualneRezerwacje();

    List<Rezerwacja> archiwumRezerwacji();

    List<Rezerwacja> rezerwacjeWybranegoUzytkownika(long id);

    List<Rezerwacja> archiwumRezerwacjiWybranegoUzytkownika(long id);

    Rezerwacja dodajRezerwacje(Rezerwacja rezerwacja);

    void dodajRezerwacje(long id_uzytkownika, long id_samochodu, int okres );

    Optional<Rezerwacja> znajdzRezerwacje(long id);

    void usunRezerwacje(long id);

    
}
