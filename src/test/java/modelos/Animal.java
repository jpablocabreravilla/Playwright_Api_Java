package modelos;

public record Animal(
        int id,
        String nombre,
        String tipo,
        int edad,
        double peso,
        String genero,
        Amo amo
) {
}