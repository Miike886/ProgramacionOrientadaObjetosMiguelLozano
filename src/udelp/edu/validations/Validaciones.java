package udelp.edu.validations;


public class Validaciones {

	
	public boolean validaEntero (String numero) {
		try 
		{
			Integer.parseInt(numero);
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}
	
	public boolean validaFlotante (String numero) {
		try 
		{
			Float.parseFloat(numero);
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}
	
	public boolean validaSemestre (String semestre) { 
		
		boolean validacion = false;
		
		if (validaEntero(semestre))
		{
			if (Integer.parseInt(semestre) >= 1 && Integer.parseInt(semestre) <= 8)
			{
				validacion = true;
			}
		}
		
		return validacion;
	}

	public boolean validaCalificacion (String calificacion) { 

		boolean validacion = false;

		if (validaFlotante(calificacion))
		{
			if (Double.parseDouble(calificacion) >= 0 && Double.parseDouble(calificacion) <= 10)
			{
				validacion = true;
			}
		}

		return validacion;
	}

	public boolean validaHorario(String horario) {

		boolean validacion = false;

		if (validaEntero(horario))
		{
			if (Integer.parseInt(horario) >= 7 && Integer.parseInt(horario) <= 21)
			{
				validacion = true;
			}
		}
		
		return validacion;
	}
}
