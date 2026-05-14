package com.mycompany.interfazgrafica;
/**
 *
 * @author Djerez
 */
public abstract class Materia implements Consultable {
    protected String nombre;
    protected String codigo;
    protected int anio;

    public Materia(String nombre, String codigo, int anio) {
        // Validación: El código debe tener entre 3 y 10 caracteres
        if (codigo == null || codigo.trim().length() < 3 || codigo.trim().length() > 10) {
            throw new IllegalArgumentException("El código de la materia debe tener entre 3 y 10 caracteres.");
        }
        this.nombre = nombre;
        this.codigo = codigo.trim().toUpperCase();
        this.anio = anio;
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public String getCodigo() { 
        return codigo; 
    }
    
    public int getAnio() { 
        return anio; 
    }
}