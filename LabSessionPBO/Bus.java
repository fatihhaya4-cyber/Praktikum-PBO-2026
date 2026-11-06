/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabSessionPBO;

/**
 *
 * @author HYPE AMD
 */
// INHERITANCE: Bus extends Kendaraan (tidak pakai asuransi)
public class Bus extends Kendaraan {
    private int kapasitas;
    private double biayaBBMPerHari;

    public Bus(String nama, String plat, double tarifPerHari, int kapasitas, double biayaBBMPerHari) {
        super(nama, plat, tarifPerHari);
        this.kapasitas = kapasitas;
        this.biayaBBMPerHari = biayaBBMPerHari;
    }

    public int getKapasitas() { return kapasitas; }
    public void setKapasitas(int kapasitas) { this.kapasitas = kapasitas; }

    @Override
    public double hitungBiayaSewa(int hari) {
        // sewa bus sudah termasuk BBM per hari
        return (getTarifPerHari() + biayaBBMPerHari) * hari;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis     : Bus (kapasitas " + kapasitas + " kursi)");
    }
}