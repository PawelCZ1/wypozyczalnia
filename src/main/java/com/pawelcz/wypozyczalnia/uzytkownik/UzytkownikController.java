package com.pawelcz.wypozyczalnia.uzytkownik;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UzytkownikController {
	
    private final UzytkownikService uzytkownikService;

    public UzytkownikController(UzytkownikService uzytkownikService) {
        this.uzytkownikService = uzytkownikService;
    }

    //Pokazuje wszystkich dostępnych użytkowników
	@GetMapping("/uzytkownicy")
	public List<Uzytkownik> wszyscyUzytkownicy(){
		return uzytkownikService.wszyscyUzytkownicy();
	}

    //Służy do tworzenia użytkowników
	@PostMapping("/uzytkownicy")
	public void dodajUzytkownika(@RequestBody Uzytkownik uzytkownik) {
		uzytkownikService.zapiszUzytkownika(uzytkownik);
		
	}

    
}
