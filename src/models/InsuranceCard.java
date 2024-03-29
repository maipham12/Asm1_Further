package models;

import java.time.LocalDate;

public class InsuranceCard {
    private String cardNumber; // Unique identifier for the insurance card
    private Customer cardHolder; // The customer who holds this card
    private String policyOwner; // The policy holder associated with this card
    private LocalDate expirationDate; // The expiration date of the insurance card

    // Constructor
    public InsuranceCard(String cardNumber, Customer cardHolder, String policyOwner, LocalDate expirationDate) {
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

    public String getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "InsuranceCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolder=" + (cardHolder != null ? cardHolder.getFullName() : "None") +
                ", policyOwner='" + policyOwner + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
