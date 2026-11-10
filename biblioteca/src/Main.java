import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Test prestito biblioteca ===");

        Libro libro = new Libro("978-88-12345-01-2", "Il nome della rosa", "Umberto Eco", 1980);
        CopiaLibro copia = new CopiaLibro("ABC-001", libro, StatoCopia.DISPONIBILE);
        Cliente cliente = new Cliente(1, "Mario", "Rossi");

        System.out.println("Libro iniziale: " + libro);
        System.out.println("Copia iniziale: " + copia);

        Prestito prestito = new Prestito(
                "P-001",
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 10),
                cliente,
                copia
        );

        System.out.println("Prestito creato: " + prestito);
        System.out.println("Stato copia dopo prestito: " + copia.getStato());

        prestito.registraRestituzione(LocalDate.of(2026, 11, 5));

        System.out.println("Prestito dopo restituzione: " + prestito);
        System.out.println("Stato copia dopo restituzione: " + copia.getStato());

        System.out.println("Numero prestiti cliente: " + cliente.getNumeroPrestiti());
        System.out.println("Numero copie libro: " + libro.getNumeroCopie());
    }
}
