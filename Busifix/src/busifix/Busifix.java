/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class Busifix extends Application{


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        //first scene
        Parent root = FXMLLoader.load(getClass().getResource("BusifixFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Busifix");
        stage.setScene(scene);
        stage.show();
    }
}
