package services;

/**
 @author <Pham Thanh Mai - s3978365>
 **/

import models.Claim;
import models.Customer;
import models.InsuranceCard;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ClaimProcessServiceImpl implements ClaimProcessManager {
    private final LinkedHashMap<String, Claim> claims = new LinkedHashMap<>();
    private final LinkedHashMap<String, Customer> customers = new LinkedHashMap<>();
    private final LinkedHashMap<String, InsuranceCard> insuranceCards = new LinkedHashMap<>();

    // Method to add a customer
    @Override
    public void addCustomer(Customer customer) {
        if (customer != null && customer.getId() != null) {
            customers.put(customer.getId(), customer);
            writeCustomersToFile(); // Call the method to write data to file after adding a new customer
        }
    }

    // Method to get a customer by ID
    @Override
    public Customer getCustomerById(String customerId) {
        return customers.get(customerId);
    }

    // Method to get all customers
    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
    // Method to add an insurance card
    @Override
    public void addInsuranceCard(InsuranceCard insuranceCard) {
        if (insuranceCard != null && insuranceCard.getCardNumber() != null) {
            insuranceCards.put(insuranceCard.getCardNumber(), insuranceCard);
            writeInsuranceCardsToFile(); // Call the method to write data to file after adding a new card
        }
    }

    // Method to get an insurance card by number
    @Override
    public InsuranceCard getInsuranceCardByNumber(String cardNumber) {
        return insuranceCards.get(cardNumber);
    }

    // Method to get all insurance cards
    @Override
    public List<InsuranceCard> getAllInsuranceCards() {
        return new ArrayList<>(insuranceCards.values());
    }

    // Method to add a claim
    @Override
    public void add(Claim claim) {
        if (claim != null && claim.getClaimId() != null) {
            claims.put(claim.getClaimId(), claim);
            writeClaimsToFile(); // Call the method to write data to file after adding a new claim
        }
    }

    // Method to update a claim
    @Override
    public void update(Claim claim) {
        if (claim != null && claim.getClaimId() != null && claims.containsKey(claim.getClaimId())) {
            claims.put(claim.getClaimId(), claim);
            writeClaimsToFile(); // Update the file after modifying a claim
        }
    }

    // Method to delete a claim by ID
    @Override
    public boolean delete(String claimId) {
        if (claimId != null && claims.remove(claimId) != null) {
            writeClaimsToFile(); // Update the file after a claim is deleted
            return true;
        }
        return false;
    }

    // Method to get one claim by ID
    @Override
    public Claim getOne(String claimId) {
        return claims.get(claimId);
    }

    // Method to get all claims
    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claims.values());
    }
    // Method to write all customers to a file
    private void writeCustomersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt"))) {
            for (Customer customer : customers.values()) {
                writer.write(customer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to write all insurance cards to a file
    private void writeInsuranceCardsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("insuranceCards.txt"))) {
            for (InsuranceCard card : insuranceCards.values()) {
                writer.write(card.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to write all claims to a file
    private void writeClaimsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("claims.txt"))) {
            for (Claim claim : claims.values()) {
                writer.write(claim.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
