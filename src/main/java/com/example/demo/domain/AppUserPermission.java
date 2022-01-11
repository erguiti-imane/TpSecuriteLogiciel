package com.example.demo.domain;

public enum AppUserPermission {
    USER_READ("user:read"), USER_WRITE("user:write");

    private final String permision;

    AppUserPermission(String permision) {
        this.permision = permision;
    }
}
