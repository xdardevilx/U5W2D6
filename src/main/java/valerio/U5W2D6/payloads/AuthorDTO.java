package valerio.U5W2D6.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthorDTO(
        @NotEmpty(message = "il nome è obbligatorio")
        String name,
        @NotEmpty(message = "il cognome è obbligatorio")
        String surname,

        @NotEmpty(message = "l'email è obbligatoria")
        @Email(message = "l'email non è valida")
        String email,

        @NotNull(message = "data di nascita è obbligatoria")
        String birthDate,

        @NotEmpty
        String avatar
        ) {
}
