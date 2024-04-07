package models;

/**
 @author <Pham Thanh Mai - s3978365>
 **/

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
        // Start with the policyholder's info
        StringBuilder sb = new StringBuilder(String.format("p,%s,%s", this.getId(), this.getFullName()));
        // Add dependent IDs
        for (Dependent dependent : this.getDependents()) {
            sb.append(",").append(dependent.getId());
        }
        return sb.toString();
    }

}