# Kasir Kantin UNS

Aplikasi kasir sederhana berbasis **Java Swing** untuk Kantin UNS. Proyek ini dikerjakan sebagai **Praktikum Pemrograman Berorientasi Objek (PBO) Kelas 3B, Pertemuan 11 (Multithreading) dan Pertemuan 12 (GUI Programming)**.

- **Nama**: Fatih Atha Haya
- **NIM**: L0325024
- **Prodi**: S1 Informatika PSDKU Kebumen, Universitas Sebelas Maret

## Fitur

- Input menu, harga, dan jumlah, lalu dihitung otomatis menjadi subtotal.
- Diskon 10% otomatis jika subtotal **lebih dari Rp 50.000**.
- Simulasi dua kasir (Dina dan Eko) yang melayani pelanggan bersamaan menggunakan `Thread`.
- Tombol **Cetak Struk** yang menunggu 2 detik di thread terpisah, sehingga jendela tidak macet.
- Validasi input (field kosong, bukan angka, nilai nol atau negatif) dengan `JOptionPane`.

## Struktur Proyek

```
kasir-kantin-uns/
├── src/
│   └── lab/
│       └── kasir/
│           ├── Pesanan.java       # Tahap 1: logika pesanan dan diskon
│           ├── Kasir.java         # Tahap 2: kasir sebagai Thread
│           ├── KasirFrame.java    # Tahap 3 dan 4: GUI Swing + cetak struk
│           └── MainKasir.java     # Entry point (uji Tahap 1, simulasi Tahap 2, buka GUI)
├── .gitignore
└── README.md
```

Semua class berada dalam package `lab.kasir`, dan atribut dibuat `private` (enkapsulasi).

## Cara Menjalankan

Prasyarat: **JDK 8 atau lebih baru**.

```bash
# 1. Clone repository
git clone <url-repository-kamu>
cd kasir-kantin-uns

# 2. Compile
mkdir out
javac -d out src/lab/kasir/*.java

# 3. Jalankan
java -cp out lab.kasir.MainKasir
```

Program akan mencetak hasil Tahap 1 dan Tahap 2 di konsol, lalu membuka jendela GUI Kasir.

## Penjelasan Tiap Tahap

### Tahap 1: Class Logika (`Pesanan`)

| Method | Fungsi |
|---|---|
| `tambahItem(double harga, int jumlah)` | Menambah `harga x jumlah` ke subtotal |
| `getSubtotal()` | Membaca nilai subtotal |
| `hitungTotal()` | Subtotal > 50.000 dikurangi diskon 10%, selain itu apa adanya |
| `reset()` | Mengembalikan subtotal ke 0 |

**Data uji:**

| Menu | Harga | Jumlah |
|---|---|---|
| Nasi Goreng | 15.000 | 2 |
| Es Teh | 5.000 | 3 |
| Ayam Bakar | 25.000 | 1 |

**Output konsol:**

```
=== TAHAP 1: Uji Class Pesanan ===
Setelah 2 menu -> subtotal: 45000, total: 45000
Setelah 3 menu -> subtotal: 70000, total: 63000
Setelah reset  -> subtotal: 0
```

Setelah dua menu pertama total 45.000 (tanpa diskon). Setelah menu ketiga subtotal 70.000 dan total 63.000 (diskon 10%).

### Tahap 2: Dua Kasir sebagai Thread (`Kasir`)

`Kasir` mewarisi `Thread`. Method `run()` mencetak kalimat `Kasir <nama> melayani pelanggan ke-<i>` sebanyak 5 kali dengan jeda 500 ms. Di `MainKasir`, kasir Dina dan Eko dijalankan dengan `start()`, lalu `join()` dipanggil untuk keduanya agar kalimat `Semua pelanggan selesai dilayani` selalu tampil paling akhir.

**Contoh output (urutan baris Dina dan Eko bisa berbeda tiap run):**

```
=== TAHAP 2: Dua Kasir sebagai Thread ===
Kasir Dina melayani pelanggan ke-1
Kasir Eko melayani pelanggan ke-1
Kasir Eko melayani pelanggan ke-2
Kasir Dina melayani pelanggan ke-2
...
Semua pelanggan selesai dilayani
```

**Catatan pengamatan (dijalankan 2 kali):** urutan output **tidak selalu sama**. Kedua thread berjalan bersamaan dan urutan eksekusinya ditentukan oleh *thread scheduler* JVM/OS, bukan oleh urutan pemanggilan `start()`. Sebaliknya, kalimat `Semua pelanggan selesai dilayani` **selalu muncul paling akhir** karena `main` menunggu kedua kasir selesai lewat `join()`.

### Tahap 3: Tampilan GUI (`KasirFrame`)

Dibuat dengan Java Swing menggunakan `setLayout(null)` dan `setBounds()`.

- **Tambah**: mengambil harga dan jumlah, memanggil `pesanan.tambahItem(...)`, menulis satu baris ke area struk, lalu mengosongkan field.
- **Hitung Total**: menampilkan `pesanan.hitungTotal()` pada label total.
- **Validasi**: field kosong atau input bukan angka menampilkan `JOptionPane.showMessageDialog`. `NumberFormatException` ditangani dengan `try-catch`.

Dengan data uji Tahap 1, label total menampilkan **Rp 63.000** setelah ketiga menu ditambahkan.

> Catatan: isi field harga dengan angka biasa tanpa titik pemisah ribuan (contoh `15000`, bukan `15.000`).

### Tahap 4: Cetak Struk dengan Thread

Tombol **Cetak Struk** membuat thread baru (`new Thread(...).start()`). Thread tersebut menampilkan `Mencetak struk...`, tidur 2 detik, lalu menampilkan `Struk selesai`. Karena penantian dilakukan di thread terpisah, tombol Tambah dan Hitung Total tetap bisa ditekan selama proses berlangsung.

Perubahan label dilakukan lewat `SwingUtilities.invokeLater(...)` karena komponen Swing tidak aman diubah langsung dari thread lain.

## Screenshot

Tambahkan hasil tangkap layar di folder `screenshots/` lalu rujuk di sini.

| Tahap | Screenshot |
|---|---|
| Tahap 1 (output konsol) | <img width="316" height="80" alt="Screenshot 2026-11-20 195656" src="https://github.com/user-attachments/assets/08560b92-2675-44c5-af5f-97ccc1309267" /> |
| Tahap 2 (output thread) | <img width="281" height="176" alt="Screenshot 2026-11-20 195713" src="https://github.com/user-attachments/assets/cad3ccf2-76aa-48f3-b6ca-c4891f514cfb" /> |
| Tahap 3 (GUI, total 63.000) | <img width="282" height="329" alt="Screenshot 2026-11-20 200055" src="https://github.com/user-attachments/assets/fa5111bc-78c1-4b77-8bfd-77dbb802e727" /> |
| Tahap 4 (status cetak) | <img width="287" height="327" alt="Screenshot 2026-11-20 200251" src="https://github.com/user-attachments/assets/22fc2661-4d17-4d7b-b4e5-ecd15666502c" /> <img width="288" height="324" alt="Screenshot 2026-11-20 200118" src="https://github.com/user-attachments/assets/d354290b-bd7f-4ad0-ab3e-3844fe44fc1f" /> |

## Konsep yang Dipelajari

- Enkapsulasi (atribut `private`, akses lewat method)
- Multithreading: `extends Thread`, `run()`, `start()`, `sleep()`, `join()`
- GUI Swing: `JFrame`, `JTextField`, `JTextArea`, `JLabel`, `JButton`, `JOptionPane`
- Event handling dengan `ActionListener` (lambda)
- Menjaga GUI tetap responsif dengan thread terpisah dan `SwingUtilities.invokeLater`
- Penanganan exception (`NumberFormatException`, `InterruptedException`)
