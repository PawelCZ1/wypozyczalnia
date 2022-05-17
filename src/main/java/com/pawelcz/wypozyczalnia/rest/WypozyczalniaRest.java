package com.pawelcz.wypozyczalnia.rest;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping("/rezerwacje")
	public void dodajRezerwacje(@RequestBody Rezerwacja rezerwacja) {
		rezerwacjaRepozytorium.save(rezerwacja);
		
	}
	


}
