package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.Warehouse;
import com.example.User;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        User user = new User(firstName, lastName);

        // Assuming Warehouse is a class responsible for managing users
        // Save the user in the Warehouse or perform relevant operations
        Warehouse instance = Warehouse.getInstance();
        instance.addUser(user);

        // Set user attribute to be used in the add.jsp page
        request.setAttribute("user", firstName + " " + lastName);

        // Forward back to the "add" page
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }
    //write your code here!
}