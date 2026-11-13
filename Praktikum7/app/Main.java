/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

/**
 *
 * @author HYPE AMD
 */

import model.Mahasiswa;
import model.NilaiInvalidException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Kelas utama: input data (Scanner), simpan ke ArrayList,
 * hitung statistik, dan tulis laporan_nilai.txt (BufferedWriter + FileWriter).
 *
 * Jalankan dengan assertion aktif:  java -ea app.Main
 */
public class Main {

    private static final String NAMA_FILE = "laporan_nilai.txt";

    public static void main(String[] args) {
        ArrayList<Mahasiswa> daftar = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== PROGRAM PENGELOLAAN NILAI MAHASISWA ===");
        int jumlah = bacaJumlah(scanner);

        // Input data beberapa mahasiswa
        int ke = 1;
        while (ke <= jumlah) {
            System.out.println("\n--- Data Mahasiswa ke-" + ke + " ---");
            try {
                System.out.print("Nama  : ");
                String nama = scanner.nextLine();
                System.out.print("NIM   : ");
                String nim = scanner.nextLine();
                System.out.print("Nilai : ");
                double nilai = Double.parseDouble(scanner.nextLine().trim());

                Mahasiswa m = new Mahasiswa(nama, nim, nilai);
                daftar.add(m);
                System.out.println("Data berhasil ditambahkan.");
                ke++;
            } catch (NilaiInvalidException e) {
                System.out.println("[ERROR NILAI] " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Nilai harus berupa angka!");
            } catch (IllegalArgumentException | AssertionError e) {
                System.out.println("[ERROR NIM] " + e.getMessage());
            }
        }

        // Hitung statistik dengan for-each
        double total = 0;
        int lulus = 0;
        int tidakLulus = 0;
        for (Mahasiswa m : daftar) {
            total += m.getNilai();
            if (m.isLulus()) {
                lulus++;
            } else {
                tidakLulus++;
            }
        }
        double rataRata = daftar.size() > 0 ? total / daftar.size() : 0;

        // Tampilkan ke layar
        System.out.println("\n=== REKAPITULASI ===");
        for (Mahasiswa m : daftar) {
            System.out.println(m);
        }
        System.out.printf("Jumlah mahasiswa : %d%n", daftar.size());
        System.out.printf("Rata-rata nilai  : %.2f%n", rataRata);
        System.out.printf("Lulus / Tidak    : %d / %d%n", lulus, tidakLulus);

        // Simpan ke file
        simpanLaporan(daftar, rataRata, lulus, tidakLulus);

        scanner.close();
    }

    /** Membaca jumlah mahasiswa, ulangi sampai input valid (> 0). */
    private static int bacaJumlah(Scanner scanner) {
        int jumlah = 0;
        while (jumlah <= 0) {
            System.out.print("Masukkan jumlah mahasiswa: ");
            try {
                jumlah = Integer.parseInt(scanner.nextLine().trim());
                if (jumlah <= 0) {
                    System.out.println("[ERROR] Jumlah harus lebih dari 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Masukkan angka bulat!");
            }
        }
        return jumlah;
    }

    /** File I/O dengan try-with-resources (otomatis close). */
    private static void simpanLaporan(ArrayList<Mahasiswa> daftar, double rataRata,
                                      int lulus, int tidakLulus) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            bw.write("LAPORAN NILAI MAHASISWA");
            bw.newLine();
            bw.write("=".repeat(70));
            bw.newLine();
            bw.write(String.format("%-12s | %-25s | %6s | %-5s | %s",
                    "NIM", "NAMA", "NILAI", "GRADE", "STATUS"));
            bw.newLine();
            bw.write("-".repeat(70));
            bw.newLine();

            for (Mahasiswa m : daftar) {
                bw.write(m.toString());
                bw.newLine();
            }

            bw.write("=".repeat(70));
            bw.newLine();
            bw.write("STATISTIK KELAS");
            bw.newLine();
            bw.write("Jumlah mahasiswa : " + daftar.size());
            bw.newLine();
            bw.write(String.format("Rata-rata nilai  : %.2f", rataRata));
            bw.newLine();
            bw.write("Jumlah lulus     : " + lulus);
            bw.newLine();
            bw.write("Jumlah tdk lulus : " + tidakLulus);
            bw.newLine();
            bw.write("Standar kelulusan: " + Mahasiswa.FINAL_PASSING_GRADE);
            bw.newLine();

            System.out.println("\nLaporan berhasil disimpan ke " + NAMA_FILE);
        } catch (IOException e) {
            System.out.println("[ERROR FILE] Gagal menulis file: " + e.getMessage());
        } finally {
            System.out.println("Proses penyimpanan selesai.");
        }
    }
}

