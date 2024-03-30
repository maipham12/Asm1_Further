package utils;

import models.*;
import services.ClaimProcessManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataLoader {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
                        // Assuming the PolicyHolder has dependents listed starting from index 3
                        for (int i = 3; i < data.length; i++) {
                            Dependent dependent = (Dependent) claimProcessManager.getCustomerById(data[i]);
                            if (dependent != null) {
                                policyHolder.addDependent(dependent);
                            }
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
                    String cardNumber = data[0];
                    Customer cardHolder = claimProcessManager.getCustomerById(data[1]);
                    String policyOwner = data[2];
                    LocalDate expirationDate = LocalDate.parse(data[3], DATE_FORMAT);
                    if (cardHolder != null && policyOwner != null) {
                        InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate);
                        claimProcessManager.addInsuranceCard(insuranceCard);
                        System.out.println("Loaded Insurance Card: " + cardNumber);
                    }
                }
            } catch (IOException e) {
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
                    String claimId = data[0];
                    LocalDate claimDate = LocalDate.parse(data[1], DATE_FORMAT);
                    Customer insuredPerson = claimProcessManager.getCustomerById(data[2]);
                    InsuranceCard insuranceCard = claimProcessManager.getInsuranceCardByNumber(data[3]);
                    LocalDate examDate = LocalDate.parse(data[4], DATE_FORMAT);
                    double claimAmount = Double.parseDouble(data[5]);
                    Claim.Status status = Claim.Status.valueOf(data[6]);
                    String receiverBankingInfo = data[7];

                    if (insuredPerson != null && insuranceCard != null) {
                        Claim claim = new Claim(claimId, claimDate, insuredPerson, insuranceCard, examDate, claimAmount, status, receiverBankingInfo);
                        claimProcessManager.add(claim);
                        System.out.println("Loaded Claim: " + claimId);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
