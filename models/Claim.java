package models;

/**
 @author <Pham Thanh Mai - s3978365>
 **/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Claim {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String claimId;
    private LocalDate claimDate;
    private Customer insuredPerson;
    private InsuranceCard insuranceCard;
    private LocalDate examDate;
    private double claimAmount;
    private Status status;
    private String receiverBankingInfo;

    public enum Status {
        NEW, PROCESSING, DONE
    }

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
        return claimId + "," +
                claimDate.format(formatter) + "," +
                (insuredPerson != null ? insuredPerson.getId() : "null") + "," +
                (insuranceCard != null ? insuranceCard.getCardNumber() : "null") + "," +
                examDate.format(formatter) + "," +
                claimAmount + "," +
                status + "," +
                receiverBankingInfo;
    }
}

