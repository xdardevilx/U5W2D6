package valerio.U5W2D6.payloads;

import jakarta.validation.constraints.NotEmpty;

public record BlogPostDTO(
        @NotEmpty(message = "categoria obbligatoria")
         String category,
        @NotEmpty (message = "titolo obbligatorio")
         String title,
        @NotEmpty (message = "cover obbligatoria")
         String cover,
        @NotEmpty (message = "content obbligatorio")
         String content,
        @NotEmpty (message = "reading time obbligatorio")
         double readingTime,
        @NotEmpty (message = "ai autore obbligatorio")
         int idAuthor
) {
}
