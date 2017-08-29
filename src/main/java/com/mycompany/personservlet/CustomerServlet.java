/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MAC
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Customer customer = createCustomer(request);
        doRequest(customer, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        Customer customer = createCustomer(request);
        doRequest(customer, resp);
    }

    private Customer createCustomer(HttpServletRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getParameter("username"));
        customer.setPassword(request.getParameter("password"));
        String gender = request.getParameter("gender");
        if (gender.equals("male")) {
            customer.setGender(GENDER.MALE);
        } else {
            customer.setGender(GENDER.FEMALE);
        }

        customer.setHobby(request.getParameterValues("hobby"));

        return customer;
    }

    //metoda na podstawie danych uzytkownika w obiekcie response
    //ma wygenerowac strone html na ktora uzytkownik zostanie przekierowany
    private void doRequest(Customer customer, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body>");
        pw.println("<h2>Odebrane dane pomyslnie</h2>");
        pw.println("<div>");
        pw.println(customer.getName());
        pw.println("</br>");
        pw.println(customer.getGender());
        pw.println("</br>");
        pw.println("Hobby: ");
        pw.println("</br>");
        String[] hobby = customer.getHobby();
        if (hobby != null) {
            for (String s : hobby) {
                pw.println(s);
                pw.println("</br>");
            }
        }
        pw.println("</div>");
        pw.println("</body>");
        pw.println("</html>");

    }
}
