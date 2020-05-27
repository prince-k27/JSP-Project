package com.project.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

public class EmployeeDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public EmployeeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (ename, emailid, contact, status) VALUES (?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, employee.getEname());
        statement.setString(2, employee.getEmailid());
        statement.setLong(3, employee.getContact());
        statement.setString(4, employee.getStatus());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Employee> listAllEmployees() throws SQLException {
        List<Employee> listEmployee = new ArrayList<>();
         
        String sql = "SELECT * FROM employee";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int eid = resultSet.getInt("employee_eid");
            String ename = resultSet.getString("ename");
            String emailid = resultSet.getString("emailid");
            Long contact = resultSet.getLong("contact");
            String status = resultSet.getString("status");
             
            Employee employee = new Employee(eid, ename, emailid, contact, status);
            listEmployee.add(employee);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listEmployee;
    }
     
    public boolean deleteEmployee(Employee employee) throws SQLException {
        String sql = "DELETE FROM employee where employee_eid = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, employee.getEid());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET ename = ?, emailid = ? , contact = ? , status = ?";
        sql += " WHERE employee_eid = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, employee.getEname());
        statement.setString(2, employee.getEmailid());
        statement.setLong(3, employee.getContact());
        statement.setString(4, employee.getStatus());
        statement.setInt(5, employee.getEid());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Employee getEmployee(int eid) throws SQLException {
    	Employee employee = null;
        String sql = "SELECT * FROM employee WHERE employee_eid = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, eid);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String ename = resultSet.getString("ename");
            String emailid = resultSet.getString("emailid");
            long phone = resultSet.getLong("phone");
            String status = resultSet.getString("status");
            
             
            employee = new Employee(eid, ename, emailid, phone, status);
        }
         
        resultSet.close();
        statement.close();
         
        return employee;
    }
}