package model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class DataWrapper {

	private String pathSwiateczne;
	private String pathBajeczne;
	private String pathZwierzeta;
	private String pathHalloween;
	private String pathFilmowe;
	private String pathSuper;
	private String pathDziki;
	private String pathMundurowe;
	private String pathHistoryczne;
	private String pathOwoce;
	private String pathInne;
	private int lastIndex;
	private String pathJaselka;
	private List<Stroj> listaStrojow;
	private List<Rezerwacja> listaRezerwacji;
	private List<Wypozyczenie> listaWypozyczen;

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

	public String getPathSwiateczne() {
		return pathSwiateczne;
	}

	public void setPathSwiateczne(String pathSwiateczne) {
		this.pathSwiateczne = pathSwiateczne;
	}

	public String getPathBajeczne() {
		return pathBajeczne;
	}

	public void setPathBajeczne(String pathBajeczne) {
		this.pathBajeczne = pathBajeczne;
	}

	public String getPathZwierzeta() {
		return pathZwierzeta;
	}

	public void setPathZwierzeta(String pathZwierzeta) {
		this.pathZwierzeta = pathZwierzeta;
	}

	public String getPathHalloween() {
		return pathHalloween;
	}

	public void setPathHalloween(String pathHalloween) {
		this.pathHalloween = pathHalloween;
	}

	public String getPathFilmowe() {
		return pathFilmowe;
	}

	public void setPathFilmowe(String pathFilmowe) {
		this.pathFilmowe = pathFilmowe;
	}

	public String getPathSuper() {
		return pathSuper;
	}

	public void setPathSuper(String pathSuper) {
		this.pathSuper = pathSuper;
	}

	public String getPathDziki() {
		return pathDziki;
	}

	public void setPathDziki(String pathDziki) {
		this.pathDziki = pathDziki;
	}

	public String getPathMundurowe() {
		return pathMundurowe;
	}

	public void setPathMundurowe(String pathMundurowe) {
		this.pathMundurowe = pathMundurowe;
	}

	public String getPathHistoryczne() {
		return pathHistoryczne;
	}

	public void setPathHistoryczne(String pathHistoryczne) {
		this.pathHistoryczne = pathHistoryczne;
	}

	public String getPathOwoce() {
		return pathOwoce;
	}

	public void setPathOwoce(String pathOwoce) {
		this.pathOwoce = pathOwoce;
	}

	public String getPathInne() {
		return pathInne;
	}

	public void setPathInne(String pathInne) {
		this.pathInne = pathInne;
	}

	public String getPathJaselka() {
		return pathJaselka;
	}

	public void setPathJaselka(String pathJaselka) {
		this.pathJaselka = pathJaselka;
	}

	
}
