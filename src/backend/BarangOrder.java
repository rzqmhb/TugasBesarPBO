/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author WINDOWS 10
 */
class BarangOrder {
    private int idBarangOrder;
    private Barang barang;
    private int qtyBarang;
    private int hargaBarang;
    private Penjualan penjualan;
    
    public BarangOrder(){
        
    }
    
    public BarangOrder(Barang barang, int qtyBarang, Penjualan penjualan){
        this.barang = barang;
        this.qtyBarang = qtyBarang;
        this.hargaBarang = barang.getHarga();
        this.penjualan = penjualan;
    }

    public int getIdBarangOrder() {
        return idBarangOrder;
    }

    public void setIdBarangOrder(int idBarangOrder) {
        this.idBarangOrder = idBarangOrder;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getQtyBarang() {
        return qtyBarang;
    }

    public void setQtyBarang(int qtyBarang) {
        this.qtyBarang = qtyBarang;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }
    
    public static BarangOrder getByid(int id){
        BarangOrder bo = null;
        String query = ("SELECT BarangOrder.*, Barang.namaBarang ,Barang.kategoriBarang, Barang.hargaBarang, Barang.stokBarang, Penjualan.tglPenjualan, Penjualan.totalHargaPenjualan")
                                            + " FROM BarangOrder"
                                            + " LEFT JOIN Penjualan ON BarangOrder.idPenjualan = Penjualan.Penjualan"
                                            + " RIGHT JOIN Barang ON BarangOrder.idBarang = Barang.idBarang"
                                            + " WHERE idBarangOrder = " + id;
            ResultSet rs = Koneksi.selectQuery(query);
        try{
            while(rs.next()){
                Barang barang = new Barang();
                barang.setIdBarang(rs.getInt("idBarang"));
                barang.setNama(rs.getString("namaBarang"));
                barang.setKategori(rs.getString("kategoriBarang"));
                barang.setHarga(rs.getInt("hargaBarang"));
                barang.setStok(rs.getInt("stokBarang"));
                
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
                
                bo = new BarangOrder();
                bo.setIdBarangOrder(rs.getInt("idBarangOrder"));
                bo.setBarang(barang);
                bo.setPenjualan(pjl);
                bo.setQtyBarang(rs.getInt("qtyBarang"));
                bo.setHargaBarang(rs.getInt("hargaBarang"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bo;
    }
    
    public static ArrayList<BarangOrder> getAll(){
        ArrayList<BarangOrder> listBarangOrder = new ArrayList();
        
        String query = ("SELECT BarangOrder.*, Barang.namaBarang ,Barang.kategoriBarang, Barang.hargaBarang, Barang.stokBarang, Penjualan.tglPenjualan, Penjualan.totalHargaPenjualan")
                                            + " FROM BarangOrder"
                                            + " LEFT JOIN Penjualan ON BarangOrder.idPenjualan = Penjualan.Penjualan"
                                            + " RIGHT JOIN Barang ON BarangOrder.idBarang = Barang.idBarang";
        
        ResultSet rs = Koneksi.selectQuery(query);
        
        try{
            while(rs.next()){
                Barang barang = new Barang();
                barang.setIdBarang(rs.getInt("idBarang"));
                barang.setNama(rs.getString("namaBarang"));
                barang.setKategori(rs.getString("kategoriBarang"));
                barang.setHarga(rs.getInt("hargaBarang"));
                barang.setStok(rs.getInt("stokBarang"));
                
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
                
                BarangOrder bo = new BarangOrder();
                bo.setIdBarangOrder(rs.getInt("idBarangOrder"));
                bo.setBarang(barang);
                bo.setPenjualan(pjl);
                bo.setQtyBarang(rs.getInt("qtyBarang"));
                bo.setHargaBarang(rs.getInt("hargaBarang"));
                
                listBarangOrder.add(bo);   
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return listBarangOrder;
    }
    
    public void save(){
        if(this.idBarangOrder == 0){
            String query =  "INSERT INTO BarangOrder (idBarang, idPenjualan, qtyBarang, hargaBarang) VALUES ("
                    + " '" + this.barang.getIdBarang() + "', "
                    + " '" + this.penjualan.getIdPenjualan() + "') "
                    + " '" + this.qtyBarang + "') "
                    + " '" + this.hargaBarang + "') ";
            
            this.idBarangOrder = Koneksi.insertQueryGetId(query);
        }
        else{
            String query = "UPDATE BarangOrder SET "
                    + " idBarang = '" + this.barang.getIdBarang() + "', "
                    + " idPenjualan = '" + this.penjualan.getIdPenjualan() + "' "
                    + " qtyBarang = '" + this.qtyBarang + "' "
                    + " hargaBarang = '" + this.hargaBarang + "' "
                    + " WHERE idBarangOrder = '" + this.idBarangOrder + "'";
            
            Koneksi.executeQuery(query);
        }
    }
    
    public void delete(){
        String sql = "DELETE FROM kategori WHERE idBarangOrder = '" +this.idBarangOrder +"'";
        Koneksi.executeQuery(sql);
    }
}
