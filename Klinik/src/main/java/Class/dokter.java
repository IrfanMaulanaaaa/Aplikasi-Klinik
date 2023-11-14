/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.sql.*;
import Database.Database;
/**
 *
 * @author user
 */
public class dokter extends Database{
    public final String idDokter;
    public String username;
    public String nama;
    public String password;
    public String bidangSpesialis;
    public String kontak;
    
    public dokter(String id, String password) {
        this.idDokter = id;
        this.password = password;
    }
    
    
    
    public boolean login(String idDokter, String password) {
        boolean isValidUser = false;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kel6?user=root&password=");

            // Buat SQL query untuk memeriksa keberadaan username dan password yang sesuai
            String query = "SELECT * FROM dokter WHERE id_dokter= ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idDokter);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                // Jika query mengembalikan hasil, username dan password sesuai
               
                isValidUser = true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Tutup semua sumber daya (koneksi, statement, dan resultSet)
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            
        }
        return isValidUser;
    }
}
