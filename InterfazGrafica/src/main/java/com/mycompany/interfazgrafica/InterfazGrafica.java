package com.mycompany.interfazgrafica;
/**
 *
 * @author Djerez
 */


import java.util.Scanner;
import java.util.ArrayList;


public class InterfazGrafica {    

    
    public static void main(String[] args) {
       
         Scanner sc = new Scanner(System.in); 
        Alumno alumno = new Alumno("Agustina Bosco", "22001", "Probabilidad y Estadistica", 2023);
 
        int opcion;
        sc.nextLine();
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Ver perfil");
            System.out.println("2. Gestion de materias");
            System.out.println("3. Registrar asistencia");
            System.out.println("4. Registrar calificacion");
            System.out.println("5. Ver reportes");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");         
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Error: Debes ingresar una opcion valida.");
                opcion = -1;
                continue;
            }
 
            switch (opcion) {
 
                case 1: // Ver perfil
                    alumno.mostrarResumen();
                    break;
 
                case 2: // Gestion de materias
                    int opcion2;
                    System.out.println("\n=== GESTION DE MATERIAS ===");
                    System.out.println("1. Inscribirse a una materia");
                    System.out.println("2. Dar de baja una materia");
                    System.out.println("3. Listado de materias inscriptas");
                    System.out.println("4. Buscar materia por codigo o nombre");
                    System.out.println("0. Volver");
                    System.out.print("Opcion: ");
                    try {
                        opcion2 = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Error: opcion invalida.");
                        break;
                    }
 
                    switch (opcion2) {
                         
                        case 1: // Inscribirse
                                         boolean datoValido = false;
                                     while (!datoValido) {
                                           try {
                                                    System.out.print("\nNombre de la materia: ");
                                                    String nombreMateria = sc.nextLine();
            
                                                     System.out.print("Codigo (3 a 10 caracteres): ");
                                                     String codigo = sc.nextLine();
            
                                                     System.out.print("Anio: ");
                                                     int anio = Integer.parseInt(sc.nextLine());
            
                                                     // Primero preguntamos el tipo de materia
                                                     System.out.println("Tipo: 1. Cuatrimestral  2. Anual");
                                                     System.out.print("Opcion: ");
                                                     int tipo = Integer.parseInt(sc.nextLine());

                                                     Materia nuevaMateria;
                                                 if (tipo == 2) {
                                                    // Si es anual, creamos el objeto directamente sin pedir cuatrimestre
                                                     nuevaMateria = new materiaAnual(nombreMateria, codigo, anio);
                                                       } else if (tipo == 1) {
                                                              // Si es cuatrimestral, recién aquí solicitamos el cuatrimestre
                                                          System.out.print("Cuatrimestre (1 o 2): ");
                                                            int cuatrimestre = Integer.parseInt(sc.nextLine());
                                                         nuevaMateria = new materiaCuatrimestral(nombreMateria, codigo, cuatrimestre, anio);
                                                             } else {
                                                                    // Si ingresa una opción de tipo inválida (ej. 3)
                                                             throw new IllegalArgumentException("Tipo de materia no válido (Debe ser 1 o 2).");
                                                 }
            
                                                  alumno.inscribirse(nuevaMateria);
                                                  datoValido = true;
            
                                                  } catch (NumberFormatException e) {
                                                  System.out.println("\n[ERROR] Debe ingresar un número válido para el año, cuatrimestre o tipo.");
                                                  System.out.println("Por favor, intente nuevamente.\n");
                                                  } catch (IllegalArgumentException e) {
                                                  System.out.println("\n[ERROR] " + e.getMessage());
                                                  System.out.println("Por favor, intente nuevamente.\n");
                                                      }
                                }
                        break;
 
                        case 2: // Dar de baja
                            System.out.print("\nIngrese el codigo de la materia: ");
                            String codigoBaja = sc.nextLine();
                            alumno.darDeBaja(codigoBaja);
                            break;
 
                        case 3: // Listar materias
                            ArrayList<InscripcionMateria> lista = alumno.getMaterias();
                            System.out.println("\nListado de materias inscriptas:");
                            if (lista.isEmpty()) {
                                System.out.println("No hay materias registradas.");
                            } else {
                                for (InscripcionMateria ins : lista) {
                                    System.out.printf("- %-20s | %-8s | Asistencia: %5.1f%% | Promedio: %.2f%n",
                                        ins.getMateria().getNombre(),
                                        ins.getCondicion(),
                                        ins.getPorcentajeAsistencia(),
                                        ins.getPromedio()
                                    );
                                }
                            }
                            break;
 
                        case 4: // Buscar
                            System.out.println("\n1. Buscar por nombre");
                            System.out.println("2. Buscar por codigo");
                            System.out.print("Opcion: ");
                            int opcion3;
                            try {
                                opcion3 = Integer.parseInt(sc.nextLine());
                            } catch (Exception e) {
                                System.out.println("Opcion invalida.");
                                break;
                            }
                            switch (opcion3) {
                                case 1:
                                    System.out.print("\nIngrese el nombre (o parte): ");
                                    String nombre = sc.nextLine();
                                    ArrayList<InscripcionMateria> resultadosNombre = alumno.buscarPorNombre(nombre);
                                    if (resultadosNombre.isEmpty()) {
                                        System.out.println("No se encontraron materias con ese nombre.");
                                    } else {
                                        for (InscripcionMateria ins : resultadosNombre) {
                                            System.out.println("Materia encontrada!");
                                            System.out.println("Nombre  : " + ins.getMateria().getNombre());
                                            System.out.println("Codigo  : " + ins.getMateria().getCodigo());
                                            System.out.printf ("Promedio: %.2f%n", ins.getPromedio());
                                        }
                                    }
                                    break;
                                case 2:
                                    System.out.print("\nIngrese el codigo: ");
                                    String codigoBuscar = sc.nextLine();
                                    InscripcionMateria encontrada = alumno.buscarPorCodigo(codigoBuscar);
                                    if (encontrada != null) {
                                        System.out.println("Materia encontrada!");
                                        System.out.println("Nombre  : " + encontrada.getMateria().getNombre());
                                        System.out.println("Codigo  : " + encontrada.getMateria().getCodigo());
                                        System.out.printf ("Promedio: %.2f%n", encontrada.getPromedio());
                                    } else {
                                        System.out.println("No se encontro ninguna materia con ese codigo.");
                                    }
                                    break;
                                default:
                                    System.out.println("Opcion invalida.");
                                    break;
                            }
                            break;
 
                        default:
                            System.out.println("Opcion invalida.");
                            break;
                    }
                    break;
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
            }
        } while (opcion != 0);
 
        sc.close();
    }

    
}