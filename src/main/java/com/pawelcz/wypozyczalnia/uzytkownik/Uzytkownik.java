package com.pawelcz.wypozyczalnia.uzytkownik;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.pawelcz.wypozyczalnia.rezerwacja.Rezerwacja;


import java.time.LocalDate;
import java.util.List;

@Entity
public class Uzytkownik {
	@Id
	@GeneratedValue
	private long id;
	private String imie;
	private String nazwisko;
	private String narodowosc;
	private int saldo;
	private LocalDate dataUrodzenia;
	
	@OneToMany(mappedBy = "uzytkownik", fetch = FetchType.LAZY) 
	private List<Rezerwacja> rezerwacje;
	
	
	public Uzytkownik() {
		
	}
	
	public long getId() {
		return id;
	}

	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public String getNarodowosc() {
		return narodowosc;
	}

	public int getSaldo() {
		return saldo;
	}


	public LocalDate getDataUrodzenia() {
		return this.dataUrodzenia;
	}

	public void setDataUrodzenia(LocalDate dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}
	
	
	

	public void saldoPoRezerwacji(int okres, int cenaZaDzien) {
		this.saldo = saldo - okres * cenaZaDzien;
	}
	
	public void saldoPrzedRezerwacja(int okres, int cenaZaDzien) {
		this.saldo = saldo + okres * cenaZaDzien;
	}
	
	
	
	

	public List<Rezerwacja> listaRezerwacji() {
		return rezerwacje;
	}
	
	
	
	
	

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", imie='" + getImie() + "'" +
			", nazwisko='" + getNazwisko() + "'" +
			", narodowosc='" + getNarodowosc() + "'" +
			", saldo='" + getSaldo() + "'" +
			", dataUrodzenia='" + getDataUrodzenia() + "'" +
			"}";
	}

	

	
	
	
	
	
	

}
