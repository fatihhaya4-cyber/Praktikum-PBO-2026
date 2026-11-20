/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.kasir;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Tampilan GUI Kasir Kantin UNS (Tahap 3) beserta tombol Cetak Struk (Tahap 4).
 */
public class KasirFrame extends JFrame {
    private JTextField txtMenu, txtHarga, txtJumlah;
    private JTextArea areaStruk;
    private JLabel lblTotal, lblStatus;
    private JButton btnTambah, btnHitung, btnCetak;
    private Pesanan pesanan = new Pesanan();

    private final NumberFormat formatRupiah = NumberFormat.getInstance(Locale.forLanguageTag("id-ID"));

    public KasirFrame() {
        formatRupiah.setMaximumFractionDigits(0);

        setTitle("Kasir Kantin UNS");
        setSize(400, 450);
        setLayout(null);

        // --- Judul ---
        JLabel lblJudul = new JLabel("Kasir Kantin UNS");
        lblJudul.setFont(lblJudul.getFont().deriveFont(java.awt.Font.BOLD, 16f));
        lblJudul.setBounds(20, 10, 350, 25);
        add(lblJudul);

        // --- Input ---
        JLabel lblMenu = new JLabel("Nama Menu :");
        lblMenu.setBounds(20, 45, 90, 25);
        add(lblMenu);
        txtMenu = new JTextField();
        txtMenu.setBounds(120, 45, 250, 25);
        add(txtMenu);

        JLabel lblHarga = new JLabel("Harga :");
        lblHarga.setBounds(20, 75, 90, 25);
        add(lblHarga);
        txtHarga = new JTextField();
        txtHarga.setBounds(120, 75, 250, 25);
        add(txtHarga);

        JLabel lblJumlah = new JLabel("Jumlah :");
        lblJumlah.setBounds(20, 105, 90, 25);
        add(lblJumlah);
        txtJumlah = new JTextField();
        txtJumlah.setBounds(120, 105, 250, 25);
        add(txtJumlah);

        // --- Tombol ---
        btnTambah = new JButton("Tambah");
        btnTambah.setBounds(40, 140, 130, 28);
        add(btnTambah);

        btnHitung = new JButton("Hitung Total");
        btnHitung.setBounds(200, 140, 130, 28);
        add(btnHitung);

        // --- Area struk ---
        areaStruk = new JTextArea();
        areaStruk.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaStruk);
        scroll.setBounds(20, 180, 350, 120);
        add(scroll);

        // --- Label total ---
        lblTotal = new JLabel("Total Bayar : Rp 0");
        lblTotal.setBounds(20, 310, 350, 25);
        add(lblTotal);

        // --- Tahap 4: tombol cetak dan label status ---
        btnCetak = new JButton("Cetak Struk");
        btnCetak.setBounds(20, 345, 130, 28);
        add(btnCetak);

        lblStatus = new JLabel("Status : -");
        lblStatus.setBounds(20, 380, 350, 25);
        add(lblStatus);

        // --- Event handler ---
        btnTambah.addActionListener(e -> tambahItem());
        btnHitung.addActionListener(e -> hitungTotal());
        btnCetak.addActionListener(e -> cetakStruk());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Tombol Tambah + validasi
    private void tambahItem() {
        String menu = txtMenu.getText().trim();
        String hargaStr = txtHarga.getText().trim();
        String jumlahStr = txtJumlah.getText().trim();

        if (menu.isEmpty() || hargaStr.isEmpty() || jumlahStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(hargaStr);
            int jumlah = Integer.parseInt(jumlahStr);

            if (harga <= 0 || jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Harga dan jumlah harus lebih dari 0!",
                        "Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }

            pesanan.tambahItem(harga, jumlah);
            areaStruk.append(menu + " x" + jumlah + " @ Rp " + formatRupiah.format(harga)
                    + " = Rp " + formatRupiah.format(harga * jumlah) + "\n");

            txtMenu.setText("");
            txtHarga.setText("");
            txtJumlah.setText("");
            txtMenu.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan jumlah harus berupa angka!",
                    "Input Tidak Valid", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Tombol Hitung Total
    private void hitungTotal() {
        lblTotal.setText("Total Bayar : Rp " + formatRupiah.format(pesanan.hitungTotal()));
    }

    // Tombol Cetak Struk: tunggu 2 detik di thread baru agar jendela tidak macet
    private void cetakStruk() {
        btnCetak.setEnabled(false); // cegah klik ganda selama proses
        new Thread(() -> {
            // Komponen Swing diubah lewat Event Dispatch Thread
            SwingUtilities.invokeLater(() -> lblStatus.setText("Status : Mencetak struk..."));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            SwingUtilities.invokeLater(() -> {
                lblStatus.setText("Status : Struk selesai");
                btnCetak.setEnabled(true);
            });
        }).start();
    }
}