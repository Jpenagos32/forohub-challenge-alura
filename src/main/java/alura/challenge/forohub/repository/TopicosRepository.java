package alura.challenge.forohub.repository;

import alura.challenge.forohub.model.TopicosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicosRepository extends JpaRepository<TopicosModel, Long> {
}
