package com.mycompany.interfazgrafica;
/**
 *
 * @author Djerez
 */
public class InscripcionMateria {
    private Materia materia;
    private String condicion;
    private double porcentajeAsistencia;
    private double promedio;

    public InscripcionMateria(Materia materia) {
        this.materia = materia;
        this.condicion = "Regular"; // Estado por defecto solicitado
        this.porcentajeAsistencia = 100.0; // Inicializado al máximo
        this.promedio = 0.0; // Promedio inicial
    }

    // Getters necesarios para el listado y búsquedas de InterfazGrafica.java
    public Materia getMateria() { 
        return materia; 
    }
    
    public String getCondicion() { 
        return condicion; 
    }
    
    public double getPorcentajeAsistencia() { 
        return porcentajeAsistencia; 
    }
    
    public double getPromedio() { 
        return promedio; 
    }

    // Setters complementarios para la gestión futura
    public void setCondicion(String condicion) { 
        this.condicion = condicion; 
    }
    
    public void setPorcentajeAsistencia(double porcentajeAsistencia) { 
        this.porcentajeAsistencia = porcentajeAsistencia; 
    }
    
    public void setPromedio(double promedio) { 
        this.promedio = promedio; 
    }
}
