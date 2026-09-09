package com.example.terguun.user;

import java.io.Serializable;
import java.util.Objects;

public class UserId implements Serializable {

    private String branchId;
    private String userId;

    public UserId() {
    }

    public UserId(String branchId, String userId) {
        this.branchId = branchId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;
        UserId that = (UserId) o;
        return Objects.equals(branchId, that.branchId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, userId);
    }
}
