/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shado
 */
public class EditproductFXMLController implements Initializable {
        
    @FXML
    private Button save;
    
    @FXML
    private TextField factor_textfield;

    @FXML
    private TextField productType_textfield;

    @FXML
    private ComboBox selectProduct;
    
    @FXML
    private TextField salesMean;
   
    @FXML
    private TextField saleDeviation;
    
    @FXML
    private ListView factor_listView;
    
    @FXML
    private TeacherFXMLController teacher;
    
    //adds the products to the selectProduct comboBox  
    @FXML
    private void addProduct(ActionEvent event) {
        String product = productType_textfield.getText();
        selectProduct.getItems().add(product);
        productType_textfield.clear();
    }

    //removes the selected product from the comboBox
    //throws error if there is no elements to delete
    @FXML
    private void removeProduct(ActionEvent event) {
        try {
            int index = selectProduct.getSelectionModel().getSelectedIndex();
            selectProduct.getItems().remove(index);
        } catch (Exception e) {
        }
    }
    
    //Saves the data and closes the window
    @FXML
    private void saveEdit(ActionEvent event) throws IOException {
        //temporary list for storing the products
        ArrayList<String> items = new ArrayList<String>();
        
        //adds the product items
        for (int index = 0; index < selectProduct.getItems().size(); index++) {
            if(index ==0) selectProduct.getItems().remove(index);
            items.add(selectProduct.getItems().get(index).toString());
        }

        //closes window
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> list = FXCollections.observableArrayList(); 
       //sets all the comboboxes to an empty list
       selectProduct.setItems(list);
       //factor_listView.setItems(list);
    }    

}
