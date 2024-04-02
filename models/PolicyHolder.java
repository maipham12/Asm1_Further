package models;

import java.util.ArrayList;
import java.util.List;
public class PolicyHolder extends Customer {
    private List<Dependent> dependents;
    public PolicyHolder(String id, String fullName) {
        super(id, fullName);
        this.dependents = new ArrayList<>();
    }

    public void addDependent(Dependent dependent) {
        this.dependents.add(dependent);
    }

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