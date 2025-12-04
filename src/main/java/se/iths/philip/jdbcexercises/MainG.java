package se.iths.philip.jdbcexercises;

import java.sql.*;

public class MainG {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/my_database";
        String user = "root";
        String pass = "Ph!Wilhelm1";

//        String sql = "SELECT * FROM person";
        String sql = "SELECT * FROM person WHERE income > ? AND dob > ?";
//        String sql = "INSERT INTO person (first_name, last_name, dob, income) VALUES (?, ?, ?, ?)";
//        String sql = "UPDATE person SET income = ? WHERE person_id = ?";
//        String sql = "DELETE FROM person WHERE person_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            System.out.println("Anslutning etablerad.");
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("Ansluten till databas:");
            System.out.println("- Produkt: " + meta.getDatabaseProductName());
            System.out.println("- Version: " + meta.getDatabaseProductVersion());
            System.out.println("- Driver: " + meta.getDriverName());
            System.out.println("- URL: " + meta.getURL());

            // Select - Visar alla personer med income högre än x & dob efter s.
            pstmt.setDouble(1, 30000.0);
            pstmt.setDate(2, java.sql.Date.valueOf("1990-01-01"));

            // Insert - Lägger in ny person in i tabellen.
            pstmt.setString(1, "Lisa");
            pstmt.setString(2, "Andersson");
            pstmt.setDate(3, java.sql.Date.valueOf("1995-05-10"));
            pstmt.setDouble(4, 42000.0);
            int rowsInserted = pstmt.executeUpdate();
            System.out.println("Antal rader infogade: " + rowsInserted);

            // Update - Ändrar i enns income värde genom Person ID
            pstmt.setDouble(1, 50000.0);
            pstmt.setInt(2, 3);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Antal rader uppdaterade: " + rowsUpdated);

            // Delete - Tar bort en person från tabellen med ID.
            pstmt.setInt(1, 9);
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println("Antal rader borttagne: " + rowsDeleted);

            // Select - Visar alla person i tabellen & alla värden i tabellen.
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("person_id");
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    java.sql.Date dob = rs.getDate("dob");
                    double income = rs.getDouble("income");

                    System.out.println(id + ": " + first + " " + last + " - "
                            + dob + " - " + income);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
