/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.main;

/**
 *
 * @author HYPE AMD
 */
import library.model.Book;
import library.model.Member;
import library.service.LibraryService;
import library.exception.BookNotFoundException;
import library.exception.BookAlreadyBorrowedException;
import library.exception.BorrowLimitExceededException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HYPE AMD
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HYPE AMD
 */

/**
 * Entry point aplikasi. Menyediakan menu interaktif berbasis console
 * untuk mengelola sistem perpustakaan mini.
 */
public class MainApp {

    private static Scanner scanner = new Scanner(System.in);
    private static LibraryService service = new LibraryService();

    public static void main(String[] args) {
        inisialisasiDataContoh(); // supaya aplikasi tidak kosong saat pertama dijalankan

        boolean lanjut = true;
        while (lanjut) {
            tampilkanMenu();
            int pilihan = bacaPilihanMenu();

            switch (pilihan) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    tampilkanDaftarBuku();
                    break;
                case 3:
                    cariBuku();
                    break;
                case 4:
                    pinjamBuku();
                    break;
                case 5:
                    kembalikanBuku();
                    break;
                case 6:
                    service.cetakLaporan();
                    break;
                case 7:
                    lanjut = false;
                    System.out.println("Terima kasih sudah menggunakan sistem perpustakaan!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("===== MENU PERPUSTAKAAN MINI =====");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Daftar Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Pinjam Buku");
        System.out.println("5. Kembalikan Buku");
        System.out.println("6. Laporan Perpustakaan");
        System.out.println("7. Keluar");
        System.out.print("Pilih menu (1-7): ");
    }

    private static int bacaPilihanMenu() {
        int pilihan;
        try {
            pilihan = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            pilihan = -1; // nilai tidak valid, akan ditangkap oleh 'default' di switch
        }
        return pilihan;
    }

    private static void tambahBuku() {
        System.out.print("Judul buku       : ");
        String judul = scanner.nextLine();
        System.out.print("Penulis          : ");
        String penulis = scanner.nextLine();
        System.out.print("Tahun terbit     : ");
        int tahun;
        try {
            tahun = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Tahun tidak valid, buku tidak ditambahkan.");
            return;
        }
        System.out.print("Kategori         : ");
        String kategori = scanner.nextLine();

        // Manipulasi character: memastikan huruf pertama kategori otomatis kapital
        kategori = kapitalisasiAwal(kategori);

        service.tambahBuku(new Book(judul, penulis, tahun, kategori));
        System.out.println("Buku \"" + judul + "\" berhasil ditambahkan.\n");
    }

    /**
     * Manipulasi character: mengubah huruf pertama kata menjadi kapital
     * menggunakan Character.toUpperCase() dan charAt().
     */
    private static String kapitalisasiAwal(String teks) {
        if (teks == null || teks.trim().isEmpty()) {
            return teks;
        }
        teks = teks.trim();
        char hurufPertama = Character.toUpperCase(teks.charAt(0));
        return hurufPertama + teks.substring(1).toLowerCase();
    }

    private static void tampilkanDaftarBuku() {
        ArrayList<Book> koleksi = service.getKoleksiBuku();
        if (koleksi.isEmpty()) {
            System.out.println("Koleksi buku masih kosong.\n");
            return;
        }
        System.out.println("\n===== DAFTAR BUKU =====");
        for (int i = 0; i < koleksi.size(); i++) {
            System.out.println((i + 1) + ". " + koleksi.get(i));
        }
        System.out.println();
    }

    private static void cariBuku() {
        System.out.print("Masukkan kata kunci (judul/kategori): ");
        String keyword = scanner.nextLine();
        ArrayList<Book> hasil = service.cariBuku(keyword);

        if (hasil.isEmpty()) {
            System.out.println("Tidak ada buku yang cocok dengan kata kunci \"" + keyword + "\".\n");
        } else {
            System.out.println("\n===== HASIL PENCARIAN =====");
            for (Book buku : hasil) {
                System.out.println("- " + buku);
            }
            System.out.println();
        }
    }

    private static void pinjamBuku() {
        System.out.print("ID Anggota  : ");
        String idAnggota = scanner.nextLine();
        System.out.print("Judul buku  : ");
        String judulBuku = scanner.nextLine();

        try {
            service.pinjamBuku(idAnggota, judulBuku);
            System.out.println("Buku \"" + judulBuku + "\" berhasil dipinjam oleh anggota " + idAnggota + ".\n");
        } catch (BookNotFoundException | BookAlreadyBorrowedException | BorrowLimitExceededException e) {
            System.out.println("Gagal meminjam buku: " + e.getMessage() + "\n");
        } catch (AssertionError e) {
            System.out.println("Gagal meminjam buku: " + e.getMessage() + "\n");
        }
    }

    private static void kembalikanBuku() {
        System.out.print("ID Anggota  : ");
        String idAnggota = scanner.nextLine();
        System.out.print("Judul buku  : ");
        String judulBuku = scanner.nextLine();

        try {
            service.kembalikanBuku(idAnggota, judulBuku);
            System.out.println("Buku \"" + judulBuku + "\" berhasil dikembalikan.\n");
        } catch (BookNotFoundException e) {
            System.out.println("Gagal mengembalikan buku: " + e.getMessage() + "\n");
        }
    }

    /**
     * Mengisi beberapa data contoh agar aplikasi langsung bisa dicoba
     * tanpa harus input manual dari awal.
     */
    private static void inisialisasiDataContoh() {
        service.tambahBuku(new Book("Laskar Pelangi", "Andrea Hirata", 2005, "Fiksi"));
        service.tambahBuku(new Book("Bumi Manusia", "Pramoedya Ananta Toer", 1980, "Fiksi"));
        service.tambahBuku(new Book("Algoritma dan Pemrograman", "Rinaldi Munir", 2018, "Teknologi"));
        service.tambahBuku(new Book("Cosmos", "Carl Sagan", 1980, "Sains"));
        service.tambahBuku(new Book("Sapiens", "Yuval Noah Harari", 2011, "Sains"));

        service.tambahAnggota(new Member("A001", "Atha"));
        service.tambahAnggota(new Member("A002", "Naila"));
        service.tambahAnggota(new Member("A003", "Duta"));
    }
}