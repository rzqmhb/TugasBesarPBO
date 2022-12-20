/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;
import backend.*;
import java.util.ArrayList;

/**
 *
 * @author WINDOWS 10
 */
public class test {
    public static void main(String[] args) {
        //Pegawai pegawai = new Pegawai("yoyok", "123@gmail.com", "0812781", "awikwok");
        //pegawai.save();
        //Pegawai yoyok = Pegawai.getById(1);
        //System.out.println(yoyok.validasi(1, "awikwok"));
        //Pegawai pegawai = new Pegawai();
        //System.out.println(pegawai.validasi(1, "awikwok"));
        
        //Barang barang = new Barang("Chiki", "makanan ringan", 2000, 100);
        //barang.save();
        //Barang barang = Barang.getById(1);
        
        //Penjualan penjualan = new Penjualan("2022-11-28", pegawai);
        //penjualan.save();
        //Penjualan penjualan = Penjualan.getByid(1);
        
        /*BarangOrder barangOrder = new BarangOrder(barang, 10, penjualan);
        if (barangOrder.validasi(barang.getIdBarang())) {
            barangOrder.save();
            System.out.println("BarangOrder tersimpan");
        } else {
            System.out.println("Stok kekurangan");
        }*/
        
        //penjualan.tambahBarang(barangOrder);
        
        ArrayList<BarangOrder> listBarang = BarangOrder.getByIdPenjualan(29);
        for (BarangOrder barangOrder : listBarang) {
            System.out.println("ID : " + barangOrder.getIdBarangOrder());
        }
    }
}
