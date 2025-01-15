package alura.challenge.forohub.controller;

import alura.challenge.forohub.model.TopicosModel;
import alura.challenge.forohub.records.DatosTopico;
import alura.challenge.forohub.repository.TopicosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

  @Autowired
  private TopicosRepository topicosRepository;

  @PostMapping
  public ResponseEntity<DatosTopico> crearTopico(@RequestBody @Valid DatosTopico datosTopico, UriComponentsBuilder uriComponentsBuilder){
    TopicosModel topicos = topicosRepository.save(new TopicosModel(datosTopico));

    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();
    return ResponseEntity.created(url).body(datosTopico);
  }

  @GetMapping
  public ResponseEntity<Page<TopicosModel>> listarTopicos(@PageableDefault(size = 10) Pageable pageable){
    return ResponseEntity.ok(topicosRepository.findAll(pageable));
  }
}
