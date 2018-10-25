package model;




import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rezerwacja{

	private StringProperty dane;
	private StringProperty telefon;
	private StringProperty termin;
	private IntegerProperty indexZarezerwowanegoStroju;
	private StringProperty nazwaZarezerwowanegoStroju;
	private String imagePath;
	private StringProperty dataOdbioru;
	
	public Rezerwacja() {
		this(null,null,null,0,null,null,null);
		
	}
	
	
	
	public Rezerwacja(String dane, String telefon, String termin, int id,String nazwaStroju,String imagePath,String dataOdbioru) {

		this.dane = new SimpleStringProperty(dane);
		this.telefon = new SimpleStringProperty(telefon);
		this.termin = new SimpleStringProperty(termin);
		this.indexZarezerwowanegoStroju = new SimpleIntegerProperty(id);
		this.nazwaZarezerwowanegoStroju = new SimpleStringProperty(nazwaStroju);
		this.imagePath = imagePath;
		this.dataOdbioru = new SimpleStringProperty(dataOdbioru);
		
		
	}
	
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	public Integer getIndexZarezerwowanegoStroju(){
		return indexZarezerwowanegoStroju.get();
	}
	
	//
	public IntegerProperty indexZarezerwowanegoProperty() {
		return indexZarezerwowanegoStroju;
	}

	public void setIndexZarezerwowanegoStroju (Integer indexZarezerwowanegoStroju) {
		this.indexZarezerwowanegoStroju.set(indexZarezerwowanegoStroju);
	}
	
	//--------
	
	public String getNazwaZarezerwowanegoStroju() {
		return nazwaZarezerwowanegoStroju.get();
	}

	public void setNazwaZarezerwowanegoStroju(String nazwaZarezerwowanegoStroju) {
		this.nazwaZarezerwowanegoStroju.set(nazwaZarezerwowanegoStroju);
	}
	
	public StringProperty nazwaZarezerwowanegoStrojuProperty(){
		return nazwaZarezerwowanegoStroju;
	}
	


	public String getDane() {
		return dane.get();
	}

	

	public void setDane(String dane) {
		this.dane.set(dane);
	}

	public StringProperty daneProperty() {
		return dane;
	}
	

	public String getTelefon() {
		return telefon.get();
	}

	public void setTelefon(String telefon) {
		this.telefon.set(telefon);
	}

	public StringProperty telefonProperty() {
		return telefon;
	}
	

	
	public String getTermin() {
		return termin.get();
	}

	public void setTermin(String termin) {
		this.termin.set(termin);
	}

	public StringProperty terminProperty() {
		return termin;
	}
	
	//-----------------------------
	
	public String getDataOdbioru() {
		return dataOdbioru.get();
	}

	public void setDataOdbioru(String dataOdbioru) {
		this.dataOdbioru.set(dataOdbioru);
	}

	public StringProperty dataOdbioruProperty() {
		return dataOdbioru;
	}
	
	
}
