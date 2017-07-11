package com.gladunalexander.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;
import java.util.Optional;

/**
 * Created by Alex on 10.07.2017.
 */
public class AuthRequestWrapper extends HttpServletRequestWrapper {

    private final HttpServletRequest realRequest;
    private final CustomPrincipal principal;


    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public AuthRequestWrapper(HttpServletRequest request) {
        super(request);
        this.realRequest = request;
        this.principal = Optional.ofNullable(request.getSession(false))
                .filter(e -> e.getAttribute("user") != null)
                .map(session -> (CustomPrincipal)session.getAttribute("user"))
                .orElse(new CustomPrincipal("guest", null));
    }

    @Override
    public String getRemoteUser() {
        if (principal == null) {
            return realRequest.getRemoteUser();
        }
        return principal.getName();
    }

    @Override
    public Principal getUserPrincipal() {
        if (principal == null) {
            return realRequest.getUserPrincipal();
        }
        return principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (principal == null) {
            return realRequest.isUserInRole(role);
        }
        return principal.isUserInRole(role);
    }
}
