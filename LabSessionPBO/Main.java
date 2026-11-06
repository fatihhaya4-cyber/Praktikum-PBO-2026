/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LabSessionPBO;

/**
 *
 * @author HYPE AMD
 */
public class Main {
    public static void main(String[] args) {
        // RUNTIME POLYMORPHISM: objek subclass disimpan di referensi superclass
        Kendaraan[] daftar = {
            new Mobil("Toyota Avanza", "AD 1234 AB", 350000, 7),
            new Mobil("Honda Brio", "AD 5678 CD", 300000, 5),
            new Motor("Honda Vario", "AD 9012 EF", 80000, true),
            new Bus("Bus Pariwisata", "AD 3456 GH", 1500000, 40, 400000)
        };

        int hari = 3;
        System.out.println("===== RENTAL KENDARAAN - Sewa " + hari + " hari =====\n");

        for (Kendaraan k : daftar) {
            k.tampilkanInfo(); // method overriding -> versi subclass yang jalan
            System.out.println("Biaya sewa: Rp" + (long) k.hitungBiayaSewa(hari));

            // Overloading: versi dengan diskon 10%
            System.out.println("Diskon 10%: Rp" + (long) k.hitungBiayaSewa(hari, 10));

            // Cek kontrak interface
            if (k instanceof Asuransi) {
                Asuransi a = (Asuransi) k;
                System.out.println("Asuransi  : " + a.getPaketAsuransi()
                        + " Rp" + (long) a.hitungAsuransi(hari));
            } else {
                System.out.println("Asuransi  : tidak tersedia");
            }
            System.out.println("------------------------------------");
        }

        // Demo encapsulation: ubah data lewat setter
        System.out.println("\n[Demo Encapsulation]");
        daftar[2].setTarifPerHari(100000);
        System.out.println("Tarif baru " + daftar[2].getNama() + ": Rp" + (long) daftar[2].getTarifPerHari());
        daftar[2].setTarifPerHari(-5000); // ditolak oleh validasi
    }
}