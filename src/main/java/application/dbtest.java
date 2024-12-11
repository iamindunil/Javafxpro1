package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class dbtest {
    public static void main(String[] args){
        /*Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username - ");
        String username = scanner.nextLine();
        System.out.print("Enter the password - ");
        String pass = scanner.nextLine();
        String connectionUrl;
        connectionUrl = "jdbc:sqlserver://DESKTOP-HRP200M:1433;database=test;trustServerCertificate=true";
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }*/
        String connectionUrl = "jdbc:sqlserver://DESKTOP-HRP200M:1433;database=test;trustServerCertificate=true;integratedSecurity=true";

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
