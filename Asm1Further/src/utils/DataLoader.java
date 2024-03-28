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
    public static void loadCustomers(ClaimProcessManager claimProcessManager) {
        InputStream customerStream = DataLoader.class.getClassLoader().getResourceAsStream("customers.txt");

        if (customerStream == null) {
            throw new IllegalArgumentException("File not found: customers.txt");
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(customerStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");

                    String type = data[0];
                    String id = data[1];
                    String name = data[2];

                    if (type.equals("d")) {
                        Dependent dependent = new Dependent(id, name);
                        claimProcessManager.addCustomer(dependent);
                        System.out.println("Created: " + dependent.getId());
                    } else {
                        PolicyHolder policyHolder = new PolicyHolder(id, name);
                        for (int i = 2; i < data.length; i++) {
                            policyHolder.addDependent((Dependent) claimProcessManager.getCustomerById(data[i]));
                        }
                        claimProcessManager.addCustomer(policyHolder);
                        System.out.println("Created: " + policyHolder.getId());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
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
                    String policyOwner = data[2];
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
                    System.out.println("Founded: " + claimId);
                    System.out.println("Founded: " + claimDate);
                    System.out.println("Founded: " + insuredPerson.getId());
                    System.out.println("Founded: " + insuranceCard.getCardNumber());
                    System.out.println("Founded: " + examDate);
                    System.out.println("Founded: " + claimAmount);
                    System.out.println("Founded: " + status);
                    System.out.println("Founded: " + receiverBankingInfo);
                    if (insuredPerson != null && insuranceCard != null) {
                        Claim claim = new Claim(claimId, claimDate, insuredPerson, insuranceCard, examDate, claimAmount, status, receiverBankingInfo);
                        System.out.println("claim created!");
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