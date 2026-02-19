package Hospital_System;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppointmentService {
    private  final Connection conn;
    private final Scanner scan;

    public AppointmentService(Connection conn, Scanner scan) {
        this.conn = conn;
        this.scan = scan;
    }

    public void showAllDoctors(){
        System.out.println("\nВсе врачи:");
        System.out.println("+----+------------------------------------+----------------------+");
        System.out.println("| ID | ФИО                                | Специализация        |");
        System.out.println("+----+------------------------------------+----------------------+");

        String sql = "SELECT * FROM doctors";
        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                System.out.printf("│ %2d │ %-34s │ %-20s │%n",
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("+----+------------------------------------+----------------------+");
    }

    public void makeAppointments(Integer patientId) {
        showAllDoctors();

        System.out.print("\nВведите идентификатор (ID) врача: ");
        String id = scan.nextLine();
        if(!Pattern.matches("\\d+",id)){
            System.out.println("\n❌ Некорректный ввод! Попробуйте снова!\n");
            return;
        }
        int doctorId = Integer.parseInt(id);

        System.out.print("Введите дату и время записи в формате (2026-03-15 14:30): ");
        String datetime = scan.nextLine().trim();
        if(!Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}", datetime)){
            System.out.println("\n❌ Неверный формат! Используйте: ГГГГ-ММ-ДД ЧЧ:ММ\n");
            return;
        }

        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setString(3, datetime);
            ps.executeUpdate();
            System.out.println("\n✅ Запись успешно создана.\n");
        }catch (SQLException e){
            System.out.println("\n❌ Не удалось создать запись.\n");
            e.printStackTrace();
        }
    }

    public void showMyAppointments(Integer patientId) {
        System.out.println("\nВаши записи к врачам:\n");
        System.out.println("+---------------------------------+------------------------------------+----------------------+");
        System.out.println("| №  | Дата и время               | Врач                               | Специализация        |");
        System.out.println("+----+----------------------------+------------------------------------+----------------------+");

        String sql = """
                SELECT a.id, a.appointment_date, d.full_name, d.specialization
                FROM appointments a
                JOIN doctors d ON a.doctor_id = d.id
                WHERE a.patient_id = ?
                ORDER BY a.appointment_date
                """;

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            int count = 0;
            while ((rs.next())){
                count++;
                System.out.printf("│ %2d │ %-26s │ %-34s │ %-20s │%n",
                        count,
                        rs.getString("appointment_date"),
                        rs.getString("full_name"),
                        rs.getString("specialization"));
            }

            if(count == 0){
                System.out.println("|                     У вас пока нет записей                  |");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("+----+----------------------------+------------------------------------+----------------------+");
    }
}
