/**
 * Rappresenta un libro nel catalogo della biblioteca.
 */
public class Libro {

    private final String titolo;
    private final String autore;
    private final int anno;
    private final String isbn;
    private boolean disponibile;

    public Libro(String titolo, String autore, int anno, String isbn) {
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.isbn = isbn;
        this.disponibile = true;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public int getAnno() {
        return anno;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    @Override
    public String toString() {
        return String.format("%-30s | %-20s | %4d | %-15s | %s",
                titolo, autore, anno, isbn,
                disponibile ? "disponibile" : "preso in prestito");
    }
}
