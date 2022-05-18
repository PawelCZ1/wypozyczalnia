package com.pawelcz.wypozyczalnia.samochod;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.pawelcz.wypozyczalnia.rezerwacja.Rezerwacja;

@Entity
public class Samochod {
	@Id
    @GeneratedValue
	private long id;
	private String model;
	private String marka;
	private String kolor;
	@Enumerated(EnumType.STRING)
	private Paliwo paliwo;
	@Enumerated(EnumType.STRING)
	private Typ typ;
	@Enumerated(EnumType.STRING)
	private SkrzyniaBiegow skrzyniaBiegow;
	@Enumerated(EnumType.STRING)
	private Klimatyzacja klimatyzacja;
	private short rocznik;
	private byte liczbaMiejsc;
	private float spalanie;
	private int cenaZaDzien;
	@OneToOne(mappedBy = "samochod")
    @JoinColumn( name = "rezerwacja" )
    @NotFound( action = NotFoundAction.IGNORE )
	private Rezerwacja rezerwacja;
	
	public Samochod() {
		
	}
	
	public long getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public String getMarka() {
		return marka;
	}

	public String getKolor() {
		return kolor;
	}

	public Paliwo getPaliwo() {
		return paliwo;
	}

	public Typ getTyp() {
		return typ;
	}

	public SkrzyniaBiegow getSkrzyniaBiegow() {
		return skrzyniaBiegow;
	}

	public Klimatyzacja getKlimatyzacja() {
		return klimatyzacja;
	}

	public short getRocznik() {
		return rocznik;
	}

	public byte getLiczbaMiejsc() {
		return liczbaMiejsc;
	}

	public float getSpalanie() {
		return spalanie;
	}

	public int getCenaZaDzien() {
		return cenaZaDzien;
	}
	
	
	public Rezerwacja rezerwacjaSamochodu() {
		return rezerwacja;
	}
	

	@Override
	public String toString() {
		return "Samochod [id=" + id + ", model=" + model + ", marka=" + marka + ", kolor=" + kolor + ", paliwo="
				+ paliwo + ", typ=" + typ + ", skrzyniaBiegow=" + skrzyniaBiegow + ", klimatyzacja=" + klimatyzacja
				+ ", rocznik=" + rocznik + ", liczbaMiejsc=" + liczbaMiejsc + ", spalanie=" + spalanie
				+ ", cenaZaDzien=" + cenaZaDzien + "]";
	}
	
	

	

}
