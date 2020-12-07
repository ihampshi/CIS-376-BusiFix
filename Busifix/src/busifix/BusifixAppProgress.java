/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import static busifix.BusifixAppData.workingSimData;
import busifix.simdatatypes.Employee;
import busifix.simdatatypes.ProductType;
import busifix.simdatatypes.SimProgress;
import busifix.simdatatypes.activetypes.ActiveInventory;
import java.util.ArrayList;

/**
 *
 * @author Isaac
 */
public class BusifixAppProgress {
    
    //The simulation progress information
    public static SimProgress simProgress;
    
    //Initializes the simulation progress
    public static void InitializeSimProgress() {
        
        simProgress = new SimProgress();
    }
    
    //Returns whether the simulation progress is initialized
    public static boolean IsSimProgressInitialized() {
        
        return simProgress != null;
    }
    
    //Returns the simulation progress
    public static SimProgress GetSimProgress() {
        
        return simProgress;
    }
    
    //Update the simulation progress and data for the current day
    public static void NextDay() {
        
        //If initialized
        if (IsSimProgressInitialized()) {
            
            // --> Perform all required simulation update algorithms here
            
            
            //Increment the day counter
            simProgress.day += 1;
            
        } else {
            
            System.out.println("Simulation progress not initialized, but application is attempting to advance to the next day.");
        }
    }
    
    //Retrieves the names of the current inventory data
    public static ArrayList<String> GetInventoryNames() {
        
        ArrayList<String> inventoryNames = new ArrayList<String>();
        
        if (simProgress != null) {
            
            ArrayList<ActiveInventory> inventories = simProgress.inventories;
            
            for (int index = 0; index < inventories.size(); index++) {
                
                ActiveInventory current = inventories.get(index);
                
                if (current.source != null && current.source.productType != null) {
                
                    ProductType productType = current.source.productType;
                    String name = productType.name;
                    String size = String.valueOf(current.source.size);
                    String amount = String.valueOf(current.amount);
                    
                    inventoryNames.add("Type: " + name + ", " + amount + "/" + size);
                } else {
                    
                    inventoryNames.add("<Invalid inventory>");
                }
            }
        }
        
        return inventoryNames;
    }
    
    //Retrieves the names of the available employees
    public static ArrayList<String> GetEmployeePoolNames() {
        
        ArrayList<String> employeeNames = new ArrayList<String>();
        
        if (simProgress != null) {
            
            ArrayList<Employee> employees = simProgress.employeePool;
            
            for (int index = 0; index < employees.size(); index++) {
                
                String name = employees.get(index).name;
                
                if (name.length() == 0) {
                
                    employeeNames.add("<Unnamed Employee>");
                } else {
                    
                    employeeNames.add(name);
                }
            }
        }
        
        return employeeNames;
    }
    
    //Retrieves the names of the hired employees
    public static ArrayList<String> GetHiredEmployeeNames() {
        
        ArrayList<String> employeeNames = new ArrayList<String>();
        
        if (simProgress != null) {
            
            ArrayList<Employee> employees = simProgress.hiredEmployees;
            
            for (int index = 0; index < employees.size(); index++) {
                
                String name = employees.get(index).name;
                
                if (name.length() == 0) {
                
                    employeeNames.add("<Unnamed Employee>");
                } else {
                    
                    employeeNames.add(name);
                }
            }
        }
        
        return employeeNames;
    }
    
    //Retrieves the employees that aren't hired
    public static ArrayList<Employee> GetUnhiredEmployees() {
        
        ArrayList<Employee> employees = simProgress.employeePool;
        ArrayList<Employee> hiredEmployees = simProgress.hiredEmployees;
        ArrayList<Employee> unhiredEmployees = new ArrayList();

        for (int index = 0; index < employees.size(); index++) {

            if (!hiredEmployees.contains(employees.get(index))) {

                unhiredEmployees.add(employees.get(index));
            }
        }
        
        return unhiredEmployees;
    }
    
    //Retrieves the names of the employees that aren't hired
    public static ArrayList<String> GetUnhiredEmployeeNames() {
        
        ArrayList<String> employeeNames = new ArrayList<String>();
        
        if (simProgress != null) {
            
            ArrayList<Employee> unhiredEmployees = GetUnhiredEmployees();
            
            for (int index = 0; index < unhiredEmployees.size(); index++) {
                
                String name = unhiredEmployees.get(index).name;
                
                if (name.length() == 0) {
                
                    employeeNames.add("<Unnamed Employee>");
                } else {
                    
                    employeeNames.add(name);
                }
            }
        }
        
        return employeeNames;
    }
}
