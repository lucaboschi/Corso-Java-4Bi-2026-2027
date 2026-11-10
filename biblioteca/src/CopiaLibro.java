/** Singolo esemplare fisico di un Libro, con storico dei prestiti. */
public class CopiaLibro {
    public static final int MAXPRESTITI = 100;

    private String barcode;
    private StatoCopia stato;

    // Associazione: ogni copia appartiene a un solo Libro.
    private Libro libro;

    // Associazione: una copia puo' avere 0..N prestiti nel tempo.
    private Prestito[] prestiti;
    private int numeroPrestiti;

    public CopiaLibro(String barcode, Libro libro, StatoCopia stato) {
        if (barcode == null || barcode.isBlank()) {
            throw new IllegalArgumentException("Il barcode e' obbligatorio");
        }
        if (libro == null || stato == null) {
            throw new IllegalArgumentException("Libro e stato sono obbligatori");
        }
        if (stato == StatoCopia.IN_PRESTITO) {
            throw new IllegalArgumentException(
                    "Una copia nuova non puo' essere IN_PRESTITO senza un Prestito");
        }
        this.barcode = barcode;
        this.libro = libro;
        this.stato = stato;
        prestiti = new Prestito[MAXPRESTITI];
        numeroPrestiti = 0;

        // Aggiorna anche il lato Libro -> copie.
        libro.aggiungiCopia(this);
    }

    public CopiaLibro(String barcode, Libro libro) {
        this(barcode, libro, StatoCopia.DISPONIBILE);
    }

    public String getBarcode() {
        return barcode;
    }

    public StatoCopia getStato() {
        return stato;
    }

    public Libro getLibro() {
        return libro;
    }

    /** Metodo presente nell'UML, ma ridondante: restituisce questa copia. */
    public CopiaLibro getCopiaLibro() {
        return this;
    }

    /** Nuovo array contenente lo storico completo dei prestiti della copia. */
    public Prestito[] getListaPrestiti() {
        Prestito[] risultato = new Prestito[numeroPrestiti];
        for (int i = 0; i < numeroPrestiti; i++) {
            risultato[i] = prestiti[i];
        }
        return risultato;
    }

    public boolean prestabile() {
        return stato == StatoCopia.DISPONIBILE;
    }

    /** Metodo di supporto dell'associazione: chiamato da Prestito. */
    void aggiungiPrestito(Prestito prestito) {
        if (prestito == null || prestito.getCopiaLibro() != this) {
            throw new IllegalArgumentException("Il prestito non riguarda questa copia");
        }
        if (!prestabile()) {
            throw new IllegalStateException("La copia non e' prestabile");
        }
        if (numeroPrestiti == prestiti.length) {
            Prestito[] piuGrande = new Prestito[prestiti.length * 2];
            for (int i = 0; i < numeroPrestiti; i++) {
                piuGrande[i] = prestiti[i];
            }
            prestiti = piuGrande;
        }
        prestiti[numeroPrestiti] = prestito;
        numeroPrestiti++;
        stato = StatoCopia.IN_PRESTITO;
    }

    /** Metodo di supporto: chiamato alla restituzione del Prestito. */
    void terminaPrestito(Prestito prestito) {
        if (prestito == null || !prestito.attivo()) {
            throw new IllegalArgumentException("Il prestito deve essere ancora attivo");
        }
        boolean trovato = false;
        for (int i = 0; i < numeroPrestiti; i++) {
            if (prestiti[i] == prestito) {
                trovato = true;
                break;
            }
        }
        if (!trovato || stato != StatoCopia.IN_PRESTITO) {
            throw new IllegalStateException("Stato del prestito incoerente");
        }
        stato = StatoCopia.DISPONIBILE;
    }

    public void impostaNonPrestabile() {
        controllaPrestitoNonAttivo();
        stato = StatoCopia.NON_PRESTABILE;
    }

    public void impostaSmarrita() {
        controllaPrestitoNonAttivo();
        stato = StatoCopia.SMARRITA;
    }

    public void impostaDisponibile() {
        controllaPrestitoNonAttivo();
        stato = StatoCopia.DISPONIBILE;
    }

    private void controllaPrestitoNonAttivo() {
        if (stato == StatoCopia.IN_PRESTITO) {
            throw new IllegalStateException(
                    "Impossibile cambiare manualmente lo stato di una copia in prestito");
        }
    }

    @Override
    public String toString() {
        return "Copia " + barcode + " di '" + libro.getTitolo()
                + "' (" + stato + ", prestiti storici: " + numeroPrestiti + ")";
    }
}
