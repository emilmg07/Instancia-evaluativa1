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
		case 3: // Registrar asistencia

    System.out.print("\nIngrese el codigo de la materia: ");
    String codigoAsistencia = sc.nextLine();

    InscripcionMateria insAsistencia = alumno.buscarPorCodigo(codigoAsistencia);

    if (insAsistencia == null) {
        System.out.println("[ERROR] No estas inscripto en esa materia.");
        break;
    }

    System.out.println("Materia: " + insAsistencia.getMateria().getNombre());

    System.out.print("El estudiante estuvo presente? (S/N): ");
    String respuesta = sc.nextLine();

    boolean presente = respuesta.equalsIgnoreCase("S");

    insAsistencia.registrarAsistencia(presente);

    System.out.printf("Asistencia actualizada: %.2f%%%n",
            insAsistencia.getPorcentajeAsistencia());

    System.out.println("Condicion actual: "
            + insAsistencia.getCondicion());

    if (insAsistencia.getPorcentajeAsistencia() < 75) {

        System.out.println("[ALERTA CRITICA] Pierde regularidad.");

    } else if (insAsistencia.getPorcentajeAsistencia() < 80) {

        System.out.println("[ADVERTENCIA] Zona de riesgo.");
    }

    break;

case 4: // Registrar calificacion

    System.out.print("\nIngrese el codigo de la materia: ");
    String codigoNota = sc.nextLine();

    InscripcionMateria insNota = alumno.buscarPorCodigo(codigoNota);

    if (insNota == null) {
        System.out.println("[ERROR] No estas inscripto en esa materia.");
        break;
    }

    try {

        System.out.println("Materia: "
                + insNota.getMateria().getNombre());

        System.out.print("Ingrese la nota (0 a 10): ");

        double nota = Double.parseDouble(sc.nextLine());

        boolean agregada = insNota.agregarNota(nota);

        if (agregada) {

            if (nota >= 6) {
                System.out.println("Parcial/TP aprobado.");
            } else {
                System.out.println("Parcial/TP desaprobado.");
            }

            System.out.println("\nNotas cargadas:");

            for (double n : insNota.getNotas()) {
                System.out.println("- " + n);
            }

            System.out.printf("Promedio actualizado: %.2f%n",
                    insNota.getPromedio());
        }

    } catch (NumberFormatException e) {

        System.out.println("[ERROR] Debe ingresar un numero valido.");
       
    }
    
    ////////////////////////////////////////////////////////////////////////
   
    case 5: // Ver reportes
            System.out.println("\n=== REPORTES ACADEMICOS ===");
            System.out.println("1. Reporte de situacion general");
            System.out.println("2. Reporte de materias en riesgo");
            System.out.println("3. Reporte de materias aprobadas");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            
            // 1. Inicializamos la variable en -1 para evitar el error del compilador
            int opcionReporte = -1; 
            
            try {
                opcionReporte = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("[ERROR] Opcion invalida.");
                break; // Agregamos este break para que aborte la operacion si ingresa un caracter no valido
            }

            // Instanciamos tu nueva clase
            ReportesAcademicos gestorReportes = new ReportesAcademicos();
            
            // Obtenemos la lista de materias del alumno
            ArrayList<InscripcionMateria> listaParaReportes = alumno.getMaterias();

            switch (opcionReporte) {
                case 1:
                    gestorReportes.mostrarSituacionGeneral(listaParaReportes);
                    break; // 2. Agregamos los break que faltaban en cada caso
                    
                case 2:
                    gestorReportes.mostrarMateriasEnRiesgo(listaParaReportes);
                    break; 
                    
                case 3:
                    gestorReportes.mostrarMateriasAprobadas(listaParaReportes);
                    break; 
                    
                case 0:
                    break; 
                    
                default:
                    System.out.println("Opcion invalida.");
                    break; 
            }
            
            break; // 3. Este break cierra el case 5 del menu principal
                
            }
        } while (opcion != 0);
 
        sc.close();
    }

    
}