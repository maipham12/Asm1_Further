package utils;

import models.Claim;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class FileUtil {

    /**
     * Writes the list of claims to a specified file.
     *
     * @param filePath The path to the file where claims should be written.
     * @param claims The list of claims to be saved.
     */
    public static void saveClaims(String filePath, List<Claim> claims) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
    // Continuation from Part 1 of FileUtil.java

    /**
     * Constructs a string representation of a claim for file storage.
     *
     * @param claim The claim object to be converted into a string.
     * @param dateFormat The date format to use for formatting claim dates.
     * @return A string representing the claim, suitable for file storage.
     */
    private static String buildClaimLine(Claim claim, SimpleDateFormat dateFormat) {
        return claim.getClaimId() + "," +
                dateFormat.format(claim.getClaimDate()) + "," +
                claim.getInsuredPerson().getId() + "," +
                claim.getInsuranceCard().getCardNumber() + "," +
                dateFormat.format(claim.getExamDate()) + "," +
                claim.getClaimAmount() + "," +
                claim.getStatus().toString() + "," +
                claim.getReceiverBankingInfo();
    }
}
