package udelp.edu.process;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import udelp.edu.model.Persona;
import udelp.edu.validations.Validaciones;

public class ProcesosPersona {

	public ArrayList<Persona> listaPersonas = new ArrayList<Persona> ();
	Validaciones validaciones = new Validaciones();
	
	public String formatearFecha (String fecha)
	{
		String fechaFormateada;
		try
		{
			String año = fecha.split("/")[2], mes = fecha.split("/")[1], dia = fecha.split("/")[0];

			if (dia.length() == 1)
			{
				dia = "0" +  dia;
			}

			if (mes.length() == 1)
			{
				mes = "0" + dia;
			}

			fechaFormateada = dia + "/" + mes + "/" + año;
		}
		catch (Exception ex)
		{
			return "";
		}
		return fechaFormateada;
	}

	Integer calcularEdad (Persona persona)
	{
		Integer edad = -1;
		LocalDate fechaHoy = LocalDate.now();
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
	
		if (validaciones.validarFecha(persona.getFechaNacimiento()))
		{
			String fechaNacimiento = persona.getFechaNacimiento();
			String fechaActual = fechaHoy.format(formatoFecha);
			
			
			Integer añoNacimiento = Integer.parseInt(fechaNacimiento.split("/")[2]), mesNacimiento = Integer.parseInt(fechaNacimiento.split("/")[1]), diaNacimiento = Integer.parseInt(fechaNacimiento.split("/")[0]);
			Integer añoActual = Integer.parseInt(fechaActual.split("/")[2]), mesActual = Integer.parseInt(fechaActual.split("/")[1]), diaActual = Integer.parseInt(fechaActual.split("/")[0]);
			

			if (validaciones.validarFecha(fechaNacimiento))
			{
				edad = añoActual - añoNacimiento;

				if (mesNacimiento > mesActual)
				{
					edad--;
				}
				else if (mesNacimiento == mesActual)
				{
					if (diaNacimiento > diaActual)
					{
						edad--;
					}
				}
			}
		}
		else 
		{
			return -1;
		}


		
		return edad;
	}
	
	private Boolean esMayorDeEdad (Persona persona)
	{
		return calcularEdad(persona) >= 18 ? true : false;
	}
	
	private Integer calcularIMC (Persona persona) 
	{
		Double IMC = persona.getPeso() / Math.sqrt(persona.getAltura());
		
		if (IMC >= 20 && IMC <= 25)
		{
			return 0;
		}
		else if (IMC > 25)
		{
			return 1;
		}
		
		return -1;
	}
	
	public Integer buscarPorId (String id)
	{
		for (int i = 0; i < listaPersonas.size(); i++)
		{
			if (listaPersonas.get(i).getId().equals(id))
			{
				return i;
			}
		}
		 return -1;
	}
	
	public String verInformacionEspecifica (String id)
	{
		int i = buscarPorId(id);
		Integer imc = calcularIMC(listaPersonas.get(i));
		String cadena = "Id\t|\tNombre\t|\tEdad\t|\tMayor de Edad\t|\tÍndice de masa corporal\n";

		cadena += listaPersonas.get(i).getId() + "\t \t" + listaPersonas.get(i).getNombre() + "\t \t" + calcularEdad(listaPersonas.get(i)) + "\t \t" + esMayorDeEdad(listaPersonas.get(i)) + "\t \t"; 
		
		if (imc == 0)
		{
			cadena += "Peso ideal\n";
		}
		else if (imc == 1)
		{
			cadena += "Sobrepeso\n";
		}
		else 
		{
			cadena += "Bajo de peso\n";
		}
		
		return cadena;
	}
	
	public String verInformacionEspecifica ()
	{
		Integer imc; 
		String cadena = "Id\t|\tNombre\t|\tEdad\t|\tMayor de Edad\t|\tÍndice de masa corporal\n";
		for (int i = 0; i < listaPersonas.size(); i++)
		{
			cadena += listaPersonas.get(i).getId() + "\t \t" + listaPersonas.get(i).getNombre() + "\t \t" + calcularEdad(listaPersonas.get(i)) + "\t \t" + esMayorDeEdad(listaPersonas.get(i)) + "\t \t"; 
			
			
			
			
			imc = calcularIMC(listaPersonas.get(i));
			if (imc == 0)
			{
				cadena += "Peso ideal\n";
			}
			else if (imc == 1)
			{
				cadena += "Sobrepeso\n";
			}
			else 
			{
				cadena += "Bajo de peso\n";
			}
		}
		return cadena;
	}

	public String verInformacionGeneral ()
	{
		String cadena = "Id\t|\tNombre\t|\tFecha de nacimiento\t|\tSexo\t|\tPeso\t|\tAltura\n";
		
		for (int i = 0; i < listaPersonas.size(); i++)
		{
				cadena += listaPersonas.get(i).getId() + "\t \t" + listaPersonas.get(i).getNombre() + "\t \t" + listaPersonas.get(i).getFechaNacimiento() + "\t \t" + listaPersonas.get(i).getSexo() + "\t \t" + listaPersonas.get(i).getPeso() + "\t \t" + listaPersonas.get(i).getAltura() + "\n";
		}
		return cadena;
	}
	

}
