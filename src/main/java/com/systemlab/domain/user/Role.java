package com.systemlab.domain.user;

public enum Role {
    ADMIN("admin"),
    SUPERVISOR("supervisor");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
