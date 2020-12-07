/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.fxml;

import busifix.BusifixAppData;
import busifix.BusifixAppProgress;
import busifix.io.SimLoader;
import busifix.simdatatypes.Employee;
import busifix.simdatatypes.SimData;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author shado
 */
public class StudentFXMLController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Label business_name;
    @FXML
    private Label business_description;
    
    //Financial business overview labels
    @FXML
    private Label balance_lbl;
    @FXML
    private Label expenses_lbl;
    @FXML
    private Label expected_sales_lbl;
    @FXML
    private Label net_lbl;
    @FXML
    private Label expected_balance_lbl;
    
    //Inventory status listview
    @FXML
    private ListView inventory_listview;
    
    //Event log text area
    @FXML
    private TextArea log_txtarea;
    
    //Action buttonn
    @FXML
    private Button advance_btn;
    @FXML
    private Button fire_btn;
    @FXML
    private Button hire_btn;
    
    //Day counter label
    @FXML
    private Label day_lbl;
    
    //Employees listview
    @FXML
    private ListView employees_listview;
    
    //Employees listview
    @FXML
    private ListView employee_pool_listview;
    
    //Placed orders listview
    @FXML
    private ListView placed_orders_listview;
    
    //Available orders listview
    @FXML
    private ListView available_orders_listview;
    
    //Loads the chosen data file into the working simulation data
    public void loadSim() {    
        
        //Get load location
        FileChooser chooser = new FileChooser();
        File chosenFile = chooser.showOpenDialog(null);
        
        //If valid file was chosen
        if (chosenFile != null) {
        
            //Load working simulation data from file
            SimLoader simLoader = new SimLoader();
            SimData loadedData = null;
            
            try {
                
                //Load and retrieve data
                loadedData = simLoader.load(chosenFile.getAbsolutePath());
            } catch (Exception e) {
                
                e.printStackTrace();
                
                //Log error
                logEvent("Simulation file: '" + chosenFile.getAbsolutePath() + "' could not be successfully loaded");
            }
            
            //If load was successful
            if (loadedData != null) {
                
                //Setup data
                BusifixAppData.SetWorkingData(loadedData);
                
                //Perform secondary initialization
                initializeSimulation();
                
                //Log successful load
                logEvent("Simulation file: '" + chosenFile.getAbsolutePath() + "' successfully loaded");
            }
        }
    }
    
    //Fires the selected employee
    public void fireEmployee(ActionEvent event) {
        
        //Get the source button
        Button source = (Button)event.getSource();
        
        if (source.equals(fire_btn)) {
            
            ArrayList<Employee> hiredEmployees = BusifixAppProgress.GetSimProgress().hiredEmployees;
            
            int selectedIndex = employees_listview.getSelectionModel().getSelectedIndex();
            
            if (selectedIndex > -1 && hiredEmployees.size() > 0) {
                
                hiredEmployees.remove(selectedIndex);
                displayProgress();
            }
        }
    }
    
    //Hires the selected employee
    public void hireEmployee(ActionEvent event) {
        
        //Get the source button
        Button source = (Button)event.getSource();
        
        if (source.equals(hire_btn)) {
            
            ArrayList<Employee> candidates = BusifixAppProgress.GetUnhiredEmployees();
            
            int selectedIndex = employee_pool_listview.getSelectionModel().getSelectedIndex();
            
            if (selectedIndex > -1 && candidates.size() > 0) {
                
                Employee selectedEmployee = candidates.get(selectedIndex);
                BusifixAppProgress.GetSimProgress().hiredEmployees.add(selectedEmployee);
                
                displayProgress();
            }
        }
    }
    
    //Advances the simulation to the next day
    public void advanceDay() {
        
        //If the simulation data is initialized
        if (BusifixAppData.IsWorkingDataInitialized()) {
        
            //Log end of day
            logEvent("Finishing day: " + BusifixAppProgress.GetSimProgress().day);

            //Perform updates for the next day
            BusifixAppProgress.NextDay();

            //Update displays
            displayProgress();

            //Log beginning of day
            logEvent("Starting day: " + BusifixAppProgress.GetSimProgress().day);
        } else {
            
            System.out.println("Unable to advance day: simulation not initialized");
        }
    }
    
    //Update the student interface
    private void displayProgress() {
        
        //Update day counter
        int dayCounter = BusifixAppProgress.GetSimProgress().day;
        day_lbl.setText(String.valueOf(dayCounter));
        
        //Update financial labels
        double balance = BusifixAppData.GetWorkingData().balance;
        balance_lbl.setText("$" + String.valueOf(balance));
        
        //Retrieve progress data
        ArrayList<String> inventoryNames = BusifixAppProgress.GetInventoryNames();
        ArrayList<String> candidateNames = BusifixAppProgress.GetUnhiredEmployeeNames();
        ArrayList<String> hiredEmployeeNames = BusifixAppProgress.GetHiredEmployeeNames();
        
        //Update inventories listview
        displayInListView(inventory_listview, inventoryNames, "<No inventories>");
        
        //Update employee listviews
        displayInListView(employees_listview, hiredEmployeeNames, "<No employees>");
        displayInListView(employee_pool_listview, candidateNames, "<No candidates>");
        
        //Update order listviews
        displayInListView(placed_orders_listview, null, "<No orders placed>");
        displayInListView(available_orders_listview, null, "<No orders available>");
    }
    
    //Displays the contents of the given list in the given list view
    //Displays empty message if the givne list is empty
    private void displayInListView(ListView listView, ArrayList<String> items, String emptyMessage) {
        
        //Clear existing items
        listView.getItems().clear();
        
        //If the given list is invalid or empty
        if (items == null || items.size() == 0) {
            
            //Display empty message
            listView.getItems().add(emptyMessage);
        } else {
            
            //Display the given items
            for (int index = 0; index < items.size(); index++) {
                
                listView.getItems().add(items.get(index));
            }
        }
    }
    
    //Transitions to the teacher interface
    public void toTeacherMode() {
        
        AnchorPane pane = new AnchorPane();
        
        try {
            
            //Load teacher mode
            pane = FXMLLoader.load(getClass().getResource("teacherFXML.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    //Logs the given message to the event log
    public void logEvent(String eventMessage) {
        
        //Retrieve the current event text
        String logText = log_txtarea.getText();
        
        //Add the event message to the text
        log_txtarea.appendText(eventMessage + "\n--\n");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Initialize simulation progress
        BusifixAppProgress.InitializeSimProgress();
        
        //Log initialization
        logEvent("Initializing student interface");
        
        //Detect existing simulation data
        if (BusifixAppData.IsWorkingDataInitialized()) {
            
            //Perform simulation initialization
            initializeSimulation();
        }
    }    
    
    private void initializeSimulation() {
        
        //Load interface settings
        business_name.setText(BusifixAppData.GetWorkingData().businessName);
        business_description.setText(BusifixAppData.GetWorkingData().businessDescription);
        
        //Update displays
        displayProgress();
        
        //Display welcome message
        String message = BusifixAppData.GetWorkingData().welcomeMessage;
        
        //If a welcome message is present
        if (message != "") {
            
            //Display welcome message
            logEvent(message);
        }
    }
    
}
