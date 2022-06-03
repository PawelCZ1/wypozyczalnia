package com.pawelcz.wypozyczalnia.rezerwacja;

import java.time.LocalDate;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


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
	@ManyToOne
	@JoinColumn(name = "idSamochodu", nullable=false)
	private Samochod samochod;
	private LocalDate dataRezerwacji;
	private LocalDate dataZakonczenia;
	private boolean aktualne;
		
	public Rezerwacja() {
		
	}
	
	public Rezerwacja(Uzytkownik uzytkownik, Samochod samochod, LocalDate dataZakonczenia) {
		this.uzytkownik = uzytkownik;
		this.samochod = samochod;
		this.dataRezerwacji = LocalDate.now();
		this.dataZakonczenia = dataZakonczenia;
		this.aktualne = true;
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
	
	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDataRezerwacji() {
		return this.dataRezerwacji;
	}

	public void setDataRezerwacji(LocalDate dataRezerwacji) {
		this.dataRezerwacji = dataRezerwacji;
	}

	public LocalDate getDataZakonczenia() {
		return this.dataZakonczenia;
	}

	public void setDataZakonczenia(LocalDate dataZakonczenia) {
		this.dataZakonczenia = dataZakonczenia;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public void setSamochod(Samochod samochod) {
		this.samochod = samochod;
	}

	public void setAktualne(boolean aktualne) {
		this.aktualne = aktualne;
	}

	public boolean isAktualne() {
		return this.aktualne;
	}

	public boolean getAktualne() {
		return this.aktualne;
	}

	public boolean aktualnosc() {
		return dataZakonczenia.compareTo(LocalDate.now()) >= 0 && isAktualne() == true;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", uzytkownik='" + getUzytkownik().getId() + "'" +
			", samochod='" + getSamochod().getId() + "'" +
			", dataRezerwacji='" + getDataRezerwacji() + "'" +
			", dataZakonczenia='" + getDataZakonczenia() + "'" +
			"}";
	}
	
	
	
	
	
	
	
	
}
