/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab.kasir;

/**
 * Class logika untuk menyimpan dan menghitung pesanan pelanggan.
 */
public class Pesanan {
    private static final double BATAS_DISKON = 50000;
    private static final double PERSEN_DISKON = 0.10;

    private double subtotal;

    // Menambahkan harga x jumlah ke subtotal
    public void tambahItem(double harga, int jumlah) {
        subtotal += harga * jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }

    // Subtotal > 50.000 mendapat diskon 10%, selain itu apa adanya
    public double hitungTotal() {
        if (subtotal > BATAS_DISKON) {
            return subtotal - (subtotal * PERSEN_DISKON);
        }
        return subtotal;
    }

    // Mengembalikan subtotal ke 0
    public void reset() {
        subtotal = 0;
    }
}