Nama: Fatih Atha Hayaa
NIM: L0325024

Program ini adalah simulasi kasir minimarket sederhana berbasis console. Program menyimpan daftar produk (nama, harga, dan stok) di dalam sebuah ArrayList. Pengguna memilih satu produk dan memasukkan jumlah yang ingin dibeli, lalu program menghitung total harga (sudah termasuk PPN 11%) dan mengurangi stok produk yang bersangkutan.

Contoh Output
=== DAFTAR PRODUK MINIMARKET ===
1. Indomie Goreng | Kode: I | Singkatan: Indom. | Harga: Rp3500.0 | Stok: 50
2. Aqua Botol | Kode: A | Singkatan: Aqua . | Harga: Rp4000.0 | Stok: 30
3. Beras Premium | Kode: B | Singkatan: Beras. | Harga: Rp65000.0 | Stok: 10

Masukkan nomor produk yang ingin dibeli (1-3): 3
Masukkan jumlah beli: 5

=== STRUK PEMBELIAN ===
Produk : Beras Premium
Jumlah : 5
Total (termasuk PPN 11%) : Rp360750,00
Sisa stok : 5

Contoh 2 — Input nomor produk bukan angka (exception ditangani)
Masukkan nomor produk yang ingin dibeli (1-3): abc
Input harus berupa angka! Error: For input string: "abc"

Contoh 3 — Jumlah beli melebihi stok yang tersedia (beli Beras Premium 20 pcs, stok cuma 10)
Masukkan nomor produk yang ingin dibeli (1-3): 3
Masukkan jumlah beli: 20
Stok tidak cukup! Stok tersedia: 10

Contoh 4 — Nomor produk di luar jangkauan daftar
Masukkan nomor produk yang ingin dibeli (1-3): 9
Masukkan jumlah beli: 2
Terjadi kesalahan: Nomor produk tidak valid!
