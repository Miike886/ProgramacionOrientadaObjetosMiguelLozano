package udelp.edu.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Persona {
	
	private Integer semestre;
	private List<MateriaAlumno> materias;
	private String generacion;
	private Carrera carrera;
	
	public Estudiante (String nombre, String fechaNacimiento, Sexo sexo, Integer semestre, String generacion) {
		super(nombre, fechaNacimiento, sexo);
		this.materias = new ArrayList<MateriaAlumno>();
		this.semestre = semestre; 
		this.generacion = generacion;
		carrera = null;
		id = generaId();
	}
	
	public Integer getSemestre() {
		return semestre;
	}
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	
	public String getGeneracion() {
		return generacion;
	}
	public void setGeneracion(String generacion) {
		this.generacion = generacion;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public List<MateriaAlumno> getMaterias() {
		return materias;
	}
	public void setMaterias(List<MateriaAlumno> materias) {
		this.materias = materias;
	}
	
	public void addMateria (MateriaAlumno materia) {
		this.materias.add(materia);
	}
}
