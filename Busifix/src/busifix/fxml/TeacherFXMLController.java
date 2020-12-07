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
import busifix.simdatatypes.*;
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
    private ListView hired_listview;
    @FXML
    private ListView placed_orders_listview;
    
    //Add/remove buttons
    @FXML
    private Button add_position_btn;
    @FXML
    private Button remove_position_btn;
    @FXML
    private Button add_inventory_btn;
    @FXML
    private Button remove_inventory_btn;
    @FXML
    private Button add_product_btn;
    @FXML
    private Button remove_product_btn;
    @FXML
    private Button add_factor_btn;
    @FXML
    private Button remove_factor_btn;
    @FXML
    private Button add_task_btn;
    @FXML
    private Button remove_task_btn;
    @FXML
    private Button add_supplier_btn;
    @FXML
    private Button remove_supplier_btn;
    @FXML
    private Button add_event_btn;
    @FXML
    private Button remove_event_btn;
    @FXML
    private Button add_employee_btn;
    @FXML
    private Button remove_employee_btn;
    @FXML
    private Button add_hired_btn;
    @FXML
    private Button remove_hired_btn;
    @FXML
    private Button add_place_order_btn;
    @FXML
    private Button remove_place_order_btn;
    
    //Combo boxes
    @FXML
    private ComboBox hired_combo;
    @FXML
    private ComboBox order_combo;
    
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
                
                //Update display
                displayListContents();
                
                //Display text
                setTextContents();
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
    
    //When an add/remove button is pressed
    public void addRemoveItem(ActionEvent event) {
        
        //Get the source button
        Button source = (Button)event.getSource();
        
        //Positions
        testAddRemovePair(source, add_position_btn, remove_position_btn, BusifixAppData.GetWorkingData().positions, positions_listview, new Position());
        
        //Inventories
        testAddRemovePair(source, add_inventory_btn, remove_inventory_btn, BusifixAppData.GetWorkingData().inventories, inventories_listview, new Inventory());
        
        //Product types
        testAddRemovePair(source, add_product_btn, remove_product_btn, BusifixAppData.GetWorkingData().products, product_types_listview, new ProductType());
        
        //Factors
        testAddRemovePair(source, add_factor_btn, remove_factor_btn, BusifixAppData.GetWorkingData().factors, factors_listview, new Factor());
        
        //Tasks
        testAddRemovePair(source, add_task_btn, remove_task_btn, BusifixAppData.GetWorkingData().tasks, tasks_listview, new Task());
        
        //Product suppliers
        testAddRemovePair(source, add_supplier_btn, remove_supplier_btn, BusifixAppData.GetWorkingData().productSuppliers, suppliers_listview, new ProductSupplier());
        
        //Random events
        testAddRemovePair(source, add_event_btn, remove_event_btn, BusifixAppData.GetWorkingData().randomEvents, events_listview, new RandomEvent());
        
        //Employee pool
        testAddRemovePair(source, add_employee_btn, remove_employee_btn, BusifixAppData.GetWorkingData().employeePool, employees_listview, new Employee());
        
        //Hired employees
        hireFireEmployee(source);

        //Placed orders
        placeCancelOrder(source);
        
        displayListContents();
    }
    
    //Tests whether the given add/remove button pair were pressed
    private void testAddRemovePair(Button source, Button addBtn, Button removeBtn, ArrayList list, ListView selector, Object newItem) {
        
        //Add button pressed
        if (source.equals(addBtn)) {
            
            list.add(newItem);
        }
        
        //Remove button pressed
        else if (source.equals(removeBtn)) {
            
            int selectedIndex = selector.getSelectionModel().getSelectedIndex();
            
            //If entry selected and items exist in list
            if (selectedIndex > -1 && list.size() > 0) {
                
                //Remove selected item
                list.remove(selectedIndex);
            }
        }
    }
    
    //Tests whether the given button adds/removes a hired employee
    private void hireFireEmployee(Button source) {
        
        ArrayList<Employee> employeePool = BusifixAppData.GetWorkingData().employeePool;
        ArrayList<Employee> hiredEmployees = BusifixAppData.GetWorkingData().hiredEmployees;
        
        //Add button pressed
        if (source.equals(add_hired_btn)) {
            
            //Get selected index
            int selectedIndex = hired_combo.getSelectionModel().getSelectedIndex();
            
            //If entry selected and items exist in list
            if (selectedIndex > -1 && employeePool.size() > 0) {
                
                //Get selected employee
                ArrayList<Employee> unhiredEmployees = BusifixAppData.GetUnhiredEmployees();
                Employee selectedEmployee = unhiredEmployees.get(selectedIndex);
                
                //If employee is not already hired
                if (!hiredEmployees.contains(selectedEmployee)) {
                                        
                    //Hire employee
                    hiredEmployees.add(selectedEmployee);
                }
            }
        }
        
        //Remove button pressed
        if (source.equals(remove_hired_btn)) {
            
            //Get selected index
            int selectedIndex = hired_listview.getSelectionModel().getSelectedIndex();
            
            //If entry selected and items exist in list
            if (selectedIndex > -1 && hiredEmployees.size() > 0) {
                
                //Get selected employee
                Employee selectedEmployee = hiredEmployees.get(selectedIndex);
                
                //Remove employee
                hiredEmployees.remove(selectedEmployee);
            }
        }
    }
    
    //Tests whether the given button places/cancels an order
    private void placeCancelOrder(Button source) {
        
        //Add button pressed
        if (source.equals(add_place_order_btn)) {
            
            ArrayList<Order> ordersAvailable = BusifixAppData.GetOrdersAvailable();
            
            //Get selected index
            int selectedIndex = order_combo.getSelectionModel().getSelectedIndex();
            
            //If entry selected and items exist in list
            if (selectedIndex > -1 && ordersAvailable.size() > 0) {
                
                //Get selected order
                Order selectedOrder = ordersAvailable.get(selectedIndex);
                
                //Place order
                PlacedOrder newPlacedOrder = new PlacedOrder(selectedOrder);
                BusifixAppData.GetWorkingData().placedOrders.add(newPlacedOrder);
            }
        }
        
        //Remove button pressed
        if (source.equals(remove_place_order_btn)) {
            
            ArrayList<PlacedOrder> placedOrders = BusifixAppData.GetWorkingData().placedOrders;
            
            //Get selected index
            int selectedIndex = placed_orders_listview.getSelectionModel().getSelectedIndex();
            
            //If entry selected and items exist in list
            if (selectedIndex > -1 && placedOrders.size() > 0) {
                
                //Get selected order
                PlacedOrder selectedOrder = placedOrders.get(selectedIndex);
                
                //Remove employee
                placedOrders.remove(selectedOrder);
            }
        }
    }
    
    
    //Displays the contents of the simulation data within the available list views
    private void displayListContents() {
        
        //Retrieve working data as displayable arrays
        ArrayList<String> positionNames = BusifixAppData.GetPositionNames();
        ArrayList<String> inventoryNames = BusifixAppData.GetInventoryNames();
        ArrayList<String> productNames = BusifixAppData.GetProductTypeNames();
        ArrayList<String> factorNames = BusifixAppData.GetFactorNames();
        ArrayList<String> taskNames = BusifixAppData.GetTaskNames();
        ArrayList<String> supplierNames = BusifixAppData.GetSupplierNames();
        ArrayList<String> eventNames = BusifixAppData.GetEventNames();
        ArrayList<String> employeeNames = BusifixAppData.GetEmployeePoolNames();
        ArrayList<String> hireNames = BusifixAppData.GetHiredEmployeeNames();
        ArrayList<String> placedOrderNames = BusifixAppData.GetPlacedOrderNames();
        
        ArrayList<String> unhiredNames = BusifixAppData.GetUnhiredEmployeeNames();
        ArrayList<String> orderNames = BusifixAppData.GetOrderNames();
        
        //Display working data
        displayInListView(positions_listview, positionNames, "<No positions>");
        displayInListView(inventories_listview, inventoryNames, "<No inventories>");
        displayInListView(product_types_listview, productNames, "<No product types>");
        displayInListView(factors_listview, factorNames, "<No factors>");
        displayInListView(tasks_listview, taskNames, "<No tasks>");
        displayInListView(suppliers_listview, supplierNames, "<No suppliers>");
        displayInListView(events_listview, eventNames, "<No events>");
        displayInListView(employees_listview, employeeNames, "<No employees>");
        displayInListView(hired_listview, hireNames, "<No hired employees>");
        displayInListView(placed_orders_listview, placedOrderNames, "<No placed orders>");
        
        //Display hireable employees
        displayInComboBox(hired_combo, unhiredNames, "<No hireable employees>");
        
        //Display placeable orders
        displayInComboBox(order_combo, orderNames, "<No placeable orders>");
    }
    
    //Displays the contents of the given list in the given list view
    //Displays empty message if the given list is empty
    public void displayInListView(ListView listView, ArrayList<String> items, String emptyMessage) {
        
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
    
    //Displays the contents of the given list in the given combo box
    //Displays empty message if the given list is empty
    private void displayInComboBox(ComboBox comboBox, ArrayList<String> items, String emptyMessage) {
        
        //Clear existing items
        comboBox.getItems().clear();
        
        //If the given list is invalid or empty
        if (items == null || items.size() == 0) {
            
            //Display empty message
            comboBox.getItems().add(emptyMessage);
        } else {
            
            //Display the given items
            for (int index = 0; index < items.size(); index++) {
                
                comboBox.getItems().add(items.get(index));
            }
        }
    }
    
    
    private void setTextContents() {
        
        business_name_txtfield.setText(BusifixAppData.GetWorkingData().businessName);
        business_description_txtarea.setText(BusifixAppData.GetWorkingData().businessDescription);
        
        balance_txtfield.setText(String.valueOf(BusifixAppData.GetWorkingData().balance));
        welcome_txtarea.setText(BusifixAppData.GetWorkingData().welcomeMessage);
    }  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Populate list view contents
        displayListContents();
        
        //Load text components
        setTextContents();
    }    
    
}
