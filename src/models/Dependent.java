package models;

/**
 * Represents a dependent of a policy holder in the insurance system.
 */
public class Dependent extends Customer {

    /**
     * Constructs a new Dependent instance.
     *
     * @param id The dependent's ID.
     * @param fullName The full name of the dependent.
     */
    public Dependent(String id, String fullName) {
        super(id, fullName);
    }

    // Getters and Setters

}