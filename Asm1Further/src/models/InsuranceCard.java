package models;

import java.util.Date;

/**
 * Represents an insurance card in the insurance system.
 */
public class InsuranceCard {
    private String cardNumber; // Unique identifier for the insurance card, consisting of 10 digits
    private Customer cardHolder; // The customer who holds this card
    private PolicyHolder policyOwner; // The policy holder associated with this card
    private Date expirationDate; // The expiration date of the insurance card

    /**
     * Constructs a new InsuranceCard instance.
     *
     * @param cardNumber The card number.
     * @param cardHolder The customer holding the card.
     * @param policyOwner The policy holder of the card.
     * @param expirationDate The expiration date of the card.
     */
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

    @Override
    public String toString() {
        return "InsuranceCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + (cardHolder != null ? cardHolder.getFullName() : "None") + '\'' +
                ", policyOwner='" + (policyOwner != null ? policyOwner.getFullName() : "None") + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
