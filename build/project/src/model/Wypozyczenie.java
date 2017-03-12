package model;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Wypozyczenie {
	
	private StringProperty daneKlienta;
	private StringProperty telefon;
	private StringProperty uwagi;
	private StringProperty dataUtworzenia;
	private IntegerProperty indexWypozyczonegoStroju;
	private StringProperty nazwaWypozyczonegoStroju;
	private String imagePath;
	private StringProperty dataZwrotu;

	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy            HH:mm:ss");
	
	public Wypozyczenie() {
		this(null,null,null,0, null,null,null);
	}

	public Wypozyczenie(String dane, String telefon, String uwagi, int idWypozyczonegoStroju,String nazwaStroju,String imagePath,String dataZwrotu) {

		this.daneKlienta = new SimpleStringProperty(dane);
		this.telefon = new SimpleStringProperty(telefon);
		this.uwagi = new SimpleStringProperty(uwagi);
		LocalDateTime date = LocalDateTime.now();
		this.dataUtworzenia = new SimpleStringProperty(date.format(formatter));
		this.dataZwrotu = new SimpleStringProperty(dataZwrotu);
		this.indexWypozyczonegoStroju =  new SimpleIntegerProperty(idWypozyczonegoStroju);
		this.nazwaWypozyczonegoStroju = new SimpleStringProperty(nazwaStroju);
		this.imagePath = imagePath;
				
	}
	
	public Integer getIndexWypozyczonegoStroju(){
		return indexWypozyczonegoStroju.get();
	}
	
	//
	public IntegerProperty indexWypozyczonegoStrojuProperty() {
		return indexWypozyczonegoStroju;
	}

	public void setIndexWypozyczonegoStroju (Integer indexWypozyczonegoStroju) {
		this.indexWypozyczonegoStroju.set(indexWypozyczonegoStroju);
	}

	
	
	public String getNazwaWypozyczonegoStroju() {
		return nazwaWypozyczonegoStroju.get();
	}

	public void setNazwaWypozyczonegoStroju(String nazwaWypozyczonegoStroju) {
		this.nazwaWypozyczonegoStroju.set(nazwaWypozyczonegoStroju);
	}
	
	public StringProperty nazwaWypozyczonegoStrojuProperty(){
		return nazwaWypozyczonegoStroju;
	}
	
	
	
	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDataUtworzenia() {
	       return dataUtworzenia.get();
	   }

	  
	   public StringProperty dataUtworzeniaProperty() {
	       return dataUtworzenia;
	   }
	  
	   
	   public String getDataZwrotu() {
	       return dataZwrotu.get();
	   }
	   
	   public void setDataZwrotu(String dataZwrotu) {
			this.dataZwrotu.set(dataZwrotu);
		}
	   

	  
	   public StringProperty dataZwrotuProperty() {
	       return dataZwrotu;
	   }
	

	public String getDaneKlienta() {
		return daneKlienta.get();
	}

	

	public void setDaneKlienta(String dane) {
		this.daneKlienta.set(dane);
	}

	public StringProperty daneKlientaProperty() {
		return daneKlienta;
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

	public String getUwagi() {
		return uwagi.get();
	}

	public void setUwagi(String uwagi) {
		this.uwagi.set(uwagi);
	}

	public StringProperty uwagiProperty() {
		return uwagi;
	}



	
	
}
