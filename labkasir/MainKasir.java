/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.kasir;

import javax.swing.SwingUtilities;

/**
 * Class utama: menjalankan uji Tahap 1, simulasi Tahap 2, lalu membuka GUI (Tahap 3 dan 4).
 */
public class MainKasir {
    public static void main(String[] args) {
        ujiTahap1();
        simulasiTahap2();

        // Swing sebaiknya dijalankan di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new KasirFrame());
    }

    // ===== Tahap 1: uji class Pesanan =====
    private static void ujiTahap1() {
        System.out.println("=== TAHAP 1: Uji Class Pesanan ===");
        Pesanan pesanan = new Pesanan();

        pesanan.tambahItem(15000, 2); // Nasi Goreng
        pesanan.tambahItem(5000, 3);  // Es Teh
        System.out.println("Setelah 2 menu -> subtotal: " + (long) pesanan.getSubtotal()
                + ", total: " + (long) pesanan.hitungTotal());

        pesanan.tambahItem(25000, 1); // Ayam Bakar
        System.out.println("Setelah 3 menu -> subtotal: " + (long) pesanan.getSubtotal()
                + ", total: " + (long) pesanan.hitungTotal());

        pesanan.reset();
        System.out.println("Setelah reset  -> subtotal: " + (long) pesanan.getSubtotal());
        System.out.println();
    }

    // ===== Tahap 2: dua kasir sebagai thread =====
    private static void simulasiTahap2() {
        System.out.println("=== TAHAP 2: Dua Kasir sebagai Thread ===");
        Kasir dina = new Kasir("Dina");
        Kasir eko = new Kasir("Eko");

        dina.start();
        eko.start();

        try {
            dina.join();
            eko.join();
        } catch (InterruptedException e) {
            System.out.println("Main terhenti: " + e.getMessage());
        }

        System.out.println("Semua pelanggan selesai dilayani");
        System.out.println();
    }
}