package cl.impac.diagnostico.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.impac.diagnostico.models.entities.BaseCategory;

@Repository
public interface BaseCategoryRepository extends JpaRepository<BaseCategory, Long> {

}
