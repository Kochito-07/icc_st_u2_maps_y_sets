package controllers;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import models.Persona;

public class PersonaController {

    public Set<Persona> filtrarYOrdenar(List<Persona> personas, int umbral) {
        
        Set<Persona> resultado = new TreeSet<>((p1, p2) -> {
            int compEdad = Integer.compare(p2.getEdad(), p1.getEdad());
            if (compEdad != 0) {
                return compEdad;
            }
            return p1.getNombre().compareToIgnoreCase(p2.getNombre());
        });

        for (Persona persona : personas) {
            if (persona.getEdad() >= umbral) {
                resultado.add(persona);
            }
        }

        return resultado;
    }

    public Map<String, Set<String>> agruparPorRangoEdad(List<Persona> personas) {
        
        Map<String, Set<String>> grupos = new TreeMap<>();

        grupos.put("ADULTO", new LinkedHashSet<>());
        grupos.put("JOVEN", new LinkedHashSet<>());
        grupos.put("MAYOR", new LinkedHashSet<>());

        for (Persona persona : personas) {
            
            String primerNombre = persona.getNombre().split(" ")[0];

            if (persona.getEdad() <= 30) {
                grupos.get("JOVEN").add(primerNombre);
            } else if (persona.getEdad() <= 60) {
                grupos.get("ADULTO").add(primerNombre);
            } else {
                grupos.get("MAYOR").add(primerNombre);
            }
        }

        return grupos;
    }
}