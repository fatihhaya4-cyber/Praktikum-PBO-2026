# Program Pengelolaan Nilai Mahasiswa

Aplikasi Java berbasis console untuk mengelola data nilai mahasiswa. Program menerima input (nama, NIM, nilai), memvalidasinya, menyimpan ke `ArrayList`, menghitung statistik kelas, lalu menulis laporan ke file `laporan_nilai.txt`.

Proyek ini dibuat untuk tugas **Pengganti Pertemuan 7** dengan penerapan konsep OOP, exception handling, dan File I/O.

## Fitur

- Input data beberapa mahasiswa lewat `Scanner`
- Validasi **NIM**: tidak boleh kosong dan hanya boleh berisi angka
- Validasi **nilai**: harus berupa angka dalam rentang 0 sampai 100
- Konversi nilai ke **grade huruf** (A, B, C, D) dan **status** kelulusan
- Rekapitulasi di layar: daftar mahasiswa, jumlah, rata-rata, jumlah lulus dan tidak lulus
- Penyimpanan laporan ke `laporan_nilai.txt`
- Input yang salah tidak mengulang dari awal, hanya data mahasiswa yang bermasalah yang diminta ulang

## Struktur Proyek

```
src/
├── app/
│   └── Main.java                    # Kelas utama: input, statistik, tulis laporan
└── model/
    ├── Mahasiswa.java               # Kelas data + validasi + grade/status
    └── NilaiInvalidException.java   # Custom checked exception
```

| File | Peran |
| ---- | ----- |
| `Main.java` | Alur program: baca jumlah, loop input, hitung statistik, tampilkan rekap, simpan file |
| `Mahasiswa.java` | Menyimpan `nama`, `nim`, `nilai`; merapikan dan memvalidasi input di constructor |
| `NilaiInvalidException.java` | Dilempar saat nilai < 0 atau > 100 |

## Konsep yang Diterapkan

- **OOP**: encapsulation (atribut `private` + getter), pemisahan package `model` dan `app`
- **Konstanta**: `public static final double FINAL_PASSING_GRADE = 70.0`
- **Custom exception**: `NilaiInvalidException extends Exception` (checked exception)
- **Assertion**: `assert` untuk NIM tidak kosong (aktif dengan flag `-ea`)
- **String manipulation**: `trim()`, `toUpperCase()`, `Character.isDigit()`
- **Collection**: `ArrayList<Mahasiswa>` dan perulangan `for-each`
- **Exception handling**: `try-catch` (multi-catch), `finally`
- **File I/O**: `BufferedWriter` + `FileWriter` dengan `try-with-resources`

## Aturan Validasi dan Penilaian

### Validasi input

| Input | Aturan | Jika salah |
| ----- | ------ | ---------- |
| Jumlah mahasiswa | Bilangan bulat > 0 | Diminta ulang sampai valid |
| NIM | Tidak kosong, hanya angka | `[ERROR NIM]`, data ke-n diminta ulang |
| Nilai | Angka, 0 sampai 100 | `[ERROR]` atau `[ERROR NILAI]`, data ke-n diminta ulang |

Nama otomatis di-`trim()` dan diubah ke huruf kapital.

### Grade dan status

| Nilai | Grade |
| ----- | ----- |
| >= 85 | A |
| 70 - 84 | B |
| 55 - 69 | C |
| < 55 | D |

Status **LULUS** jika nilai >= 70 (`FINAL_PASSING_GRADE`), selain itu **TIDAK LULUS**.

## Cara Menjalankan

### Prasyarat

- JDK 11 atau lebih baru (kode memakai `String.repeat()`)

### Lewat terminal

Dari folder `src`:

```bash
# Compile
javac model/*.java app/*.java

# Jalankan (assertion aktif)
java -ea app.Main
```

Flag `-ea` mengaktifkan `assert`. Tanpa flag ini, NIM kosong tetap ditolak lewat pengecekan `IllegalArgumentException`.

### Lewat NetBeans

Buka proyek, lalu klik kanan `Main.java` dan pilih **Run File**. Untuk mengaktifkan assertion, tambahkan `-ea` di **Project Properties > Run > VM Options**.

## Contoh Penggunaan

**Sesi input (cuplikan):**

```
=== PROGRAM PENGELOLAAN NILAI MAHASISWA ===
Masukkan jumlah mahasiswa: 22

--- Data Mahasiswa ke-1 ---
Nama  : Doma
NIM   : K33201
Nilai : 89
[ERROR NIM] NIM hanya boleh berisi angka! Ditemukan karakter 'K'

--- Data Mahasiswa ke-1 ---
Nama  : Kadi
NIM   : 33202
Nilai : 98
Data berhasil ditambahkan.
```

**Rekapitulasi (cuplikan):**

```
=== REKAPITULASI ===
33202        | KADI                      |  98,00 | A     | LULUS
33203        | DODI                      |  78,00 | B     | LULUS
...
33207        | YING                      |  66,00 | C     | TIDAK LULUS
...
Jumlah mahasiswa : 22
Rata-rata nilai  : 81,14
Lulus / Tidak    : 20 / 2
```

Contoh sesi lengkap dengan 22 mahasiswa ada di `output_pertemuan_7.txt`.

## Format File Laporan

`laporan_nilai.txt` berisi judul, tabel data (NIM, NAMA, NILAI, GRADE, STATUS), lalu statistik kelas:

```
LAPORAN NILAI MAHASISWA
======================================================================
NIM          | NAMA                      |  NILAI | GRADE | STATUS
----------------------------------------------------------------------
...
======================================================================
STATISTIK KELAS
Jumlah mahasiswa : 22
Rata-rata nilai  : 81,14
Jumlah lulus     : 20
Jumlah tdk lulus : 2
Standar kelulusan: 70.0
```

## Catatan

- File `laporan_nilai.txt` **ditimpa** setiap program dijalankan dan dibuat di *working directory*.
- Pemisah desimal mengikuti locale sistem (`81,14` di locale Indonesia, `81.14` di locale lain). Saat input, nilai desimal diketik dengan titik (contoh `85.5`).
- Program belum mengecek **NIM duplikat**. Pada contoh output, NIM `33207` tercatat dua kali (YING dan YUDO).

## Penulis

Fatih Atha Haya
