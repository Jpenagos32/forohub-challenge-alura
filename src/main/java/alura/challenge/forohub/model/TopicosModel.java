package alura.challenge.forohub.model;

import alura.challenge.forohub.records.DatosActualizarTopico;
import alura.challenge.forohub.records.DatosTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "TopicosModel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicosModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String titulo;
  private String mensaje;
  private LocalDateTime fecha;
  private String status;
  private String autor;
  private String curso;

  public TopicosModel(DatosTopico datosTopico) {
    this.titulo = datosTopico.titulo();
    this.mensaje = datosTopico.mensaje();
    this.fecha = datosTopico.fecha();
    this.status = datosTopico.status();
    this.autor = datosTopico.autor();
    this.curso = datosTopico.curso();
  }

  public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
    this.titulo = datosActualizarTopico.titulo();
    this.mensaje = datosActualizarTopico.mensaje();
    this.fecha = datosActualizarTopico.fecha();
    this.status = datosActualizarTopico.status();
    this.autor = datosActualizarTopico.autor();
    this.curso = datosActualizarTopico.curso();
  }
}
