package com.example.demo.domain;

import static com.example.demo.domain.AppUserPermission.USER_READ;
import static com.example.demo.domain.AppUserPermission.USER_WRITE;

import com.google.common.collect.Sets;

import java.util.Set;

public enum AppUserRole {

    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE)),
    USER(Sets.newHashSet(USER_READ));

    private final Set<AppUserPermission> permissions;


    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

}
