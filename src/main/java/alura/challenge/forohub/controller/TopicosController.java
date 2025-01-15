package alura.challenge.forohub.controller;

import alura.challenge.forohub.model.TopicosModel;
import alura.challenge.forohub.records.DatosActualizarTopico;
import alura.challenge.forohub.records.DatosTopico;
import alura.challenge.forohub.repository.TopicosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

  @Autowired
  private TopicosRepository topicosRepository;

  @PostMapping
  public ResponseEntity<DatosTopico> crearTopico(@RequestBody @Valid DatosTopico datosTopico, UriComponentsBuilder uriComponentsBuilder){
    TopicosModel topicos = topicosRepository.save(new TopicosModel(datosTopico));

    // Devolver en los headers la url en la que se encuentra el tópico recien creado
    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();
    return ResponseEntity.created(url).body(datosTopico);
  }

  @GetMapping
  public ResponseEntity<Page<TopicosModel>> listarTopicos(@PageableDefault(size = 10) Pageable pageable){
    return ResponseEntity.ok(topicosRepository.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DatosTopico> detallarTopico(@PathVariable Long id){
    TopicosModel topicosModel = topicosRepository.getReferenceById(id);
    DatosTopico datosTopico = new DatosTopico(
      topicosModel.getId(),
      topicosModel.getTitulo(),
      topicosModel.getMensaje(),
      topicosModel.getFecha(),
      topicosModel.getStatus(),
      topicosModel.getAutor(),
      topicosModel.getCurso()
    );
    return ResponseEntity.ok(datosTopico);
  }

  @PutMapping
  @Transactional //Esto es para que los cambios se guarden en la base de datos
  public ResponseEntity<DatosTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
    // Traer el topico de la base de datos, buscandolo por ID (el id se obtiene por requestBody)
    TopicosModel topicosModel= topicosRepository.getReferenceById(datosActualizarTopico.id());
    topicosModel.actualizarTopico(datosActualizarTopico);
    return ResponseEntity.ok(new DatosTopico(
      topicosModel.getId(),
      topicosModel.getTitulo(),
      topicosModel.getMensaje(),
      topicosModel.getFecha(),
      topicosModel.getStatus(),
      topicosModel.getAutor(),
      topicosModel.getCurso()
    ));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity eliminarTopico(@PathVariable Long id){
    Optional<TopicosModel> topicoOpcional = topicosRepository.findById(id);
    if (topicoOpcional.isPresent()){
      topicosRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el tópico en la base de datos");
    }
  }
}
