package udelp.edu.model;
import java.util.Random;
public class Persona {

	private String nombre; 
	private String fechaNacimiento;
	protected String id;
	private Sexo sexo;
	private Float peso;
	private Float altura;

	public Persona () {
		
	}
// Ocupar este constructor para los estuadiantes, no será necesario preguntarles peso ni altura
	public Persona (String nombre, String fechaNacimiento, Sexo sexo) {
		
		this.nombre = nombre;
		id = generaId();
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;	
	}
	
	public Persona (String nombre, String fechaNacimiento, Sexo sexo, Float peso, Float altura)
	{
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		id = generaId();
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Float getAltura() {
		return altura;
	}

	public void setAltura(Float altura) {
		this.altura = altura;
	}
	
	@Override
	public String toString() {
		
		return "Persona [nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", id=" + id + ", sexo=" + sexo.inicial +   
				", peso=" + peso + ", altura=" + altura + "]";
	}
	
	protected String generaId () {
	
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
		
	public enum Sexo
	{
		HOMBRE('H'), MUJER('M');
		
		private Character inicial;
		
		private Sexo (Character inicial)
		{
			this.inicial = inicial;
			
		}
		
		public Character getInicial() 
		{
			return inicial;
		}
	
	}
}