package services;

import models.Claim;
import java.util.List;

/**
 * Utility class for file operations.
 * @author Pham Thanh Mai - s3978365
 */
public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(String claimId);
    Claim getOne(String claimId);
    List<Claim> getAll();
}
