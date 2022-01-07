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

public class Main extends Application {
	
	private static Stage stg;
	public int numOfProcesses;
	public int physicalMemorySize;
	public int minNumOfFrames;
	public ArrayList<Process> processList;
	
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
	
	
	public boolean readFile(String filename) throws FileNotFoundException {
		File input = new File(filename);
		try {
			Scanner sc = new Scanner(input);
			if(!sc.hasNextLine()) { errorInFile(); sc.close(); return false;}
			String line = sc.nextLine();
			if(line.contains(" ")) {
				errorInFile();
				sc.close();
				return false;
			}
			else {
				numOfProcesses = Integer.parseInt(line);
			}
			if(!sc.hasNextLine()) { errorInFile(); sc.close(); return false;}
			line = sc.nextLine();
			if(line.contains(" ")) {
				errorInFile();
				sc.close();
				return false;
			}
			else {
				physicalMemorySize = Integer.parseInt(line);
			}
			if(!sc.hasNextLine()) { errorInFile(); sc.close(); return false;}
			line = sc.nextLine();
			if(line.contains(" ")) {
				errorInFile();
				sc.close();
				return false;
			}
			else {
				minNumOfFrames = Integer.parseInt(line);
			}
			processList = new ArrayList<Process>();
			if(!sc.hasNextLine()) { errorInFile(); sc.close(); return false;}
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				if(line.equals("")) {
					continue;
				}
				String[] lineSplit = line.split(" ");
				if(lineSplit.length >= 5) {
					int PID = Integer.parseInt(lineSplit[0]);
					double startTime = Double.parseDouble(lineSplit[1]);
					double duration = Double.parseDouble(lineSplit[2]);
					int size = Integer.parseInt(lineSplit[3]);
					ArrayList<Page> pages = new ArrayList<Page>();
					for(int j=4; j<lineSplit.length; j++) {
						//!check if page number in range
						Page p = new Page(PID, lineSplit[j]);
						pages.add(p);
					}
					
					Process pr = new Process(PID, startTime, duration, size, pages);
					processList.add(pr);
				}
				else {
					errorInFile();
					sc.close();
					return false;
				}
				
			}
			sc.close();
			return true;
		} catch(Exception FileNotFoundException) {
			errorInFile();
			return false;
		}
		
	}
	
	public void errorInFile() {
		JOptionPane.showInternalMessageDialog(null, "Error in File\nTry another file");
	}
	
	public void generateFile(String filename) {
		
	}
	
	
	
	
}














