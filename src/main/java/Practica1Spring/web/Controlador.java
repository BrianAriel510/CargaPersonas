package Practica1Spring.web;

import Practica1Spring.domain.Persona;
import Practica1Spring.service.PersonaService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Controlador {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {

        var listaPersonas = personaService.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");

        /*Con el AuthenticationPrincipal + pasandole el objeto User, me habilita a que pueda acceder al log.info y saber
         hizo el loggeo. Esta informacion aparece en consola*/
        log.info("usuario que hizo el loggin " + user);
        
        model.addAttribute("listaPersonas", listaPersonas);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregarPersona(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardarPersona(@Valid Persona persona, Errors errors) {
        if (errors.hasErrors()){
            return "modificar";
        }
            
        personaService.guardarPersona(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editarPersona(Persona persona, Model model){
        persona= personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    //@GetMapping ("/eliminar/{idPersona}") <-- path variable
    @GetMapping("/eliminar") //<-- Query parameter
    public String eliminarPersona(Persona persona){
        personaService.eliminarPersona(persona);
        return "redirect:/";
    }
}
