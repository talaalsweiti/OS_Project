package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Simulator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.MyProcess;

public class MainPageController implements Initializable{

    @FXML
    private TableView<MyProcess> process_table;
    
    @FXML
    private ToggleGroup algorithm;

    @FXML
    private TableColumn<MyProcess, Double> duration;

    @FXML
    private TextField faults_rate;

    @FXML
    private TableColumn<MyProcess, Double> finish_time;

    @FXML
    private TextArea memory_view;

    @FXML
    private TableColumn<MyProcess, Integer> num_faluts;

    @FXML
    private TableColumn<MyProcess, Integer> num_pages;

    @FXML
    private TableColumn<MyProcess, Integer> pid;

    @FXML
    private TextField quantum_text;

    @FXML
    private TableColumn<MyProcess, Double> start_time;

    @FXML
    private TextField total_faults;

    @FXML
    private TableColumn<MyProcess, Double> turn_around;

    @FXML
    private TableColumn<MyProcess, Double> wait_time;
    @FXML
    private RadioButton clock;
    @FXML
    private RadioButton second_chance;


    @FXML
    private Label totalPageFaults;
    
    @FXML
    private Label faultsRate;
    
    
    ObservableList<MyProcess> list;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	list = FXCollections.observableArrayList();
    	pid.setCellValueFactory(new PropertyValueFactory<MyProcess,Integer>("PID"));
    	start_time.setCellValueFactory(new PropertyValueFactory<MyProcess,Double>("startTime"));
    	finish_time.setCellValueFactory(new PropertyValueFactory<MyProcess,Double>("finishTime"));
    	wait_time.setCellValueFactory(new PropertyValueFactory<MyProcess,Double>("waitTime"));
    	duration.setCellValueFactory(new PropertyValueFactory<MyProcess,Double>("duration"));
    	turn_around.setCellValueFactory(new PropertyValueFactory<MyProcess,Double>("turnaround"));
    	num_faluts.setCellValueFactory(new PropertyValueFactory<MyProcess,Integer>("pageFaults"));
    	num_pages.setCellValueFactory(new PropertyValueFactory<MyProcess,Integer>("pagesNum"));
    } 
    public void startSimulation(ActionEvent event) {
    	
    	if(quantum_text.getText()!=null || quantum_text.getText()!="") {
    		Simulator.quantum= Integer.parseInt(quantum_text.getText());
    	}else {
    		quantum_text.setText("20");
    	}
    	if(clock.isSelected()) {
    		Simulator.algorithm =2;
    	}
    	Simulator.startSimulation();
    	
    	double totalPages=0;
    	int pageFaults=Simulator.totalPageFaults;
    	for(int i=0;i<Simulator.allThreads.size();i++) {
    		MyProcess ppp = Simulator.allThreads.get(i).p;
    		totalPages+=ppp.pagesNum;
    		list.add(ppp);
    	}
    	
    	totalPageFaults.setText(pageFaults+"");
    	faultsRate.setText((pageFaults/totalPages)*100 + "%");
    	process_table.setItems(list);
    }

}
