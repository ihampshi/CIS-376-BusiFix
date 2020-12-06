/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.simdatatypes;

import java.util.ArrayList;

/**
 *
 * @author Isaac
 */
public class Position {
    
    //The name of the position
    public String name;
    
    //The tasks owned (automatically performed) by employees in this position
    public ArrayList<Task> tasks;
    
    public Position() {
        
        name = "";
        tasks = new ArrayList();
    }
}
