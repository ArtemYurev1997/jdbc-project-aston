package org.aston.jdbc.enums;

public enum Status {
    UNFORMED("Unformed"),
    WAITING_FOR_THE_COURIER("Waiting"),
    ON_THE_WAY("On the way"),
    DONE("Done");

    private String role;

    Status(String role) {
        this.role = role;
    }

    public String getStatus() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }
}
