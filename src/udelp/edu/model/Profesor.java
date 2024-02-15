package udelp.edu.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Profesor extends Persona {

	private String fechaIngreso;
	private List<Materia> materias;

	
	
	
	LocalDate fechaHoy = LocalDate.now();
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Profesor(String nombre, String fechaNacimiento, Sexo sexo) {
		super(nombre, fechaNacimiento, sexo);
		this.materias = new ArrayList<Materia>();
		fechaIngreso = fechaHoy.format(formatoFecha);
		id = generaId();
	}
	
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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
