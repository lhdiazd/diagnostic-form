package cl.impac.diagnostico.models.general.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.impac.diagnostico.models.general.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
