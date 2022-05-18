package com.pawelcz.wypozyczalnia.rest;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pawelcz.wypozyczalnia.rezerwacja.Rezerwacja;
import com.pawelcz.wypozyczalnia.rezerwacja.RezerwacjaRepozytorium;
import com.pawelcz.wypozyczalnia.samochod.Samochod;
import com.pawelcz.wypozyczalnia.samochod.SamochodRepozytorium;
import com.pawelcz.wypozyczalnia.uzytkownik.Uzytkownik;
import com.pawelcz.wypozyczalnia.uzytkownik.UzytkownikRepozytorium;


import java.util.List;
import java.util.Optional;



@RestController
public class WypozyczalniaRest {
	@Autowired
	private SamochodRepozytorium samochodRepozytorium;
	@Autowired
	private UzytkownikRepozytorium uzytkownikRepozytorium;
	@Autowired
	private RezerwacjaRepozytorium rezerwacjaRepozytorium;
	
	
	
	
	@GetMapping("/uzytkownicy")
	public List<Uzytkownik> wszyscyUzytkownicy(){
		return uzytkownikRepozytorium.findAll();
	}
	
	@GetMapping("/rezerwacje/{id}")
	public List<Rezerwacja> rezerwacjeWybranegoUzytkownika(@PathVariable long id) {
		Optional<Uzytkownik> uzytkownik = uzytkownikRepozytorium.findById(id);
		if(uzytkownik.isEmpty()) {
    		throw new  RuntimeException("Nie istnieje użytkownik z id:" + id);
    	}
		return uzytkownik.get().listaRezerwacji();
	}
	
	@GetMapping("/rezerwacje/{id}/archiwum")
	public List<Rezerwacja> archwiumRezerwacjiWybranegoUzytkownika(@PathVariable long id) {
		Optional<Uzytkownik> uzytkownik = uzytkownikRepozytorium.findById(id);
		if(uzytkownik.isEmpty()) {
    		throw new  RuntimeException("Nie istnieje użytkownik z id:" + id);
    	}
		return Uzytkownik.listaArchiwumRezerwacji().stream()
				.filter(element -> element.getUzytkownik().getId() == id ).toList();
	}
	
	@PostMapping("/uzytkownicy")
	public void dodajUzytkownika(@RequestBody Uzytkownik uzytkownik) {
		uzytkownikRepozytorium.save(uzytkownik);
		
	}
	
	@GetMapping("/samochody")
	public List<Samochod> wszystkieSamochody(){
		return samochodRepozytorium.findAll();
	}
	
	@GetMapping("/samochody/dostepne")
	public List<Samochod> dostepneSamochody(){
		return wszystkieSamochody().stream().filter(element -> element.rezerwacjaSamochodu() == null).toList();
	}
	
	@PostMapping("/samochody")
	public void dodajSamochod(@RequestBody Samochod samochod) {
		samochodRepozytorium.save(samochod);
		
	}
	
	@GetMapping("/rezerwacje")
	public List<Rezerwacja> wszystkieRezerwacje(){
		return rezerwacjaRepozytorium.findAll();
	}
	
	@PostMapping("/rezerwacje/{id_uzytkownika},{id_samochodu},{okres}")
	public void dodajRezerwacje(@PathVariable long id_uzytkownika, @PathVariable long id_samochodu, @PathVariable int okres) {
		Optional<Uzytkownik> uzytkownik = uzytkownikRepozytorium.findById(id_uzytkownika);
		Optional<Samochod> samochod = samochodRepozytorium.findById(id_samochodu);
		if(uzytkownik.isEmpty()) {
    		throw new  RuntimeException("Nie istnieje użytkownik z id:" + id_uzytkownika);
    	}
		if(samochod.isEmpty()) {
    		throw new  RuntimeException("Nie istnieje samochód z id:" + id_samochodu);
    	}
		if(samochod.get().rezerwacjaSamochodu() == null) {
			if(okres * uzytkownik.get().getSaldo() >= 0) {
				Rezerwacja rezerwacja = new Rezerwacja(uzytkownik.get(), samochod.get(), okres);
				uzytkownik.get().saldoPoRezerwacji(okres, samochod.get().getCenaZaDzien());
				Uzytkownik.listaArchiwumRezerwacji().add(rezerwacja);
				rezerwacjaRepozytorium.save(rezerwacja);
			}else {
				throw new RuntimeException("Użytkownik nie posiada wystarczającej kwoty aby"
						+ " wypożyczyć wybrany samochód");
			}	
		}else {
			throw new RuntimeException("Samochód już jest zarezerwowany");
		}		
	}
	
	@DeleteMapping("/rezerwacje/{id}")
	public void usunRezerwacje(@PathVariable long id) {
		Optional<Rezerwacja> rezerwacja = rezerwacjaRepozytorium.findById(id);
		
		if(rezerwacja.isEmpty()) {
			throw new  RuntimeException("Nie istnieje rezerwacja z id:" + id);
		}
		
		rezerwacja.get().getUzytkownik().saldoPrzedRezerwacja(rezerwacja.get().getPozostaleDni()
				, rezerwacja.get().getSamochod().getCenaZaDzien());
		
		
		rezerwacjaRepozytorium.deleteById(id);
		
	}

}
