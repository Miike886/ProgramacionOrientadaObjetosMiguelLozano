package udelp.edu.main;
import udelp.edu.process.ProcesosPersona;
import udelp.edu.model.Persona;
import udelp.edu.model.Persona.Sexo;
import java.util.Scanner;

public class IndiceMasaCorporal {
	public static void main(String[] args) {

		Scanner leer = new Scanner (System.in);
		ProcesosPersona procesos = new ProcesosPersona();

		
		String auxiliar, auxiliar2, opcion, nombre, fechaNacimiento, id;
		Float peso, altura;
		boolean repetir = true;
		Sexo sexo;
		int opcionEntero;

		do
		{
			System.out.println("\t\tMenú\n1. Ingresar persona\n2. Mostrar información específica de persona (por Id)\n3. Mostrar información específica de todas las personas\n4. Mostrar lista de personas con su información general\n5. Salir");
			do
			{
				opcion = leer.nextLine();

				if (procesos.validaEntero(opcion))
				{
					opcionEntero = Integer.parseInt(opcion);
					switch(opcionEntero)
					{
					case 1:

						if(procesos.listaPersonas.size() != 10)
						{
							System.out.println("Seleccionaste ingresar persona\nIngresa el nombre de la persona");
							nombre = leer.nextLine();
							nombre = nombre.toUpperCase();
							
							do
							{
								System.out.println("Ingresa la fecha de nacimiento (dd/mm/aaaa)");
								fechaNacimiento = leer.nextLine();

								if(procesos.validarFecha(fechaNacimiento))
								{	

									do
									{
										System.out.println("Ingresa el sexo: \n1) Hombre\n2) Mujer \n(Escribir solo el número)");
										opcion = leer.nextLine();

										if (procesos.validaEntero(opcion) && (Integer.parseInt(opcion) == 1 || Integer.parseInt(opcion) == 2))
										{
											sexo = Integer.parseInt(opcion) == 1? Sexo.HOMBRE: Sexo.MUJER;

											do
											{
												System.out.println("Ingresa el peso en kilos (entre 15 y 250 kilos)");
												auxiliar = leer.nextLine();

												if (procesos.validaFlotante(auxiliar) && (Float.parseFloat(auxiliar) >= 15f && Float.parseFloat(auxiliar) <= 250f))
												{
													peso = Float.parseFloat(auxiliar);

													do
													{
														System.out.println("Ingresa la altura en metros (entre 0.20 y 2.5 metros)");
														auxiliar2 = leer.nextLine();

														if (procesos.validaFlotante(auxiliar2) && (Float.parseFloat(auxiliar2) >= 0.5f && Float.parseFloat(auxiliar2) <= 2.5f))
														{
															altura = Float.parseFloat(auxiliar2);
															Persona persona = new Persona (nombre, fechaNacimiento, sexo, peso, altura);
															procesos.listaPersonas.add(persona);
															System.out.println("¡La persona se ha agregado con éxito!\n"+ persona.toString());
														}
														
														else
														{
															System.out.println("Ingresa un peso válido en el rango establecido");
														}

													}
													while (!procesos.validaFlotante(auxiliar2) || (Float.parseFloat(auxiliar2) < 0.5  || Float.parseFloat(auxiliar2) > 2.5f));
												}

												else
												{
													System.out.println("Ingresa un peso válido en el rango establecido");
												}

											} while (!procesos.validaFlotante(auxiliar) || (Float.parseFloat(auxiliar) < 15f || Float.parseFloat(auxiliar) > 250f));

										}

										else
										{
											System.out.println("Ingresa un número válido para elegir el sexo");
										}

									}
									while(!procesos.validaEntero(opcion) || !(Integer.parseInt(opcion) == 1 || Integer.parseInt(opcion) == 2));

								}
								else
								{
									System.out.println("Ingresa una fecha de nacimiento válida, ¡Intenta de nuevo!");
								}
							} while (!procesos.validarFecha(fechaNacimiento));
						}
						else
						{
							System.out.println("La base de datos se ha llenado, has alcanzado el límite de personas");
						}
						break;

					case 2: 
						System.out.println("Seleccionaste ver la información específica de una persona");
						
						if(procesos.listaPersonas.size() == 0)
						{
							System.out.println("Aún no hay personas en la lista, ingresa al menos una antes de usar estas opciones");
						}
						else 
						{
							do
							{
								System.out.println("Ingresa el id de la persona (presiona 's' si deseas regresar al menú y poder consultar la lista con la información general, incluyendo los id's");
								id = leer.nextLine();
								
								if (procesos.buscarPorId(id) == -1)
								{
									System.out.println("No se ha encontrado una persona con dicho id, ¡Intenta de nuevo!");
								}
								else
								{
									System.out.println(procesos.verInformacionEspecifica(id));	
									break;
								}
								
							} while (!id.equals("s"));
							
							
						}
						break;
						
						
					case 3: 
						
						if(procesos.listaPersonas.size() == 0)
						{
							System.out.println("Aún no hay personas en la lista, ingresa al menos una antes de usar estas opciones");
						}
						else 
						{
							System.out.println(procesos.verInformacionEspecifica());
						}
						break;

					case 4: 
						
						if(procesos.listaPersonas.size() == 0)
						{
							System.out.println("Aún no hay personas en la lista, ingresa al menos una antes de usar estas opciones");
						}
						else 
						{
							System.out.println(procesos.verInformacionGeneral());
							
						}
						break;
						
					case 5:
						System.out.println("Saliendo del programa");
						repetir = false;
						break;

					default:
						System.out.println("El número entero ingresado debe de estar dentro del rango establecido");
					} 
				}
				else
				{
					System.out.println("El valor ingresado debe ser un número entero");
				}
			} while (!procesos.validaEntero(opcion));

		}while(repetir);

		leer.close();
	}
}
