package models;

import java.time.LocalDate;

public class Claim {
    private String claimId; // Unique identifier for the claim
    private LocalDate claimDate; // The date the claim was filed
    private Customer insuredPerson; // The customer the claim is for
    private InsuranceCard insuranceCard; // The insurance card used for the claim
    private LocalDate examDate; // The date of the medical examination
    private double claimAmount; // The amount being claimed
    private Status status; // The current status of the claim
    private String receiverBankingInfo; // Banking info for claim payment

    public enum Status {
        NEW, PROCESSING, DONE
    }

    // Constructor
    public Claim(String claimId, LocalDate claimDate, Customer insuredPerson,
                 InsuranceCard insuranceCard, LocalDate examDate, double claimAmount,
                 Status status, String receiverBankingInfo) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.insuranceCard = insuranceCard;
        this.examDate = examDate;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }

    // Getters and setters for claimId, claimDate, and examDate
    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    // ... continuation of Claim class ...

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
                ", insuredPerson=" + (insuredPerson != null ? insuredPerson.getFullName() : "None") +
                ", insuranceCard=" + (insuranceCard != null ? insuranceCard.getCardNumber() : "None") +
                ", examDate=" + examDate +
                ", claimAmount=" + claimAmount +
                ", status=" + status +
                ", receiverBankingInfo='" + receiverBankingInfo + '\'' +
                '}';
    }
}


