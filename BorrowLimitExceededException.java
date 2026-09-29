/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.exception;

/**
 *
 * @author HYPE AMD
 */
public class BorrowLimitExceededException extends Exception {
    public BorrowLimitExceededException(String message) {
        super(message);
    }
}