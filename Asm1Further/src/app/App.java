package app;

import models.*;
import services.ClaimProcessManager;
import services.ClaimProcessServiceImpl;
import utils.DataLoader;
import utils.FileUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final ClaimProcessManager claimProcessManager = new ClaimProcessServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        loadInitialData();
        runApplication();
//        System.out.println(LocalDate.parse("2023-03-03"));
    }

    private static void loadInitialData() {
        // Pass the claimProcessManager instance to each method to ensure they work correctly
        DataLoader.loadCustomers(claimProcessManager); // Adjusted call
        DataLoader.loadInsuranceCards(claimProcessManager); // Adjusted call
        DataLoader.loadClaims(claimProcessManager); // Adjusted call
        System.out.println("Data loaded successfully.");
    }

    private static void runApplication() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Insurance Claims Management System ===");
            System.out.println("1. View Claims");
            System.out.println("2. Add New Claim");
            System.out.println("3. Update a Claim");
            System.out.println("4. Delete a Claim");
            System.out.println("5. Get One Claim");
            System.out.println("6. Save & Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    viewClaims();
                    break;
                case 2:
                    addNewClaim();
                    break;
                case 3:
                    updateClaim();
                    break;
                case 4:
                    deleteClaim();
                    break;
                case 5:
                    getOneClaim();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
        // Adjusted the file path to be more general, not fixed to a specific OS directory structure
        FileUtil.saveClaims("claims.txt", claimProcessManager.getAll());
        System.out.println("Changes saved. Exiting application...");
    }

    private static void getOneClaim() {
    }

    private static void deleteClaim() {
    }

    private static void viewClaims() {
        List<Claim> claims = claimProcessManager.getAll();
        for (Claim claim : claims) {
            System.out.println(claim);
        }
    }

    private static void addNewClaim() {
        System.out.println("Adding a new claim...");

        System.out.print("Enter Claim ID (format f-xxxxxxxxxx): ");
        String claimId = scanner.nextLine();

        System.out.print("Enter Claim Date (yyyy-MM-dd): ");
        Date claimDate = parseDate(scanner.nextLine());

        System.out.print("Enter Insured Person's Customer ID: ");
        String customerId = scanner.nextLine();
        Customer insuredPerson = claimProcessManager.getCustomerById(customerId);

        System.out.print("Enter Card Number: ");
        String cardNumber = scanner.nextLine();
        InsuranceCard insuranceCard = claimProcessManager.getInsuranceCardByNumber(cardNumber);

        System.out.print("Enter Exam Date (yyyy-MM-dd): ");
        Date examDate = parseDate(scanner.nextLine());

        System.out.print("Enter Claim Amount: ");
        double claimAmount = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Claim Status (NEW, PROCESSING, DONE): ");
        Claim.Status status = Claim.Status.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter Receiver Banking Info: ");
        String bankingInfo = scanner.nextLine();

        Claim newClaim = new Claim(claimId, claimDate, insuredPerson, insuranceCard, examDate, claimAmount, status, bankingInfo);
        claimProcessManager.add(newClaim);
        System.out.println("Claim added successfully.");
    }

    private static Date parseDate(String date) {
        return new Date(date);
        // tìm cách để parse "2023-03-03" thành Date
    }


    private static void updateClaim() {
        System.out.print("Enter the ID of the claim you want to update: ");
        String claimId = scanner.nextLine();
        Claim claim = claimProcessManager.getOne(claimId);
        if (claim == null) {
            System.out.println("Claim not found.");
            return;
        }

        System.out.println("Existing claim details: " + claim);
        System.out.println("Enter new details for the claim (leave blank to keep current value):");

        System.out.print("New Claim Date (yyyy-MM-dd): ");
        String newDateStr = scanner.nextLine();
        if (!newDateStr.isEmpty()) {
            Date newDate = parseDate(newDateStr);
            if (newDate != null) {
                claim.setClaimDate(newDate);
            }
        }

        System.out.print("New Claim Amount: ");
        String newAmountStr = scanner.nextLine();
        if (!newAmountStr.isEmpty()) {
            double newAmount = Double.parseDouble(newAmountStr);
            claim.setClaimAmount(newAmount);
        }

        System.out.print("New Claim Status (NEW, PROCESSING, DONE): ");
        String newStatusStr = scanner.nextLine();
        if (!newStatusStr.isEmpty()) {
            claim.setStatus(Claim.Status.valueOf(newStatusStr.toUpperCase()));
        }

        System.out.print("New Receiver Banking Info: ");
        String newBankingInfo = scanner.nextLine();
        if (!newBankingInfo.isEmpty()) {
            claim.setReceiverBankingInfo(newBankingInfo);
        }

        claimProcessManager.update(claim);
        System.out.println("Claim updated successfully.");
    }

}