/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;
import Database.Database;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author user
 */
public class JadwalKunjungan extends Database {
    public String kodeJadwal;
    public String namaPasien;
    public String waktuKunjungan;
    public static String kodedokter;
    
    public JadwalKunjungan(String kodejadwal, String namapasien, String waktukunjungan, String kodedokter){
        this.kodeJadwal = kodejadwal;
        this.namaPasien = namapasien;
        this.waktuKunjungan = waktukunjungan;
        this.kodedokter = kodedokter;
        
    }
    
    public boolean create(String idAdmin, String dokterid, String kodedokter){
        boolean isOperationSuccess = false;
        try{
            connection = this.openConnection();
            String  sql = "Insert into jadwal_kunjungan values(?,?,?,?,?)";
            this.preparedStatement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1,this.kodeJadwal);
            this.preparedStatement.setString(2,this.namaPasien);
            this.preparedStatement.setString(3,this.waktuKunjungan);
            this.preparedStatement.setString(4,idAdmin);    
            this.preparedStatement.setString(5, kodedokter);    
            int result = this.preparedStatement.executeUpdate();
            isOperationSuccess = result > 0;
        }catch (SQLException ex){
            this.displayErrors(ex);
            JOptionPane.showMessageDialog(null, "error "+ ex);
        }finally {
            this.disconnect();
        }
        return isOperationSuccess;
    }
    public boolean delete(){
        boolean isOperationSuccess = false;
//        
        try {
            this.openConnection();
           
            String sql = "delete from jadwal_kunjungan where kode_jadwal = ?";
            this.preparedStatement = this.connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            
            this.preparedStatement.setString(1, kodeJadwal);
            
            
            
            int res = this.preparedStatement.executeUpdate(); 
            isOperationSuccess = res > 0;
        } catch (SQLException ex) {
            this.displayErrors(ex);
            JOptionPane.showMessageDialog(null, "error"+ex);
        } finally {
            this.disconnect();
        }
        return isOperationSuccess;
    }
    
}
    