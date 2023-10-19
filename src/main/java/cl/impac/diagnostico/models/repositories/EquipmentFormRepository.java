package cl.impac.diagnostico.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.impac.diagnostico.models.entities.EquipmentForm;

@Repository
public interface EquipmentFormRepository extends JpaRepository<EquipmentForm, Long> {

}
