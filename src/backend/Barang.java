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
public class Barang {
    private int idBarang, stok, harga;
    private String nama, kategori;

    public Barang() {
    }

    public Barang(String nama, String kategori, int harga, int stok) {
        this.stok = stok;
        this.harga = harga;
        this.nama = nama;
        this.kategori = kategori;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    public static Barang getById(int id){
        Barang barang = null;
        ResultSet rs = Koneksi.selectQuery("SELECT * FROM barang "
        + "WHERE idBarang = " + id + "");
        
        try {
            while(rs.next()){
                barang = new Barang();
                barang.setIdBarang(rs.getInt("idBarang"));
                barang.setNama(rs.getString("namaBarang"));
                barang.setKategori(rs.getString("kategoriBarang"));
                barang.setHarga(rs.getInt("hargaBarang"));
                barang.setStok(rs.getInt("stokBarang"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return barang;
    }
    
    public static ArrayList<Barang> getAll(){
        ArrayList<Barang> listBarang = new ArrayList();
        ResultSet rs = Koneksi.selectQuery("SELECT * FROM barang");
        
        try {
            while(rs.next()){
                Barang barang = new Barang();
                barang.setIdBarang(rs.getInt("idBarang"));
                barang.setNama(rs.getString("namaBarang"));
                barang.setKategori(rs.getString("kategoriBarang"));
                barang.setHarga(rs.getInt("hargaBarang"));
                barang.setStok(rs.getInt("stokBarang"));
                
                listBarang.add(barang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listBarang;
    }
    
    public static ArrayList<Barang> search(String keyword){
        ArrayList<Barang> listBarang = new ArrayList();
        
        String query = "SELECT * FROM barang WHERE namaBarang LIKE '%" + keyword + "%'"
                + " OR kategoriBarang LIKE '%" + keyword + "%'";
        
        ResultSet rs = Koneksi.selectQuery(query);
        
        try {
            while(rs.next()){
                Barang barang = new Barang();
                barang.setIdBarang(rs.getInt("idBarang"));
                barang.setNama(rs.getString("namaBarang"));
                barang.setKategori(rs.getString("kategoriBarang"));
                barang.setHarga(rs.getInt("hargaBarang"));
                barang.setStok(rs.getInt("stokBarang"));
                
                listBarang.add(barang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listBarang;
    }
    
    public void save(){
        if (this.idBarang == 0){
            String query = "INSERT INTO barang (namaBarang, kategoriBarang, hargaBarang, stokBarang) VALUES ("
                    + "'" + this.nama + "', "
                    + "'" + this.kategori + "', "
                    + "" + this.harga + ", "
                    + "" + this.stok + ")";
            
            this.idBarang = Koneksi.insertQueryGetId(query);
        }
        else {
            String query = "UPDATE barang SET "
                    + "namaBarang = '" + this.nama + "', "
                    + "kategoriBarang = '" + this.kategori + "', "
                    + "hargaBarang = " + this.harga + " "
                    + "stokBarang = " + this.stok + " "
                    + "WHERE idPegawai = '" +this.idBarang + "'";
            
            Koneksi.executeQuery(query);
        }
    }
    
    public void delete(){
        String query = "DELETE FROM barang WHERE idBarang = '"+this.idBarang+"'";
        Koneksi.executeQuery(query);
    }
}
