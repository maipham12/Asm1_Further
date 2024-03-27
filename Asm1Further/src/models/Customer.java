package models;

/**
 * Abstract class representing a customer in the insurance system.
 * This class provides a common foundation for different types of customers.
 */
public abstract class Customer {
    protected String id; // Customer ID with format c-numbers; 7 numbers
    protected String fullName; // Full name of the customer
    protected InsuranceCard insuranceCard; // Associated insurance card

    /**
     * Constructs a new Customer instance.
     *
     * @param id The customer's ID.
     * @param fullName The full name of the customer.
     */
    public Customer(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    // Getters and Setters

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

    /**
     * Provides a string representation of the customer.
     *
     * @return A string describing the customer.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", insuranceCard=" + (insuranceCard != null ? insuranceCard.getCardNumber() : "None") +
                '}';
    }
}
