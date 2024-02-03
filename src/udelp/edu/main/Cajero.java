package udelp.edu.main;

import udelp.edu.model.Cuenta;
import udelp.edu.process.ProcesosCuenta;

public class Cajero {

	public static void main(String[] args) throws Exception {

		ProcesosCuenta procesos = new ProcesosCuenta();
		
		Cuenta c1 = new Cuenta();	
		c1.setTitular("Luis Ochoa");
		
		c1 = procesos.ingresar(10D, c1);
		
		System.out.println(c1);
		
		
		c1 = new Cuenta("Miguel Lozano", 1000D);
		System.out.println(c1);
		
		
		
	}

}
