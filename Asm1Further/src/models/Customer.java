package models;

/**
 * Abstract class representing a customer in the insurance claims management system.
 * @author Your Full Name - Your Student ID
 */
public abstract class Customer {
    protected String id;
    protected String fullName;
    protected InsuranceCard insuranceCard;

    // Default constructor
    public Customer() {
        // Initialize any default values if needed
    }

    // Constructor with parameters
    public Customer(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Setter for fullName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Getter for fullName
    public String getFullName() {
        return fullName;
    }

    // Getter for insuranceCard
    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    // Setter for insuranceCard
    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    // Custom toString() method to provide a readable representation of the Customer object
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", insuranceCard=" + insuranceCard +
                '}';
    }
}
