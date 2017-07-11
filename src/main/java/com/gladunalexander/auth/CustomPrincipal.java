package com.gladunalexander.auth;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Alex on 10.07.2017.
 */
public class CustomPrincipal implements Principal, Serializable {

    private static final long serialVersionUID = 6429527622593486174L;
    private final String name;
    private final List<String> roles;

    public CustomPrincipal(String name, List<String> roles) {
        this.name = name;
        this.roles = Optional.ofNullable(roles).orElseGet(ArrayList::new);
    }

    public boolean isUserInRole(final String role) {
        return roles.stream().anyMatch(e -> e.equalsIgnoreCase(role));
    }

    @Override
    public String getName() {
        return name;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "MyCustomPrincipal [name=" + name + ", roles=" + roles + "]";
    }
}
