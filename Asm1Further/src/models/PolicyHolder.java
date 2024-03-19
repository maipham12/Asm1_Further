package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for file operations.
 * @author Pham Thanh Mai - s3978365
 */
public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    public PolicyHolder(String id, String fullName) {
        super(id, fullName);
        this.dependents = new ArrayList<>();
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    // Additional constructors, getters, and setters as needed
}
