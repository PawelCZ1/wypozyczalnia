package com.pawelcz.wypozyczalnia.uzytkownik;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.pawelcz.wypozyczalnia.rezerwacja.Rezerwacja;
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
	private byte wiek;
	
	@OneToMany(mappedBy = "uzytkownik")
	private List<Rezerwacja> rezerwacje;
	@OneToMany(mappedBy = "uzytkownik")
	private List<Rezerwacja> archiwumRezerwacji;
	
	
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

	public byte getWiek() {
		return wiek;
	}
	
	

	public void noweSaldo(int okres, int cenaZaDzien) {
		this.saldo = saldo - okres * cenaZaDzien;
	}
	
	

	public List<Rezerwacja> listaRezerwacji() {
		return rezerwacje;
	}
	
	

	public List<Rezerwacja> listaArchiwumRezerwacji() {
		return archiwumRezerwacji;
	}
	
	@Override
	public String toString() {
		return "Uzytkownik [id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + ", narodowosc=" + narodowosc
				+ ", saldo=" + saldo + ", wiek=" + wiek + "]";
	}

	

	
	
	
	
	
	

}
