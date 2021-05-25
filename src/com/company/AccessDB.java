package com.company;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class AccessDB {
    static final String DB_URL = "jdbc:mysql://localhost/JDBCproject?serverTimezone=Europe/Rome";
    static final String USER = "root";
    static final String PASS = "aaaa";
    static final String QUERY = "SELECT * FROM Employees";


    public static void showAll(String table) {
        String query = "SELECT * FROM " + table;
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);) {

            switch (table) {
                case "medics":
                    System.out.println("medics");
                    while (rs.next()) {
                        System.out.print(" ID: " + rs.getInt("id"));
                        System.out.print(", Age: " + rs.getInt("age"));
                        System.out.print(", First: " + rs.getString("first"));
                        System.out.print(", Last: " + rs.getString("last"));
                        System.out.print(", Location: " + rs.getString("location"));
                        System.out.print(", Salary: " + rs.getInt("salary"));
                        System.out.println(", Specialization: " + rs.getString("specialization"));
                    }
                    System.out.println();
                    break;
                case "clients":
                    System.out.println("clients");
                    while (rs.next()) {
                        System.out.print(" ID: " + rs.getInt("id"));
                        System.out.print(", Age: " + rs.getInt("age"));
                        System.out.print(", First: " + rs.getString("first"));
                        System.out.print(", Last: " + rs.getString("last"));
                        System.out.println(", Insurance_id: " + rs.getInt("insurance_id"));
                    }
                    System.out.println();
                    break;
                case "insurances":
                    System.out.println("insurances");
                    while (rs.next()) {
                        System.out.print(" ID: " + rs.getInt("id"));
                        System.out.print(", Title: " + rs.getString("title"));
                        System.out.print(", Percentage reduction: " + rs.getInt("percentageReduction"));
                        System.out.println(", Flat reduction: " + rs.getInt("flatReduction"));
                    }
                    System.out.println();
                    break;
                case "appointments":
                    System.out.println("appointments");
                    while (rs.next()) {
                        System.out.print(" ID: " + rs.getInt("id"));
                        System.out.print(" Medic id: " + rs.getInt("medic_id"));
                        System.out.print(" Client id: " + rs.getInt("client_id"));
                        System.out.println(", Date: " + rs.getDate("date"));
                    }
                    System.out.println();

                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void Create(String[] values, String table){
        String query = "";
        switch (table) {
            case "medics":
                query = "INSERT INTO medics VALUES("+Integer.parseInt(values[0])+","+Integer.parseInt(values[1])+", ";
                query = query + "'" + values[2] +"', '" + values[3] +"', '" + values[4] +"', " + Integer.parseInt(values[5]) + ", ";
                query = query + "'" + values[6] +"'" + ")";
                break;
            case "clients":
                query = "INSERT INTO clients VALUES("+Integer.parseInt(values[0])+", ";
                query = query + "'" + values[2] +"', '" + values[3] +"', " + Integer.parseInt(values[1]) + ", " + Integer.parseInt(values[4])+ ")";
                break;
            case "insurances":
                query = "INSERT INTO insurances VALUES("+Integer.parseInt(values[0])+", ";
                query = query + "'" + values[1] +"', " + Integer.parseInt(values[2]) + ", " + Integer.parseInt(values[3])+ ")";
                break;
            case "appointments":
                query = "INSERT INTO appointments VALUES("+Integer.parseInt(values[0])+", ";
                query = query + Integer.parseInt(values[1]) +", " + Integer.parseInt(values[2]) + ", '" + values[3]+ "')";
                break;
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ) {

            stmt.executeUpdate(query);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Delete(String id, String table){
        String query = "DELETE FROM ";
        query = query + table + " WHERE id =" + Integer.parseInt(id);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {

            stmt.executeUpdate(query);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void giveRaise(String id){
        String query = "UPDATE medics SET salary = salary+200 WHERE id = " + id;
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        // Test
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}