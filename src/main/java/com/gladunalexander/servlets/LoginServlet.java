package com.gladunalexander.servlets;

import com.gladunalexander.auth.CustomPrincipal;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Alex on 10.07.2017.
 */
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
        }

        if (username.equals("admin") && password.equals("admin")){
            request.getSession().setAttribute("user", new CustomPrincipal(username, Arrays.asList("admin", "manager")));
            request.getRequestDispatcher("listUser.jsp").forward(request, response);
        }else {
            try {
                throw new LoginException("You are anouthorized");
            } catch (LoginException e) {
                e.printStackTrace();
            }finally {
                response.sendRedirect(request.getContextPath() + "/");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
