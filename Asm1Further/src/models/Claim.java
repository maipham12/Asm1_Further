package models;

import java.util.Date;

/**
 * Utility class for file operations.
 * @author Pham Thanh Mai - s3978365
 */

public class Claim {

    private String claimId;
    private Date claimDate;
    private Customer insuredPerson;
    private InsuranceCard insuranceCard;
    private Date examDate;
    private double claimAmount;
    private Status status;
    private String receiverBankingInfo;

    public enum Status {
        NEW, PROCESSING, DONE
    }

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
                ", insuredPerson=" + insuredPerson +
                ", insuranceCard=" + insuranceCard +
                ", examDate=" + examDate +
                ", claimAmount=" + claimAmount +
                ", status=" + status +
                ", receiverBankingInfo='" + receiverBankingInfo + '\'' +
                '}';
    }
}
