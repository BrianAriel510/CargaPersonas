package Practica1Spring.dao;

import Practica1Spring.domain.Persona;
import org.springframework.data.repository.CrudRepository;

public interface IpersonaDao extends CrudRepository<Persona, Long> {

}
