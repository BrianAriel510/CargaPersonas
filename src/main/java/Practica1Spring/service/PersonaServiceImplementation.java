package Practica1Spring.service;

import Practica1Spring.dao.IpersonaDao;
import Practica1Spring.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImplementation implements PersonaService {
    
    /*Hay que tener en cuenta que en el dao se realizan transacciones y si algo sale mal, habría que hacer
    un rollback, o en el caso que todo esté bien, un commit. Para eso, aclaro en cada método qué tipo de transaccion es.
    Tanto en el guardar como eliminar, dejo la anotaciond de transactional sola, porque son las que van a hacer rollback
    o commit. Listar y encontrar, solo consultan la DB sin realizar cambios*/

    @Autowired
    private IpersonaDao personadao;

    @Override
    @Transactional (readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personadao.findAll();//casteo la lista porque el find all me retorna un objeto
    }

    @Override
    @Transactional 
    public void guardarPersona(Persona persona) {
        personadao.save(persona);
    }

    @Override
    @Transactional 
    public void eliminarPersona(Persona persona) {
        personadao.delete(persona);
    }

    @Override
    @Transactional (readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personadao.findById(persona.getIdPersona()).orElse(null);
        /*el agrego el .orElse porq el findById retorna un optional. Eso es por si no encuentra el valor que le
        pasamos y en su defecto, es nulo. Tambien se podría tratar con una excepcion.*/
    }

}
