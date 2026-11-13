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
 * Custom Exception: dilempar jika nilai kurang dari 0 atau lebih dari 100.
 * Turunan dari Exception (checked exception), jadi wajib ditangani (try-catch / throws).
 */
public class NilaiInvalidException extends Exception {

    public NilaiInvalidException(String message) {
        super(message);
    }
}
