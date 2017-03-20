package controller.ZarzadzanieStrojami;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.Main;
import model.DataWrapper;
import model.Gallery;
import model.Rezerwacja;
import model.Stroj;
import model.Wypozyczenie;

public class MainController {

	@FXML
	private AnchorPane oknoZarzadzanieStrojami;

	@FXML
	private Button btnDodajStroj;

	@FXML
	private Button btnEdytujStroj;

	@FXML
	private Button btnUsunStroj;
	@FXML
	private Button btnNoweWypozyczenie;
	@FXML
	private Button btnUsunWypozyczenie;
	@FXML
	private Button btnDodajRezerwacje;
	@FXML
	private Button btnUsunRezerwacje;
	@FXML
	private Button btnZapisz;
	@FXML
	private Button zapiszZmiany;
	@FXML
	private Button btnGaleria;

	@FXML
	public TableView<Stroj> strojeTabela;
	@FXML
	public TableView<Stroj> strojeTabela1;
	@FXML
	public TableView<Wypozyczenie> strojeTabela2;
	@FXML
	public TableView<Rezerwacja> strojeTabela3;
	@FXML
	public TableColumn<Rezerwacja, Integer> idColumn3;
	@FXML
	public TableColumn<Rezerwacja, String> nazwaStrojuCol3;
	@FXML
	public TableColumn<Rezerwacja, String> terminColumn;
	@FXML
	public TableColumn<Rezerwacja, String> dataOdbioruCol;
	@FXML
	public TableColumn<Stroj, String> nazwaStrojuCol;
	@FXML
	public TableColumn<Stroj, Integer> idColumn;
	@FXML
	public TableColumn<Stroj, String> kategoriaColumn;
	@FXML
	public TableColumn<Stroj, String> nazwaStrojuCol1;
	@FXML
	public TableColumn<Stroj, Integer> idColumn1;
	@FXML
	public TableColumn<Stroj, String> kategoriaCol;
	@FXML
	public TableColumn<Stroj, String> regalColumn;
	@FXML
	public TableColumn<Stroj, String> regalColumn1;
	@FXML
	public TableColumn<Wypozyczenie, String> nazwaStrojuCol2;
	@FXML
	public TableColumn<Wypozyczenie, Integer> idColumn2;
	@FXML
	public TableColumn<Wypozyczenie, String> dataWypozyczeniaCol;
	@FXML
	public TableColumn<Wypozyczenie, String> dataZwrotuCol;

	@FXML
	public DatePicker datePicker;
	@FXML
	public DatePicker datePickerZwrot;

	@FXML
	private Label akcesoriaLabel;
	@FXML
	private Label kategoriaLabel;
	@FXML
	private Label wymiaryLabel;
	@FXML
	private Label cenaLabel;
	@FXML
	private Label daneKlientaLabel;
	@FXML
	private Label telefonLabel;
	@FXML
	private Label uwagiLabel;
	@FXML
	private Label daneRezerwacjaLabel;
	@FXML
	private Label telefonRezerwacjaLabel;
	@FXML
	private ImageView imgView;
	@FXML
	private ImageView imgView1;
	@FXML
	private ImageView imgView2;
	@FXML
	private ImageView imgView3;
	@FXML
	private TextField daneKlientaField;
	@FXML
	private TextField daneRezerwacja;
	@FXML
	private TextField telefonField;
	@FXML
	private TextField telefonRezerwacja;
	@FXML
	private TextField uwagiField;
	@FXML
	private TextField dataZwrotuField;

	@FXML
	private TextField szukajField;
	@FXML
	private TextField szukajField1;
	@FXML
	private TextField szukajField2;
	@FXML
	private TextField szukajField3;
	@FXML
	private TextField odbiorStroju;
	@FXML
	private BorderPane bdPane;

	private static String pathJaselka;
	private static String pathSwiateczne;
	private static String pathBajeczne;
	private static String pathZwierzeta;
	private static String pathHalloween;
	private static String pathFilmowe;
	private static String pathSuper;
	private static String pathDziki;
	private static String pathMundurowe;
	private static String pathHistoryczne;
	private static String pathOwoce;
	private static String pathInne ;
	
	private Stage galleryStage = new Stage();
	private Image image;
	private Stroj stroj;
	private DateTimeFormatter formatter;
	private static int indexNowegoStroju = 1;
	private Main main;

	public ObservableList<Stroj> listaStrojow = FXCollections.observableArrayList();
	public ObservableList<Wypozyczenie> listaWypozyczen = FXCollections.observableArrayList();
	public ObservableList<Rezerwacja> listaRezerwacji = FXCollections.observableArrayList();

	public MainController() {

		File file = getWypozyczalniaPath();
		if (file != null) {

			wczytajStroje(file);
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() {

		strojeTabela.setItems(listaStrojow);
		strojeTabela1.setItems(listaStrojow);
		strojeTabela2.setItems(listaWypozyczen);
		strojeTabela3.setItems(listaRezerwacji);

		nazwaStrojuCol.setCellValueFactory(cellData -> cellData.getValue().nazwaStrojuProperty());
		nazwaStrojuCol1.setCellValueFactory(cellData -> cellData.getValue().nazwaStrojuProperty());
		nazwaStrojuCol2.setCellValueFactory(cellData -> cellData.getValue().nazwaWypozyczonegoStrojuProperty());
		nazwaStrojuCol3.setCellValueFactory(cellData -> cellData.getValue().nazwaZarezerwowanegoStrojuProperty());
		kategoriaColumn.setCellValueFactory(cellData -> cellData.getValue().kategoriaProperty());
		kategoriaCol.setCellValueFactory(cellData -> cellData.getValue().kategoriaProperty());
		regalColumn.setCellValueFactory(cellData -> cellData.getValue().regalProperty());
		regalColumn1.setCellValueFactory(cellData -> cellData.getValue().regalProperty());
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		idColumn1.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		idColumn2.setCellValueFactory(cellData -> cellData.getValue().indexWypozyczonegoStrojuProperty().asObject());
		idColumn3.setCellValueFactory(cellData -> cellData.getValue().indexZarezerwowanegoProperty().asObject());

		terminColumn.setCellValueFactory(cellData -> cellData.getValue().terminProperty());

		dataWypozyczeniaCol.setCellValueFactory(cellData -> cellData.getValue().dataUtworzeniaProperty());
		dataZwrotuCol.setCellValueFactory(cellData -> cellData.getValue().dataZwrotuProperty());
		dataOdbioruCol.setCellValueFactory(cellData -> cellData.getValue().dataOdbioruProperty());

		strojeTabela.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> pokazInfoZarzadzanieStrojami(newValue));
		strojeTabela1.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> pokazInfoNoweWypozyczenie(newValue));
		strojeTabela2.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> pokazInfoWypozyczenie(newValue));
		strojeTabela3.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> pokazInfoRezerwacja(newValue));

		szukajField.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				szukajStroju((String) oldValue, (String) newValue);
			}
		});

		szukajField1.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				szukajStroju2((String) oldValue, (String) newValue);
			}
		});
		szukajField2.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				szukajWypozyczenia((String) oldValue, (String) newValue);
			}
		});
		szukajField3.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				szukajRezerwacji((String) oldValue, (String) newValue);
			}
		});

	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void dodajStroj() {

		Stroj nowyStroj = new Stroj();
		boolean okClicked = dodajNowyStroj(nowyStroj);
		if (okClicked) {
			listaStrojow.add(nowyStroj);
			indexNowegoStroju++;
		}

	}

	public boolean dodajNowyStroj(Stroj stroj) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/DodajNowyStroj.fxml"));
			loader.load();
			Parent parent = loader.getRoot();

			Scene scene = new Scene(parent);

			Stage dialogStage = new Stage();
			dialogStage.setScene(scene);

			DodajNowyStrojController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStroj(stroj);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@FXML
	private void dodajWypozyczenie() {

		if (isValidWypozyczenie()) {

			Wypozyczenie noweWypozyczenie = new Wypozyczenie();
			noweWypozyczenie.setDaneKlienta(daneKlientaField.getText());
			noweWypozyczenie.setTelefon(telefonField.getText());
			noweWypozyczenie.setUwagi(uwagiField.getText());
			noweWypozyczenie
					.setNazwaWypozyczonegoStroju(strojeTabela1.getSelectionModel().getSelectedItem().getNazwaStroju());
			noweWypozyczenie.setImagePath(strojeTabela1.getSelectionModel().getSelectedItem().getImagePath());
			noweWypozyczenie
					.setIndexWypozyczonegoStroju(strojeTabela1.getSelectionModel().getSelectedItem().getIdNaZapis());
			noweWypozyczenie.setDataZwrotu(
					datePickerZwrot.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString());

			listaWypozyczen.add(noweWypozyczenie);
			datePicker.getEditor().clear();
			datePickerZwrot.getEditor().clear();

			daneKlientaField.clear();
			daneRezerwacja.clear();
			telefonField.clear();
			telefonRezerwacja.clear();
			uwagiField.clear();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("NOWE WYPO¯YCZENIE");
			alert.setContentText("Dodano pomyœlnie nowe wypo¿yczenie do bazy ");
			alert.showAndWait();
			// } else {
			// Alert alert = new Alert(AlertType.WARNING);
			// alert.setHeaderText("NOWE WYPO¯YCZENIE");
			// alert.setContentText("Ten strój zosta³ ju¿ wypo¿yczony - wybierz
			// inny ");
			// alert.showAndWait();
			// }

		}
	}

	@FXML
	private void dodajRezerwacje() {
		if (isValidRezerwacja()) {

			Rezerwacja nowaRezerwacja = new Rezerwacja();
			nowaRezerwacja.setDane(daneRezerwacja.getText());
			nowaRezerwacja.setTelefon(telefonRezerwacja.getText());
			nowaRezerwacja
					.setTermin(datePicker.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString());
			nowaRezerwacja.setNazwaZarezerwowanegoStroju(
					strojeTabela1.getSelectionModel().getSelectedItem().getNazwaStroju());
			nowaRezerwacja.setImagePath(strojeTabela1.getSelectionModel().getSelectedItem().getImagePath());
			nowaRezerwacja
					.setIndexZarezerwowanegoStroju(strojeTabela1.getSelectionModel().getSelectedItem().getIdNaZapis());
			nowaRezerwacja.setDataOdbioru(odbiorStroju.getText());

			listaRezerwacji.add(nowaRezerwacja);
			datePicker.getEditor().clear();
			datePickerZwrot.getEditor().clear();
			odbiorStroju.clear();
			daneKlientaField.clear();
			daneRezerwacja.clear();
			telefonField.clear();
			telefonRezerwacja.clear();
			uwagiField.clear();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("NOWA REZERWACJA");
			alert.setContentText("Dodano pomyœlnie now¹ rezerwacjê do bazy ");
			alert.showAndWait();
		}
	}

	private void pokazInfoZarzadzanieStrojami(Stroj stroj) {
		if (stroj != null) {
			akcesoriaLabel.setText(stroj.getAkcesoria());
			wymiaryLabel.setText(stroj.getWymiary());

			// imgView.fitWidthProperty().bind(oknoZarzadzanieStrojami.widthProperty());
			// imgView.fitHeightProperty().bind(oknoZarzadzanieStrojami.heightProperty());
			imgView.setImage(new Image(stroj.getImagePath()));

		} else {

			akcesoriaLabel.setText("");
			wymiaryLabel.setText("");

			imgView.setImage(null);
		}
	}

	private void pokazInfoNoweWypozyczenie(Stroj stroj) {
		if (stroj != null) {

			imgView1.setImage(new Image(stroj.getImagePath()));
		} else {
			imgView1.setImage(null);
		}
	}

	private void pokazInfoWypozyczenie(Wypozyczenie wypozyczenie) {
		if (wypozyczenie != null) {
			daneKlientaLabel.setText(wypozyczenie.getDaneKlienta());
			telefonLabel.setText(wypozyczenie.getTelefon());
			uwagiLabel.setText(wypozyczenie.getUwagi());
			// dataWypozyczeniaLabel.setText(wypozyczenie.getDataUtworzenia().toString());

			// imgView.fitWidthProperty().bind(oknoZarzadzanieStrojami.widthProperty());
			// imgView.fitHeightProperty().bind(oknoZarzadzanieStrojami.heightProperty());
			imgView2.setImage(new Image(wypozyczenie.getImagePath()));

		} else {
			daneKlientaLabel.setText("");
			telefonLabel.setText("");
			uwagiLabel.setText("");
			// dataWypozyczeniaLabel.setText("");
			imgView2.setImage(null);
		}
	}

	private void pokazInfoRezerwacja(Rezerwacja rezerwacja) {
		if (rezerwacja != null) {
			daneRezerwacjaLabel.setText(rezerwacja.getDane());
			telefonRezerwacjaLabel.setText(rezerwacja.getTelefon());
			// imgView.fitWidthProperty().bind(oknoZarzadzanieStrojami.widthProperty());
			// imgView.fitHeightProperty().bind(oknoZarzadzanieStrojami.heightProperty());
			imgView3.setImage(new Image(rezerwacja.getImagePath()));

		} else {
			daneRezerwacjaLabel.setText("");
			telefonRezerwacjaLabel.setText("");

			imgView3.setImage(null);
		}
	}

	@FXML
	private void usunWypozyczenie() {
		int selectedIndex = strojeTabela2.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {

			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Usuwanie wypo¿yczenia");
			alert1.setHeaderText("Czy chcesz by strój zosta³ oddany ?");
			alert1.setContentText(strojeTabela2.getSelectionModel().getSelectedItem().getNazwaWypozyczonegoStroju());

			Optional<ButtonType> result = alert1.showAndWait();
			if (result.get() == ButtonType.OK) {

				strojeTabela2.getItems().remove(selectedIndex);

			} else {
				alert1.close();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);

			alert.setHeaderText("Nie wybra³eœ wypo¿yczenia");
			alert.setContentText("Wybierz który strój chcesz oddaæ");
			alert.showAndWait();

		}
	}

	@FXML
	private void usunRezerwacje() {
		int selectedIndex = strojeTabela3.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {

			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Usuwanie rezerwacji");
			alert1.setHeaderText("Czy na pewno chcesz usun¹æ t¹ rezerwacjê ?");
			alert1.setContentText(strojeTabela3.getSelectionModel().getSelectedItem().getNazwaZarezerwowanegoStroju());

			Optional<ButtonType> result = alert1.showAndWait();
			if (result.get() == ButtonType.OK) {

				strojeTabela3.getItems().remove(selectedIndex);

			} else {
				alert1.close();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);

			alert.setHeaderText("Nie wybra³eœ rezerwacji");
			alert.setContentText("Wybierz rezerwacjê do usuniêcia");
			alert.showAndWait();

		}
	}

	@FXML
	private void usunStroj() {

		int selectedIndex = strojeTabela.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {

			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Usuwanie stroju");
			alert1.setHeaderText("Czy na pewno chcesz usun¹æ ten strój ?");
			alert1.setContentText(strojeTabela.getSelectionModel().getSelectedItem().getNazwaStroju());

			Optional<ButtonType> result = alert1.showAndWait();
			if (result.get() == ButtonType.OK) {

				strojeTabela.getItems().remove(selectedIndex);

			} else {
				alert1.close();

			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);

			alert.setHeaderText("Nie wybra³eœ stroju");
			alert.setContentText("Wybierz strój do usuniêcia");

			alert.showAndWait();

		}

	}

	@FXML
	private void edytujStroj() {
		Stroj wybranyStroj = strojeTabela.getSelectionModel().getSelectedItem();
		if (wybranyStroj != null) {
			boolean okClicked = dodajNowyStroj(wybranyStroj);
			if (okClicked) {
				pokazInfoZarzadzanieStrojami(wybranyStroj);
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			;
			alert.setHeaderText("Nie wybra³eœ stroju");
			alert.setContentText("Wybierz strój do edycji");

			alert.showAndWait();
		}
	}

	public ObservableList<Stroj> getListaStrojow() {
		return listaStrojow;
	}

	public void setListaStrojow(ObservableList<Stroj> listaStrojow) {
		this.listaStrojow = listaStrojow;
	}

	public File getWypozyczalniaPath() {
		Preferences preferences = Preferences.userNodeForPackage(MainController.class);
		String filePath = preferences.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	public void setWypozyczalniaPath(File file) {
		Preferences preferences = Preferences.userNodeForPackage(MainController.class);
		if (file != null) {
			preferences.put("filePath", file.getPath());
		}

	}

	public void ZapiszStrojeDoPliku(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(DataWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			DataWrapper wrapper = new DataWrapper();

			wrapper.setListaStrojow(listaStrojow);
			wrapper.setListaRezerwacji(listaRezerwacji);
			wrapper.setListaWypozyczen(listaWypozyczen);
			wrapper.setLastIndex(indexNowegoStroju);
			wrapper.setPathBajeczne(pathBajeczne);
			wrapper.setPathDziki(pathDziki);
			wrapper.setPathFilmowe(pathFilmowe);
			wrapper.setPathHalloween(pathHalloween);
			wrapper.setPathHistoryczne(pathHistoryczne);
			wrapper.setPathInne(pathInne);
			wrapper.setPathJaselka(pathJaselka);
			wrapper.setPathMundurowe(pathMundurowe);
			wrapper.setPathOwoce(pathOwoce);
			wrapper.setPathSuper(pathSuper);
			wrapper.setPathSwiateczne(pathSwiateczne);
			wrapper.setPathZwierzeta(pathZwierzeta);
			m.marshal(wrapper, file);

			setWypozyczalniaPath(file);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + file.getPath());
			e.printStackTrace();
			alert.showAndWait();
		}

	}

	public void wczytajStroje(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(DataWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			DataWrapper wrapper = (DataWrapper) um.unmarshal(file);

			indexNowegoStroju = wrapper.getLastIndex();
			pathBajeczne = wrapper.getPathBajeczne();
			pathDziki = wrapper.getPathDziki();
			pathFilmowe = wrapper.getPathFilmowe();
			pathHalloween = wrapper.getPathHalloween();
			pathHistoryczne = wrapper.getPathHistoryczne();
			pathInne = wrapper.getPathInne();
			pathJaselka = wrapper.getPathJaselka();
			pathMundurowe = wrapper.getPathMundurowe();
			pathOwoce = wrapper.getPathOwoce();
			pathSuper = wrapper.getPathSuper();
			pathSwiateczne = wrapper.getPathSwiateczne();
			pathZwierzeta = wrapper.getPathZwierzeta();
			
			if (wrapper.getListaStrojow() == null) {
				System.out.println("lista strojow jest pusta");
			} else {
				listaStrojow.clear();
				listaStrojow.addAll(wrapper.getListaStrojow());
			}

			if (wrapper.getListaRezerwacji() == null) {
				System.out.println("lista rezerwacji jest pusta");
			} else {
				listaRezerwacji.clear();
				listaRezerwacji.addAll(wrapper.getListaRezerwacji());
			}
			if (wrapper.getListaWypozyczen() == null) {
				System.out.println("lista wypozyczen jest pusta");
			} else {
				listaWypozyczen.clear();
				listaWypozyczen.addAll(wrapper.getListaWypozyczen());
			}

			setWypozyczalniaPath(file);

		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n" + file.getAbsolutePath());

			alert.showAndWait();
		}
	}

	@FXML
	private void handleNew() {

		indexNowegoStroju = 1;
	
		listaStrojow.clear();
		listaWypozyczen.clear();
		listaRezerwacji.clear();
		pathJaselka = null;
		pathBajeczne=null;
		pathDziki=null;
		pathFilmowe=null;
		pathHalloween=null;
		pathHistoryczne=null;
		pathInne=null;
		pathMundurowe=null;
		pathOwoce=null;
		pathSuper=null;
		pathSwiateczne=null;
		pathZwierzeta=null;
		
		
	}
	@FXML
	private void handleNewGallery() {

		pathJaselka = null;
		pathBajeczne=null;
		pathDziki=null;
		pathFilmowe=null;
		pathHalloween=null;
		pathHistoryczne=null;
		pathInne=null;
		pathMundurowe=null;
		pathOwoce=null;
		pathSuper=null;
		pathSwiateczne=null;
		pathZwierzeta=null;
		
		
	}

	@FXML
	private void handleSave() {
		File wypozyczalnia = getWypozyczalniaPath();
		if (wypozyczalnia != null) {
			ZapiszStrojeDoPliku(wypozyczalnia);
		} else {
			handleSaveAs();
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Zapisywanie");
		alert.setHeaderText("Wszystkie zmiany zosta³y zapisane");
		alert.showAndWait();
	}

	@FXML
	private void handleSaveAs() {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(main.getPrimaryStage());

		ZapiszStrojeDoPliku(file);

	}

	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(main.getPrimaryStage());

		wczytajStroje(file);

	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}

	private boolean isValidWypozyczenie() {
		String msg = "";

		if (datePickerZwrot.getEditor().getText().length() == 0) {
			msg += "Wprowadz datê zwrotu stroju \n\n";
		}
		if (strojeTabela1.getSelectionModel().getSelectedItem() == null) {
			msg += "Zaznacz strój z bazy \n\n";
		}
		if (daneKlientaField.getText() == null || daneKlientaField.getText().length() == 0) {
			msg += "Brakuje danych klienta \n\n";
		}
		if (telefonField.getText() == null || telefonField.getText().length() == 0) {
			msg += "Brakuje telefonu klienta \n\n";
		}

		if (msg.length() == 0) {
			return true;
		} else {

			Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Niepoprawne pola");
			alert.setHeaderText("Uzupe³nij brakuj¹ce pola :");
			alert.setContentText(msg);

			alert.showAndWait();

			return false;
		}
	}

	private boolean isValidRezerwacja() {
		String msg = "";

		if (datePicker.getEditor().getText().length() == 0) {
			msg += "Wprowadz datê rezerwacji \n\n";
		}

		if (strojeTabela1.getSelectionModel().getSelectedItem() == null) {
			msg += "Zaznacz strój z bazy \n\n";
		}
		if (daneRezerwacja.getText() == null || daneRezerwacja.getText().length() == 0) {
			msg += "Brakuje danych klienta \n\n";
		}
		if (telefonRezerwacja.getText() == null || telefonRezerwacja.getText().length() == 0) {
			msg += "Brakuje telefonu klienta \n\n";
		}

		if (msg.length() == 0) {
			return true;
		} else {

			Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Niepoprawne pola");
			alert.setHeaderText("Uzupe³nij brakuj¹ce pola :");
			alert.setContentText(msg);

			alert.showAndWait();

			return false;
		}
	}

	public void szukajStroju(String oldValue, String newValue) {
		ObservableList<Stroj> filteredList = FXCollections.observableArrayList();
		if (szukajField == null || (newValue.length() < oldValue.length()) || newValue == null) {
			strojeTabela.setItems(listaStrojow);
		} else {
			newValue = newValue.toUpperCase();
			for (Stroj stroj : strojeTabela.getItems()) {
				Integer filterID = stroj.getIdNaZapis();
				String filterIDstring = String.valueOf(filterID);
				String filterNazwaStroju = stroj.getNazwaStroju();
				String filterKategoria = stroj.getKategoria();
				if (filterNazwaStroju.toUpperCase().contains(newValue)
						|| filterKategoria.toUpperCase().contains(newValue)
						|| filterIDstring.toUpperCase().contains(newValue)) {
					filteredList.add(stroj);
				}
			}
			strojeTabela.setItems(filteredList);

		}
	}

	public void szukajStroju2(String oldValue, String newValue) {
		ObservableList<Stroj> filteredList = FXCollections.observableArrayList();
		if (szukajField1 == null || (newValue.length() < oldValue.length()) || newValue == null) {
			strojeTabela1.setItems(listaStrojow);
		} else {
			newValue = newValue.toUpperCase();
			for (Stroj stroj : strojeTabela1.getItems()) {
				Integer filterID = stroj.getIdNaZapis();
				String filterIDstring = String.valueOf(filterID);
				String filterNazwaStroju = stroj.getNazwaStroju();
				String filterKategoria = stroj.getKategoria();
				if (filterNazwaStroju.toUpperCase().contains(newValue)
						|| filterKategoria.toUpperCase().contains(newValue)
						|| filterIDstring.toUpperCase().contains(newValue)) {
					filteredList.add(stroj);
				}
			}
			strojeTabela1.setItems(filteredList);

		}
	}

	public void szukajWypozyczenia(String oldValue, String newValue) {
		ObservableList<Wypozyczenie> filteredList = FXCollections.observableArrayList();
		if (szukajField2 == null || (newValue.length() < oldValue.length()) || newValue == null) {
			strojeTabela2.setItems(listaWypozyczen);
		} else {
			newValue = newValue.toUpperCase();
			for (Wypozyczenie wypozyczenie : strojeTabela2.getItems()) {
				String filterNazwaStroju = wypozyczenie.getNazwaWypozyczonegoStroju();
				Integer filterID = wypozyczenie.getIndexWypozyczonegoStroju();
				String filterIDstring = String.valueOf(filterID);
				if (filterNazwaStroju.toUpperCase().contains(newValue)

						|| filterIDstring.toUpperCase().contains(newValue))

				{
					filteredList.add(wypozyczenie);
				}
			}
			strojeTabela2.setItems(filteredList);

		}
	}

	public void szukajRezerwacji(String oldValue, String newValue) {
		ObservableList<Rezerwacja> filteredList = FXCollections.observableArrayList();
		if (szukajField3 == null || (newValue.length() < oldValue.length()) || newValue == null) {
			strojeTabela3.setItems(listaRezerwacji);
		} else {
			newValue = newValue.toUpperCase();
			for (Rezerwacja rezerwacja : strojeTabela3.getItems()) {
				String filterNazwaStroju = rezerwacja.getNazwaZarezerwowanegoStroju();
				Integer filterID = rezerwacja.getIndexZarezerwowanegoStroju();
				String filterIDstring = String.valueOf(filterID);

				if (filterNazwaStroju.toUpperCase().contains(newValue) || filterIDstring.contains(newValue)) {
					filteredList.add(rezerwacja);
				}
			}
			strojeTabela3.setItems(filteredList);

		}
	}

	// @FXML
	// public void openGaleria(){
	//
	//
	// ScrollPane root = new ScrollPane();
	// TilePane tile = new TilePane();
	// tile.setStyle("-fx-background-color: ef99ff;");
	// tile.setPadding(new Insets(15, 15, 15, 15));
	// tile.setHgap(15);
	// tile.setVgap(15);
	//
	// String path = "C:\\Users\\MaYDaY\\Desktop\\zarabie";
	//
	// File folder = new File(path);
	// File[] listOfFiles = folder.listFiles();
	//
	// for (final File file : listOfFiles) {
	// ImageView imageView;
	// imageView = createImageView(file);
	// tile.getChildren().addAll(imageView);
	// }
	//
	//
	// root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
	// root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical
	// scroll bar
	// root.setFitToWidth(true);
	// root.setContent(tile);
	//
	// galleryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
	// galleryStage.setHeight(Screen.getPrimary().getVisualBounds()
	// .getHeight());
	// Scene scene = new Scene(root);
	// galleryStage.setScene(scene);
	// galleryStage.show();
	// }

	@FXML
	public void openGaleria() {
		BorderPane bPane = new BorderPane();
		FlowPane fPane = new FlowPane();
		ScrollPane root = new ScrollPane();
		TilePane tile = new TilePane();
		BorderPane topBorderPane = new BorderPane();
		Pane pane = new Pane();

		fPane.getStylesheets().add("/view/MojStyl.css");
		fPane.getStyleClass().add("background-FlowPaneGaleria");
		pane.getStylesheets().add("/view/MojStyl.css");
		pane.getStyleClass().add("background-Galeria-Logo");
		bPane.setTop(topBorderPane);
		bPane.setCenter(root);
		pane.setPrefWidth(400);
		pane.setPrefHeight(70);

		topBorderPane.setTop(pane);
		topBorderPane.setCenter(fPane);
		tile.setPrefSize(1680, 1050);
		tile.getStylesheets().add("/view/MojStyl.css");
		tile.getStyleClass().add("background-9");
		tile.setPadding(new Insets(15, 15, 15, 15));
		tile.setHgap(15);
		tile.setVgap(15);
		
		Button btnCategory1 = new Button("JASE£KA");
		btnCategory1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathJaselka == null) {
					pathJaselka = pobierzFolder();
				} else {
					
					File folder = new File(pathJaselka);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory2 = new Button("ŒWI¥TECZNE");	
		btnCategory2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathSwiateczne == null) {
					pathSwiateczne = pobierzFolder();
				} else {

					File folder = new File(pathSwiateczne);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory3 = new Button("POSTACIE Z BAJEK");
		btnCategory3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathBajeczne == null) {
					pathBajeczne = pobierzFolder();
				} else {

					File folder = new File(pathBajeczne);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory4 = new Button("ZWIERZÊTA");
		btnCategory4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathZwierzeta == null) {
					pathZwierzeta = pobierzFolder();
				} else {

					File folder = new File(pathZwierzeta);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory5 = new Button("HALLOWEEN");
		btnCategory5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathHalloween == null) {
					pathHalloween = pobierzFolder();
				} else {

					File folder = new File(pathHalloween);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory6 = new Button("POSTACIE Z FILMÓW");
		btnCategory6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathFilmowe == null) {
					pathFilmowe = pobierzFolder();
				} else {

					File folder = new File(pathFilmowe);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory7 = new Button("SUPERBOHATER");
		btnCategory7.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathSuper == null) {
					pathSuper = pobierzFolder();
				} else {

					File folder = new File(pathSuper);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory8 = new Button("DZIKI ZACHÓD");
		btnCategory8.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathDziki == null) {
					pathDziki = pobierzFolder();
				} else {

					File folder = new File(pathDziki);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory9 = new Button("MUNDUROWE");
		btnCategory9.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathMundurowe == null) {
					pathMundurowe = pobierzFolder();
				} else {

					File folder = new File(pathMundurowe);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory10 = new Button("HISTORYCZNE");
		btnCategory10.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathHistoryczne == null) {
					pathHistoryczne = pobierzFolder();
				} else {

					File folder = new File(pathHistoryczne);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory11 = new Button("OWOCE I WARZYWA");
		btnCategory11.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathOwoce == null) {
					pathOwoce = pobierzFolder();
				} else {

					File folder = new File(pathOwoce);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		Button btnCategory12 = new Button("   INNE   ");
		btnCategory12.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent e) {
				tile.getChildren().clear();
				if (pathInne == null) {
					pathInne = pobierzFolder();
				} else {

					File folder = new File(pathInne);
					File[] listOfFiles = folder.listFiles();

					for (final File file : listOfFiles) {
						ImageView imageView;
						imageView = createImageView(file);
						tile.getChildren().addAll(imageView);
					}
				}
			}

		});
		
		fPane.getChildren().addAll(btnCategory1, btnCategory2, btnCategory3, btnCategory4, btnCategory5, btnCategory6,
				btnCategory7, btnCategory8, btnCategory9, btnCategory10, btnCategory11, btnCategory12);


		root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
		root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical
																	// scroll
																	// bar
		root.setFitToWidth(true);
		root.setContent(tile);

		galleryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		galleryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
		Scene scene = new Scene(bPane);
		galleryStage.setScene(scene);
		galleryStage.show();
	}

	public String pobierzFolder() {
		String path = null;
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		final File selectedDirectory = directoryChooser.showDialog(galleryStage);
		if (selectedDirectory != null) {
			path = selectedDirectory.getAbsolutePath();

		}
		return path;

	}

	private ImageView createImageView(final File imageFile) {
		// DEFAULT_THUMBNAIL_WIDTH is a constant you need to define
		// The last two arguments are: preserveRatio, and use smooth (slower)
		// resizing

		ImageView imageView = null;
		try {
			final Image image = new Image(new FileInputStream(imageFile), 200, 0, true, true);
			imageView = new ImageView(image);
			imageView.setFitWidth(200);
			imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {

						if (mouseEvent.getClickCount() == 2) {
							try {
								BorderPane borderPane = new BorderPane();
								ImageView imageView = new ImageView();
								Image image = new Image(new FileInputStream(imageFile));
								imageView.setImage(image);
								imageView.setStyle("-fx-background-color: BLACK");
								imageView.setFitHeight(galleryStage.getHeight() - 10);
								imageView.setPreserveRatio(true);
								imageView.setSmooth(true);
								imageView.setCache(true);
								borderPane.setCenter(imageView);
								borderPane.setStyle("-fx-background-color: BLACK");
								Stage newStage = new Stage();
								newStage.setWidth(galleryStage.getWidth());
								newStage.setHeight(galleryStage.getHeight());
								newStage.setTitle(imageFile.getName());
								Scene scene = new Scene(borderPane, Color.BLACK);
								newStage.setScene(scene);
								newStage.show();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}

						}
					}
				}
			});
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		return imageView;
	}

	public static int getIndexNowegoStroju() {
		return indexNowegoStroju;
	}

	public static void setIndexNowegoStroju(int indexNowegoStroju) {
		MainController.indexNowegoStroju = indexNowegoStroju;
	}

	public static String getPathJaselka() {
		return pathJaselka;
	}

	public static void setPathJaselka(String pathJaselka) {
		MainController.pathJaselka = pathJaselka;
	}

	public static String getPathSwiateczne() {
		return pathSwiateczne;
	}

	public static void setPathSwiateczne(String pathSwiateczne) {
		MainController.pathSwiateczne = pathSwiateczne;
	}

	public static String getPathBajeczne() {
		return pathBajeczne;
	}

	public static void setPathBajeczne(String pathBajeczne) {
		MainController.pathBajeczne = pathBajeczne;
	}

	public static String getPathZwierzeta() {
		return pathZwierzeta;
	}

	public static void setPathZwierzeta(String pathZwierzeta) {
		MainController.pathZwierzeta = pathZwierzeta;
	}

	public static String getPathHalloween() {
		return pathHalloween;
	}

	public static void setPathHalloween(String pathHalloween) {
		MainController.pathHalloween = pathHalloween;
	}

	public static String getPathFilmowe() {
		return pathFilmowe;
	}

	public static void setPathFilmowe(String pathFilmowe) {
		MainController.pathFilmowe = pathFilmowe;
	}

	public static String getPathSuper() {
		return pathSuper;
	}

	public static void setPathSuper(String pathSuper) {
		MainController.pathSuper = pathSuper;
	}

	public static String getPathDziki() {
		return pathDziki;
	}

	public static void setPathDziki(String pathDziki) {
		MainController.pathDziki = pathDziki;
	}

	public static String getPathMundurowe() {
		return pathMundurowe;
	}

	public static void setPathMundurowe(String pathMundurowe) {
		MainController.pathMundurowe = pathMundurowe;
	}

	public static String getPathHistoryczne() {
		return pathHistoryczne;
	}

	public static void setPathHistoryczne(String pathHistoryczne) {
		MainController.pathHistoryczne = pathHistoryczne;
	}

	public static String getPathOwoce() {
		return pathOwoce;
	}

	public static void setPathOwoce(String pathOwoce) {
		MainController.pathOwoce = pathOwoce;
	}

	public static String getPathInne() {
		return pathInne;
	}

	public static void setPathInne(String pathInne) {
		MainController.pathInne = pathInne;
	}

	
	
}


