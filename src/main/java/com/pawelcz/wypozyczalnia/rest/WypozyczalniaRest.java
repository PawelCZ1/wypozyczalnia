package com.pawelcz.wypozyczalnia.rest;

import org.apache.naming.java.javaURLContextFactory;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/uzytkownicy")
	public void dodajUzytkownika(@RequestBody Uzytkownik uzytkownik) {
		uzytkownikRepozytorium.save(uzytkownik);
		
	}
	
	@GetMapping("/samochody")
	public List<Samochod> wszystkieSamochody(){
		return samochodRepozytorium.findAll();
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
		if(okres * uzytkownik.get().getSaldo() >= 0) {
			Rezerwacja rezerwacja = new Rezerwacja(uzytkownik.get(), samochod.get(), okres);
			uzytkownik.get().noweSaldo(okres, samochod.get().getCenaZaDzien());
			rezerwacjaRepozytorium.save(rezerwacja);
		}else {
			throw new  RuntimeException("Użytkownik nie posiada wystarczającej kwoty aby"
					+ " wypożyczyć wybrany samochód" + id_samochodu);
		}
		
		
		
		
		
	}
	


}
