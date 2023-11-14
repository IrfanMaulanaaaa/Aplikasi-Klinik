/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.sql.*;
import Database.Database;
import java.awt.HeadlessException;
import java.text.BreakIterator;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class pasien extends Database{  
    public String id;
    public String namaDepan;
    public String namaBelakang;
    public String noTelepon;
    public String noKerabat;
    public String tanggalLahir;
    public String tanggalPeriksa;
    public String jenisKelamin;
    public String alamat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
    
    public String getNoKerabat() {
        return noKerabat;
    }

    public void setNoKerabat(String noKerabat) {
        this.noKerabat = noKerabat;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTanggalPeriksa() {
        return tanggalPeriksa;
    }

    public void setTanggalPeriksa(String tanggalPeriksa) {
        this.tanggalPeriksa = tanggalPeriksa;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
        public pasien(String id, String namaDepan, String namaBelakang, String noTelepon, String noKerabat, String tanggalLahir, String tanggalPeriksa, String jenisKelamin,  String alamat){
        this.id  = id;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.noTelepon = noTelepon;
        this.noKerabat = noKerabat; 
        this.tanggalLahir = tanggalLahir;
        this.tanggalPeriksa = tanggalPeriksa;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }
    public String getPasienId(){
        return id;
    } 
    public boolean create(String idAdmin){
        
        boolean isOperationSuccess = false;
        try {
            Connection con = this.openConnection();
            
            String sql = "INSERT INTO pasien VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            this.preparedStatement = con.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1,this.id);
            this.preparedStatement.setString(2, this.namaDepan);
            this.preparedStatement.setString(3, this.namaBelakang);
            this.preparedStatement.setString(4, this.noTelepon);
            this.preparedStatement.setString(5, this.noKerabat);
            this.preparedStatement.setString(6, this.alamat);
            this.preparedStatement.setString(7, this.jenisKelamin);
            this.preparedStatement.setString(8, this.tanggalLahir);
            this.preparedStatement.setString(9, this.tanggalPeriksa);
            this.preparedStatement.setString(10, idAdmin);
            
            
            
            int result = this.preparedStatement.executeUpdate();
            isOperationSuccess = result > 0;
        } catch (SQLException ex) {
            this.displayErrors(ex);
            JOptionPane.showMessageDialog(null, "error"+ex);
        } finally {
            this.disconnect();
        }
        return isOperationSuccess;
    }
        public boolean delete(){
        boolean isOperationSuccess = false;
//        
        try {
            this.openConnection();
           
            String sql = "delete from pasien where id_pasien = ?";
            this.preparedStatement = this.connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            
            this.preparedStatement.setString(1, id);
            
            
            
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

   public boolean update(){
       boolean isOperationSuccess = false;
       try{
           Connection con = this.openConnection();
           String sql = "update pasien set nama_depan = ?, nama_belakang = ?, no_telepon = ?, no_kerabat = ?, alamat = ?, jenis_kelamin = ?, tanggal_lahir = ?, tanggal_periksa = ? where id_pasien = ?";
           this.preparedStatement = con.prepareStatement(sql,statement.RETURN_GENERATED_KEYS);
           
           this.preparedStatement.setString(1, getNamaDepan());
           this.preparedStatement.setString(2, getNamaBelakang());
           this.preparedStatement.setString(3, getNoTelepon());
           this.preparedStatement.setString(4, getNoKerabat());
           this.preparedStatement.setString(5, getAlamat());
           this.preparedStatement.setString(6, getJenisKelamin());
           this.preparedStatement.setString(7, getTanggalLahir());
           this.preparedStatement.setString(8, getTanggalPeriksa());
           this.preparedStatement.setString(9, getId());
           this.preparedStatement.executeUpdate();
           JOptionPane.showMessageDialog(null, "data diupdate");
       }catch (HeadlessException | SQLException e){
           JOptionPane.showMessageDialog(null, "error in updating:" + e);
       }
       return isOperationSuccess;
   }


   public pasien returnValueById(String id) {
        pasien pasienn = null;
        try{
            openConnection();
            String sql = "Select * from pasien where id_pasien = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("id_pasien");
                String namadepan = rs.getString("nama_depan");
                String namabelakang = rs.getString("nama_belakang");
                String notelepon = rs.getString("no_telepon");
                String nokerabat = rs.getString("no_kerabat");
                String tgllahir = rs.getString("tanggal_lahir");
                String tglperiksa = rs.getString("tanggal_periksa");
                String jeniskelamin = rs.getString("jenis_kelamin");
                String alamatt = rs.getString("alamat");
                pasienn = new pasien(id, namadepan, namabelakang, notelepon, nokerabat, tgllahir, tglperiksa, jeniskelamin, alamatt);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "error "+e);
        }
        return pasienn;
        
   }


   
   
}