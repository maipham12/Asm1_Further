package services;

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

    @Override
    public void addCustomer(Customer customer) {
        if (customer != null && customer.getId() != null) {
            customers.put(customer.getId(), customer);
            writeCustomersToFile();
        }
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customers.get(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public void addInsuranceCard(InsuranceCard insuranceCard) {
        if (insuranceCard != null && insuranceCard.getCardNumber() != null) {
            insuranceCards.put(insuranceCard.getCardNumber(), insuranceCard);
            writeInsuranceCardsToFile();
        }
    }

    @Override
    public InsuranceCard getInsuranceCardByNumber(String cardNumber) {
        return insuranceCards.get(cardNumber);
    }

    @Override
    public List<InsuranceCard> getAllInsuranceCards() {
        return new ArrayList<>(insuranceCards.values());
    }

    @Override
    public void add(Claim claim) {
        if (claim != null && claim.getClaimId() != null) {
            claims.put(claim.getClaimId(), claim);
            writeClaimsToFile();
        }
    }

    @Override
    public void update(Claim updatedClaim) {
        if (updatedClaim != null && updatedClaim.getClaimId() != null) {
            Claim existingClaim = claims.get(updatedClaim.getClaimId());
            if (existingClaim != null) {
                existingClaim.setClaimDate(updatedClaim.getClaimDate());
                existingClaim.setStatus(updatedClaim.getStatus());
                // Consider updating other fields as necessary

                claims.put(existingClaim.getClaimId(), existingClaim);
                writeClaimsToFile();
            }
        }
    }

    @Override
    public boolean delete(String claimId) {
        if (claimId != null && claims.remove(claimId) != null) {
            writeClaimsToFile();
            return true;
        }
        return false;
    }

    @Override
    public Claim getOne(String claimId) {
        return claims.get(claimId);
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claims.values());
    }

    private void writeCustomersToFile() {
        // Implement file writing logic for customers
    }

    private void writeInsuranceCardsToFile() {
        // Implement file writing logic for insurance cards
    }

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
