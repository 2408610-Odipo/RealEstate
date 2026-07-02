package realestate;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Module: Property Catalog Management
 * Developer: Musa Odipo 2408610
 * System: Real Estate Management System
 */

abstract class Property {
    // Private fields protect data integrity
    private String propertyId;
    private String location;
    private double price;
    private String tenantName; 

    public Property(String id, String loc, double price, String tenantName) {
        this.propertyId = id;
        this.location = loc;
        setPrice(price); 
        this.tenantName = tenantName;
    }

    // Setter with validation
    public void setPrice(double newPrice) {
        if (newPrice > 0) {
            this.price = newPrice;
        } else {
            System.out.println("Error: Rent cannot be zero or negative.");
        }
    }

    public void displayDetails() {
        System.out.println("ID: " + propertyId + " | Location: " + location + 
                           " | Rent: Ksh " + price + " | Tenant: " + tenantName);
    }
}


class ResidentialProperty extends Property {
    private int bedrooms;

    public ResidentialProperty(String id, String loc, double price, String tenantName, int bedrooms) {
        super(id, loc, price, tenantName);
        this.bedrooms = bedrooms;
    }

    // Overriding the method to add residential-specific details
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("   -> Property Type: Residential (" + bedrooms + " Bedrooms)");
    }
}

class CommercialProperty extends Property {
    private String businessType;

    public CommercialProperty(String id, String loc, double price, String tenantName, String businessType) {
        super(id, loc, price, tenantName);
        this.businessType = businessType;
    }

    @Override
    public void displayDetails() {
        super.displayDetails(); 
        System.out.println("   -> Property Type: Commercial (" + businessType + " Space)");
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Property> propertyDatabase = new ArrayList<>();

        propertyDatabase.add(new ResidentialProperty("RES-102", "Milimani", 45000, "Alice O.", 2));
        propertyDatabase.add(new CommercialProperty("COM-204", "CBD", 80000, "TechHub Ltd", "Office"));

        boolean isRunning = true;

        System.out.println("=== REAL ESTATE CATALOG MODULE ===");

        while (isRunning) {
            System.out.println("\n1. View All Properties");
            System.out.println("2. Add New Residential Property");
            System.out.println("3. Exit System");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\n--- PROPERTY LISTINGS ---");
                    for (Property p : propertyDatabase) {

                        p.displayDetails(); 
                        System.out.println("-------------------------");
                    }
                    break;
                case 2:
                    System.out.print("Enter Property ID (e.g., RES-105): ");
                    String id = scanner.nextLine();
                    
                    System.out.print("Enter Location (e.g., Riat Hills): ");
                    String loc = scanner.nextLine();
                    
                    System.out.print("Enter Monthly Rent (Ksh): ");
                    double rent = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Enter Tenant Name (or 'Vacant' if empty): ");
                    String tenant = scanner.nextLine();
                    
                    System.out.print("Enter Number of Bedrooms: ");
                    int beds = scanner.nextInt();
                    
                    ResidentialProperty newProp = new ResidentialProperty(id, loc, rent, tenant, beds);
                    propertyDatabase.add(newProp);
                    
                    System.out.println("\n[Success] Property Added to Database!");
                    break;
                case 3:
                    System.out.println("Exiting Catalog Module...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}