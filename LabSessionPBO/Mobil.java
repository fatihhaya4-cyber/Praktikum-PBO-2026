/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabSessionPBO;

/**
 *
 * @author HYPE AMD
 */
// INHERITANCE: Mobil extends Kendaraan, implements Asuransi
public class Mobil extends Kendaraan implements Asuransi {
    private int jumlahPenumpang;

    public Mobil(String nama, String plat, double tarifPerHari, int jumlahPenumpang) {
        super(nama, plat, tarifPerHari);
        this.jumlahPenumpang = jumlahPenumpang;
    }

    public int getJumlahPenumpang() { return jumlahPenumpang; }
    public void setJumlahPenumpang(int jumlahPenumpang) { this.jumlahPenumpang = jumlahPenumpang; }

    @Override
    public double hitungBiayaSewa(int hari) {
        return getTarifPerHari() * hari;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis     : Mobil (" + jumlahPenumpang + " penumpang)");
    }

    @Override
    public double hitungAsuransi(int hari) {
        return 50000 * hari;
    }

    @Override
    public String getPaketAsuransi() {
        return "Asuransi All Risk";
    }
}