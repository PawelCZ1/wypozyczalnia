package com.pawelcz.wypozyczalnia.rezerwacja;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RezerwacjaController {

    private final RezerwacjaService rezerwacjaService;
    


    public RezerwacjaController(RezerwacjaService rezerwacjaService) {
        this.rezerwacjaService = rezerwacjaService;
        
    }
    

    //Pokazuje wszystkie aktualne rezerwacje
	@GetMapping("/rezerwacje")
	public List<Rezerwacja> aktualneRezerwacje(){
		return rezerwacjaService.aktualneRezerwacje();
	}

    //Pokazuje wszystkie aktualne rezerwacje dla użytkownika z wybranym id
	@GetMapping("/rezerwacje/{id}")
	public List<Rezerwacja> rezerwacjeWybranegoUzytkownika(@PathVariable long id) {
		return rezerwacjaService.rezerwacjeWybranegoUzytkownika(id);
	}

    //Służy do tworzenia rezerwacji
	@PostMapping("/rezerwacje/{id_uzytkownika},{id_samochodu},{okres}")
	public void dodajRezerwacje(@PathVariable long id_uzytkownika, @PathVariable long id_samochodu, @PathVariable int okres) {
		rezerwacjaService.dodajRezerwacje(id_uzytkownika, id_samochodu, okres);
	}

    //Służy do usuwania rezerwacji, kwota zostaje zwrócona
	@DeleteMapping("/rezerwacje/{id}")
	public void usunRezerwacje(@PathVariable long id) {
		rezerwacjaService.usunRezerwacje(id);
	}
		//Pokazuje wszystkie rezerwacje wybranego użytkownika
	@GetMapping("/rezerwacje/{id}/archiwum")
	List<Rezerwacja> archiwumRezerwacjiWybranegoUzytkownika(@PathVariable long id){
		return rezerwacjaService.archiwumRezerwacjiWybranegoUzytkownika(id);

	}
		//Pokazuje wszystkie rezerwacje
	@GetMapping("/rezerwacje/archiwum")
	List<Rezerwacja> archiwumRezerwacji(){
		return rezerwacjaService.archiwumRezerwacji();
	}

	
    
    
}
