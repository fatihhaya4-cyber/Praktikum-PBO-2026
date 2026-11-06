/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package LabSessionPBO;

/**
 *
 * @author HYPE AMD
 */
// ABSTRACT CLASS (superclass) + ENCAPSULATION
public abstract class Kendaraan {
    private String nama;
    private String plat;
    private double tarifPerHari;

    // Constructor
    public Kendaraan(String nama, String plat, double tarifPerHari) {
        this.nama = nama;
        this.plat = plat;
        setTarifPerHari(tarifPerHari); // lewat setter agar divalidasi
    }

    // Getter
    public String getNama() { return nama; }
    public String getPlat() { return plat; }
    public double getTarifPerHari() { return tarifPerHari; }

    // Setter (dengan validasi)
    public void setNama(String nama) { this.nama = nama; }
    public void setTarifPerHari(double tarifPerHari) {
        if (tarifPerHari > 0) {
            this.tarifPerHari = tarifPerHari;
        } else {
            System.out.println("Tarif harus lebih dari 0, perubahan ditolak.");
        }
    }

    // ABSTRACT METHOD: wajib di-implement subclass
    public abstract double hitungBiayaSewa(int hari);

    // OVERLOADING: nama sama, parameter beda
    public double hitungBiayaSewa(int hari, double diskonPersen) {
        double total = hitungBiayaSewa(hari);
        return total - (total * diskonPersen / 100);
    }

    public void tampilkanInfo() {
        System.out.println("Kendaraan : " + nama + " (" + plat + ")");
        System.out.println("Tarif/hari: Rp" + (long) tarifPerHari);
    }

}
