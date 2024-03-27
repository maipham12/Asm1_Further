package services;

import models.Claim;
import models.Customer;
import models.InsuranceCard;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ClaimProcessServiceImpl implements ClaimProcessManager {

    private final ConcurrentHashMap<String, Claim> claims = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Customer> customers = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, InsuranceCard> insuranceCards = new ConcurrentHashMap<>();

    @Override
    public void addCustomer(Customer customer) {
        if (customer != null && customer.getId() != null) {
            customers.put(customer.getId(), customer);
        }
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerId != null ? customers.get(customerId) : null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public void addInsuranceCard(InsuranceCard insuranceCard) {
        if (insuranceCard != null && insuranceCard.getCardNumber() != null) {
            insuranceCards.put(insuranceCard.getCardNumber(), insuranceCard);
        }
    }

    @Override
    public InsuranceCard getInsuranceCardByNumber(String cardNumber) {
        return cardNumber != null ? insuranceCards.get(cardNumber) : null;
    }

    @Override
    public List<InsuranceCard> getAllInsuranceCards() {
        return new ArrayList<>(insuranceCards.values());
    }

    @Override
    public void add(Claim claim) {
        if (claim != null && claim.getClaimId() != null) {
            claims.put(claim.getClaimId(), claim);
        }
    }

    @Override
    public void update(Claim claim) {
        if (claim != null && claim.getClaimId() != null && claims.containsKey(claim.getClaimId())) {
            claims.replace(claim.getClaimId(), claim);
        }
    }

    @Override
    public boolean delete(String claimId) {
        return claimId != null && claims.remove(claimId) != null;
    }

    @Override
    public Claim getOne(String claimId) {
        return claimId != null ? claims.get(claimId) : null;
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claims.values());
    }
}
