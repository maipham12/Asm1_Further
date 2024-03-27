package utils;

import models.*;
import services.ClaimProcessManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataLoader {

    // This method reads customer data from a file and adds customers to the system
    public static void loadCustomers(ClaimProcessManager claimProcessManager) {
        InputStream customerStream = DataLoader.class.getClassLoader().getResourceAsStream("customers.txt");

        if (customerStream == null) {
            throw new IllegalArgumentException("File not found: customers.txt");
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(customerStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    Customer customer;
                    if (data[0].startsWith("p-")) {
                        customer = new PolicyHolder(data[0], data[1]);
                        claimProcessManager.addCustomer(customer);
                    } else if (data[0].startsWith("d-")) {
                        PolicyHolder policyHolder = (PolicyHolder) claimProcessManager.getCustomerById(data[2]);
                        if(policyHolder != null) {
                            Dependent dependent = new Dependent(data[0], data[1], policyHolder);
                            claimProcessManager.addCustomer(dependent);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // This method reads insurance card data from a file and adds insurance cards to the system
    public static void loadInsuranceCards(ClaimProcessManager claimProcessManager) {
        InputStream cardStream = DataLoader.class.getClassLoader().getResourceAsStream("insuranceCards.txt");

        if (cardStream == null) {
            throw new IllegalArgumentException("File not found: insuranceCards.txt");
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(cardStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    // Assume data format is correct and complete
                    String cardNumber = data[0];
                    Customer cardHolder = claimProcessManager.getCustomerById(data[1]);
                    PolicyHolder policyOwner = (PolicyHolder) claimProcessManager.getCustomerById(data[2]);
                    Date expirationDate = DATE_FORMAT.parse(data[3]);
                    if (cardHolder != null && policyOwner != null) {
                        InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate);
                        claimProcessManager.addInsuranceCard(insuranceCard);
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
    // This method reads claim data from a file and adds claims to the system
    public static void loadClaims(ClaimProcessManager claimProcessManager) {
        InputStream claimStream = DataLoader.class.getClassLoader().getResourceAsStream("claims.txt");

        if (claimStream == null) {
            throw new IllegalArgumentException("File not found: claims.txt");
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(claimStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    // Assume data format is correct and complete
                    String claimId = data[0];
                    Date claimDate = DATE_FORMAT.parse(data[1]);
                    Customer insuredPerson = claimProcessManager.getCustomerById(data[2]);
                    InsuranceCard insuranceCard = claimProcessManager.getInsuranceCardByNumber(data[3]);
                    Date examDate = DATE_FORMAT.parse(data[4]);
                    double claimAmount = Double.parseDouble(data[5]);
                    Claim.Status status = Claim.Status.valueOf(data[6]);
                    String receiverBankingInfo = data[7];
                    if (insuredPerson != null && insuranceCard != null) {
                        Claim claim = new Claim(claimId, claimDate, insuredPerson, insuranceCard, examDate, claimAmount, status, receiverBankingInfo);
                        claimProcessManager.add(claim);
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
}
