package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HYPE AMD
 */

/**
 * Kelas data Mahasiswa.
 * Atribut: nama (String), nim (String), nilai (double).
 */
public class Mahasiswa {

    // Konstanta standar kelulusan (final)
    public static final double FINAL_PASSING_GRADE = 70.0;

    private String nama;   // reference type
    private String nim;    // reference type
    private double nilai;  // primitive type

    /**
     * Constructor: merapikan nama/NIM, memvalidasi NIM & nilai.
     *
     * @throws NilaiInvalidException jika nilai < 0 atau > 100
     */
    public Mahasiswa(String nama, String nim, double nilai) throws NilaiInvalidException {
        // Rapikan input dengan String manipulation
        String namaRapi = nama.trim().toUpperCase();
        String nimRapi = nim.trim();

        // Assertion: NIM tidak boleh kosong (aktif jika dijalankan dengan flag -ea)
        assert !nimRapi.isEmpty() : "NIM tidak boleh kosong!";

        // Validasi tambahan (tetap jalan walau assertion dimatikan)
        if (nimRapi.isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh kosong!");
        }

        // Validasi NIM hanya angka memakai Character.isDigit()
        for (char c : nimRapi.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(
                        "NIM hanya boleh berisi angka! Ditemukan karakter '" + c + "'");
            }
        }

        // Validasi nilai -> Custom Exception
        if (nilai < 0 || nilai > 100) {
            throw new NilaiInvalidException(
                    "Nilai " + nilai + " tidak valid! Harus antara 0 sampai 100.");
        }

        this.nama = namaRapi;
        this.nim = nimRapi;
        this.nilai = nilai;
    }

    // ===== Getter =====
    public String getNama() { return nama; }
    public String getNim()  { return nim; }
    public double getNilai() { return nilai; }

    /** Memetakan nilai ke grade huruf (If-Else). */
    public String getGrade() {
        if (nilai >= 85) {
            return "A";
        } else if (nilai >= 70) {
            return "B";
        } else if (nilai >= 55) {
            return "C";
        } else {
            return "D";
        }
    }

    /** Lulus jika nilai >= FINAL_PASSING_GRADE (primitive boolean). */
    public boolean isLulus() {
        return nilai >= FINAL_PASSING_GRADE;
    }

    public String getStatus() {
        return isLulus() ? "LULUS" : "TIDAK LULUS";
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-25s | %6.2f | %-5s | %s",
                nim, nama, nilai, getGrade(), getStatus());
    }
}
