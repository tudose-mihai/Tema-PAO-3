package com.company;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class ClinicService {




    public void Menu(){
        Scanner scanner = new Scanner(System.in);
        Boolean first = true;
        while(1>0){

            System.out.println();
            if(first) {
                System.out.println("Welcome. Please select an option:");
                first = false;
            }

            System.out.println("1) See appointments");
            System.out.println("2) See medics");
            System.out.println("3) See insurance types");
            System.out.println("4) See clients");
            System.out.println("5) Add appointment");
            System.out.println("6) Remove appointment");
            System.out.println("7) Raise medic salary");
            System.out.println("8) Exit");
            System.out.println();

            int option = scanner.nextInt();
            if(option == 1){
                AccessDB.showAll("appointments");
            }
            else if (option == 2){
                AccessDB.showAll("medics");
            }
            else if (option == 3){
                AccessDB.showAll("insurances");
            }
            else if (option == 4){
                AccessDB.showAll("clients");
            }
            else if(option == 5) {
                System.out.println("Choose a medic by id: ");
                AccessDB.showAll("medics");
                String medic_id = String.valueOf(scanner.nextInt());
                System.out.println("Choose a client by id: ");
                AccessDB.showAll("clients");
                String client_id = String.valueOf(scanner.nextInt());
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String str_date = dateFormat.format(date);
                AccessDB.Create(new String[]{"103", medic_id, client_id, str_date}, "appointments");

            }
            else if(option == 6){
                System.out.println("Choose appointment by id: ");
                AccessDB.showAll("appointments");
                String appointment_id = String.valueOf(scanner.nextInt());
                AccessDB.Delete(appointment_id, "appointments");

            }
            else if(option == 7){
                System.out.println("Choose a medic by id: ");
                AccessDB.showAll("medics");
                String lucky_doctor = String.valueOf(scanner.nextInt());
                AccessDB.giveRaise(lucky_doctor);
            }
            else if(option == 8){
                System.out.println("Goodbye!");
                return;
            }
            else{
                System.out.println("Invalid option");
            }


        }


    }

}
