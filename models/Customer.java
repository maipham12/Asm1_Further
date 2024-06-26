package models;

/**
 @author <Pham Thanh Mai - s3978365>
 **/

public abstract class Customer {
    protected String id;
    protected String fullName;
    protected InsuranceCard insuranceCard;

    public Customer(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    @Override
    public String toString() {
        // Assuming 'None' is used if there is no insurance card.
        return String.format("d,%s,%s",
                this.getId(),
                this.getFullName());
    }

}