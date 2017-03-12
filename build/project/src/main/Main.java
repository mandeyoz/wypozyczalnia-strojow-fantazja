package main;


import java.io.IOException;


import controller.ZarzadzanieStrojami.MainController;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private AnchorPane oknoZarzadzanieStrojami;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.getIcons().add(new Image("view/icon.ico"));
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/OknoZarzadzanieStrojami.fxml"));
			oknoZarzadzanieStrojami = (AnchorPane) loader.load();
			Scene scene = new Scene(oknoZarzadzanieStrojami);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Fantazja Wypo¿yczalnia strojów");
	        primaryStage.setResizable(false);
	     

			MainController controller = loader.getController();
			controller.setMain(this);
			primaryStage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
