package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a policy holder in the insurance system. A policy holder can have dependents.
 */
public class PolicyHolder extends Customer {
    private List<Dependent> dependents; // List of dependents associated with the policy holder

    /**
     * Constructs a new PolicyHolder instance.
     *
     * @param id The policy holder's ID.
     * @param fullName The full name of the policy holder.
     */
    public PolicyHolder(String id, String fullName) {
        super(id, fullName);
        this.dependents = new ArrayList<>();
    }

    // Adds a dependent to the policy holder
    public void addDependent(Dependent dependent) {
        this.dependents.add(dependent);
    }

    // Getters and Setters

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    @Override
    public String toString() {
        return "PolicyHolder{" +
                "id='" + getId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + (getInsuranceCard() != null ? getInsuranceCard().getCardNumber() : "None") +
                ", dependents=" + dependents.size() +
                '}';
    }
}
