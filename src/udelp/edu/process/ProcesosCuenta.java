package udelp.edu.process;
import udelp.edu.model.Cuenta;

public class ProcesosCuenta {

	public Cuenta ingresar (Double monto, Cuenta cuenta) throws Exception
	{
		if (null == monto || monto < 0) 
		{
			throw new Exception("La cantidad no puede ser nulo / negativo.");
			
		}
		
		Double cantidad = cuenta.getCantidad() + monto; 
		
		if (cantidad > 0)
		{
			cuenta.setCantidad(cantidad);
		}
		
		return cuenta;
	}
	
	public Cuenta retirar (Double monto, Cuenta cuenta) throws Exception
	{
		if (null == monto)
		{
			throw new Exception("La cantidad no puede ser nulo.");
			
		}
		
		Double cantidad = cuenta.getCantidad() - monto;
		cuenta.setCantidad(cantidad);
		
		if (cantidad < 0)
		{
			cuenta.setCantidad(0D);
		}
		
		return cuenta;
		
	}
}
