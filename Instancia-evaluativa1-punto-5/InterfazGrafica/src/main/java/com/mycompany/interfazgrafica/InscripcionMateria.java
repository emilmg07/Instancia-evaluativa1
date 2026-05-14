package com.mycompany.interfazgrafica;

import java.util.ArrayList;

public class InscripcionMateria implements Evaluable {

    private Materia materia;
    private String condicion;
    private double porcentajeAsistencia;
    private double promedio;
    private int totalClases;
    private int clasesAsistidas;
    private ArrayList<Double> notas;

    public InscripcionMateria(Materia materia) {
        this.materia = materia;
        this.condicion = "Regular";
        this.porcentajeAsistencia = 100.0;
        this.promedio = 0.0;
        this.totalClases = 0;
        this.clasesAsistidas = 0;
        this.notas = new ArrayList<>();
    }

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

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public void setPorcentajeAsistencia(double porcentajeAsistencia) {
        this.porcentajeAsistencia = porcentajeAsistencia;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public void registrarAsistencia(boolean presente) {
        totalClases++;

        if (presente) {
            clasesAsistidas++;
        }

        porcentajeAsistencia = (clasesAsistidas * 100.0) / totalClases;

        if (porcentajeAsistencia >= 75) {
            condicion = "Regular";
        } else {
            condicion = "Libre";
        }
    }

    public boolean agregarNota(double nota) {
        if (nota < 0 || nota > 10) {
            System.out.println("[ERROR] La nota debe estar entre 0 y 10.");
            return false;
        }

        if (notas.size() >= 5) {
            System.out.println("[ERROR] No se pueden cargar mas de 5 notas por materia.");
            return false;
        }

        notas.add(nota);
        calcularPromedio();
        return true;
    }

    private void calcularPromedio() {
        double suma = 0;

        for (double n : notas) {
            suma += n;
        }

        if (!notas.isEmpty()) {
            promedio = suma / notas.size();
        }
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public boolean estaAprobada() {
        return promedio >= 6 && condicion.equalsIgnoreCase("Regular");
    }
}
