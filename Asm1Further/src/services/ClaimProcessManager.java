package services;

import models.Claim;
import models.Customer;
import models.InsuranceCard;
import java.util.List;

public interface ClaimProcessManager {
    // ... existing methods ...

    void addCustomer(Customer customer);

    Customer getCustomerById(String customerId);

    List<Customer> getAllCustomers();

    void addInsuranceCard(InsuranceCard insuranceCard);

    InsuranceCard getInsuranceCardByNumber(String cardNumber);

    void add(Claim claim);

    void update(Claim claim);
    boolean delete(String claimId);
    Claim getOne(String claimId);
    List<Claim> getAll();

    List<InsuranceCard> getAllInsuranceCards();
    // Any other methods needed for your implementation
}
