import java.util.List;
import java.util.Scanner;

/**
 * Punto di ingresso dell'applicazione console "Biblioteca".
 */
public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Biblioteca ===");
        boolean inEsecuzione = true;

        while (inEsecuzione) {
            stampaMenu();
            int scelta = leggiIntero(scanner, "Scegli un'opzione: ");

            switch (scelta) {
                case 1:
                    aggiungiLibro(biblioteca, scanner);
                    break;
                case 2:
                    elencaLibri(biblioteca);
                    break;
                case 3:
                    cercaLibro(biblioteca, scanner);
                    break;
                case 4:
                    rimuoviLibro(biblioteca, scanner);
                    break;
                case 5:
                    inEsecuzione = false;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida, riprova.");
            }
        }
        scanner.close();
    }

    private static void stampaMenu() {
        System.out.println();
        System.out.println("1. Aggiungi libro");
        System.out.println("2. Elenca libri");
        System.out.println("3. Cerca per titolo");
        System.out.println("4. Rimuovi libro (per ISBN)");
        System.out.println("5. Esci");
    }

    private static void aggiungiLibro(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine().trim();
        System.out.print("Autore: ");
        String autore = scanner.nextLine().trim();
        System.out.print("Anno: ");
        int anno = leggiIntero(scanner, "Anno: ");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        if (titolo.isEmpty() || autore.isEmpty() || isbn.isEmpty()) {
            System.out.println("Titolo, autore e ISBN non possono essere vuoti.");
            return;
        }

        biblioteca.aggiungi(new Libro(titolo, autore, anno, isbn));
        System.out.println("Libro aggiunto con successo.");
    }

    private static void elencaLibri(Biblioteca biblioteca) {
        List<Libro> libri = biblioteca.elenca();
        if (libri.isEmpty()) {
            System.out.println("Il catalogo è vuoto.");
            return;
        }
        System.out.println();
        System.out.println(String.format("%-30s | %-20s | %4s | %-15s | %s",
                "Titolo", "Autore", "Anno", "ISBN", "Stato"));
        System.out.println("-".repeat(90));
        for (Libro libro : libri) {
            System.out.println(libro);
        }
        System.out.println("Totale: " + libri.size() + " libro/i");
    }

    private static void cercaLibro(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Titolo da cercare: ");
        String titolo = scanner.nextLine().trim();
        List<Libro> risultati = biblioteca.cercaPerTitolo(titolo);

        if (risultati.isEmpty()) {
            System.out.println("Nessun libro trovato per \"" + titolo + "\".");
            return;
        }
        System.out.println();
        for (Libro libro : risultati) {
            System.out.println(libro);
        }
    }

    private static void rimuoviLibro(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("ISBN del libro da rimuovere: ");
        String isbn = scanner.nextLine().trim();

        if (biblioteca.rimuovi(isbn)) {
            System.out.println("Libro rimosso con successo.");
        } else {
            System.out.println("Nessun libro trovato con ISBN " + isbn + ".");
        }
    }

    private static int leggiIntero(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.");
            }
        }
    }
}
