package udelp.edu.validations;

import udelp.edu.process.ProcesosPersona;

public class Validaciones {

	ProcesosPersona procesosPersona = new ProcesosPersona();
	
	public Boolean validarFecha (String fecha) {
		
		procesosPersona.formatearFecha(fecha);
		
		boolean comprobacion = false;
		try
		{
			Integer año = Integer.parseInt(fecha.split("/")[2]), mes = Integer.parseInt(fecha.split("/")[1]), dia = Integer.parseInt(fecha.split("/")[0]);
			
			if ((año < 1900) | (año > 2024))
			{
				return false;
			}


			if ((mes == 01) | (mes == 03) | (mes == 05) | (mes == 07) | (mes == 8) | (mes == 10) | (mes == 12))
			{
				if ((dia >= 01) && (dia <= 31))
				{
					comprobacion = true;
				}

			}
			else if ((mes == 04) | (mes == 06) | (mes == 9) | (mes == 11))
			{
				if (dia >= 01 && dia <= 30)
				{
					comprobacion = true;
				}
			}
			else if (mes == 02)
			{
				if ((año >= 1900) && (año <=2024))
				{	
					if ((año % 4) == 0)
					{
						if (dia >= 01 && dia <= 29)
						{
							comprobacion = true;
						}
					}
					else 
					{
						if ((dia >= 01) && (dia <= 28))	
						{
							comprobacion = true;
						}
					}
				}	   	
			}		 	
		} 
		catch (Exception ex)
		{
			comprobacion = false;
		}
		return comprobacion;
	}
	
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
			if (Integer.parseInt(semestre) > 1 && Integer.parseInt(semestre) <= 8)
			{
				validacion = true;
			}
		}
		
		return validacion;
	}
}
