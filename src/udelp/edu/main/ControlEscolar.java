package udelp.edu.main;

import java.util.Scanner;

import udelp.edu.model.Carrera;
import udelp.edu.model.Persona;
import udelp.edu.model.Persona.Sexo;
import udelp.edu.process.ProcesosControlEscolar;
import udelp.edu.process.ProcesosPersona;
import udelp.edu.validations.Validaciones;

public class ControlEscolar {

	public static void main(String[] args) {

		Scanner leer = new Scanner (System.in);
		ProcesosPersona procesos = new ProcesosPersona();
		Validaciones validaciones = new Validaciones();
		
		ProcesosControlEscolar funciones = new ProcesosControlEscolar();
		
		String auxiliar, auxiliar2, opcion, nombre, fechaNacimiento, id, semestre;
		Float peso, altura;
		
		boolean repetir = true;
		Sexo sexo;
		int opcionEntero;

		do
		{
			System.out.println("\t\tMenú\n1. Dar de alta carrera\n2. Dar de alta materia\n3. Dar de alta profesor\n4. Dar de alta alumno\n5. Asignar materia a profesor\n6. Asignar carrera a alumnos\n7. Agregar calificación a alumno por materia\n8. Obtener datos de alumno\n9.Obtener promedio por materia de alumno\n10. Obtener promedio del alumno\n11. Obtener promedio del semestre de alumnos de una carrera\n12. Obtener promedio por materia de una carrera en un semestre\n13. Obtener promedio por carrera\n14. Salir");
			do
			{
				opcion = leer.nextLine();

				if (validaciones.validaEntero(opcion))
				{
					opcionEntero = Integer.parseInt(opcion);
					switch(opcionEntero)
					{
					case 1:

						System.out.println("Seleccionaste dar de alta una carrera\nIngresa el nombre de la carrera");
						
						nombre = leer.nextLine();
						nombre = nombre.toUpperCase();
						Carrera carrera = new Carrera (nombre);
						funciones.altaDeCarreras(nombre);
						System.out.println("¡La carrera se ha agregado con éxito!\n"+ carrera.toString());
						
						break;

					case 2: 
						System.out.println("Seleccionaste dar de alta materia\nIngresa el nombre de la materia");
						
						nombre = leer.nextLine();
						nombre = nombre.toUpperCase();
						
						do
						{
							System.out.println("Ingresa el semestre (entre 1 - 8)");
							semestre = leer.nextLine();

							if(validaciones.validaSemestre(semestre))
							{	

								do
								{
									System.out.println("Ingresa el sexo: \n1) Hombre\n2) Mujer \n(Escribir solo el número)");
									opcion = leer.nextLine();

									if (validaciones.validaEntero(opcion) && (Integer.parseInt(opcion) == 1 || Integer.parseInt(opcion) == 2))
									{
										sexo = Integer.parseInt(opcion) == 1? Sexo.HOMBRE: Sexo.MUJER;

										do
										{
											System.out.println("Ingresa el peso en kilos (entre 15 y 250 kilos)");
											auxiliar = leer.nextLine();

											if (validaciones.validaFlotante(auxiliar) && (Float.parseFloat(auxiliar) >= 15f && Float.parseFloat(auxiliar) <= 250f))
											{
												peso = Float.parseFloat(auxiliar);

												do
												{
													System.out.println("Ingresa la altura en metros (entre 0.20 y 2.5 metros)");
													auxiliar2 = leer.nextLine();

													if (validaciones.validaFlotante(auxiliar2) && (Float.parseFloat(auxiliar2) >= 0.5f && Float.parseFloat(auxiliar2) <= 2.5f))
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
												while (!validaciones.validaFlotante(auxiliar2) || (Float.parseFloat(auxiliar2) < 0.5  || Float.parseFloat(auxiliar2) > 2.5f));
											}

											else
											{
												System.out.println("Ingresa un peso válido en el rango establecido");
											}

										} while (!validaciones.validaFlotante(auxiliar) || (Float.parseFloat(auxiliar) < 15f || Float.parseFloat(auxiliar) > 250f));

									}

									else
									{
										System.out.println("Ingresa un número válido para elegir el sexo");
									}

								}
								while(!validaciones.validaEntero(opcion) || !(Integer.parseInt(opcion) == 1 || Integer.parseInt(opcion) == 2));

							}
							else
							{
								System.out.println("Ingresa un semestre válido en el rango comprendido, ¡Intenta de nuevo!");
							}
						} while (!validaciones.validaSemestre(semestre));
					
						
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
						
					case 14:
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
			} while (!validaciones.validaEntero(opcion));

		}while(repetir);

		leer.close();
	}

}
