package com.mycompany.interfazgrafica;

import java.util.ArrayList;

public class ReportesAcademicos {

    // Punto 5.1: Reporte de situación general
    public void mostrarSituacionGeneral(ArrayList<InscripcionMateria> listaMaterias) {
        System.out.println("\n--- Reporte de Situacion General ---");
        
        if (listaMaterias.isEmpty()) {
            System.out.println("No hay materias registradas para analizar.");
            return;
        }

        double sumaPromediosGen = 0;
        int cantRegulares = 0, cantEnRiesgo = 0, cantLibres = 0;

        for (InscripcionMateria ins : listaMaterias) {
            String nombre = ins.getMateria().getNombre();
            String condicion = ins.getCondicion() != null ? ins.getCondicion() : "Desconocida";
            double asistencia = ins.getPorcentajeAsistencia();
            double promedio = ins.getPromedio();
            
            String estado = "En curso";
            if (promedio >= 6 && condicion.equalsIgnoreCase("Regular")) {
                estado = "Aprobada";
            } else if (asistencia < 75 || condicion.equalsIgnoreCase("Libre")) {
                estado = "Libre";
            }

            if (asistencia < 75 || condicion.equalsIgnoreCase("Libre")) {
                cantLibres++;
            } else if (asistencia >= 75 && asistencia <= 85) {
                cantEnRiesgo++;
            } else {
                cantRegulares++;
            }

            System.out.printf("Materia: %-25s | Condicion: %-8s | Asistencia: %5.1f%% | Promedio: %5.2f | Estado: %s%n",
                    nombre, condicion, asistencia, promedio, estado);
            sumaPromediosGen += promedio;
        }

        System.out.printf("\nPromedio general del estudiante: %.2f%n", (sumaPromediosGen / listaMaterias.size()));
        System.out.println("Resumen de cantidades:");
        System.out.println("- Materias Regulares: " + cantRegulares);
        System.out.println("- Materias en Riesgo: " + cantEnRiesgo);
        System.out.println("- Materias Libres:    " + cantLibres);
    }

    // Punto 5.2: Reporte de materias en riesgo
    public void mostrarMateriasEnRiesgo(ArrayList<InscripcionMateria> listaMaterias) {
        System.out.println("\n--- Reporte de Materias en Riesgo ---");
        ArrayList<InscripcionMateria> materiasRiesgo = new ArrayList<>();
        
        for (InscripcionMateria ins : listaMaterias) {
            double asis = ins.getPorcentajeAsistencia();
            if (asis >= 75 && asis <= 85) {
                materiasRiesgo.add(ins);
            }
        }

        // Ordenamiento por asistencia (método burbuja)
        for (int i = 0; i < materiasRiesgo.size() - 1; i++) {
            for (int j = 0; j < materiasRiesgo.size() - i - 1; j++) {
                if (materiasRiesgo.get(j).getPorcentajeAsistencia() > materiasRiesgo.get(j + 1).getPorcentajeAsistencia()) {
                    InscripcionMateria temp = materiasRiesgo.get(j);
                    materiasRiesgo.set(j, materiasRiesgo.get(j + 1));
                    materiasRiesgo.set(j + 1, temp);
                }
            }
        }

        if (materiasRiesgo.isEmpty()) {
            System.out.println("No hay materias en riesgo (asistencia entre 75% y 85%).");
        } else {
            for (InscripcionMateria ins : materiasRiesgo) {
                System.out.printf("Materia: %-25s | Asistencia: %5.1f%%%n", 
                        ins.getMateria().getNombre(), ins.getPorcentajeAsistencia());
            }
        }
    }

    // Punto 5.3: Reporte de materias aprobadas
    public void mostrarMateriasAprobadas(ArrayList<InscripcionMateria> listaMaterias) {
        System.out.println("\n--- Reporte de Materias Aprobadas ---");
        ArrayList<InscripcionMateria> materiasAprobadas = new ArrayList<>();
        double sumaPromAprobadas = 0;
        double maxNota = -1;
        double minNota = 11;

        for (InscripcionMateria ins : listaMaterias) {
            if (ins.getPromedio() >= 6 && (ins.getCondicion() != null && ins.getCondicion().equalsIgnoreCase("Regular"))) {
                materiasAprobadas.add(ins);
                double prom = ins.getPromedio();
                sumaPromAprobadas += prom;
                
                if (prom > maxNota) maxNota = prom;
                if (prom < minNota) minNota = prom;
            }
        }

        if (materiasAprobadas.isEmpty()) {
            System.out.println("No hay materias que cumplan los requisitos de aprobacion.");
        } else {
            for (InscripcionMateria ins : materiasAprobadas) {
                System.out.printf("Materia: %-25s | Promedio: %.2f%n", 
                        ins.getMateria().getNombre(), ins.getPromedio());
            }
            System.out.println("\nEstadisticas del conjunto de aprobadas:");
            System.out.printf("- Nota Maxima: %.2f%n", maxNota);
            System.out.printf("- Nota Minima: %.2f%n", minNota);
            System.out.printf("- Promedio del conjunto: %.2f%n", (sumaPromAprobadas / materiasAprobadas.size()));
        }
    }
}