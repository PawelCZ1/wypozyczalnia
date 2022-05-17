package com.pawelcz.wypozyczalnia.rezerwacja;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.pawelcz.wypozyczalnia.samochod.Samochod;
import com.pawelcz.wypozyczalnia.uzytkownik.Uzytkownik;

@Entity
public class Rezerwacja {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "idUzytkownika", nullable=false)
	private Uzytkownik uzytkownik;
	@OneToOne
	@JoinColumn(name = "idSamochodu", nullable=false)
	private Samochod samochod;
	private int pozostaleDni;
	
	
	public Rezerwacja() {
		
	}
	
	public long getId() {
		return id;
	}
	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}
	public Samochod getSamochod() {
		return samochod;
	}
	public int getPozostaleDni() {
		return pozostaleDni;
	}
	
	
	
	
	
}
