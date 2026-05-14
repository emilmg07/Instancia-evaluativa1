package com.mycompany.interfazgrafica;
/**
 *
 * @author Djerez
 */
public class materiaCuatrimestral extends Materia {
    private int cuatrimestre;

    public materiaCuatrimestral(String nombre, String codigo, int cuatrimestre, int anio) {
        super(nombre, codigo, anio);
        // Validación: Cuatrimestre debe ser 1 o 2
        if (cuatrimestre != 1 && cuatrimestre != 2) {
            throw new IllegalArgumentException("El cuatrimestre debe ser 1 o 2.");
        }
        this.cuatrimestre = cuatrimestre;
    }

    public int getCuatrimestre() { 
        return cuatrimestre; 
    }
}
