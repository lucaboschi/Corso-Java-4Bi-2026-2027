/** Descrizione bibliografica; un libro puo' avere piu' copie fisiche. */
public class Libro {
    public static final int MAXCOPIE = 100;

    private String isbn;
    private String titolo;
    private String autore;
    private int annoPubblicazione;

    // Associazione Libro (1) -- (0..N) CopiaLibro.
    private CopiaLibro[] copie;
    private int numeroCopie;

    public Libro(String isbn, String titolo, String autore, int annoPubblicazione) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        copie = new CopiaLibro[MAXCOPIE];
        numeroCopie = 0;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getNumeroCopie() {
        return numeroCopie;
    }

    /** Nuovo array che contiene le sole copie esistenti. */
    public CopiaLibro[] getListaCopie() {
        CopiaLibro[] risultato = new CopiaLibro[numeroCopie];
        for (int i = 0; i < numeroCopie; i++) {
            risultato[i] = copie[i];
        }
        return risultato;
    }

    /** Metodo di supporto dell'associazione: chiamato da CopiaLibro. */
    void aggiungiCopia(CopiaLibro copia) {
        if (copia == null || copia.getLibro() != this) {
            throw new IllegalArgumentException("La copia non appartiene al libro");
        }
        for (int i = 0; i < numeroCopie; i++) {
            if (copie[i] == copia) {
                return;
            }
        }
        if (numeroCopie == copie.length) {
            CopiaLibro[] piuGrande = new CopiaLibro[copie.length * 2];
            for (int i = 0; i < numeroCopie; i++) {
                piuGrande[i] = copie[i];
            }
            copie = piuGrande;
        }
        copie[numeroCopie] = copia;
        numeroCopie++;
    }

    @Override
    public String toString() {
        return titolo + " - " + autore + " (ISBN: " + isbn
                + ", anno: " + annoPubblicazione
                + ", copie: " + numeroCopie + ")";
    }
}
