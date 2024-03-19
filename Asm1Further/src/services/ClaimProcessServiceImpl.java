package services;

import models.Claim;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for file operations.
 * @author Pham Thanh Mai - s3978365
 */
public class ClaimProcessServiceImpl implements ClaimProcessManager {
    private List<Claim> claims;

    public ClaimProcessServiceImpl() {
        this.claims = new ArrayList<>();
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void update(Claim claim) {
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).getClaimId().equals(claim.getClaimId())) {
                claims.set(i, claim);
                return;
            }
        }
    }

    @Override
    public void delete(String claimId) {
        claims.removeIf(claim -> claim.getClaimId().equals(claimId));
    }

    @Override
    public Claim getOne(String claimId) {
        for (Claim claim : claims) {
            if (claim.getClaimId().equals(claimId)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return claims;
    }
}
