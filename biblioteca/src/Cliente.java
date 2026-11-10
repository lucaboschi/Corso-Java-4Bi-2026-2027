/** Un cliente della biblioteca: un cliente puo' avere piu' prestiti. */
public class Cliente {
    private int id;
    private String nome;
    private String cognome;

    // Associazione Cliente (1) -- (0..N) Prestito.
    // Usiamo esclusivamente un array monodimensionale.
    private Prestito[] prestiti;
    private int numeroPrestiti;

    public Cliente(int id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        prestiti = new Prestito[2];
        numeroPrestiti = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getNumeroPrestiti() {
        return numeroPrestiti;
    }

    /** Restituisce solo gli elementi utilizzati, in un nuovo array. */
    public Prestito[] getListaPrestiti() {
        Prestito[] risultato = new Prestito[numeroPrestiti];
        for (int i = 0; i < numeroPrestiti; i++) {
            risultato[i] = prestiti[i];
        }
        return risultato;
    }

    /** Metodo di supporto dell'associazione: chiamato da Prestito. */
    void aggiungiPrestito(Prestito prestito) {
        if (prestito == null || prestito.getCliente() != this) {
            throw new IllegalArgumentException("Il prestito non appartiene al cliente");
        }
        for (int i = 0; i < numeroPrestiti; i++) {
            if (prestiti[i] == prestito) {
                return; // Evita di aggiungere due volte lo stesso oggetto.
            }
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
    }

    @Override
    public String toString() {
        return "Cliente " + id + ": " + nome + " " + cognome
                + " (prestiti registrati: " + numeroPrestiti + ")";
    }
}
