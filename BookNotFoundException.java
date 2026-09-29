/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HYPE AMD
 */
package library.exception;

/**
 * Dilempar ketika buku yang dicari/ditransaksikan tidak ditemukan
 * di dalam koleksi perpustakaan.
 */
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}