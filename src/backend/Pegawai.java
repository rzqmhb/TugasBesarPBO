/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author WINDOWS 10
 */
public class Pegawai {
    private int idPegawai;
    private String nama, email, noTelp, password;

    public Pegawai() {
    }

    public Pegawai(String nama, String email, String noTelp, String password) {
        this.nama = nama;
        this.email = email;
        this.noTelp = noTelp;
        this.password = password;
    }

    public int getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(int idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static Pegawai getById(int id){
        Pegawai pegawai = null;
        ResultSet rs = Koneksi.selectQuery("SELECT * FROM pegawai "
        + "WHERE idPegawai = " + id + "");
        
        try {
            while(rs.next()){
                pegawai = new Pegawai();
                pegawai.setIdPegawai(rs.getInt("idPegawai"));
                pegawai.setNama(rs.getString("namaPegawai"));
                pegawai.setEmail(rs.getString("emailPegawai"));
                pegawai.setNoTelp(rs.getString("noTelpPegawai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return pegawai;
    }
    
    public static ArrayList<Pegawai> getAll(){
        ArrayList<Pegawai> listPegawai = new ArrayList();
        ResultSet rs = Koneksi.selectQuery("SELECT * FROM pegawai");
        
        try {
            while(rs.next()){
                Pegawai pegawai = new Pegawai();
                pegawai.setIdPegawai(rs.getInt("idPegawai"));
                pegawai.setNama(rs.getString("namaPegawai"));
                pegawai.setEmail(rs.getString("emailPegawai"));
                pegawai.setNoTelp(rs.getString("noTelpPegawai"));
                
                listPegawai.add(pegawai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listPegawai;
    }
    
    public static ArrayList<Pegawai> search(String keyword){
        ArrayList<Pegawai> listPegawai = new ArrayList();
        
        String query = "SELECT * FROM pegawai WHERE namaPegawai LIKE '%" + keyword + "%'"
                + " OR emailPegawai LIKE '%" + keyword + "%'"
                + " OR noTelpPegawai LIKE '%" + keyword + "%'";
        
        ResultSet rs = Koneksi.selectQuery(query);
        
        try {
            while(rs.next()){
                Pegawai pegawai = new Pegawai();
                pegawai.setIdPegawai(rs.getInt("idPegawai"));
                pegawai.setNama(rs.getString("namaPegawai"));
                pegawai.setEmail(rs.getString("emailPegawai"));
                pegawai.setNoTelp(rs.getString("noTelpPegawai"));
                
                listPegawai.add(pegawai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listPegawai;
    }
    
    public void save(){
        if (this.idPegawai == 0){
            String query = "INSERT INTO pegawai (namaPegawai, emailPegawai, noTelpPegawai, passwordPegawai) VALUES ("
                    + "'" + this.nama + "', "
                    + "'" + this.email + "', "
                    + "'" + this.noTelp + "', "
                    + "'" + this.password + "')";
            
            this.idPegawai = Koneksi.insertQueryGetId(query);
        }
        else {
            String query = "UPDATE pegawai SET "
                    + "namaPegawai = '" + this.nama + "', "
                    + "emailPegawai = '" + this.email + "', "
                    + "noTelpPegawai = '" + this.noTelp + "' "
                    + "passwordPegawai = '" + this.password + "' "
                    + "WHERE idPegawai = '" +this.idPegawai + "'";
            
            Koneksi.executeQuery(query);
        }
    }
    
    public void delete(){
        String query = "DELETE FROM pegawai WHERE idPegawai = '"+this.idPegawai+"'";
        Koneksi.executeQuery(query);
    }
}
