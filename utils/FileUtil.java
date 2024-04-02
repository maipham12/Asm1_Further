package utils;

import models.Claim;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileUtil {

    public static void saveClaims(String filePath, List<Claim> claims) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Claim claim : claims) {
                String line = buildClaimLine(claim, dateFormat);
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Claims have been successfully saved to " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to " + filePath + ": " + e.getMessage());
        }
    }

    private static String buildClaimLine(Claim claim, DateTimeFormatter dateFormat) {
        return claim.getClaimId() + "," +
                claim.getClaimDate().format(dateFormat) + "," +
                claim.getInsuredPerson().getId() + "," +
                claim.getInsuranceCard().getCardNumber() + "," +
                claim.getExamDate().format(dateFormat) + "," +
                claim.getClaimAmount() + "," +
                claim.getStatus().toString() + "," +
                claim.getReceiverBankingInfo();
    }
}

