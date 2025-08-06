package com.tca;

import com.tca.config.DatabaseConfigurations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddStudent {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

            //Register Driver
            Class.forName(DatabaseConfigurations.DB_DRIVER);

            //Form Connection
            connection = DriverManager.getConnection(DatabaseConfigurations.DB_URL, DatabaseConfigurations.DB_USER, DatabaseConfigurations.DB_PWD);

            //Prepare SQL
            ps = connection.prepareStatement("INSERT INTO EMPLOYEE VALUES(?,?,?)");

            int choice;

            while(true){
                int id;
                String name;
                double salary;

                System.out.print("\nEnter '1' to insert new record : ");

                try{
                    choice = Integer.parseInt(br.readLine());
                }catch (NumberFormatException ne){
                    break;
                }

                if(choice != 1){
                    break;
                }

                try{
                    System.out.print("Enter the id     : ");
                    id = Integer.parseInt(br.readLine());

                    System.out.print("Enter the name   : ");
                    name = br.readLine();

                    System.out.print("Enter the salary : ");
                    salary = Double.parseDouble(br.readLine());
                }
                catch (Exception e){
                    System.out.println(e.getMessage() + " : Please enter valid data !");
                    continue;
                }

                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setDouble(3, salary);

                try {
                    if( ps.executeUpdate() == 1 ){
                        System.out.println("Record is added successfully !");
                        System.out.println("---------------------------------");
                    }
                    else{
                        System.out.println("Unable to add record, please try again !");
                        System.out.println("---------------------------------");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage() + " : Unable to add record, please try again !");
                }
            }

            System.out.println("Exiting...");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {

            try {
                connection.close();
                System.out.println("Connection closed !");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}





/*
    CREATE TABLE EMPLOYEE(
        id int PRIMARY KEY,
        name varchar(32) not null,
        salary float not null
    );
 */