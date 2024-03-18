package utils;

import models.Claim;
import models.Customer;
import models.InsuranceCard;
import models.PolicyHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Utility class to load data from text files into the application.
 */
public class DataLoader {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Loads customers from a text file.
     *
     * @param filePath The path to the customers text file.
     * @return A list of Customer objects.
     * @throws FileNotFoundException if the file is not found.
     * @throws ParseException        if an error occurs during parsing dates.
     */
    public static List<Customer> loadCustomers(String filePath) throws FileNotFoundException, ParseException {
        List<Customer> customers = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String customerId = parts[0];
                    String fullName = parts[1];
                    Customer customer = new PolicyHolder(customerId, fullName);
                    customers.add(customer);
                }
            }
        }
        return customers;
    }

    /**
     * Loads insurance cards from a text file and associates them with customers.
     *
     * @param filePath  The path to the insurance cards text file.
     * @param customers The list of customers to associate with the insurance cards.
     * @return A list of InsuranceCard objects.
     * @throws FileNotFoundException if the file is not found.
     * @throws ParseException        if an error occurs during parsing dates.
     */
    public static List<InsuranceCard> loadInsuranceCards(String filePath, List<Customer> customers) throws FileNotFoundException, ParseException {
        List<InsuranceCard> cards = new ArrayList<>();
        File file = new File(filePath);
        Map<String, Customer> customerMap = new HashMap<>();
        for (Customer customer : customers) {
            customerMap.put(customer.getId(), customer);
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String cardNumber = parts[0];
                    String cardHolderId = parts[1];
                    String policyOwnerId = parts[2];
                    Date expirationDate = dateFormat.parse(parts[3]);

                    Customer cardHolder = customerMap.get(cardHolderId);
                    Customer policyOwner = customerMap.get(policyOwnerId);
                    if (cardHolder != null && policyOwner != null && policyOwner instanceof PolicyHolder) {
                        InsuranceCard card = new InsuranceCard(cardNumber, cardHolder, (PolicyHolder) policyOwner, expirationDate);
                        cards.add(card);
                        cardHolder.setInsuranceCard(card); // Ensure insurance card is set for the card holder
                    }
                }
            }
        }
        return cards;
    }

    /**
     * Loads claims from a text file.
     *
     * @param filePath       The path to the claims text file.
     * @param customers      The list of customers.
     * @param insuranceCards The list of insurance cards.
     * @return A list of Claim objects.
     * @throws FileNotFoundException if the file is not found.
     * @throws ParseException        if an error occurs during parsing dates.
     */
    public static List<Claim> loadClaims(String filePath, List<Customer> customers, List<InsuranceCard> insuranceCards) throws FileNotFoundException, ParseException {
        List<Claim> claims = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String claimId = parts[0];
                    Date claimDate = dateFormat.parse(parts[1]);
                    String insuredPersonId = parts[2];
                    String cardNumber = parts[3];
                    Date examDate = dateFormat.parse(parts[4]);
                    double claimAmount = Double.parseDouble(parts[5]);
                    Claim.Status status = Claim.Status.valueOf(parts[6]);
                    String receiverBankingInfo = parts[7];

                    // Find the customer associated with the claim
                    Customer insuredPerson = findCustomerById(customers, insuredPersonId);

                    // Find the insurance card associated with the claim
                    InsuranceCard insuranceCard = findInsuranceCardByNumber(insuranceCards, cardNumber);

                    // Create the claim if both insured person and insurance card are found
                    if (insuredPerson != null && insuranceCard != null) {
                        Claim claim = new Claim(claimId, claimDate, insuredPerson, insuranceCard, examDate, claimAmount, status, receiverBankingInfo);
                        claims.add(claim);
                    }
                }
            }
        }
        return claims;
    }

    // Helper method to find a customer by ID
    private static Customer findCustomerById(List<Customer> customers, String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    // Helper method to find an insurance card by card number
    private static InsuranceCard findInsuranceCardByNumber(List<InsuranceCard> cards, String cardNumber) {
        for (InsuranceCard card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null; // Card not found
    }
}
