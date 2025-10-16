package br.com.energycontrol.EnergyControl.model;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String user_role;

    UserRole(String role) {
        this.user_role = role;
    }

    public String getUser_role() {
        return this.user_role;
    }
}
