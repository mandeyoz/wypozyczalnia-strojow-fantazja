package model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class DataWrapper {

	
	private int lastIndex;
	private int lastIndexWypozyczen=1;
	
	private List<Stroj> listaStrojow;
	private List<Rezerwacja> listaRezerwacji;
	private List<Wypozyczenie> listaWypozyczen ;
	private List<String> kategoria;
	private List<Wypozyczenie>historiaWypozyczen;

	


	
	public List<Wypozyczenie> getHistoriaWypozyczen() {
		return historiaWypozyczen;
	}

	public void setHistoriaWypozyczen(List<Wypozyczenie> historiaWypozyczen) {
		this.historiaWypozyczen = historiaWypozyczen;
	}

	

	public List<String> getKategoria() {
		return kategoria;
	}

	public void setKategoria(List<String> kategoria) {
		this.kategoria = kategoria;
	}

	public List<Stroj> getListaStrojow() {
		return listaStrojow;
	}

	public void setListaStrojow(List<Stroj> lista) {
		this.listaStrojow = lista;
	}

	public List<Rezerwacja> getListaRezerwacji() {
		return listaRezerwacji;
	}

	public void setListaRezerwacji(List<Rezerwacja> listaRezerwacji) {
		this.listaRezerwacji = listaRezerwacji;
	}

	public List<Wypozyczenie> getListaWypozyczen() {
		return listaWypozyczen;
	}

	public void setListaWypozyczen(List<Wypozyczenie> listaWypozyczen) {
		this.listaWypozyczen = listaWypozyczen;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}


	public void setLastIndexWypozyczen(int lastIndexWypozyczen) {
		this.lastIndexWypozyczen = lastIndexWypozyczen;
	}

	
	public int getLastIndexWypozyczen() {
		return lastIndexWypozyczen;
	}


	
}
