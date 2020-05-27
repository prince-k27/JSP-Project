package com.project.java;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        employeeDAO = new EmployeeDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertEmployee(request, response);
                break;
            case "/delete":
                deleteEmployee(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateEmployee(request, response);
                break;
            default:
                listEmployee(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listEmployee = employeeDAO.listAllEmployees();
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int eid = Integer.parseInt(request.getParameter("eid"));
        Employee existingEmployee = employeeDAO.getEmployee(eid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);
 
    }
 
    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
       
        String ename = request.getParameter("ename");
        String emailid = request.getParameter("emailid");
        long phone = Long.parseLong(request.getParameter("phone"));
        String status = request.getParameter("status");
 
        Employee newEmployee = new Employee(ename, emailid, phone, status);
        employeeDAO.insertEmployee(newEmployee);
        response.sendRedirect("list");
    }
 
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int eid = Integer.parseInt(request.getParameter("eid"));
        String ename = request.getParameter("ename");
        String emailid = request.getParameter("emailid");
        long phone = Long.parseLong(request.getParameter("phone"));
        String status = request.getParameter("status");
 
        Employee employee = new Employee(eid, ename,emailid, phone, status);
        employeeDAO.updateEmployee(employee);
        response.sendRedirect("list");
    }
 
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int eid = Integer.parseInt(request.getParameter("eid"));
 
        Employee employee = new Employee(eid);
        employeeDAO.deleteEmployee(employee);
        response.sendRedirect("list");
 
    }
    
    
}

