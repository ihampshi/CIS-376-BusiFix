/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import busifix.simdatatypes.*;
import java.util.ArrayList;

/**
 *
 * @author Isaac
 */
public class BusifixAppData {
    
    //The current working simulation data used by the application
    static SimData workingSimData;
    
    //Returns whether the working simulation data has been initialized
    public static boolean IsWorkingDataInitialized() {
        
        return workingSimData != null;
    }  
    
    //Initializes simulation data
    public static void InitializeData() {
        
        //Initialize working simulation data
        workingSimData = new SimData();
    }
    
    //Sets the simulation data reference (used when loading)
    public static void SetWorkingData(SimData data) {
        
        workingSimData = data;
    }
    
    //Retrieves the working simulation data
    public static SimData GetWorkingData() {
        
        return workingSimData;
    }
    
    //Retrieves the names of the positions
    public static ArrayList<String> GetPositionNames() {
        
        ArrayList<String> positionNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<Position> positions = workingSimData.positions;
            
            for (int index = 0; index < positions.size(); index++) {
                
                String name = positions.get(index).name;
                
                if (name.length() == 0) {
                
                    positionNames.add("<Unnamed Position>");
                } else {
                    
                    positionNames.add(name);
                }
            }
        }
        
        return positionNames;
    }
    
    //Retrieves the names of the inventories
    public static ArrayList<String> GetInventoryNames() {
        
        ArrayList<String> inventoryNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<Inventory> inventories = workingSimData.inventories;
            
            for (int index = 0; index < inventories.size(); index++) {
                
                int size = inventories.get(index).size;
                ProductType product = inventories.get(index).productType;
                
                String message = "Contains: ";
                
                if (product != null) {
                    
                    message += product.name;
                } else {
                    
                    message += "<unassigned>";
                }
                
                message += ", Size: " + size;
                
                inventoryNames.add(message);
            }
        }
        
        return inventoryNames;
    }
    
    //Retrieves the names of the product types
    public static ArrayList<String> GetProductTypeNames() {
        
        ArrayList<String> productNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<ProductType> products = workingSimData.products;
            
            for (int index = 0; index < products.size(); index++) {
                
                String name = products.get(index).name;
                
                if (name.length() == 0) {
                
                    productNames.add("<Unnamed Product Type>");
                } else {
                    
                    productNames.add(name);
                }
            }
        }
        
        return productNames;
    }
    
    //Retrieves the names of the factors
    public static ArrayList<String> GetFactorNames() {
        
        ArrayList<String> factorNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<Factor> factors = workingSimData.factors;
            
            for (int index = 0; index < factors.size(); index++) {
                
                String name = factors.get(index).name;
                String mode = factors.get(index).mode.name();
                
                if (name.length() == 0) {
                
                    factorNames.add("<Unnamed Factor>");
                } else {
                    
                    factorNames.add(name + ", Mode: " + mode);
                }
            }
        }
        
        return factorNames;
    }
    
    //Retrieves the names of the tasks
    public static ArrayList<String> GetTaskNames() {
        
        ArrayList<String> taskNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<Task> tasks = workingSimData.tasks;
            
            for (int index = 0; index < tasks.size(); index++) {
                
                String name = tasks.get(index).name;
                
                if (name.length() == 0) {
                
                    taskNames.add("<Unnamed Task>");
                } else {
                    
                    taskNames.add(name);
                }
            }
        }
        
        return taskNames;
    }
    
    //Retrieves the names of the product suppliers
    public static ArrayList<String> GetSupplierNames() {
        
        ArrayList<String> supplierNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<ProductSupplier> suppliers = workingSimData.productSuppliers;
            
            for (int index = 0; index < suppliers.size(); index++) {
                
                String name = suppliers.get(index).name;
                int numOrders = suppliers.get(index).ordersOffered.size();
                
                String message = "Contains: ";
                
                if (name.length() > 0) {
                    
                    message += name;
                } else {
                    
                    message += "<unassigned>";
                }
                
                message += ", Orders Offered: " + numOrders;
                
                supplierNames.add(message);
            }
        }
        
        return supplierNames;
    }
    
    //Retrieves the names of the events
    public static ArrayList<String> GetEventNames() {
        
        ArrayList<String> eventNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<RandomEvent> events = workingSimData.randomEvents;
            
            for (int index = 0; index < events.size(); index++) {
                
                String name = events.get(index).name;
                String probability = String.valueOf(events.get(index).probability);
                
                if (name.length() == 0) {
                
                    eventNames.add("<Unnamed Random Event>");
                } else {
                    
                    eventNames.add(name + ", Probability: " + probability);
                }
            }
        }
        
        return eventNames;
    }
    
    //Retrieves the names of the employees in the employee pool
    public static ArrayList<String> GetEmployeePoolNames() {
        
        ArrayList<String> employeeNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<Employee> employees = workingSimData.employeePool;
            
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
        
        if (workingSimData != null) {
            
            ArrayList<Employee> employees = workingSimData.hiredEmployees;
            
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
    
    //Retrieves the names of the placed orders
    public static ArrayList<String> GetPlacedOrderNames() {
        
        ArrayList<String> orderNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<PlacedOrder> orders = workingSimData.placedOrders;
            
            for (int index = 0; index < orders.size(); index++) {
                
                ProductType productType = orders.get(index).source.productType;
                double cost = orders.get(index).source.cost;
                int size = orders.get(index).source.quantity;
                int daysRemaining = orders.get(index).daysRemaining;
                
                String message = "$" + cost;
                message += ", Product: ";
                
                if (productType != null) {
                    
                    if (productType.name.length() > 0) {
                    
                        message += productType.name;
                    } else {
                        
                        message += "<Unnamed Product Type>";
                    }
                    
                } else {
                    
                    message += "<unassigned>";
                }
                
                message += ", Size: " + size;
                message += ", Days Remaining: " + daysRemaining;
                
                orderNames.add(message);
            }
        }
        
        return orderNames;
    }
    
    //Retrieves the employees that aren't hired
    public static ArrayList<Employee> GetUnhiredEmployees() {
        
        ArrayList<Employee> employees = workingSimData.employeePool;
        ArrayList<Employee> hiredEmployees = workingSimData.hiredEmployees;
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
        
        if (workingSimData != null) {
            
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
    
    //Retrieves the orders available to be placed
    public static ArrayList<Order> GetOrdersAvailable() {
        
        ArrayList<ProductSupplier> productSuppliers = workingSimData.productSuppliers;
        ArrayList<Order> orders = new ArrayList();

        //For each product supplier
        for (int index = 0; index < productSuppliers.size(); index++) {

            ProductSupplier currentSupplier = productSuppliers.get(index);
            
            //For each order offered
            for (int orderIndex = 0; orderIndex < currentSupplier.ordersOffered.size(); orderIndex++) {
                
                orders.add(currentSupplier.ordersOffered.get(orderIndex));
            }
        }
        
        return orders;
    }
    
    //Retrieves the names of all available orders
    public static ArrayList<String> GetOrderNames() {
        
        ArrayList<String> orderNames = new ArrayList<String>();
        
        if (workingSimData != null) {
            
            ArrayList<Order> orders = GetOrdersAvailable();
            
            for (int index = 0; index < orders.size(); index++) {
                
                ProductType productType = orders.get(index).productType;
                double cost = orders.get(index).cost;
                int size = orders.get(index).quantity;
                int distance = orders.get(index).distance;
                
                String message = "$" + cost;
                message += ", Prdt: ";
                
                if (productType != null) {
                    
                    if (productType.name.length() > 0) {
                    
                        message += productType.name;
                    } else {
                        
                        message += "<Unnamed Product Type>";
                    }
                    
                } else {
                    
                    message += "<unassigned>";
                }
                
                message += ", Siz: " + size;
                message += ", Wait: " + distance + " days";
                
                orderNames.add(message);
            }
        }
        
        return orderNames;
    }
}
