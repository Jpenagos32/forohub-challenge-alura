package alura.challenge.forohub.records;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
  @NotNull
  Long id,
  @NotBlank // NotBlank es unicamente para strings
  String titulo,
  @NotBlank
  String mensaje,
  @NotNull
  @FutureOrPresent
  LocalDateTime fecha,
  @NotBlank
  String status,
  @NotBlank
  String autor,
  @NotBlank
  String curso

) {
}
