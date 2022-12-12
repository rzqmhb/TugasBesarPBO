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
public class Penjualan {
        private int idPenjualan;
        private Pegawai pegawai;
        private String tglPenjualan;
        private int totalHargaPenjualan;
    
    public Penjualan(){
        
    }
    
    public Penjualan(String tglPenjualan, Pegawai pegawai, int totalHargaPenjualan){
        this.tglPenjualan=tglPenjualan;
        this.pegawai=pegawai;
        this.totalHargaPenjualan=totalHargaPenjualan;
    }
    
    public int getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(int idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public String getTglPenjualan() {
        return tglPenjualan;
    }

    public void setTglPenjualan(String tglPenjualan) {
        this.tglPenjualan = tglPenjualan;
    }

    public int getTotalHargaPenjualan() {
        return totalHargaPenjualan;
    }

    public void setTotalHargaPenjualan(int totalHargaPenjualan) {
        this.totalHargaPenjualan = totalHargaPenjualan;
    }
    
    public static Penjualan getByid(int id){
        Penjualan pjl = null;
        String query = ("SELECT Penjualan.*, Pegawai.namaPegawai, Pegawai.emailPegawai, Pegawai.noTelpPegawai")
                                            + " FROM Penjualan"
                                            + " LEFT JOIN Pegawai ON Penjualan.idPegawai = Pegawai.idPegawai"
                                            + " WHERE idPenjualan = " + id;
            
            ResultSet rs = Koneksi.selectQuery(query);
        try{
            while(rs.next()){
                Pegawai pegawai = new Pegawai();
                pegawai.setIdPegawai(rs.getInt("idPegawai"));
                pegawai.setNama(rs.getString("namaPegawai"));
                pegawai.setEmail(rs.getString("emailPegawai"));
                pegawai.setNoTelp(rs.getString("noTelpPegawai"));
                
                pjl = new Penjualan();
                pjl.setIdPenjualan(rs.getInt("idPenjualan"));
                pjl.setPegawai(pegawai);
                pjl.setTglPenjualan(rs.getString("tglPenjualan"));
                pjl.setTotalHargaPenjualan(rs.getInt("totalHargaBarang"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return pjl;
    }
    
    public static ArrayList<Penjualan> getAll(){
        ArrayList<Penjualan> listPenjualan = new ArrayList();
        
        String query = ("SELECT Penjualan.*, Pegawai.namaPegawai, Pegawai.emailPegawai, Pegawai.noTelpPegawai")
                       + " FROM Penjualan"
                       + " LEFT JOIN Pegawai ON Penjualan.idPegawai = Pegawai.idPegawai";
        
        ResultSet rs = Koneksi.selectQuery(query);
        
        try{
            while(rs.next()){
                Pegawai pegawai = new Pegawai();
                pegawai.setIdPegawai(rs.getInt("idPegawai"));
                pegawai.setNama(rs.getString("namaPegawai"));
                pegawai.setEmail(rs.getString("emailPegawai"));
                pegawai.setNoTelp(rs.getString("noTelpPegawai"));
                
                Penjualan pjl = new Penjualan();
                pjl.setIdPenjualan(rs.getInt("idPenjualan"));
                pjl.setPegawai(pegawai);
                pjl.setTglPenjualan(rs.getString("tglPenjualan"));
                pjl.setTotalHargaPenjualan(rs.getInt("totalHargaBarang"));
                
                listPenjualan.add(pjl);   
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return listPenjualan;
    }
    
    public static ArrayList<Penjualan> search(String keyword){
        ArrayList<Penjualan> listPenjualan = new ArrayList();
        
        String query = ("SELECT Penjualan.*, Pegawai.namaPegawai, Pegawai.emailPegawai, Pegawai.noTelpPegawai")
                       + " FROM Penjualan"
                       + " LEFT JOIN Pegawai ON Penjualan.idPegawai = Pegawai.idPegawai"
                       + " WHERE nama LIKE '%"+keyword+"%'"
                       + " OR idPegawai LIKE '%"+keyword+"%'";
        
        ResultSet rs = Koneksi.selectQuery(query);
        
        try{
            while(rs.next()){
                Pegawai pegawai = new Pegawai();
                pegawai.setIdPegawai(rs.getInt("idPegawai"));
                pegawai.setNama(rs.getString("namaPegawai"));
                pegawai.setEmail(rs.getString("emailPegawai"));
                pegawai.setNoTelp(rs.getString("noTelpPegawai"));
                
                Penjualan pjl = new Penjualan();
                pjl.setIdPenjualan(rs.getInt("idPenjualan"));
                pjl.setPegawai(pegawai);
                pjl.setTglPenjualan(rs.getString("tglPenjualan"));
                pjl.setTotalHargaPenjualan(rs.getInt("totalHargaBarang"));
                
                listPenjualan.add(pjl);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return listPenjualan;
    }
    
    public void save(){
        if(this.idPenjualan == 0){
            String query =  "INSERT INTO Penjualan (idPegawai, tglPenjualan, totalHargaPenjualan) VALUES ("
                    + " '" + this.pegawai.getIdPegawai() + "', "
                    + " '" + this.tglPenjualan + "', "
                    + " '" + this.totalHargaPenjualan + "') ";
            
            this.idPenjualan = Koneksi.insertQueryGetId(query);
        }
        else{
            String query = "UPDATE Penjualan SET "
                    + " idPegawai = '" + this.pegawai.getIdPegawai() + "', "
                    + " tglPenjualan = '" + this.tglPenjualan + "', "
                    + " totalHargaPenjualan = '" + this.totalHargaPenjualan + "' "
                    + " WHERE idPenjualan = '" + this.idPenjualan + "'";
            
            Koneksi.executeQuery(query);
        }
    }
    
    public void delete(){
        String sql = "DELETE FROM kategori WHERE idPenjualan = '" +this.idPenjualan +"'";
        Koneksi.executeQuery(sql);
    }
    
}
