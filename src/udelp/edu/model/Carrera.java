package udelp.edu.model;

import java.util.List;

public class Carrera {

	private String carrera;
	private List<Materia> materias;
	
	public Carrera (String carrera) {
		
		this.carrera = carrera;
	}
	
	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
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









