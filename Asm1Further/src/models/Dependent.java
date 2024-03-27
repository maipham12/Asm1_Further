package models;

/**
 * Represents a dependent of a policy holder in the insurance system.
 */
public class Dependent extends Customer {
    private PolicyHolder policyOwner; // The policy holder to whom the dependent is associated

    /**
     * Constructs a new Dependent instance.
     *
     * @param id The dependent's ID.
     * @param fullName The full name of the dependent.
     * @param policyOwner The policy holder who is the owner of the dependent.
     */
    public Dependent(String id, String fullName, PolicyHolder policyOwner) {
        super(id, fullName);
        this.policyOwner = policyOwner;
    }

    // Getters and Setters

    public PolicyHolder getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(PolicyHolder policyOwner) {
        this.policyOwner = policyOwner;
    }

    @Override
    public String toString() {
        return "Dependent{" +
                "id='" + getId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", policyOwner='" + (policyOwner != null ? policyOwner.getFullName() : "None") +
                ", insuranceCard=" + (getInsuranceCard() != null ? getInsuranceCard().getCardNumber() : "None") +
                '}';
    }
}
