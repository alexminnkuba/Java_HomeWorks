package Hospital_System;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PatientService {
    private final Connection conn;
    private final Scanner scan;

    public PatientService(Connection conn, Scanner scan) {
        this.conn = conn;
        this.scan = scan;
    }

    public  void register(){
        System.out.print("Введите ФИО: ");
        String name = scan.nextLine().trim();

        System.out.print("Введите email: ");
        String email = scan.nextLine().trim();
        if(!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$", email)){
            System.out.println("\n❌ Неверный формат email\n");
            return;
        }

        System.out.print("Введите дату рождения в формате (YYYY-MM-DD): ");
        String birth = scan.nextLine().trim();
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        if(!Pattern.matches(regex, birth)){
            System.out.println("\n❌ Неверный формат! Используйте: ГГГГ-ММ-ДД\n");
            return;
        }

        System.out.print("Введите пароль: ");
        String password = scan.nextLine().trim();
        if (password.length() < 6) {
            System.out.println("\n❌ Пароль должен быть не менее 6 символов.\n");
            return;
        }

        if(!checkPatient(email)){
            System.out.println("\n❌ Такой пользователь уже существует.\n");
            return;
        }

        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String sql = "INSERT INTO patients (full_name, email, birth_date, password_hash) VALUES (?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, birth);
            ps.setString(4, hash);
            ps.executeUpdate();
            System.out.println("\n✅ Регистрация успешно завершена.\n");
        }catch (SQLException e){
            e.printStackTrace();;
        }
    }

    public Integer login(){
        System.out.print("Введите email: ");
        String email = scan.nextLine().trim();
        if(!Pattern.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$", email)){
            System.out.println("\n❌ Неверный формат email\n");
            return  null;
        }

        System.out.print(" Введите пароль: ");
        String pass = scan.nextLine().trim();
        if (pass.length() < 6) {
            System.out.println("\n❌ Пароль должен быть не менее 6 символов.\n");
            return null;
        }

        String sql = "SELECT id, password_hash FROM patients WHERE email = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                String hash = rs.getString("password_hash");
                if(BCrypt.checkpw(pass, hash)){
                    System.out.println("\nВход выполнен!\n");
                    return rs.getInt("id");
                }
            }
            System.out.println("\n❌ Неверный email или пароль.\n");

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private  boolean checkPatient(String email){
        String sql = "SELECT 1 FROM patients WHERE email = ? LIMIT 1";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
