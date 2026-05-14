package com.mycompany.interfazgrafica;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Djerez
 */
public class PerfilEstudiante {

    String nombre;
    String codigo;
    String carrera;
    int ingreso;

    public PerfilEstudiante(String nombre, String codigo, String carrera, int ingreso) {

        this.nombre = nombre;
        this.codigo = codigo;
        this.carrera = carrera;
        this.ingreso = ingreso;
    }

    public void mostrarPerfil() {

        System.out.println("\n=== PERFIL DEL ESTUDIANTE ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Codigo: " + codigo);
        System.out.println("Carrera: " + carrera);
        System.out.println("Anio de ingreso: " + ingreso);
    }
}