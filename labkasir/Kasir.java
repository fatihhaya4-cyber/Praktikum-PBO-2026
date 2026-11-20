/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.kasir;

/**
 * Kasir sebagai thread: melayani 5 pelanggan, 500 ms per pelanggan.
 */
public class Kasir extends Thread {
    private String nama;

    Kasir(String nama) {
        this.nama = nama;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Kasir " + nama + " melayani pelanggan ke-" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Kasir " + nama + " terhenti: " + e.getMessage());
                return;
            }
        }
    }
}