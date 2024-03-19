package app;

import models.Claim;
import models.Customer;
import models.InsuranceCard;
import utils.DataLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for file operations.
 * @author Pham Thanh Mai - s3978365
 */

public class App {

    private static final String CUSTOMERS_FILE_PATH = "C:\\ClaimsManagementSystem\\Asm1Further\\customers.txt";
    private static final String INSURANCE_CARDS_FILE_PATH = "C:\\ClaimsManagementSystem\\Asm1Further\\insuranceCards.txt";
    private static final String CLAIMS_FILE_PATH = "C:\\ClaimsManagementSystem\\Asm1Further\\claims.txt";

    private static List<Customer> customers;
    private static List<InsuranceCard> insuranceCards;
    private static List<Claim> claims;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        try {
            loadInitialData();
            runApplication();
        } catch (Exception e) {
            System.err.println("Error during application startup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadInitialData() throws ParseException {
        try {
            customers = DataLoader.loadCustomers(CUSTOMERS_FILE_PATH);
            insuranceCards = DataLoader.loadInsuranceCards(INSURANCE_CARDS_FILE_PATH, customers);
            claims = DataLoader.loadClaims(CLAIMS_FILE_PATH, customers, insuranceCards);
        } catch (IOException e) {
            System.err.println("Error loading initial data: " + e.getMessage());
        }
    }

    private static void runApplication() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("=== Insurance Claims Management System ===");
            System.out.println("1. View Customers");
            System.out.println("2. View Insurance Cards");
            System.out.println("3. View Claims");
            System.out.println("4. Add New Claim");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewCustomers();
                    break;
                case 2:
                    viewInsuranceCards();
                    break;
                case 3:
                    viewClaims();
                    break;
                case 4:
                    addNewClaim(scanner);
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void viewCustomers() {
        System.out.println("\n=== Customers ===");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void viewInsuranceCards() {
        System.out.println("\n=== Insurance Cards ===");
        for (InsuranceCard card : insuranceCards) {
            System.out.println(card);
        }
    }

    private static void viewClaims() {
        System.out.println("\n=== Claims ===");
        for (Claim claim : claims) {
            System.out.println(claim);
        }
    }

    private static void addNewClaim(Scanner scanner) {
        System.out.println("\n=== Adding a new claim ===");
        try {
            String claimId = generateClaimId();
            System.out.print("Enter claim date (yyyy-mm-dd): ");
            Date claimDate = dateFormat.parse(scanner.nextLine());
            System.out.print("Enter insured person ID: ");
            String insuredPersonId = scanner.nextLine();
            System.out.print("Enter insurance card number: ");
            String cardNumber = scanner.nextLine();
            System.out.print("Enter exam date (yyyy-mm-dd): ");
            Date examDate = dateFormat.parse(scanner.nextLine());
            System.out.print("Enter claim amount: ");
            double claimAmount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter claim status (NEW, PROCESSING, DONE): ");
            String statusStr = scanner.nextLine().toUpperCase();
            Claim.Status status = Claim.Status.valueOf(statusStr);
            System.out.print("Enter receiver banking info: ");
            String receiverBankingInfo = scanner.nextLine();

            Customer insuredPerson = findCustomerById(insuredPersonId);
            InsuranceCard insuranceCard = findInsuranceCardByNumber(cardNumber);

            if (insuredPerson != null && insuranceCard != null) {
                Claim claim = new Claim(claimId, claimDate, insuredPerson, insuranceCard, examDate, claimAmount, status, receiverBankingInfo);
                claims.add(claim);
                saveClaimToFile(claim);
                System.out.println("Claim added successfully.");
            } else {
                System.out.println("Invalid insured person ID or insurance card number.");
            }
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
    }

    private static String generateClaimId() {
        int index = claims.size() + 1;
        return "f-" + String.format("%010d", index);
    }

    private static Customer findCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    private static InsuranceCard findInsuranceCardByNumber(String cardNumber) {
        for (InsuranceCard card : insuranceCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null; // Card not found
    }

    private static void saveClaimToFile(Claim claim) {
        try (FileWriter writer = new FileWriter(CLAIMS_FILE_PATH, true)) {
            writer.write(claim.toString());
            writer.write("\n");
        } catch (IOException e) {
            System.err.println("Error saving claim to file: " + e.getMessage());
        }
    }
}
