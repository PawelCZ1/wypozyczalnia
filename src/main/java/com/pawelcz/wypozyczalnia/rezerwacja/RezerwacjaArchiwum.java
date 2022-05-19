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
public class RezerwacjaArchiwum {
	
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
	
	
	
	public RezerwacjaArchiwum() {
		
	}
	
	public RezerwacjaArchiwum(Uzytkownik uzytkownik, Samochod samochod, int pozostaleDni) {
		this.uzytkownik = uzytkownik;
		this.samochod = samochod;
		this.pozostaleDni = pozostaleDni;
		
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

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public void setSamochod(Samochod samochod) {
		this.samochod = samochod;
	}

	public void setPozostaleDni(int pozostaleDni) {
		this.pozostaleDni = pozostaleDni;
	}
	
	

	

	@Override
	public String toString() {
		return "Rezerwacja [uzytkownik=" + uzytkownik.getId() + ", samochod=" + samochod.getId() + ", pozostaleDni="
				+ pozostaleDni + "]";
	}
	
	
	
	
	
	
	
}
