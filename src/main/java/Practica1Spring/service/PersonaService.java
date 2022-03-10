package Practica1Spring.service;

import Practica1Spring.domain.Persona;
import java.util.List;

public interface PersonaService {

    public List<Persona> listarPersonas();

    public void guardarPersona(Persona persona);

    public void eliminarPersona(Persona persona);

    public Persona encontrarPersona(Persona persona);

}
