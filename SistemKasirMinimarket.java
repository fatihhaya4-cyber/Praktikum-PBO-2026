/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.session.pbo;

/**
 *
 * @author HYPE AMD
 */
import java.util.ArrayList;
import java.util.Scanner;

public class SistemKasirMinimarket {

    // ================== KELAS PRODUK (Poin 1: Class & Object) ==================
    static class Produk {
        // Atribut (variabel)
        private String namaProduk;
        private double harga;
        private int stok;

        // Poin 3: Konstanta (final) yang relevan dengan kasus -> tarif PPN
        static final double PPN = 0.11; // 11%

        // Poin 2: Constructor untuk inisialisasi nilai awal atribut
        public Produk(String namaProduk, double harga, int stok) {
            this.namaProduk = namaProduk;
            this.harga = harga;
            this.stok = stok;
        }

        // Method 1: menghitung total harga transaksi termasuk pajak
        public double hitungTotalHarga(int jumlahBeli) {
            double subtotal = harga * jumlahBeli;
            double pajak = subtotal * PPN;
            return subtotal + pajak;
        }

        // Method 2: mengurangi stok setelah transaksi berhasil
        public void kurangiStok(int jumlahBeli) {
            this.stok -= jumlahBeli;
        }

        // Poin 7: Character & String - pakai tipe char + method String
        public char getKodeAwal() {
            // toUpperCase() lalu charAt() -> ambil huruf pertama sebagai "kode"
            return namaProduk.toUpperCase().charAt(0);
        }

        public String getNamaSingkat() {
            // length() & substring() -> singkat nama kalau kepanjangan
            if (namaProduk.length() > 5) {
                return namaProduk.substring(0, 5) + ".";
            }
            return namaProduk;
        }

        public String getNamaProduk() { return namaProduk; }
        public double getHarga() { return harga; }
        public int getStok() { return stok; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Poin 8: Array/Collection - ArrayList menyimpan daftar produk
        ArrayList<Produk> daftarProduk = new ArrayList<>();

        // Poin 9: Membuat object dari class Produk, lalu ditampilkan ke layar
        daftarProduk.add(new Produk("Indomie Goreng", 3500, 50));
        daftarProduk.add(new Produk("Aqua Botol", 4000, 30));
        daftarProduk.add(new Produk("Beras Premium", 65000, 10));

        System.out.println("=== DAFTAR PRODUK MINIMARKET ===");

        // Poin 5: Looping -- for, menampilkan seluruh data produk
        for (int i = 0; i < daftarProduk.size(); i++) {
            Produk p = daftarProduk.get(i);
            System.out.println((i + 1) + ". " + p.getNamaProduk()
                    + " | Kode: " + p.getKodeAwal()
                    + " | Singkatan: " + p.getNamaSingkat()
                    + " | Harga: Rp" + p.getHarga()
                    + " | Stok: " + p.getStok());
        }

        System.out.print("\nMasukkan nomor produk yang ingin dibeli (1-" + daftarProduk.size() + "): ");
        int pilihan;
        int jumlahBeli;

        // Poin 6: Exception Handling -- tangani input tidak valid
        try {
            pilihan = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Masukkan jumlah beli: ");
            jumlahBeli = Integer.parseInt(scanner.nextLine().trim());

            if (pilihan < 1 || pilihan > daftarProduk.size()) {
                throw new IllegalArgumentException("Nomor produk tidak valid!");
            }

            Produk produkDipilih = daftarProduk.get(pilihan - 1);

            // Poin 4: Kondisional - if-else cek ketersediaan stok
            if (jumlahBeli <= 0) {
                System.out.println("Jumlah beli harus lebih dari 0!");
            } else if (jumlahBeli > produkDipilih.getStok()) {
                System.out.println("Stok tidak cukup! Stok tersedia: " + produkDipilih.getStok());
            } else {
                double total = produkDipilih.hitungTotalHarga(jumlahBeli);
                produkDipilih.kurangiStok(jumlahBeli);

                System.out.println("\n=== STRUK PEMBELIAN ===");
                System.out.println("Produk : " + produkDipilih.getNamaProduk());
                System.out.println("Jumlah : " + jumlahBeli);
                System.out.printf("Total (termasuk PPN %.0f%%) : Rp%.2f%n", Produk.PPN * 100, total);
                System.out.println("Sisa stok : " + produkDipilih.getStok());
            }

        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka! Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}