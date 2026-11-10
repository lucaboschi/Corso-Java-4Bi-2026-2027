import java.time.LocalDate;

/** Un prestito associa esattamente un Cliente a una CopiaLibro. */
public class Prestito {
    private String id;
    private LocalDate dataInizio;
    private LocalDate dataScadenza;
    private LocalDate dataRestituzione; // null finche' il prestito e' attivo

    // Lati singoli delle associazioni 1 -- 0..N.
    private Cliente cliente;
    private CopiaLibro copiaLibro;

    public Prestito(String id, LocalDate dataInizio, LocalDate dataScadenza,
                    Cliente cliente, CopiaLibro copiaLibro) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'id del prestito e' obbligatorio");
        }
        if (dataInizio == null || dataScadenza == null) {
            throw new IllegalArgumentException("Le date sono obbligatorie");
        }
        if (dataScadenza.isBefore(dataInizio)) {
            throw new IllegalArgumentException("Scadenza precedente all'inizio");
        }
        if (cliente == null || copiaLibro == null) {
            throw new IllegalArgumentException("Cliente e copia sono obbligatori");
        }
        if (!copiaLibro.prestabile()) {
            throw new IllegalStateException("La copia non e' disponibile");
        }
        this.id = id;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
        this.dataRestituzione = null;
        this.cliente = cliente;
        this.copiaLibro = copiaLibro;

        // Entrambi gli estremi dell'associazione conoscono il prestito.
        copiaLibro.aggiungiPrestito(this);
        cliente.aggiungiPrestito(this);
    }

    public String getId() {
        return id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public LocalDate getDataRestituzione() {
        return dataRestituzione;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public CopiaLibro getCopiaLibro() {
        return copiaLibro;
    }

    public boolean attivo() {
        return dataRestituzione == null;
    }

    public void registraRestituzione(LocalDate data) {
        if (data == null || data.isBefore(dataInizio)) {
            throw new IllegalArgumentException("Data di restituzione non valida");
        }
        if (!attivo()) {
            throw new IllegalStateException("Il prestito e' gia' stato restituito");
        }
        dataRestituzione = data;
        copiaLibro.terminaPrestito(this);
    }

    @Override
    public String toString() {
        return "Prestito " + id + " | cliente " + cliente.getId()
                + " | copia " + copiaLibro.getBarcode()
                + " | dal " + dataInizio + " al " + dataScadenza
                + " | restituzione: "
                + (dataRestituzione == null ? "non ancora effettuata" : dataRestituzione);
    }
}
