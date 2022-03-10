package Practica1Spring.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Bean
    public LocaleResolver localeResolver(){ // metodo para elegir el lenguaje
        SessionLocaleResolver slr= new SessionLocaleResolver();//(i18n)
        slr.setDefaultLocale(new Locale("es")); //lista de elementos de internacionalizacion -> codigos de lenguajes
        return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;        
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){ //metodo para cambiar dinamicamente el lenguaje
        registro.addInterceptor(localeChangeInterceptor());
        
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registro){ //son path que configuran la vista. Sino tiraria el error por default
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403"); 
    }
}
