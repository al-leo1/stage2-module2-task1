package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import com.example.User;
import com.example.Warehouse;

@WebServlet("/users")
public class GetUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Ensure Warehouse.getInstance() is not null
            Warehouse warehouse = Warehouse.getInstance();
            if (warehouse != null) {
                Set<User> users = warehouse.getUsers();

                if (users != null) {
                    req.setAttribute("users", users);
                } else {
                    // Handle the case where users is null
                    req.setAttribute("errorMessage", "Unable to retrieve user data.");
                }
            } else {
                // Handle the case where warehouse is null
                req.setAttribute("errorMessage", "Warehouse is not initialized.");
            }
        } catch (Exception e) {
            // Log or handle the exception appropriately
            req.setAttribute("errorMessage", "An error occurred while processing your request.");
        }

        // Forward to the appropriate JSP page
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}
