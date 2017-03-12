package controller.ZarzadzanieStrojami;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import model.Stroj;





public class DodajNowyStrojController {
	

	
	@FXML
	private AnchorPane acPane;
	
	@FXML
	private TextField nazwaStrojuField;
	@FXML
	private ComboBox<String> cbRegal;
	@FXML
	private ComboBox<String> cbKategoria;
	@FXML
	private TextField wymiaryField;

	@FXML
	private TextField akcesoriaField;
	@FXML
	private TextField idField;
	
	@FXML
	private Button btnOK;
	@FXML
	private Button btnAnuluj;
	
	

	private Stage dialogStage;
	private Stroj stroj;
	private boolean okClicked = false;
	private File plik;
	private BufferedImage bufferedImage;
    private Image image;
    
    private String imagePath;
	
	private ObservableList<String> Rega³ = FXCollections.observableArrayList("A","B","C","D","E","F","G","H","I","J");
	private ObservableList<String> Kategoria = FXCollections.observableArrayList
			("JASE£KA","ŒWI¥TECZNE","POSTACIE Z BAJEK","ZWIERZÊTA","HALLOWEEN","POSTACIE Z FILMÓW","SUPERBOHATER","DZIKI ZACHÓD","MUNDUROWE",
					"HISTORYCZNE","OWOCE I WARZYWA","INNE");
	
	
	@FXML
	private void initialize() {
		cbKategoria.setItems(Kategoria);
		cbRegal.setItems(Rega³);
		cbKategoria.getEditor().textProperty().addListener((obs, oldText, newText) -> {
			cbKategoria.setValue(newText);
		
	    });
		cbRegal.getEditor().textProperty().addListener((obs, oldText, newText) -> {
			cbRegal.setValue(newText);
		
	    });
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	
	
	


	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOkButton() {
		if (isValid()) {

			stroj.setRegal(cbRegal.getEditor().getText().toString());
			stroj.setKategoria(cbKategoria.getEditor().getText().toString());
			stroj.setNazwaStroju(nazwaStrojuField.getText());
			stroj.setWymiary(wymiaryField.getText());
			stroj.setAkcesoria(akcesoriaField.getText());
			stroj.setImagePath(imagePath);
			okClicked = true;
			dialogStage.close();
			
		}
	}
	
	@FXML
	private void cancelButton() {
		dialogStage.close();
	}
	
	
	 @FXML
	   private void zalaczZdjecie(ActionEvent event) throws IOException {
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("JPG (Joint Photographic Group)", "*.jpg"),
	                new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg"),
	                new FileChooser.ExtensionFilter("PNG (Portable Network Graphics)", "*.png")
	        );

	        fileChooser.setTitle("Wybierz Zdjêcie stroju");

	        plik = fileChooser.showOpenDialog(null);

	        if (plik != null) {
	            System.out.println(plik);
	            bufferedImage = ImageIO.read(plik);
	            image = SwingFXUtils.toFXImage(bufferedImage, null);
	            imagePath = plik.toURI().toString();
	            
	            
	        }
	    }
	
	 public void setStroj(Stroj stroj) {
			this.stroj = stroj;

			cbRegal.getEditor().setText(stroj.getRegal());
			nazwaStrojuField.setText(stroj.getNazwaStroju());
			cbKategoria.getEditor().setText(stroj.getKategoria());
			wymiaryField.setText(stroj.getWymiary());
			akcesoriaField.setText(stroj.getAkcesoria());
			imagePath = stroj.getImagePath();
			
		}
	
		private boolean isValid() {
			String msg = "";
			
			if (cbRegal.getSelectionModel().isEmpty()) {
				msg += "Brakuje Rega³u na którym bêdzie strój stroju!\n\n";
			}
			if (nazwaStrojuField.getText() == null || nazwaStrojuField.getText().length() == 0) {
				msg += "Brakuje nazwy stroju!\n\n";
			}
			if (wymiaryField.getText() == null || wymiaryField.getText().length() == 0) {
				msg += "Brakuje rozmiaru stroju!\n\n";
			}
			
			if (akcesoriaField.getText() == null || akcesoriaField.getText().length() == 0) {
				msg += "Wprowadz akcesoria (Jeœli nie ma wpisz 'brak')\n\n";
			
			}

			if  (imagePath == null) {
				msg += "Za³¹cz plik ze zdjêciem\n\n";
			}

			if (cbKategoria.getSelectionModel().isEmpty()) {
				msg += "Wybierz kategoriê dla stroju\n\n";
			} 
			
			if (msg.length() == 0) {
				return true;
			} else {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("Niepoprawne pola");
				alert.setHeaderText("Uzupe³nij brakuj¹ce pola");
				alert.setContentText(msg);

				alert.showAndWait();

				return false;
			}
		}
	
}
