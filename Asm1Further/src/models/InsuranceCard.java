package models;

import java.util.Date;

/**
 * Class representing an insurance card in the insurance claims management system.
 * @author Your Full Name - Your Student ID
 */
public class InsuranceCard {
    private String cardNumber;
    private Customer cardHolder;
    private PolicyHolder policyOwner;
    private Date expirationDate;

    // Default constructor
    public InsuranceCard() {
        // Initialize any default values if needed
    }

    // Constructor with parameters
    public InsuranceCard(String cardNumber, Customer cardHolder, PolicyHolder policyOwner, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }

    // Getters and Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Customer getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(Customer cardHolder) {
        this.cardHolder = cardHolder;
    }

    public PolicyHolder getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(PolicyHolder policyOwner) {
        this.policyOwner = policyOwner;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Custom toString() method to provide a readable representation of the InsuranceCard object
    @Override
    public String toString() {
        return "InsuranceCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolder=" + cardHolder.getFullName() +
                ", policyOwner=" + policyOwner.getFullName() +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
