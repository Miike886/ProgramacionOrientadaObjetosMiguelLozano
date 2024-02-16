package udelp.edu.process;
import java.util.ArrayList;
import java.util.List;

import udelp.edu.model.Carrera;
import udelp.edu.model.Estudiante;
import udelp.edu.model.Materia;
import udelp.edu.model.MateriaAlumno;
import udelp.edu.model.Profesor;
import udelp.edu.model.Persona.Sexo;

public class ProcesosControlEscolar {

	ProcesosPersona funcion = new ProcesosPersona();
	
	public List<Carrera> listaCarreras = new ArrayList<Carrera>();
	public List<Materia> listaMaterias = new ArrayList<Materia>();
	public List<Profesor> listaProfesores = new ArrayList<Profesor>();
	public List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
	
	public Integer buscarMateriaPorId (String id) {
		
		for (int i = 0; i < listaMaterias.size(); i++)
		{
			if (listaMaterias.get(i).getId().equals(id))
			{
				return i;
			}
		}
		 return -1;
	}
	
	public Integer buscarProfesorPorId (String id) {
		
		for (int i = 0; i < listaProfesores.size(); i++)
		{
			if (listaProfesores.get(i).getId().equals(id))
			{
				return i;
			}
		}
		 return -1;
	}
	
	public Integer buscarEstudiantePorId (String id) {
		
		for (int i = 0; i < listaEstudiantes.size(); i++)
		{
			if (listaEstudiantes.get(i).getId().equals(id))
			{
				return i;
			}
		}
		 return -1;
	}

	private int buscarPorMateria (Estudiante estudiante, Materia materia) {

		for (int i = 0; i < estudiante.getMaterias().size(); i++)
		{
			if (estudiante.getMaterias().get(i).equals(materia))
			{
				return i;
			}
		}
		return -1;
	}

	private void asignarMateriasEstudiante (Estudiante estudiante, Carrera carrera) {
		
		List<MateriaAlumno> listaMaterias = new ArrayList<MateriaAlumno>();
		
		if (carrera.getMaterias() != null)
		{
		for (int i = 0; i < carrera.getMaterias().size(); i++)
		{
			if (Integer.parseInt(carrera.getMaterias().get(i).getSemestre()) == (estudiante.getSemestre()))
			{
				MateriaAlumno materiaAlumno = new MateriaAlumno(carrera.getMaterias().get(i).getSemestre(), carrera.getMaterias().get(i).getHorario(), carrera.getMaterias().get(i).getNombre(), carrera.getMaterias().get(i).getCarrera());
				listaMaterias.add(materiaAlumno);
			}
		}
		
		estudiante.setMaterias(listaMaterias);
		}
		
	}
	
	public void altaDeCarreras (String nombreCarrera) {
		
		Carrera carrera = new Carrera (nombreCarrera.toUpperCase());
		listaCarreras.add(carrera);
	}
	
	public void altaDeMaterias (String semestre, String horario, String nombre, Carrera carrera) {
 		
		// validar si la carrea proporcionada ya ha sido dada de alta previamente
		
		Materia materia = new Materia (semestre, horario, nombre, carrera);
		listaMaterias.add(materia); 
		carrera.getMaterias().add(materia);
	}
	
	public void altaDeProfesor (String nombre, String fechaNacimiento, Sexo sexo) {

		Profesor profesor = new Profesor (nombre, fechaNacimiento, sexo);
		listaProfesores.add(profesor);
	}

	public void altaDeEstudiante (String nombre, String fechaNacimiento, Sexo sexo, Integer semestre, String generacion) {

		Estudiante estudiante = new Estudiante (nombre, fechaNacimiento, sexo, semestre, generacion);
		listaEstudiantes.add(estudiante);
	}
	
	public void asignarMateriaProfesor (Materia materia, Profesor profesor) {
		
		profesor.addMateria(materia);
	}
	
	public void asignarCarreraEstudiante (Carrera carrera, Estudiante estudiante) {
		
		estudiante.setCarrera(carrera);
		asignarMateriasEstudiante(estudiante, carrera);
		
	}

	public void agregarCalificacion (Estudiante estudiante, Materia materia, Double calificacion, int opcion) {
		
		int indice = buscarPorMateria(estudiante, materia);
		
		switch (opcion)
		{
			// Se ingresa como parámetro el número 1 para el primer parcial 
			case 1:
				estudiante.getMaterias().get(indice).setPrimerParcial(calificacion);
				break;
				
			// Número 2 para el segundo parcial 
			case 2:
				estudiante.getMaterias().get(indice).setSegundoParcial(calificacion);
				break;
				
			// Número 3 para el proyecto	
			case 3:
				estudiante.getMaterias().get(indice).setProyecto(calificacion);
				break;
				
			// Número 4 para el exámen final
			case 4:
				estudiante.getMaterias().get(indice).setExamenFinal(calificacion);
				break;

		}
		
	}
	
	public String mostrarDatosEstudiante (Estudiante estudiante) {
		
		String cadena = "Id\t|\tNombre\t|\tEdad\t|\tSexo\t|\tSemestre\t|\tGeneracion\t|\tCarrera\n";
		
		cadena += estudiante.getId() + "\t \t" + estudiante.getNombre() + "\t \t" + funcion.calcularEdad(estudiante) + "\t \t" + estudiante.getSexo() 
				+ "\t \t" + estudiante.getSemestre() + "\t \t" + estudiante.getGeneracion() + "\t \t" + estudiante.getCarrera() + "\n";
		
		return cadena;
	}
	
	public Double calcularPromedioMateria (Estudiante estudiante, MateriaAlumno materia) {
		
		int indice = buscarPorMateria(estudiante, materia);
		
		Double promedio = (estudiante.getMaterias().get(indice).getPrimerParcial() + estudiante.getMaterias().get(indice).getSegundoParcial() 
				+ estudiante.getMaterias().get(indice).getProyecto() + estudiante.getMaterias().get(indice).getExamenFinal()) / 4; 
		
		return promedio;
	}
	
	public Double calcularPromedioEstudiante (Estudiante estudiante) {
		
		Double promedio = 0D;
		
		for (int i = 0; i < estudiante.getMaterias().size(); i++)
		{
			promedio += calcularPromedioMateria(estudiante, estudiante.getMaterias().get(i));
		}
		
		promedio = promedio / estudiante.getMaterias().size();
		return promedio;
	}
	
	public Double calcularPromedioSemestreCarrera (Carrera carrera, Integer semestre) {
		
		Double promedio = 0D;
		int contadorAlumnos = 0;
		
		for (int i = 0; i < listaEstudiantes.size(); i++)
		{
			if (listaEstudiantes.get(i).getCarrera() == carrera && listaEstudiantes.get(i).getSemestre() == semestre)
			{
				promedio += calcularPromedioEstudiante(listaEstudiantes.get(i));
				contadorAlumnos++;
			}
		}

		promedio = promedio / contadorAlumnos;
		return promedio;
	}
	
	public String mostrarMaterias () {

		String cadena = "Id\t|\tNombre de materia\t|\tSemestre\t|\tHorario\t|\tCarrera\n";

		for (int i = 0; i < listaMaterias.size(); i++) 
		{
			cadena += listaMaterias.get(i).getId() + "\t \t" + listaMaterias.get(i).getNombre() + "\t \t" + listaMaterias.get(i).getSemestre() 
					+ "\t \t" + listaMaterias.get(i).getHorario() + "\t \t" + listaMaterias.get(i).getCarrera().getNombre() + "\n";
		}

		return cadena;
	}

	public String mostrarCarreras () {

		String cadena = "Carreras\n";

		for (int i = 0; i < listaCarreras.size(); i++) 
		{
			cadena += listaCarreras.get(i).getNombre() + "\n";
		}

		return cadena;
	}
	
	public String mostrarProfesores () {

		String cadena = "Id\t|\tNombre del profesor\t|\tEdad\t|\tSexo\t|\t\n";

		for (int i = 0; i < listaProfesores.size(); i++) 
		{
			cadena += listaProfesores.get(i).getId() + "\t \t" + listaProfesores.get(i).getNombre() + "\t \t" 
					 + funcion.calcularEdad(listaProfesores.get(i)) + "\t \t" + listaProfesores.get(i).getSexo() + "\n";
		}

		return cadena;
	}
	
	public String mostrarGeneralEstudiantes () {

		String cadena = "Id\t|\tNombre del estudiante\t|\tEdad\t|\tSexo\t|\t\n";

		for (int i = 0; i < listaEstudiantes.size(); i++) 
		{
			cadena += listaEstudiantes.get(i).getId() + "\t \t" + listaEstudiantes.get(i).getNombre() + "\t \t" 
					 + funcion.calcularEdad(listaEstudiantes.get(i)) + "\t \t" + listaEstudiantes.get(i).getSexo() + "\n";
		}

		return cadena;
	}
	
	
	public String mostrarEstudianteCompleto (Estudiante estudiante) {
		
		String cadena = "Id\t|\tNombre\t|\tEdad\t|\tSexo\t|\tSemestre\t|\tGeneracion\t|\tCarrera\n";
		
			cadena += estudiante.getId() + "\t \t" + estudiante.getNombre() + "\t \t" 
					 + funcion.calcularEdad(estudiante) + "\t \t" + estudiante.getSexo() + "\t \t"
					 + estudiante.getSemestre() + "\t \t" + estudiante.getGeneracion() + "\t \t" 
					 + estudiante.getCarrera().getNombre() + "\n\n" + mostrarMateriasEstudiante(estudiante) + "\n";

		return cadena;
	}
	
	
	public String mostrarMateriasEstudiante (Estudiante estudiante) {

		String cadena = "Materias\nNombre\t|\tCarrera\t|\tSemestre\t|\tHorario\t|\tPrimer Parcial\t|\tSegundo Parcial\t|\tProyecto\t|\tExamen Final\n";

		for (int i = 0; i < estudiante.getMaterias().size(); i++) 
		{
			cadena += estudiante.getMaterias().get(i).getNombre() + "\t \t" + estudiante.getMaterias().get(i).getCarrera().getNombre() + "\t \t" 
					 + estudiante.getMaterias().get(i).getSemestre() + "\t \t" + estudiante.getMaterias().get(i).getHorario() + "\t \t" 
					 + estudiante.getMaterias().get(i).getPrimerParcial() + "\t \t" + estudiante.getMaterias().get(i).getSegundoParcial() + "\t \t" 
					 + estudiante.getMaterias().get(i).getProyecto() + "\t \t" + estudiante.getMaterias().get(i).getExamenFinal() + "\n";
		}

		return cadena;
	}
	
	public String mostrarMateriasProfesor (Profesor profesor) {

		String cadena = "Materias de " + profesor.getNombre() + ":\n\n";

		for (int i = 0; i < profesor.getMaterias().size(); i++) 
		{
			cadena += profesor.getMaterias().get(i).getNombre() + "\n";
		}

		return cadena;
	}
	
	
	public Carrera buscarCarreraPorNombre (String nombreCarrera) {
		
		for (int i = 0; i < listaCarreras.size(); i++)
		{
			if (listaCarreras.get(i).getNombre().equals(nombreCarrera))
			{
				return listaCarreras.get(i);
			}
		}
		 return null;
	}
	
	public boolean validaMateriaAsignadaRepetida (Materia materia, Profesor profesor) {
		
		boolean validacion = true;

		for (int i = 0; i < profesor.getMaterias().size(); i++)
		{
			if (profesor.getMaterias().get(i).equals(materia))
			{
				return false;
			}
		}
		return validacion;
	}
	
	public boolean validaCarreraAsignada (Estudiante estudiante) {

		boolean validacion = false;

		if (estudiante.getCarrera() == null)
		{
			validacion = true;
		}

		return validacion;
	}

	public void actualizarMaterias (Carrera carrera) {
		
		for (int i = 0; i < listaEstudiantes.size(); i++)
		{
			if (listaEstudiantes.get(i).getCarrera().equals(carrera))
			{
				asignarMateriasEstudiante(listaEstudiantes.get(i), carrera);
			}
		}
	}
/*
	public Double calcularPromedioSemestreCarrera (Carrera carrera, Integer semestre, Materia materia) {

		Double promedio = 0D;
		int contadorAlumnos = 0;

		for (int i = 0; i < listaEstudiantes.size(); i++)
		{
			if (listaEstudiantes.get(i).getCarrera() == carrera && listaEstudiantes.get(i).getSemestre() == semestre)
			{
				for (int j = 0; j < listaEstudiantes.get(i).getMaterias().size(); i++)
				{
					if ((buscarPorMateria(listaEstudiantes.get(i), listaEstudiantes.get(i).getMaterias().get(i))) == materia)
					{
						promedio += calcularPromedioEstudiante(listaEstudiantes.get(i));
						contadorAlumnos++;
					}
					
				}
			}
		}

		promedio = promedio / contadorAlumnos;
		return promedio;
	}
	*/
}
