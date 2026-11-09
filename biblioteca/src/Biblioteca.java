import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Gestisce il catalogo dei libri della biblioteca.
 */
public class Biblioteca {

    private final List<Libro> libri = new ArrayList<>();

    /** Aggiunge un libro al catalogo. */
    public void aggiungi(Libro libro) {
        libri.add(libro);
    }

    /** Restituisce una copia della lista dei libri. */
    public List<Libro> elenca() {
        return new ArrayList<>(libri);
    }

    /** Cerca i libri il cui titolo contiene la stringa indicata (case-insensitive). */
    public List<Libro> cercaPerTitolo(String titolo) {
        String filtro = titolo.toLowerCase(Locale.ROOT);
        List<Libro> risultati = new ArrayList<>();
        for (Libro libro : libri) {
            if (libro.getTitolo().toLowerCase(Locale.ROOT).contains(filtro)) {
                risultati.add(libro);
            }
        }
        return risultati;
    }

    /** Rimuove il libro con l'ISBN indicato. Restituisce true se è stato rimosso. */
    public boolean rimuovi(String isbn) {
        return libri.removeIf(libro -> libro.getIsbn().equalsIgnoreCase(isbn));
    }

    /** Restituisce il numero di libri nel catalogo. */
    public int dimensione() {
        return libri.size();
    }
}
