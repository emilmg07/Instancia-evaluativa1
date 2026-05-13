package com.mycompany.interfazgrafica;
/**
 *
 * @author Djerez
 */
import java.util.ArrayList;

public class Alumno {

    private String nombre;
    private String codigo;
    private String carrera;
    private int ingreso;
    // Lista para gestionar las materias asociadas al alumno
    private ArrayList<InscripcionMateria> materias;

    public Alumno(String nombre, String codigo, String carrera, int ingreso) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.carrera = carrera;
        this.ingreso = ingreso;
        this.materias = new ArrayList<>(); // Inicialización de la lista
    }

    // Método exigido por el case 1 de tu menú principal
    public void mostrarResumen() {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("PERFIL DEL ESTUDIANTE");
        System.out.println("=".repeat(55));
        System.out.println("Nombre: " + nombre);
        System.out.println("Codigo: " + codigo);
        System.out.println("Carrera: " + carrera);
        System.out.println("Anio de ingreso: " + ingreso);
        System.out.println("Materias Inscriptas: " + materias.size());
        System.out.println("=".repeat(55));
    }

    // Retorna la lista completa requerida por el case 3 del menú
    public ArrayList<InscripcionMateria> getMaterias() {
        return materias;
    }

    // Alta con Validación: No permitir inscripción duplicada (mismo código)
    public void inscribirse(Materia nuevaMateria) {
        for (InscripcionMateria ins : materias) {
            if (ins.getMateria().getCodigo().equalsIgnoreCase(nuevaMateria.getCodigo())) {
                // Lanza excepción para que el catch del main la muestre en consola
                throw new IllegalArgumentException("Ya estás inscripto en la materia con código " + nuevaMateria.getCodigo());
            }
        }
        
        // Si no está duplicada, se crea la inscripción y se añade a la lista
        InscripcionMateria nuevaInscripcion = new InscripcionMateria(nuevaMateria);
        materias.add(nuevaInscripcion);
        System.out.println("\n[ÉXITO] Inscripción completada correctamente.");
    }

    // Baja de materia por código (case 2)
    public void darDeBaja(String codigoMateria) {
        InscripcionMateria encontrada = null;
        for (InscripcionMateria ins : materias) {
            if (ins.getMateria().getCodigo().equalsIgnoreCase(codigoMateria)) {
                encontrada = ins;
                break;
            }
        }

        if (encontrada != null) {
            materias.remove(encontrada);
            System.out.println("[ÉXITO] Se dio de baja la materia: " + encontrada.getMateria().getNombre());
        } else {
            System.out.println("[ERROR] No estás inscripto en ninguna materia con el código: " + codigoMateria.toUpperCase());
        }
    }

    // Búsqueda parcial por nombre (case 4 - opción 1)
    public ArrayList<InscripcionMateria> buscarPorNombre(String nombreBuscar) {
        ArrayList<InscripcionMateria> resultados = new ArrayList<>();
        String query = nombreBuscar.toLowerCase();
        
        for (InscripcionMateria ins : materias) {
            if (ins.getMateria().getNombre().toLowerCase().contains(query)) {
                resultados.add(ins);
            }
        }
        return resultados;
    }

    // Búsqueda exacta/parcial por código (case 4 - opción 2)
    public InscripcionMateria buscarPorCodigo(String codigoBuscar) {
        for (InscripcionMateria ins : materias) {
            if (ins.getMateria().getCodigo().equalsIgnoreCase(codigoBuscar.trim())) {
                return ins;
            }
        }
        return null; // Devuelve null si no encuentra coincidencias
    }
}
