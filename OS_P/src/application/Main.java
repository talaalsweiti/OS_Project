package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Page;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import model.Process;
import model.ProcessThread;

public class Main extends Application {
	
	private static Stage stg;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			primaryStage.setResizable(false);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/WelcomePage.fxml"));
			Scene scene = new Scene(root,1350,850);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Virtual Memory Management Simulation");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	  public void changeScene(String fxml) throws IOException {
	        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
	        stg.getScene().setRoot(pane);
	    }

	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
}














