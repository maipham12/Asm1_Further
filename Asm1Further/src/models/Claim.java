package models;

import java.util.Date;

/**
 * Represents an insurance claim in the insurance system.
 */
public class Claim {
    private String claimId; // Unique identifier for the claim, with format f-numbers; 10 numbers
    private Date claimDate; // The date the claim was filed
    private Customer insuredPerson; // The customer (policy holder or dependent) the claim is for
    private InsuranceCard insuranceCard; // The insurance card used for the claim
    private Date examDate; // The date of the medical examination or event the claim is for
    private double claimAmount; // The amount being claimed
    private Status status; // The current status of the claim (NEW, PROCESSING, DONE)
    private String receiverBankingInfo; // Banking information for where the claim amount should be sent

    /**
     * Enumeration for the possible statuses of a claim.
     */
    public enum Status {
        NEW, PROCESSING, DONE
    }

    /**
     * Constructs a new Claim instance.
     *
     * @param claimId The unique identifier for the claim.
     * @param claimDate The date the claim was filed.
     * @param insuredPerson The customer the claim is for.
     * @param insuranceCard The insurance card used for the claim.
     * @param examDate The date of the examination/event.
     * @param claimAmount The amount being claimed.
     * @param status The current status of the claim.
     * @param receiverBankingInfo The banking information for the claim payout.
     */
    public Claim(String claimId, Date claimDate, Customer insuredPerson, InsuranceCard insuranceCard,
                 Date examDate, double claimAmount, Status status, String receiverBankingInfo) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.insuranceCard = insuranceCard;
        this.examDate = examDate;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }

    // Getters and Setters

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    public void setReceiverBankingInfo(String receiverBankingInfo) {
        this.receiverBankingInfo = receiverBankingInfo;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimId='" + claimId + '\'' +
                ", claimDate=" + claimDate +
                ", insuredPerson=" + insuredPerson.getFullName() +
                ", insuranceCard=" + insuranceCard.getCardNumber() +
                ", examDate=" + examDate +
                ", claimAmount=" + claimAmount +
                ", status=" + status +
                ", receiverBankingInfo='" + receiverBankingInfo + '\'' +
                '}';
    }
}
