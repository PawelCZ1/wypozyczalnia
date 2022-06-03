package com.pawelcz.wypozyczalnia.samochod;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;




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
	@OneToMany(mappedBy = "samochod", fetch = FetchType.LAZY) 
	private List<Rezerwacja> rezerwacje;
	
   
	
	
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
	

	public boolean Dostepnosc() {
		return rezerwacje.stream().filter
		(element -> element.aktualnosc())
		.toList().size() == 0;
	}

	public List<Rezerwacja> listaRezerwacji() {
		return rezerwacje;
	}
	
	
	

	@Override
	public String toString() {
		return "Samochod [id=" + id + ", model=" + model + ", marka=" + marka + ", kolor=" + kolor + ", paliwo="
				+ paliwo + ", typ=" + typ + ", skrzyniaBiegow=" + skrzyniaBiegow + ", klimatyzacja=" + klimatyzacja
				+ ", rocznik=" + rocznik + ", liczbaMiejsc=" + liczbaMiejsc + ", spalanie=" + spalanie
				+ ", cenaZaDzien=" + cenaZaDzien + "]";
	}
	
	

	

}
