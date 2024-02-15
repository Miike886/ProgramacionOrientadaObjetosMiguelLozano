package udelp.edu.model;

import java.util.Random;

public class Materia {
	
	private String id;
	private String semestre;
	private String horario;
	private String nombre;
	private Carrera carrera;
	
	public Materia (String semestre, String horario, String nombre, Carrera carrera) {
		
		id = generaId();
		this.semestre = semestre;
		this.horario = horario;
		this.nombre = nombre; 
		this.carrera = carrera;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	private String generaId () {
		
		Random aleatorio = new Random();
		int numeroAleatorio = 0;
		
		
		String id = "";
		
		for (int i = 0; i < 8; i++)
		{
			numeroAleatorio  = aleatorio.nextInt(10);
			id  +=  numeroAleatorio;
		}
		
		return id;
	}
		
}
