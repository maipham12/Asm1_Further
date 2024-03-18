package services;

import models.Claim;
import java.util.List;

/**
 * Interface for managing insurance claims.
 * @author Your Full Name - Your Student ID
 */
public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(String claimId);
    Claim getOne(String claimId);
    List<Claim> getAll();
}
