package com.pawelcz.wypozyczalnia.samochod;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SamochodController {

    private final SamochodService samochodService;

    public SamochodController(SamochodService samochodService) {
        this.samochodService = samochodService;
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

    
}
