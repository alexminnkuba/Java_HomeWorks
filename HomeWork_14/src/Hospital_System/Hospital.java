package Hospital_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Hospital {
    private  static  final  String URL = "jdbc:mysql://localhost:3307/hospitalSystemDB";
    private  static final String USER = "root";
    private static final String PASS = "123456";

    static void main(String[] args) {
       try(Connection conn = DriverManager.getConnection(URL,USER, PASS)){
           Scanner scan = new Scanner(System.in);
           AppointmentService appointmentService = new AppointmentService(conn, scan);
           PatientService patientService = new PatientService(conn, scan);

           System.out.println("\n=== СИСТЕМА УПРАВЛЕНИЯ БОЛЬНИЦЕЙ ===");

           while (true){
               System.out.println(" \n1. Авторизация");
               System.out.println("2. Регистрация");
               System.out.println("3. Посмотреть всех врачей");
               System.out.println("0. Выход");
               System.out.print("   Ваш выбор: ");


               String choice = scan.nextLine();

               System.out.println(choice);

               switch (choice){
                   case "1":
                        Integer patientId = patientService.login();
                        if(patientId != null){
                            patientMenu(patientId, patientService, appointmentService, scan);
                    }
                        break;
                   case "2":
                        patientService.register();
                        break;
                   case "3":
                       appointmentService.showAllDoctors();
                       break;
                   case "0":
                       System.out.println("\nВыход из системы...");
                       return;
                   default:
                       System.out.println("\n❌ Неверный выбор! Попробуйте снова!\n");
                       break;
               }
           }
       }catch (SQLException e){
           System.out.println("\n ❌Не удалось подключиться к базе данных!\n");
           e.printStackTrace();
       }
    }

    private static void patientMenu(Integer patientId, PatientService patientService, AppointmentService appointmentService, Scanner scan) {
        while (true){
            System.out.println("\n=== Личный кабинет пациента ===\n");
            System.out.println(" 1. Мои записи на приём");
            System.out.println(" 2. Записаться к врачу");
            System.out.println(" 0. Выйти");
            System.out.print("    Ваш выбор: ");

            String choice = scan.nextLine().trim();

            switch (choice){
                case "1":
                    appointmentService.showMyAppointments(patientId);
                    break;
                case "2":
                    appointmentService.makeAppointments(patientId);
                    break;
                case "0":
                    System.out.println("\nВыход из аккаунта...");
                    return;
                default:
                    System.out.println("\n❌ Неверный выбор.");
                    break;
            }
        }
    }
}
