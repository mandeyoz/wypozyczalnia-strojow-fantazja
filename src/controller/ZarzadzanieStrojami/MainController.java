package controller.ZarzadzanieStrojami;

import java.io.File;
//import java.io.FileInputStream;
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
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.Main;
import model.DataWrapper;

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
	private Button btnWyczysc;
	@FXML
	public TableView<Stroj> strojeTabela;
	@FXML
	public TableView<Stroj> strojeTabela1;
	@FXML
	public TableView<Wypozyczenie> strojeTabela2;
	@FXML
	public TableView<Rezerwacja> strojeTabela3;
	@FXML
	public TableView<Wypozyczenie> historiaTabela;
	@FXML
	public TableColumn<Wypozyczenie, Integer> numCol;
	@FXML
	public TableColumn<Wypozyczenie, String> dataCol;
	@FXML
	public TableColumn<Wypozyczenie, String> typCol;
	@FXML
	public TableColumn<Wypozyczenie, Integer> idCol;
	@FXML
	public TableColumn<Wypozyczenie, String> nazwaCol;
	@FXML
	public TableColumn<Wypozyczenie, String> klientCol;
	@FXML
	public TableColumn<Wypozyczenie, String> telCol;

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
	public TextField daneKlientaField;
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
	private TextField szukajField4;
	@FXML
	private TextField odbiorStroju;
	@FXML
	private BorderPane bdPane;

	@SuppressWarnings("unused")
	private Image image;
	@SuppressWarnings("unused")
	private Stroj stroj;
	@SuppressWarnings("unused")
	private DateTimeFormatter formatter;
	private static int indexNowegoStroju = 1;
	private static int indexWypozyczen = 1;
	private Main main;

	private Stage galleryStage = new Stage();
	public ImageView imageView = new ImageView();
	public ComboBox<String> cbKategoria = new ComboBox<String>();
	public TilePane tile = new TilePane();
	public Button btnSzukajKategorii = new Button();

	public ObservableList<Stroj> listaStrojow = FXCollections.observableArrayList();
	public ObservableList<Wypozyczenie> listaWypozyczen = FXCollections.observableArrayList();
	public ObservableList<Wypozyczenie> historiaWypozyczen = FXCollections.observableArrayList();
	public ObservableList<Rezerwacja> listaRezerwacji = FXCollections.observableArrayList();

	public MainController() {

		File file = getWypozyczalniaPath();
		if (file != null) {

			wczytajStroje(file);

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void initialize() {

		strojeTabela.setItems(listaStrojow);
		strojeTabela1.setItems(listaStrojow);
		strojeTabela2.setItems(listaWypozyczen);
		strojeTabela3.setItems(listaRezerwacji);
		historiaTabela.setItems(historiaWypozyczen);

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

		numCol.setCellValueFactory(cellData -> cellData.getValue().indexWypozyczenProperty().asObject());
		telCol.setCellValueFactory(cellData -> cellData.getValue().telefonProperty());
		idCol.setCellValueFactory(cellData -> cellData.getValue().indexWypozyczonegoStrojuProperty().asObject());
		dataCol.setCellValueFactory(cellData -> cellData.getValue().dataUtworzeniaProperty());
		typCol.setCellValueFactory(cellData -> cellData.getValue().typOperacjiProperty());
		nazwaCol.setCellValueFactory(cellData -> cellData.getValue().nazwaWypozyczonegoStrojuProperty());
		klientCol.setCellValueFactory(cellData -> cellData.getValue().daneKlientaProperty());

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
		szukajField4.textProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				szukajwHistorii((String) oldValue, (String) newValue);
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
	private void usunStroj() {

		int selectedIndex = strojeTabela.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {

			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Usuwanie stroju");
			alert1.setHeaderText("Czy na pewno chcesz usunąć ten strój ?");
			alert1.setContentText(strojeTabela.getSelectionModel().getSelectedItem().getNazwaStroju());

			Optional<ButtonType> result = alert1.showAndWait();
			if (result.get() == ButtonType.OK) {

				listaStrojow.remove(selectedIndex);

			} else {
				alert1.close();

			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);

			alert.setHeaderText("Nie wybrałeś stroju");
			alert.setContentText("Wybierz strój do usunięcia");

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
			alert.setHeaderText("Nie wybrałeś stroju");
			alert.setContentText("Wybierz strój do edycji");

			alert.showAndWait();
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
			noweWypozyczenie.setTypOperacji("Nowe wypożyczenie");

			if (listaWypozyczen.isEmpty()) {

				listaWypozyczen.add(noweWypozyczenie);
				indexWypozyczen++;
				historiaWypozyczen.add(noweWypozyczenie);

				datePicker.getEditor().clear();
				datePickerZwrot.getEditor().clear();
				daneKlientaField.clear();
				daneRezerwacja.clear();
				telefonField.clear();
				telefonRezerwacja.clear();
				uwagiField.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("NOWE WYPOŻYCZENIE");
				alert.setContentText("Dodano pomyślnie nowe wypożyczenie do bazy ");
				alert.showAndWait();

			} else if (listaWypozyczen != null) {

				boolean exist = false;
				for (Wypozyczenie test1 : listaWypozyczen) {

					int check = test1.getIndexWypozyczonegoStroju();

					if (check == noweWypozyczenie.getIndexWypozyczonegoStroju()) {
						exist = true;
						Alert alert = new Alert(AlertType.WARNING);
						alert.setHeaderText("NOWE WYPOŻYCZENIE");
						alert.setContentText("Strój o takim identyfikatorze jest już wypożyczony ");
						alert.showAndWait();

						break;
					}

				}
				if (exist == false) {
					listaWypozyczen.add(noweWypozyczenie);
					indexWypozyczen++;

					historiaWypozyczen.add(noweWypozyczenie);
					datePicker.getEditor().clear();
					datePickerZwrot.getEditor().clear();
					daneKlientaField.clear();
					daneRezerwacja.clear();
					telefonField.clear();
					telefonRezerwacja.clear();
					uwagiField.clear();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("NOWE WYPOŻYCZENIE");
					alert.setContentText("Dodano pomyślnie nowe wypożyczenie do bazy ");
					alert.showAndWait();
				}

			}
		}
	}

	@FXML
	private void usunWypozyczenie() {
		int selectedIndex = strojeTabela2.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {

			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Usuwanie wypożyczenia");
			alert1.setHeaderText("Czy chcesz by strój został oddany ?");
			alert1.setContentText(strojeTabela2.getSelectionModel().getSelectedItem().getNazwaWypozyczonegoStroju());

			Optional<ButtonType> result = alert1.showAndWait();
			if (result.get() == ButtonType.OK) {
				Wypozyczenie wypozyczenie = new Wypozyczenie();
				wypozyczenie.setDaneKlienta(daneKlientaLabel.getText());
				wypozyczenie.setTelefon(telefonLabel.getText());
				wypozyczenie.setIndexWypozyczonegoStroju(
						strojeTabela2.getSelectionModel().getSelectedItem().getIndexWypozyczonegoStroju());
				wypozyczenie.setNazwaWypozyczonegoStroju(
						strojeTabela2.getSelectionModel().getSelectedItem().getNazwaWypozyczonegoStroju());
				wypozyczenie.setDataUtworzenia(strojeTabela2.getSelectionModel().getSelectedItem().getDataUtworzenia());
				wypozyczenie.setTypOperacji("Oddanie stroju");
				historiaWypozyczen.add(wypozyczenie);

				listaWypozyczen.remove(selectedIndex);
				indexWypozyczen++;
			} else {
				alert1.close();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);

			alert.setHeaderText("Nie wybrałeś wypożyczenia");
			alert.setContentText("Wybierz który strój chcesz oddać");
			alert.showAndWait();

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
			alert.setContentText("Dodano pomyślnie nową rezerwację do bazy ");
			alert.showAndWait();
		}
	}

	@FXML
	private void usunRezerwacje() {
		int selectedIndex = strojeTabela3.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {

			Alert alert1 = new Alert(AlertType.CONFIRMATION);
			alert1.setTitle("Usuwanie rezerwacji");
			alert1.setHeaderText("Czy na pewno chcesz usunąć tą rezerwację ?");
			alert1.setContentText(strojeTabela3.getSelectionModel().getSelectedItem().getNazwaZarezerwowanegoStroju());

			Optional<ButtonType> result = alert1.showAndWait();
			if (result.get() == ButtonType.OK) {

				listaRezerwacji.remove(selectedIndex);

			} else {
				alert1.close();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);

			alert.setHeaderText("Nie wybrałeś rezerwacji");
			alert.setContentText("Wybierz rezerwację do usunięcia");
			alert.showAndWait();

		}
	}

	@FXML
	public void otworzGalerie() {
		BorderPane bPane = new BorderPane();
		FlowPane fPane = new FlowPane();
		ScrollPane root = new ScrollPane();

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

		cbKategoria.setItems(DodajNowyStrojController.Kategoria);
		fPane.getChildren().addAll(cbKategoria, btnSzukajKategorii);
		fPane.setPadding(new Insets(15, 15, 15, 15));
		fPane.setHgap(15);
		btnSzukajKategorii.setText("Szukaj");

		btnSzukajKategorii.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				szukajKategorii();

			}

		});

		root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

		root.setFitToWidth(true);
		root.setContent(tile);

		galleryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		galleryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
		Scene scene = new Scene(bPane);
		galleryStage.setScene(scene);
		galleryStage.show();
	}

	public void szukajKategorii() {

		String wybranaKategoria = cbKategoria.getSelectionModel().getSelectedItem().toUpperCase();
		tile.getChildren().clear();

		for (Stroj stroj : listaStrojow) {

			String filterKategoria = stroj.getKategoria();

			if (filterKategoria.contains(wybranaKategoria)) {

				String filterPath = stroj.getImagePath();
				ImageView imageView;
				imageView = createImageView(filterPath);
				tile.getChildren().addAll(imageView);

			}

		}

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

	private ImageView createImageView(String imageURL) {

		ImageView imageView = null;
		Image image = new Image(imageURL);
		imageView = new ImageView(image);
		imageView.setFitWidth(200);
		imageView.setPreserveRatio(true);
		imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {

				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {

					if (mouseEvent.getClickCount() == 2) {
						BorderPane borderPane = new BorderPane();
						ImageView imageView = new ImageView();
						Image image = new Image(imageURL);
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

						Scene scene = new Scene(borderPane, Color.BLACK);
						newStage.setScene(scene);
						newStage.show();

					}
				}
			}
		});
		return imageView;
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

	public void wczytajStroje(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(DataWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			DataWrapper wrapper = (DataWrapper) um.unmarshal(file);
			indexWypozyczen = wrapper.getLastIndexWypozyczen();
			indexNowegoStroju = wrapper.getLastIndex();

			DodajNowyStrojController.Kategoria.clear();
			DodajNowyStrojController.Kategoria.addAll(wrapper.getKategoria());

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
			if (wrapper.getHistoriaWypozyczen() == null) {
				System.out.println("historia wypozyczen jest pusta");
			} else {
				historiaWypozyczen.clear();
				historiaWypozyczen.addAll(wrapper.getHistoriaWypozyczen());
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
		indexWypozyczen = 1;
		listaStrojow.clear();
		listaWypozyczen.clear();
		listaRezerwacji.clear();

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
			wrapper.setKategoria(DodajNowyStrojController.Kategoria);
			wrapper.setLastIndexWypozyczen(indexWypozyczen);
			wrapper.setHistoriaWypozyczen(historiaWypozyczen);
			m.marshal(wrapper, file);

			setWypozyczalniaPath(file);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Nie wybrano pliku");

			alert.showAndWait();

		}

	}

	@FXML
	private void handleSave() {
		try {
			File wypozyczalnia = getWypozyczalniaPath();
			if (wypozyczalnia != null) {
				ZapiszStrojeDoPliku(wypozyczalnia);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Zapisywanie");
				alert.setHeaderText("Wszystkie zmiany zostały zapisane ");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wybierz plik");
				alert.setHeaderText("najpierw zapisz plik w MENU->ZAPISZ JAKO");

				alert.showAndWait();

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Zmiany nie zostały zapisane");
			alert.setContentText("Wybierz plik do zapisu");
			alert.showAndWait();
			e.printStackTrace();
		}

	}

	@FXML
	private void handleSaveAs() {

		try {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showSaveDialog(main.getPrimaryStage());

			ZapiszStrojeDoPliku(file);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Błąd");
			alert.setHeaderText("Zmiany nie zostały zapisane");
			alert.setContentText("Wybierz plikdo zapisu");
			alert.showAndWait();

		}

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
			msg += "Wprowadz datę zwrotu stroju \n\n";
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
			alert.setHeaderText("Uzupełnij brakujące pola :");
			alert.setContentText(msg);

			alert.showAndWait();

			return false;
		}
	}

	private boolean isValidRezerwacja() {
		String msg = "";

		if (datePicker.getEditor().getText().length() == 0) {
			msg += "Wprowadz datę rezerwacji \n\n";
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
			alert.setHeaderText("Uzupełnij brakujące pola :");
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

	public void szukajwHistorii(String oldValue, String newValue) {
		ObservableList<Wypozyczenie> filteredList = FXCollections.observableArrayList();
		if (szukajField4 == null || (newValue.length() < oldValue.length()) || newValue == null) {
			historiaTabela.setItems(historiaWypozyczen);
		} else {
			newValue = newValue.toUpperCase();
			for (Wypozyczenie wypozyczenie : historiaTabela.getItems()) {
				String filterNazwa = wypozyczenie.getNazwaWypozyczonegoStroju();
				Integer filterID = wypozyczenie.getIndexWypozyczonegoStroju();
				String filterIDstring = String.valueOf(filterID);
				String filterKlient = wypozyczenie.getDaneKlienta();

				if (filterNazwa.toUpperCase().contains(newValue) || filterIDstring.contains(newValue)
						|| filterIDstring.contains(newValue) | filterKlient.contains(newValue)) {
					filteredList.add(wypozyczenie);
				}
			}
			historiaTabela.setItems(filteredList);

		}
	}

	@FXML
	public void wyczyscHistorie() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Usuwanie dziennika zdarzeń");
		alert.setHeaderText("Czy na pewno chcesz usunąć WSZYSTKIE wpisy ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			historiaWypozyczen.clear();
			indexWypozyczen = 1;

		} else {
			alert.close();

		}

	}

	public static int getIndexNowegoStroju() {
		return indexNowegoStroju;
	}

	public static void setIndexNowegoStroju(int indexNowegoStroju) {
		MainController.indexNowegoStroju = indexNowegoStroju;
	}

	public static int getIndexWypozyczen() {
		return indexWypozyczen;
	}

	public static void setIndexWypozyczen(int indexWypozyczen) {
		MainController.indexWypozyczen = indexWypozyczen;
	}

}
