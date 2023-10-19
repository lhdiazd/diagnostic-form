package cl.impac.diagnostico.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.impac.diagnostico.models.entities.DiagnosticQuestion;

@Repository
public interface DiagnosticQuestionRepository extends JpaRepository<DiagnosticQuestion, Long> {
	
}
