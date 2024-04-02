package app;

/**
 @author <Pham Thanh Mai - s3978365>
 **/

import models.Claim;
import models.Customer;
import models.InsuranceCard;
import services.ClaimProcessManager;
import services.ClaimProcessServiceImpl;
import utils.DataLoader;
import utils.FileUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final ClaimProcessManager claimProcessManager = new ClaimProcessServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        loadInitialData();
        runApplication();
    }

    private static void loadInitialData() {
        DataLoader.loadCustomers(claimProcessManager);
        DataLoader.loadInsuranceCards(claimProcessManager);
        DataLoader.loadClaims(claimProcessManager);
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
            scanner.nextLine();

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
        FileUtil.saveClaims("claims.txt", claimProcessManager.getAll());
        System.out.println("Changes saved. Exiting application...");
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
        LocalDate claimDate = LocalDate.parse(scanner.nextLine(), dateFormat);

        System.out.print("Enter Insured Person's Customer ID: ");
        String customerId = scanner.nextLine();
        Customer insuredPerson = claimProcessManager.getCustomerById(customerId);

        System.out.print("Enter Card Number: ");
        String cardNumber = scanner.nextLine();
        InsuranceCard insuranceCard = claimProcessManager.getInsuranceCardByNumber(cardNumber);

        System.out.print("Enter Exam Date (yyyy-MM-dd): ");
        LocalDate examDate = LocalDate.parse(scanner.nextLine(), dateFormat);

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

    private static void updateClaim() {
        System.out.print("Enter the ID of the claim you want to update: ");
        String claimId = scanner.nextLine();
        Claim claimToUpdate = claimProcessManager.getOne(claimId);

        if (claimToUpdate != null) {
            System.out.print("Enter new Claim Date (yyyy-MM-dd) or press enter to skip: ");
            String newClaimDateStr = scanner.nextLine();
            if (!newClaimDateStr.isEmpty()) {
                LocalDate newClaimDate = LocalDate.parse(newClaimDateStr, dateFormat);
                claimToUpdate.setClaimDate(newClaimDate);
            }
            claimProcessManager.update(claimToUpdate);
            System.out.println("Claim updated successfully.");
        } else {
            System.out.println("Claim with ID " + claimId + " not found.");
        }
    }

    private static void deleteClaim() {
        System.out.print("Enter the ID of the claim you want to delete: ");
        String claimId = scanner.nextLine();
        boolean isDeleted = claimProcessManager.delete(claimId);

        if (isDeleted) {
            System.out.println("Claim deleted successfully.");
        } else {
            System.out.println("Claim with ID " + claimId + " not found or could not be deleted.");
        }
    }

    private static void getOneClaim() {
        System.out.print("Enter the ID of the claim you want to retrieve: ");
        String claimId = scanner.nextLine();
        Claim claim = claimProcessManager.getOne(claimId);

        if (claim != null) {
            System.out.println("Claim details: " + claim);
        } else {
            System.out.println("Claim with ID " + claimId + " not found.");
        }
    }
}
