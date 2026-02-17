import java.sql.*;


private static final String url = "jdbc:mysql://localhost:3306/carsDb";
private static final String userName = "root";
private static final String password ="12345";

void main() {
    try{
        Connection conn = DriverManager.getConnection(url,userName,password);
        Scanner scan = new Scanner(System.in);

        System.out.println("\n=== Приложение Автомобили ===");

        while(true){
            System.out.println("\nМеню: ");
            System.out.println("1. Показать ВСЕ автомобили");
            System.out.println("2. Показать всех производителей");
            System.out.println("3. Автомобили конкретного года");
            System.out.println("4. Автомобили конкретного производителя");
            System.out.println("5. Фильтр по цвету");
            System.out.println("6. Фильтр по объёму двигателя");
            System.out.println("7. Фильтр по типу кузова");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            String line=scan.next().trim();
            if(line.isEmpty()){
                continue;
            }
            int choice = Integer.parseInt(line);

            switch (choice){
                case 1:
                    showAllCars(conn);
                    break;
                case 2:
                    showAllManufacturers(conn);
                    break;
                case 3:
                    showCarsByYear(conn, scan);
                    break;
                case 4:
                    showCarsByManufacturer(conn, scan);
                    break;
                case 5:
                    showCarsByColor(conn, scan);
                    break;
                case 6:
                    showCarsByEngineVolume(conn, scan);
                    break;
                case 7:
                    showCarsByType(conn, scan);
                    break;
                case 0:
                    System.out.println("\nВыход...");
                    return;
                default:
                    System.out.println("Неверный пункт меню! Повторите ввод!");
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } catch (NumberFormatException e){
        System.out.println(e.getMessage());
    }
}

private static void showCarsByType(Connection conn, Scanner scan) {
    System.out.print("Введите тип (sedan / hatchback / universal): ");
    String input = scan.next().trim().toLowerCase();
    String type;

    switch (input) {
        case "sedan": case "седан":
            type = "sedan";
            break;
        case "hatchback": case "хэтчбек":
            type = "hatchback";
            break;
        case "universal": case "универсал":
            type = "universal";
            break;
        default:
            System.out.println("Неверный тип! Допустимо: sedan, hatchback, universal");
            return;
    }

    String sql = "SELECT * FROM cars WHERE car_type = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, type);

        System.out.println("DEBUG: будет выполнен запрос → " +
                sql.replace("?", "'" + type + "'"));
        ResultSet rs = stmt.executeQuery();
        System.out.println("\nАвтомобили типа " + type + ":");
        printCars(rs);
    }catch (SQLException e){
        e.printStackTrace();
    }
}

private static void showCarsByEngineVolume(Connection conn, Scanner scan) {
    System.out.print("Введите объём двигателя (например 2.0): ");
    String vol_str = scan.next();
    if(vol_str.isEmpty() || !vol_str.matches("\\d+\\.\\d+")){
        System.out.println("Некорректный ввод данных! Попробуйте снова.");
        return;
    }
    double volume = Double.parseDouble(vol_str);
    String sql = "SELECT * FROM cars WHERE engine_volume = ?";

    try(PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setDouble(1,volume);
        ResultSet rs = stmt.executeQuery();
        System.out.println("Автомобили с объёмом двигателя" + volume + " л:");
        printCars(rs);
    }catch(SQLException e){
        e.printStackTrace();
    }
}

private static void showCarsByColor(Connection conn, Scanner scan) {
    System.out.print("\nВведите цвет: ");
    String color = scan.next();
    String sql = "SELECT * FROM cars WHERE color = ?";

    try(PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1,color);
        ResultSet rs = stmt.executeQuery();
        System.out.println("Автомобили цвета " + color + ":");
        printCars(rs);
    }catch (SQLException e){
        e.printStackTrace();
    }
}

private static void showCarsByManufacturer(Connection conn, Scanner scan) {
    System.out.print("\nВведите производителя: ");
    String mn = scan.next();
    String sql = "SELECT * FROM cars WHERE manufacturer = ?";

    try(PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setString(1,mn);
        ResultSet rs = stmt.executeQuery();
        System.out.println("Автомобили " + mn + ":");
        printCars(rs);
    } catch(SQLException e){
        e.printStackTrace();
    }
}

private static void showCarsByYear(Connection conn, Scanner scan) {
    System.out.print("\nВведите год выпуска, например - 2020: ");
    String str_year = scan.next();
    if(str_year.isEmpty() || !str_year.matches("\\d+")){
        System.out.println("Некорректный ввод данных! Попробуйте снова.");
        return;
    }
    int year = Integer.parseInt(str_year);
    String sql = "SELECT * FROM cars WHERE year = ?";

    try(PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setInt(1 ,year);
        ResultSet rs = stmt.executeQuery();
        System.out.println("Автомобили " + year + " года:");
        printCars(rs);
    } catch (SQLException e){
        e.printStackTrace();
    }
}

private static void showAllManufacturers(Connection conn) {
    String sql = "SELECT DISTINCT manufacturer from CARS";
    try(Statement stmt = conn.createStatement()){
        ResultSet rs = stmt.executeQuery((sql));
        System.out.println("\nПроизводители автомобилей:");
        while ((rs.next())){
            System.out.println("• " + rs.getString("manufacturer"));
        }
    } catch(SQLException e){
        e.printStackTrace();
    }
}

private static void showAllCars(Connection conn) {
    String sql = "SELECT * FROM cars";
    try(Statement stmt = conn.createStatement()) {
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("\nВсе автомобили: ");
        printCars(rs);
    } catch (SQLException e){
        e.printStackTrace();
    }
}

private static void printCars(ResultSet rs) {
    boolean hasRows = false;
    try{
        System.out.println("+-----+---------------+----------+-------+------+--------+------------+");
        System.out.println("| id  | Производитель | Модель   | Объем | Год  | Цвет   | Тип кузова |");
        System.out.println("+-----+---------------+----------+-------+------+--------+------------+");
        while (rs.next()) {
            hasRows = true;
            System.out.printf("| %-3d | %-13s | %-8s | %-5.1f | %4d | %-6s | %-10s |%n",
                    rs.getInt("id"),
                    rs.getString("manufacturer"),
                    rs.getString("model"),
                    rs.getDouble("engine_volume"),
                    rs.getInt("year"),
                    rs.getString("color"),
                    rs.getString("car_type"));
        }
        if (!hasRows) {
            System.out.println("Нет автомобилей по вашему запросу.");
        }
        System.out.println("+-----+---------------+----------+-------+------+--------+------------+");
    } catch(SQLException e){
        e.printStackTrace();
    }
}
