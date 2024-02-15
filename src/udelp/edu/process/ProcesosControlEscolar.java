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
		
		for (int i = 0; i < carrera.getMaterias().size(); i++)
		{
			MateriaAlumno materiaAlumno = new MateriaAlumno(carrera.getMaterias().get(i).getSemestre(), carrera.getMaterias().get(i).getHorario(), carrera.getMaterias().get(i).getNombre(), carrera.getMaterias().get(i).getCarrera());
			listaMaterias.add(materiaAlumno);
		}
		
		estudiante.setMaterias(listaMaterias);
	}
	
	
	
	
	public void altaDeCarreras (String nombreCarrera) {
		
		Carrera carrera = new Carrera (nombreCarrera.toUpperCase());
		listaCarreras.add(carrera);
	}
	
	public void altaDeMaterias (String semestre, String horario, String nombre, Carrera carrera) {
 		
		// validar si la carrea proporcionada ya ha sido dada de alta previamente
		
		Materia materia = new Materia (semestre, horario, nombre, carrera);
		listaMaterias.add(materia); 
		
	}
	
	public void altaDeProfesor (String nombre, String fechaNacimiento, Sexo sexo) {

		Profesor profesor = new Profesor (nombre, fechaNacimiento, sexo);
		listaProfesores.add(profesor);
	}

	public void altaDeEstudiante (String nombre, String fechaNacimiento, Sexo sexo) {

		Estudiante estudiante = new Estudiante (nombre, fechaNacimiento, sexo);
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
	
	public String obtenerDatosEstudiante (Estudiante estudiante) {
		
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
