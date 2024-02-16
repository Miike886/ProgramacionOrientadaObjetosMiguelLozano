package udelp.edu.model;

import java.util.ArrayList;
import java.util.List;

public class Carrera {

	private String nombre;
	private List<Materia> materias;
	
	public Carrera (String carrera) {
		
		this.nombre = carrera;
		materias = new ArrayList<Materia>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String carrera) {
		this.nombre = carrera;
	}
	
	public List<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	
	public void addMateria (Materia materia) {
		this.materias.add(materia);
	}
}









