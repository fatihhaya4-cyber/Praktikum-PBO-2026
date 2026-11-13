# Laporan Nilai Siswa

Program Java berbasis console untuk mencatat nilai siswa, menentukan status kelulusan, lalu menyimpannya ke file teks (`laporan_nilai.txt`). Setelah selesai menulis, program otomatis membaca ulang file tersebut dan menampilkannya di konsol.

## Fitur

- Input jumlah siswa, nama, dan nilai lewat konsol
- Penentuan status kelulusan otomatis (**Lulus** jika nilai >= 70)
- Penulisan laporan berformat tabel ke file `laporan_nilai.txt`
- Ringkasan otomatis: rata-rata nilai kelas, jumlah siswa lulus, dan jumlah siswa tidak lulus
- Pembacaan ulang file dan penampilannya di konsol
- Penanganan error untuk input angka yang tidak valid dan kegagalan baca/tulis file

## Konsep yang Dipakai

- File I/O dengan `BufferedWriter` dan `FileWriter` (menulis) serta `BufferedReader` dan `FileReader` (membaca)
- `try-with-resources` supaya resource file ditutup otomatis
- Exception handling (`IOException`, `NumberFormatException`)
- `String.format` untuk merapikan kolom tabel
- Method `static` untuk memisahkan tugas (header, data siswa, ringkasan, baca file)

## Struktur Proyek

```
laporannilai/
├── LaporanNilai.java     # Source code utama
└── laporan_nilai.txt     # Output laporan (dibuat otomatis saat program dijalankan)
```

## Cara Menjalankan

### Prasyarat

- JDK 8 atau lebih baru

### Lewat terminal

```bash
# Compile
javac LaporanNilai.java

# Jalankan
java LaporanNilai
```

> Catatan: file ini memakai `package laporannilai;`. Kalau dijalankan dari terminal, compile dari folder induk:
>
> ```bash
> javac laporannilai/LaporanNilai.java
> java laporannilai.LaporanNilai
> ```

### Lewat NetBeans

Buka proyek, lalu klik kanan `LaporanNilai.java` dan pilih **Run File** (atau tekan `Shift + F6`).

## Alur Program

1. Program meminta **jumlah siswa**.
2. Untuk tiap siswa, program meminta **nama** dan **nilai**.
3. Status ditentukan langsung: `nilai >= 70` berarti **Lulus**, selain itu **Tidak Lulus**.
4. Data ditulis ke `laporan_nilai.txt`, diikuti ringkasan (rata-rata, jumlah lulus, jumlah tidak lulus).
5. Isi file dibaca kembali dan ditampilkan ke konsol.

## Contoh Penggunaan

**Input:**

```
Masukkan jumlah siswa: 3

Data siswa ke-1
Nama  : andi
Nilai : 78

Data siswa ke-2
Nama  : dion
Nilai : 65

Data siswa ke-3
Nama  : ayra
Nilai : 82
```

**Output (`laporan_nilai.txt`):**

```
===== LAPORAN NILAI SISWA =====
No   Nama                 Nilai    Status      
-------------------------------------------------
1    andi                 78,00    Lulus       
2    dion                 65,00    Tidak Lulus 
3    ayra                 82,00    Lulus       
-------------------------------------------------
Rata-rata nilai kelas : 75,00
Jumlah siswa lulus    : 2
Jumlah tidak lulus    : 1
```

Contoh hasil dengan 13 siswa (rata-rata kelas 83,85, semua lulus) ada di file `laporan_nilai.txt`.

> Pemisah desimal mengikuti locale sistem. Di sistem berlocale Indonesia hasilnya `78,00`, di locale lain bisa `78.00`.

## Aturan Kelulusan

| Kondisi         | Status      |
| --------------- | ----------- |
| Nilai >= 70     | Lulus       |
| Nilai < 70      | Tidak Lulus |

Batas kelulusan bisa diubah lewat konstanta `NILAI_LULUS` di `LaporanNilai.java`:

```java
static final double NILAI_LULUS = 70.0;
```

## Penanganan Error

| Kasus                                      | Perilaku program                                       |
| ------------------------------------------ | ------------------------------------------------------ |
| Jumlah atau nilai diisi bukan angka        | Menampilkan pesan "Input tidak valid" lalu berhenti    |
| Gagal menulis file (misal tidak ada izin)  | Menampilkan pesan "Gagal menulis file" lalu berhenti   |
| Gagal membaca file                         | Menampilkan pesan "Gagal membaca file"                 |

Nilai desimal diketik dengan titik (contoh: `85.5`), bukan koma.

## Catatan

- File `laporan_nilai.txt` akan **ditimpa** setiap program dijalankan.
- File output dibuat di *working directory* tempat program dijalankan.

## Penulis

Fatih Atha Haya
