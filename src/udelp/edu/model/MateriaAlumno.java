package udelp.edu.model;

public class MateriaAlumno extends Materia {

	
	private Double primerParcial;
	private Double segundoParcial;
	private Double proyecto;
	private Double examenFinal;
	private String idMateria;
	
	public MateriaAlumno(String semestre, String horario, String nombre, Carrera carrera, String id) {
		super(semestre, horario, nombre, carrera);
		primerParcial = null;
		segundoParcial = null;
		proyecto = null; 
		examenFinal = null;
		this.idMateria = id;
	}
	
	public Double getPrimerParcial() {
		return primerParcial;
	}
	public void setPrimerParcial(Double primerParcial) {
		this.primerParcial = primerParcial;
	}
	public Double getSegundoParcial() {
		return segundoParcial;
	}
	public void setSegundoParcial(Double segundoParcial) {
		this.segundoParcial = segundoParcial;
	}
	public Double getProyecto() {
		return proyecto;
	}
	public void setProyecto(Double proyecto) {
		this.proyecto = proyecto;
	}
	public String getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(String idMateria) {
		this.idMateria = idMateria;
	}

	public Double getExamenFinal() {
		return examenFinal;
	}
	public void setExamenFinal(Double examenFinal) {
		this.examenFinal = examenFinal;
	}
	
	
}
