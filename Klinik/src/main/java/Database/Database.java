/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.*;
/**
 *
 * @author user
 */
public class Database {
    
    protected Connection connection = null;
    protected Statement statement;
    public PreparedStatement preparedStatement;
    protected ResultSet resultSet;
        
public final Connection openConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/kel6?user=root&password=");
            System.out.println("MYSQL successfully connected");
        } catch (SQLException ex) {
            this.displayErrors(ex);
        }return connection;
    }
    protected final void disconnect(){
        try {
            if (this.resultSet !=null) this.resultSet.close();
            if (this.statement != null) this.statement.close();
            if (this.preparedStatement != null) this.preparedStatement.close();
            if (this.connection != null) this.connection.close();
            
            this.resultSet = null;
            this.statement = null;
            this.preparedStatement = null;
            this.connection = null;
        }catch (SQLException ex) {
            this.displayErrors(ex);
        }
    }
    

    protected final void displayErrors(SQLException ex) {
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
//    
    }

    

