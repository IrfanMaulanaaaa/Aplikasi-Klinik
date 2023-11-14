/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;
import Database.Database;
import java.sql.*;
/**
 *
 * @author user
 */


public class admin extends Database{
    public static String idAdmin;
    
    public String username;
    public String email;
    public String password;
    
    public admin(String idAdmin,String password) {
        this.idAdmin = idAdmin;
        this.password = password;
    }
    

    public String getUsername() {
        return this.username;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    } 
    
    
    
    public boolean register(){
        
        boolean isOperationSuccess = false;
        
        try {
            this.openConnection();
           
            String sql = "INSERT INTO admin VALUES (null, ?, ?, ?, null)";
            this.preparedStatement = this.connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            
            this.preparedStatement.setString(1, this.username);
            this.preparedStatement.setString(2, this.email);
            this.preparedStatement.setString(3, this.password);
            
            
            int result = this.preparedStatement.executeUpdate();
            isOperationSuccess = result > 0;
        } catch (SQLException ex) {
            this.displayErrors(ex);
        } finally {
            this.disconnect();
        }
        return isOperationSuccess;
    }
    
    
    public String login(String username, String password) {
        String idAdmin = "";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/kel6?user=root&password=");

            // Buat SQL query untuk memeriksa keberadaan username dan password yang sesuai
            String query = "SELECT * FROM admin WHERE username= ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                // Jika query mengembalikan hasil, username dan password sesuai
               
                idAdmin = resultSet.getString("id_admin"); 
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
        return idAdmin;

//        
        
        
    }
    

}
