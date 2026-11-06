/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabSessionPBO;

/**
 *
 * @author HYPE AMD
 */
// INHERITANCE: Motor extends Kendaraan, implements Asuransi
public class Motor extends Kendaraan implements Asuransi {
    private boolean gratisHelm;

    public Motor(String nama, String plat, double tarifPerHari, boolean gratisHelm) {
        super(nama, plat, tarifPerHari);
        this.gratisHelm = gratisHelm;
    }

    public boolean isGratisHelm() { return gratisHelm; }
    public void setGratisHelm(boolean gratisHelm) { this.gratisHelm = gratisHelm; }

    @Override
    public double hitungBiayaSewa(int hari) {
        return getTarifPerHari() * hari;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis     : Motor (gratis helm: " + (gratisHelm ? "ya" : "tidak") + ")");
    }

    @Override
    public double hitungAsuransi(int hari) {
        return 15000 * hari;
    }

    @Override
    public String getPaketAsuransi() {
        return "Asuransi Dasar";
    }
}