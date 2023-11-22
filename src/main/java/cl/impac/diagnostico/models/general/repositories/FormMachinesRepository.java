package cl.impac.diagnostico.models.general.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.impac.diagnostico.models.general.entities.FormMachines;

@Repository
public interface FormMachinesRepository extends JpaRepository<FormMachines, Long> {

}
