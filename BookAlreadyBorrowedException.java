package library.exception;

/**
 * Dilempar ketika buku yang ingin dipinjam ternyata sudah
 * dalam status dipinjam (statusKetersediaan = false).
 */
public class BookAlreadyBorrowedException extends Exception {
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}