package com.pawelcz.wypozyczalnia.rest;




import com.pawelcz.wypozyczalnia.rezerwacja.*;
import com.pawelcz.wypozyczalnia.samochod.SamochodService;
import com.pawelcz.wypozyczalnia.uzytkownik.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pawelcz.wypozyczalnia.samochod.Samochod;
import com.pawelcz.wypozyczalnia.uzytkownik.Uzytkownik;


import java.util.List;
import java.util.Optional;



@RestController
public class WypozyczalniaRest {

	@Autowired
	private RezerwacjaRepozytorium rezerwacjaRepozytorium;
	@Autowired 
	private RezerwacjaArchiwumRepozytorium rezerwacjaArchiwumRepozytorium;
	@Autowired
	private final UzytkownikService uzytkownikService;
	@Autowired
	private final SamochodService samochodService;
	@Autowired
	private final RezerwacjaService rezerwacjaService;
	@Autowired
	private final RezerwacjaArchiwumService rezerwacjaArchiwumService;


	public WypozyczalniaRest(UzytkownikService uzytkownikService, SamochodService samochodService,
							 RezerwacjaService rezerwacjaService, RezerwacjaArchiwumService rezerwacjaArchiwumService) {
		this.uzytkownikService = uzytkownikService;
		this.samochodService = samochodService;
		this.rezerwacjaService = rezerwacjaService;
		this.rezerwacjaArchiwumService = rezerwacjaArchiwumService;
	}



	//Pokazuje wszystkich dostępnych użytkowników
	@GetMapping("/uzytkownicy")
	public List<Uzytkownik> wszyscyUzytkownicy(){
		return uzytkownikService.wszyscyUzytkownicy();
	}
	
	//Pokazuje wszystkie aktualne rezerwacje dla użytkownika z wybranym id
	@GetMapping("/rezerwacje/{id}")
	public List<Rezerwacja> rezerwacjeWybranegoUzytkownika(@PathVariable long id) {
		return rezerwacjaService.rezerwacjeWybranegoUzytkownika(id);
	}
	
	//Pokazuje wszystkie rezerwacje dla użytkownika z wybranym id
	@GetMapping("/rezerwacje/{id}/archiwum")
	public List<RezerwacjaArchiwum> archwiumRezerwacjiWybranegoUzytkownika(@PathVariable long id) {
		Optional<Uzytkownik> uzytkownik = uzytkownikService.znajdzUzytkownika(id);
		if(uzytkownik.isEmpty()) {
    		throw new  RuntimeException("Nie istnieje użytkownik z id:" + id);
    	}
		return uzytkownik.get().listaRezerwacjiArchiwum();
	}
	
	//Służy do tworzenia użytkowników
	@PostMapping("/uzytkownicy")
	public void dodajUzytkownika(@RequestBody Uzytkownik uzytkownik) {
		uzytkownikService.zapiszUzytkownika(uzytkownik);
		
	}
	
	//Pokazuje wszystkie samochody
	@GetMapping("/samochody")
	public List<Samochod> wszystkieSamochody(){
		return samochodService.wszystkieSamochody();
	}
	
	//Pokazuje wszystkie dostępne samochody
	@GetMapping("/samochody/dostepne")
	public List<Samochod> dostepneSamochody(){
		return samochodService.dostepneSamochody();
	}
	
	//Służy do tworzenia samochodów
	@PostMapping("/samochody")
	public void dodajSamochod(@RequestBody Samochod samochod) {
		samochodService.dodajSamochod(samochod);
		
	}
	
	//Pokazuje wszystkie aktualne rezerwacje
	@GetMapping("/rezerwacje")
	public List<Rezerwacja> wszystkieRezerwacje(){
		return rezerwacjaService.wszystkieRezerwacje();
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

}
