/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.fxml;

import javafx.fxml.FXML;
import busifix.BusifixAppData;
import busifix.io.SimLoader;
import busifix.io.SimSaver;
import busifix.simdatatypes.SimData;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shado
 */
public class TeacherFXMLController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Button productBtn;
    
    @FXML
    private TextField business_name_txtfield;
    
    @FXML
    private TextArea business_description_txtarea;
    
    @FXML
    private TextField balance_txtfield;
    
    @FXML
    private TextArea welcome_txtarea;
    
    //Lists
    @FXML
    private ListView positions_listview;
    @FXML
    private ListView inventories_listview;
    @FXML
    private ListView product_types_listview;
    @FXML
    private ListView factors_listview;
    @FXML
    private ListView tasks_listview;
    @FXML
    private ListView suppliers_listview;
    @FXML
    private ListView events_listview;
    @FXML
    private ListView employees_listview;
    @FXML
    private ListView assigned_positions_listview;
    @FXML
    private ListView placed_orders_listview;
    
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
                
                loadedData = simLoader.load(chosenFile.getAbsolutePath());
            } catch (Exception e) {
                
                e.printStackTrace();
            }
            
            //If load was successful
            if (loadedData != null) {
                
                //Setup data
                BusifixAppData.SetWorkingData(loadedData);
            }
        }
    }
    
    //Saves the current working simulation data to the chosen file
    public void saveSim() throws IOException{
        
        //Get save location
        FileChooser chooser = new FileChooser();
        File chosenFile = chooser.showSaveDialog(null);
        
        //If valid file was chosen
        if (chosenFile != null) {
        
            //Save working simulation data
            SimSaver simSaver = new SimSaver();
            simSaver.save(BusifixAppData.GetWorkingData(), chosenFile.getAbsolutePath());
        }
    }
    
    //Transitions to the student interface
    public void toStudentMode() {
        
        AnchorPane pane = new AnchorPane();
        
        try {
            
            //Load student mode
            pane = FXMLLoader.load(getClass().getResource("studentFXML.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    //Transitions to the product edit interface
    public void toEditProduct(ActionEvent event) {
        Stage stage;
        Parent root;
        try {
            stage = new Stage();
            //Load product edit mode
            root = FXMLLoader.load(getClass().getResource("editproductFXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //allows for stage to have a pop up effect
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(productBtn.getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    //When the user types in the business name text field
    public void updateBusinessName() {
        
        //Read the updated name from the text box
        String updatedName = business_name_txtfield.getText();
        
        //Update the working simulation data
        BusifixAppData.GetWorkingData().businessName = updatedName;
    }
    
    //When the user types in the business description text field
    public void updateBusinessDescription() {
        
        //Read the updated description from the text area
        String updatedDescription = business_description_txtarea.getText();
        
        //Update the working simulation data
        BusifixAppData.GetWorkingData().businessDescription = updatedDescription;
    }
    
    //When the user types in the balance amount field
    public void updateBalance() {
        
        //Read the updated balance from the text box
        String updatedBalance = balance_txtfield.getText();
        
        try {
            
            //Attempt to parse the input
            double updatedBalanceDecimal = Double.parseDouble(updatedBalance);
            
            //Update the working simulation data
            BusifixAppData.GetWorkingData().balance = updatedBalanceDecimal;
        } catch (Exception e) {
            
            //Retrieve the current balance
            String oldBalance = String.valueOf(BusifixAppData.GetWorkingData().balance);
            
            //Reset the text to the last valid value
            balance_txtfield.setText(oldBalance);
        }
    }
    
    //When the user types in the welcome message text field
    public void updateWelcomeMessage() {
        
        //Read the updated description from the text area
        String updatedMessage = welcome_txtarea.getText();
        
        //Update the working simulation data
        BusifixAppData.GetWorkingData().welcomeMessage = updatedMessage;
    }
    
    //Displays the contents of the simulation data within the available list views
    private void displayListContents() {
        
        //Retrieve working data as displayable arrays
        ArrayList<String> productNames = BusifixAppData.GetProductTypeNames();
        
        //Display working data
        displayInListView(positions_listview, null, "<No positions>");
        displayInListView(inventories_listview, null, "<No inventories>");
        displayInListView(product_types_listview, productNames, "<No product types>");
        displayInListView(factors_listview, null, "<No factors>");
        displayInListView(tasks_listview, null, "<No tasks>");
        displayInListView(suppliers_listview, null, "<No suppliers>");
        displayInListView(events_listview, null, "<No events>");
        displayInListView(employees_listview, null, "<No employees>");
        displayInListView(assigned_positions_listview, null, "<No assigned positions>");
        displayInListView(placed_orders_listview, null, "<No placed orders>");
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Populate list view contents
        displayListContents();
    }    
    
}
