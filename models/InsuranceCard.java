package models;

/**
 @author <Pham Thanh Mai - s3978365>
 **/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InsuranceCard {
    private String cardNumber;
    private Customer cardHolder;
    private String policyOwner;
    private LocalDate expirationDate;

    public InsuranceCard(String cardNumber, Customer cardHolder, String policyOwner, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }

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
        // Assuming 'None' is used if there is no card holder.
        return String.format("%s,%s,%s,%s",
                this.getCardNumber(),
                this.getCardHolder() != null ? this.getCardHolder().getId() : "None",
                this.getPolicyOwner(),
                this.getExpirationDate().format(DateTimeFormatter.ISO_LOCAL_DATE)); // or use DATE_FORMAT if defined elsewhere
    }


}
