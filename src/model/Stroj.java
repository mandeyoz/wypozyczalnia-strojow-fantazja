package model;





import controller.ZarzadzanieStrojami.MainController;
import javafx.beans.property.*;


public class Stroj {

	private IntegerProperty id;
	private int idNaZapis;
	private StringProperty regal;
	private StringProperty nazwaStroju;
	private StringProperty kategoria;
	private StringProperty wymiary;
	private StringProperty akcesoria;
	private String imagePath;
	

	public Stroj() {
		this(null, null, null, null, null, null);
		
	}

	public Stroj(String regal , String nazwaStroju, String wymiary, String akcesoria, String kategoria,
			String imagePath) {
		
		this.idNaZapis=MainController.getIndexNowegoStroju();
		this.id = new SimpleIntegerProperty(idNaZapis);
		this.regal = new SimpleStringProperty(regal);
		this.nazwaStroju = new SimpleStringProperty(nazwaStroju);
		this.kategoria = new SimpleStringProperty(kategoria);
		this.wymiary = new SimpleStringProperty(wymiary);
		this.akcesoria = new SimpleStringProperty(akcesoria);
		this.imagePath = imagePath;
	
	}

	

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id.set(id);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public int getIdNaZapis() {
		return idNaZapis;
	}


	public void setIdNaZapis(int idNaZapis) {
		this.idNaZapis = idNaZapis;
	}

	public String getRegal(){
		return regal.get();
	}
	
	

	public void setRegal (String regal){
		this.regal.set(regal);
	}
	
	public StringProperty regalProperty(){
		return regal;
	}
	

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getNazwaStroju() {
		return nazwaStroju.get();
	}

	public void setNazwaStroju(String nazwaStroju) {
		this.nazwaStroju.set(nazwaStroju);
	}

	public StringProperty nazwaStrojuProperty() {
		return nazwaStroju;
	}

	public String getKategoria() {
		return kategoria.get();
	}

	public void setKategoria(String kategoria) {
		this.kategoria.set(kategoria);
	}

	public StringProperty kategoriaProperty() {
		return kategoria;
	}

	public String getWymiary() {
		return wymiary.get();
	}

	public void setWymiary(String wymiary) {
		this.wymiary.set(wymiary);
	}

	public StringProperty wymiaryProperty() {
		return kategoria;
	}

	public String getAkcesoria() {
		return akcesoria.get();
	}

	public void setAkcesoria(String akcesoria) {
		this.akcesoria.set(akcesoria);
	}

	public StringProperty akcesoriaProperty() {
		return akcesoria;
	}

	
	
	
	
}
