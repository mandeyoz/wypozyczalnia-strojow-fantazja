package model;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controller.ZarzadzanieStrojami.MainController;
import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;

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
	private IntegerProperty indexWypozyczen;
	private int indexNumer;
	private String dataUtw;
	private StringProperty typOperacji ;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy            HH:mm:ss");
	
	public Wypozyczenie() {
		this(null,null,null,0, null,null,null,null);
		
	}

	public Wypozyczenie(String dane, String telefon, String uwagi, int idWypozyczonegoStroju,String nazwaStroju,String imagePath,String dataZwrotu,String typ) {

		this.daneKlienta = new SimpleStringProperty(dane);
		this.telefon = new SimpleStringProperty(telefon);
		this.uwagi = new SimpleStringProperty(uwagi);
		LocalDateTime date = LocalDateTime.now();
		this.dataUtworzenia = new SimpleStringProperty(date.format(formatter));
		this.dataZwrotu = new SimpleStringProperty(dataZwrotu);
		this.indexWypozyczonegoStroju =  new SimpleIntegerProperty(idWypozyczonegoStroju);
		this.nazwaWypozyczonegoStroju = new SimpleStringProperty(nazwaStroju);
		this.imagePath = imagePath;
		this.indexNumer = MainController.getIndexWypozyczen();	
		this.indexWypozyczen = new SimpleIntegerProperty(indexNumer);
		this.dataUtw=dataUtworzenia.toString();
		this.typOperacji= new SimpleStringProperty(typ);
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

	public Integer getIndexWypozyczen(){
		return indexWypozyczen.get();
	}
	
	//
	public IntegerProperty indexWypozyczenProperty() {
		return indexWypozyczen;
	}

	public void setIndexWypozyczen (Integer indexWypozyczen) {
		this.indexWypozyczen.set(indexWypozyczen);
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

	public void setDataUtworzenia(String data){
		this.dataUtw=data;
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

	public String getTypOperacji() {
		return typOperacji.get();
	}

	public void setTypOperacji(String typOperacji) {
		this.typOperacji.set(typOperacji); 
	}

	public StringProperty typOperacjiProperty(){
		return typOperacji;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((daneKlienta == null) ? 0 : daneKlienta.hashCode());
		result = prime * result + ((dataUtw == null) ? 0 : dataUtw.hashCode());
		result = prime * result + ((dataUtworzenia == null) ? 0 : dataUtworzenia.hashCode());
		result = prime * result + ((dataZwrotu == null) ? 0 : dataZwrotu.hashCode());
		result = prime * result + ((formatter == null) ? 0 : formatter.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + indexNumer;
		result = prime * result + ((indexWypozyczen == null) ? 0 : indexWypozyczen.hashCode());
		result = prime * result + ((indexWypozyczonegoStroju == null) ? 0 : indexWypozyczonegoStroju.hashCode());
		result = prime * result + ((nazwaWypozyczonegoStroju == null) ? 0 : nazwaWypozyczonegoStroju.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		result = prime * result + ((typOperacji == null) ? 0 : typOperacji.hashCode());
		result = prime * result + ((uwagi == null) ? 0 : uwagi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wypozyczenie other = (Wypozyczenie) obj;
		if (daneKlienta == null) {
			if (other.daneKlienta != null)
				return false;
		} else if (!daneKlienta.equals(other.daneKlienta))
			return false;
		if (dataUtw == null) {
			if (other.dataUtw != null)
				return false;
		} else if (!dataUtw.equals(other.dataUtw))
			return false;
		if (dataUtworzenia == null) {
			if (other.dataUtworzenia != null)
				return false;
		} else if (!dataUtworzenia.equals(other.dataUtworzenia))
			return false;
		if (dataZwrotu == null) {
			if (other.dataZwrotu != null)
				return false;
		} else if (!dataZwrotu.equals(other.dataZwrotu))
			return false;
		if (formatter == null) {
			if (other.formatter != null)
				return false;
		} else if (!formatter.equals(other.formatter))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (indexNumer != other.indexNumer)
			return false;
		if (indexWypozyczen == null) {
			if (other.indexWypozyczen != null)
				return false;
		} else if (!indexWypozyczen.equals(other.indexWypozyczen))
			return false;
		if (indexWypozyczonegoStroju == null) {
			if (other.indexWypozyczonegoStroju != null)
				return false;
		} else if (!indexWypozyczonegoStroju.equals(other.indexWypozyczonegoStroju))
			return false;
		if (nazwaWypozyczonegoStroju == null) {
			if (other.nazwaWypozyczonegoStroju != null)
				return false;
		} else if (!nazwaWypozyczonegoStroju.equals(other.nazwaWypozyczonegoStroju))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		if (typOperacji == null) {
			if (other.typOperacji != null)
				return false;
		} else if (!typOperacji.equals(other.typOperacji))
			return false;
		if (uwagi == null) {
			if (other.uwagi != null)
				return false;
		} else if (!uwagi.equals(other.uwagi))
			return false;
		return true;
	}

	

	

	
	

    

	
	
}
