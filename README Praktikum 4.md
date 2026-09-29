>> Sistem Perpustakaan Mini

Aplikasi perpustakaan sederhana berbasis console. Fiturnya: manajemen buku, pencarian, peminjaman dan pengembalian dengan aturan batas pinjam, serta laporan statistik perpustakaan.

 Struktur Project

```
library-perpustakaan/
└── library/
    ├── exception/
    │   ├── BookAlreadyBorrowedException.java
    │   ├── BookNotFoundException.java
    │   └── BorrowLimitExceededException.java
    ├── main/
    │   └── MainApp.java
    ├── model/
    │   ├── Book.java
    │   └── Member.java
    └── service/
        └── LibraryService.java
```

Project dibagi per package supaya tanggung jawab tiap class jelas:

| Package | Fungsi |
|---|---|
| `model` | Data dan atribut (buku & anggota) |
| `service` | Logika bisnis (cari, pinjam, kembali, laporan) |
| `exception` | Custom exception untuk kondisi error peminjaman |
| `main` | Entry point dan menu interaktif |

 Penjelasan Tiap Class

 1. Package `model`

`Book` merepresentasikan satu buku di koleksi perpustakaan.

| Atribut | Tipe | Keterangan |
|---|---|---|
| `judul`, `penulis`, `kategori` | `String` | Data identitas buku |
| `tahunTerbit` | `int` | Tahun terbit |
| `statusKetersediaan` | `boolean` | `true` = tersedia, `false` = sedang dipinjam |
| `jumlahDipinjam` | `int` | Counter berapa kali buku dipinjam (untuk analisis) |

Method penting:
- `cocokDenganKeyword(String keyword)` mengecek apakah judul atau kategori mengandung keyword. Memakai `toLowerCase()` dan `contains()` sehingga pencarian tidak case-sensitive.
- `tambahJumlahDipinjam()` menambah counter peminjaman (`jumlahDipinjam++`).
- `toString()` memformat data buku jadi satu baris rapi dengan `String.format`.

Buku baru otomatis berstatus tersedia dan `jumlahDipinjam = 0`.

`Member` merepresentasikan anggota perpustakaan.

| Atribut | Tipe | Keterangan |
|---|---|---|
| `id`, `nama` | `String` | Identitas anggota |
| `daftarPinjaman` | `ArrayList<String>` | Judul buku yang sedang dipinjam |
| `BATAS_PINJAM` | `static final int` | Konstanta batas maksimal pinjaman (3 buku) |

Method penting:
- `sudahMencapaiBatasPinjam()` bernilai `true` jika jumlah pinjaman sudah `>= 3`.
- `tambahPinjaman()` / `hapusPinjaman()` mengelola isi `daftarPinjaman`.
- `isDataValid()` memvalidasi bahwa `id` dan `nama` tidak null dan tidak kosong.

 2. Package `exception`

Ada tiga custom exception (semuanya `extends Exception`, jadi termasuk checked exception):

| Exception | Dilempar ketika |
|---|---|
| `BookNotFoundException` | Buku (atau anggota) tidak ditemukan |
| `BookAlreadyBorrowedException` | Buku yang mau dipinjam sedang dipinjam orang lain |
| `BorrowLimitExceededException` | Anggota sudah meminjam 3 buku |

 3. Package `service`

`LibraryService` adalah inti aplikasi. Class ini menyimpan seluruh data dan menangani proses bisnis.

Penyimpanan data:
- `ArrayList<Book> koleksiBuku` menyimpan semua buku.
- `HashMap<String, Member> daftarAnggota` menyimpan anggota dengan key `id`, sehingga pencarian anggota cepat.
- `int totalTransaksiPinjam` menghitung total peminjaman untuk laporan.

Kelompok method:

a. Manajemen & pencarian
- `tambahBuku()` / `tambahAnggota()` menambah data ke koleksi.
- `cariBukuBerdasarkanJudul()` mencari dengan judul persis (case-insensitive), dan melempar `BookNotFoundException` kalau tidak ketemu.
- `cariBuku(keyword)` mencari semua buku yang judul atau kategorinya mengandung keyword.
- `hitungJumlahBukuPerKategori()` merekap jumlah buku per kategori memakai `HashMap<String, Integer>`.

b. Peminjaman & pengembalian

`pinjamBuku(idAnggota, judulBuku)` memvalidasi dengan urutan berikut:
1. Ambil data anggota, lalu cek dengan `assert`.
2. Anggota tidak ada → `BookNotFoundException`.
3. Buku tidak ditemukan → `BookNotFoundException`.
4. Buku sedang dipinjam → `BookAlreadyBorrowedException`.
5. Anggota sudah 3 pinjaman → `BorrowLimitExceededException`.
6. Semua lolos → status buku jadi dipinjam, `jumlahDipinjam` naik, judul masuk ke `daftarPinjaman` anggota, dan `totalTransaksiPinjam` bertambah.

`kembalikanBuku(idAnggota, judulBuku)` mengecek anggota dan buku ada, dan bahwa anggota memang sedang meminjam buku itu. Kalau valid, status buku kembali tersedia dan judulnya dihapus dari `daftarPinjaman`.

c. Analisis & laporan
- `bukuPalingSeringDipinjam()` mencari buku dengan `jumlahDipinjam` terbesar lewat looping.
- `anggotaPalingAktif()` mencari anggota dengan jumlah pinjaman aktif terbanyak.
- `kategoriPalingPopuler()` mencari kategori dengan jumlah buku terbanyak.
- `cetakLaporan()` mencetak semua ringkasan di atas ke console.

 4. Package `main`

`MainApp` adalah entry point (`main()`) yang menampilkan menu interaktif dalam loop `while` + `switch`.

| Menu | Fungsi |
|---|---|
| 1 | Tambah buku (kategori otomatis dikapitalisasi lewat `kapitalisasiAwal()`) |
| 2 | Tampilkan daftar buku |
| 3 | Cari buku berdasarkan judul/kategori |
| 4 | Pinjam buku |
| 5 | Kembalikan buku |
| 6 | Laporan perpustakaan |
| 7 | Keluar |

Beberapa hal yang perlu diketahui:
- Saat program mulai, `inisialisasiDataContoh()` mengisi 5 buku dan 3 anggota (`A001`, `A002`, `A003`) supaya bisa langsung dicoba.
- Input angka (pilihan menu dan tahun terbit) dibungkus `try-catch` untuk `NumberFormatException`, jadi input salah tidak membuat program crash.
- Semua exception dari `LibraryService` ditangkap di `MainApp` dan ditampilkan sebagai pesan error yang ramah.

 Konsep Java yang Digunakan

- OOP: encapsulation (atribut `private` + getter/setter) dan pemisahan class per tanggung jawab.
- Collections: `ArrayList` dan `HashMap`.
- Exception handling: custom exception, `throws`, dan multi-catch (`catch (A | B | C e)`).
- Assertion: `assert` untuk validasi data anggota (hanya aktif dengan flag `-ea`).
- Manipulasi String & char: `toLowerCase()`, `contains()`, `equals()`, `charAt()`, `Character.toUpperCase()`, `substring()`, `String.format()`.
- Tipe data: primitive (`int`, `boolean`, `char`) dan reference (`String`, `ArrayList`, `HashMap`).

 Cara Menjalankan

Jalankan dari folder `library-perpustakaan/` (folder yang berisi package `library`):

```bash
 Compile
javac library/exception/.java library/model/.java library/service/.java library/main/.java

 Jalankan (tambahkan -ea supaya assertion aktif)
java -ea library.main.MainApp
```

 Contoh Alur Penggunaan

1. Pilih menu 4, isi ID `A001` dan judul `Laskar Pelangi` → berhasil dipinjam.
2. Pinjam buku yang sama dengan `A002` → gagal (`BookAlreadyBorrowedException`).
3. Pinjam 4 buku berbeda dengan `A001` → buku ke-4 gagal (`BorrowLimitExceededException`).
4. Pilih menu 6 untuk melihat laporan.
