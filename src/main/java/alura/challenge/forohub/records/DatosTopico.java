package alura.challenge.forohub.records;

import alura.challenge.forohub.model.TopicosModel;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

public record DatosTopico(
  @NotBlank
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
