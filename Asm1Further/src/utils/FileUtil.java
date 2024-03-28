package utils;

import models.Claim;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileUtil {

    /**
     * Writes the list of claims to a specified file.
     *
     * @param filePath The path to the file where claims should be written.
     * @param claims   The list of claims to be saved.
     */
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
    // ... continuation of FileUtil class ...

    /**
     * Constructs a string representation of a claim for file storage.
     *
     * @param claim      The claim object to be converted into a string.
     * @param dateFormat The date format to use for formatting claim dates.
     * @return A string representing the claim, suitable for file storage.
     */
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


// Continuation in the next part...
