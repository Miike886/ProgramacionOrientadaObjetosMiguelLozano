package udelp.edu.main;

import java.util.Scanner;

import udelp.edu.model.Carrera;
import udelp.edu.model.Estudiante;
import udelp.edu.model.Materia;
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
		
		String auxiliar, opcion, nombre, fechaNacimiento, id, semestre, horario, nombreCarrera, generacion, calificacion;
		
		boolean repetir = true, salir = false;
		Sexo sexo;
		int opcionEntero, opcionCalificacion = 0;

		do
		{
			System.out.println("\t\tMenú\n1. Dar de alta carrera\n2. Dar de alta materia\n3. Dar de alta profesor\n4. Dar de alta alumno\n5. Asignar materia a profesor\n6. Asignar carrera a alumnos\n7. Agregar calificación a alumno por materia\n8. Obtener datos de alumno\n9. Obtener promedio por materia de alumno\n10. Obtener promedio del alumno\n11. Obtener promedio del semestre de alumnos de una carrera\n12. Obtener promedio por materia de una carrera en un semestre\n13. Obtener promedio por carrera\n14. Salir");
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
						funciones.altaDeCarreras(nombre);
						System.out.println("¡La carrera se ha agregado con éxito!\n" + funciones.mostrarCarreras());

						break;

					case 2: 
						if (funciones.listaCarreras.size() != 0)
						{
							System.out.println("Seleccionaste dar de alta materia\nIngresa el nombre de la materia");
							// semestre, horario, nombre, carrera
							nombre = leer.nextLine().toUpperCase();

							do
							{
								System.out.println("Ingresa el semestre (entre 1 - 8)");
								semestre = leer.nextLine();

								if(validaciones.validaSemestre(semestre))
								{	

									do
									{
										System.out.println("Ingresa el horario (Número entero entre 7 y 21)");
										horario = leer.nextLine();

										if (validaciones.validaHorario(horario))
										{

											do
											{
												System.out.println("Ingresa el nombre de la carrera a la que deseas asignar la materia");
												System.out.println(funciones.mostrarCarreras());
												nombreCarrera = leer.nextLine().toUpperCase();

												if (funciones.buscarCarreraPorNombre(nombreCarrera) != null)
												{
													funciones.altaDeMaterias(semestre, horario, nombre, funciones.buscarCarreraPorNombre(nombreCarrera));
													funciones.actualizarMaterias(funciones.buscarCarreraPorNombre(nombreCarrera));
													
													System.out.println("Materia dada de alta con éxito\n" + funciones.mostrarMaterias());
													
												}
												else
												{
													System.out.println("Ingresa el nombre de una carrera existente");
												}

											} while (funciones.buscarCarreraPorNombre(nombreCarrera) == null);

										}

										else
										{
											System.out.println("Ingresa un horario válido entre las 7 - 21 horas");
										}

									}
									while(!validaciones.validaHorario(horario));

								}
								else
								{
									System.out.println("Ingresa un semestre válido en el rango comprendido, ¡Intenta de nuevo!");
								}
							} while (!validaciones.validaSemestre(semestre));
						}
						else 
						{
							System.out.println("Primero debes dar de alta una carrera");
						}

						break;

						
					case 3: 
						
						System.out.println("Seleccionaste ingresar profesor\nIngresa el nombre del profesor");
						nombre = leer.nextLine().toUpperCase();
						
						do
						{
							System.out.println("Ingresa la fecha de nacimiento (dd/mm/aaaa)");
							fechaNacimiento = leer.nextLine();

							if(procesos.validarFechaMaestro(fechaNacimiento))
							{	

								do
								{
									System.out.println("Ingresa el sexo: \n1) Hombre\n2) Mujer \n(Escribir solo el número)");
									opcion = leer.nextLine();

									if (validaciones.validaEntero(opcion) && (Integer.parseInt(opcion) == 1 || Integer.parseInt(opcion) == 2))
									{
										sexo = Integer.parseInt(opcion) == 1? Sexo.HOMBRE: Sexo.MUJER;

										funciones.altaDeProfesor(nombre, fechaNacimiento, sexo);
										System.out.println("¡El profesor se ha agregado con éxito!\n"+ funciones.mostrarProfesores());
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
								System.out.println("Ingresa una fecha de nacimiento válida (1924 - 2005), ¡Intenta de nuevo!");
							}
						} while (!procesos.validarFechaMaestro(fechaNacimiento));
						
						break;

					case 4: 
						
						System.out.println("Seleccionaste ingresar alumno\nIngresa el nombre del alumno");
						nombre = leer.nextLine().toUpperCase();
						
						do
						{
							System.out.println("Ingresa la fecha de nacimiento (dd/mm/aaaa)");
							fechaNacimiento = leer.nextLine();

							if(procesos.validarFechaAlumno(fechaNacimiento))
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
											System.out.println("Ingresa el semestre");
											semestre = leer.nextLine();
											
											if (validaciones.validaSemestre(semestre))
											{
												System.out.println("Ingresa la generación");
												generacion = leer.nextLine();
												funciones.altaDeEstudiante(nombre, fechaNacimiento, sexo, Integer.parseInt(semestre), generacion);
												System.out.println("¡El alumno se ha agregado con éxito!\n"+ funciones.mostrarGeneralEstudiantes());
												
											}
										
										} while (!(validaciones.validaSemestre(semestre)));
										
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
								System.out.println("Ingresa una fecha de nacimiento válida (1934 - 2007), ¡Intenta de nuevo!");
							}
						} while (!procesos.validarFechaMaestro(fechaNacimiento));
						
						break;
					
					case 5:
						salir = false;
						
						if (funciones.listaProfesores.size() == 0 || funciones.listaMaterias.size() == 0)
						{
							System.out.println("Para asignar una materia a un profesor debe haber al menos una materia y un profesor registrados");
						}
						else
						{
							
							do
							{
								
								System.out.println("Selecionaste materia a profesor\nIngresa el id de la materia a asignar:\n" + funciones.mostrarMaterias());
								id = leer.nextLine();
								
								if (funciones.buscarMateriaPorId(id) == -1)
								{
									System.out.println("No se ha encontrado una materia con dicho id, ¡Intenta de nuevo!");
								}
								else
								{
									do
									{
										System.out.println("Ingresa el id del profesor:\n" + funciones.mostrarProfesores());
										auxiliar = leer.nextLine();
										
										if (funciones.buscarProfesorPorId(auxiliar) == -1)
										{
											System.out.println("No se ha encontrado un profesor con dicho id, ¡Intenta de nuevo!");
										}
										else
										{	
											if (funciones.validaMateriaAsignadaRepetida(funciones.listaMaterias.get(funciones.buscarMateriaPorId(id)), funciones.listaProfesores.get(funciones.buscarProfesorPorId(auxiliar))))
											{
												funciones.asignarMateriaProfesor(funciones.listaMaterias.get(funciones.buscarMateriaPorId(id)), funciones.listaProfesores.get(funciones.buscarProfesorPorId(auxiliar)));	
												
												System.out.println("La materia se ha asignado con éxito.\n\n" + funciones.mostrarMateriasProfesor(funciones.listaProfesores.get(funciones.buscarProfesorPorId(auxiliar))));
											}
											else
											{
												System.out.println("La materia ya ha sido asignada, intenta de nuevo con otra, o regresa al menú (pulsa '1' para regresar al menú)");
												auxiliar = leer.nextLine();
												if (auxiliar.equals("1"))
												{
													salir = true;
												}
											}
										}
										
									} while (funciones.buscarProfesorPorId(auxiliar) == -1 || salir == true);
								}
								
							} while (funciones.buscarMateriaPorId(id) == -1 || salir == true);
							
						}
						break;
						
						
					case 6:
						salir = false;
						
						if (funciones.listaEstudiantes.size() == 0 || funciones.listaCarreras.size() == 0)
						{
							System.out.println("Para asignarle una carrera a un estudiante debe haber al menos una carrera y un estudiante registrados");
						}
						else
						{
							do
							{
								System.out.println("Ingresa el id del estudiante a asignar:\n" + funciones.mostrarGeneralEstudiantes());
								id = leer.nextLine();
								
								if (funciones.buscarEstudiantePorId(id) == -1)
								{
									System.out.println("No se ha encontrado una estudiante con dicho id, ¡Intenta de nuevo!");
								}
								else
								{
									do
									{
										System.out.println("Ingresa el nombre de la carrera:\n" + funciones.mostrarCarreras());
										auxiliar = leer.nextLine().toUpperCase();
										
										if (null == funciones.buscarCarreraPorNombre(auxiliar).getNombre())
										{
											System.out.println("No se ha encontrado una carrera con dicho nombre, ¡Intenta de nuevo!");
										}
										else
										{	
											if (funciones.validaCarreraAsignada(funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id))))
											{
												funciones.asignarCarreraEstudiante(funciones.buscarCarreraPorNombre(auxiliar), funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id)));	

												System.out.println("Se le ha asginado la carrera al estudiante con éxito.\n" + funciones.mostrarEstudianteCompleto(funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id))));
												salir = true;
											}
											else
											{
												System.out.println("El estudiante ya tiene una carrera asignada, intenta de nuevo con otra, o regresa al menú (pulsa '1' para regresar al menú)");
												salir = true;
											}
										}
										
									} while (!salir);
								}
								
							} while (!salir);
							
						}
						break;
					
					case 7:
						salir = false;
						System.out.println("Seleccionaste agregar calificación");
						if (funciones.listaEstudiantes.size() == 0 || funciones.listaCarreras.size() == 0 || funciones.listaMaterias.size() == 0)
						{
							System.out.println("Para poner una calificación debes haber asignado una carrera con al menos una materia a un estudiante");
						}
						else
						{
							do
							{
								System.out.println("Ingresa el id del estudiante a asignar:\n" + funciones.mostrarGeneralEstudiantes());
								id = leer.nextLine();
								
								if (funciones.buscarEstudiantePorId(id) == -1)
								{
									System.out.println("No se ha encontrado una estudiante con dicho id, ¡Intenta de nuevo!");
								}
								else
								{
									do
									{
										System.out.println("Ingresa el id de la Materia:\n" + funciones.mostrarMateriasEstudiante(funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id))));
										auxiliar = leer.nextLine();
										
										if (funciones.buscarMateriaPorId(auxiliar) == -1)
										{
											System.out.println("No se ha encontrado una materia con dicho id, ¡Intenta de nuevo!");
										}
										else
										{	
											do
											{
												System.out.println("Selecciona el tipo de calificación que deseas asignar:\n1) Primer parcial\n2) Segundo parcial\n3) Proyecto\n4)Examen Final");
												opcion = leer.nextLine();

												if (validaciones.validaEntero(opcion))
												{
													opcionCalificacion = Integer.parseInt(opcion);
													
													do
													{
														System.out.println("Escribe la calificación:");
														calificacion = leer.nextLine();

														if (validaciones.validaCalificacion(calificacion))
														{
															funciones.agregarCalificacion(funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id)), funciones.listaMaterias.get(funciones.buscarMateriaPorId(auxiliar)), Double.parseDouble(calificacion), opcionCalificacion);
															System.out.println("Calificación agregada con éxito\n" + funciones.mostrarEstudianteCompleto(funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id))));
															
															salir = true;
														}
														else
														{
															System.out.println("Debes ingresar una opción valida en el rango establecido");
														}
													} while (opcionCalificacion < 1 || opcionCalificacion > 4 );
												}
												else
												{
													System.out.println("Debes ingresar una opción valida en el rango establecido");
												}
											} while (opcionCalificacion < 1 || opcionCalificacion > 4 );
										}
										
									} while (!salir);
								}
								
							} while (!salir);
							
						}
						break;
					
						
						
						
						
					case 8:
						
						
						if (funciones.listaEstudiantes.size() == 0 )
						{
							System.out.println("Primero debes dar de alta al menos un estudiante");
						}
						else 
						{
							
							System.out.println("Ingresa el id del estudiante:\n" + funciones.mostrarGeneralEstudiantes());
							id = leer.nextLine();

							if (funciones.buscarEstudiantePorId(id) == -1 || funciones.validaCarreraAsignada(funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id))))
							{
								System.out.println("No se ha encontrado ningún estudiante con dicho id, o el estudiante no tiene una carrera asignada, ¡Intenta de nuevo!");
								break;
							}
							else
							{
								System.out.println(funciones.mostrarEstudianteCompleto(funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id))));	
								
							}

							
						}
						
						break;
						
					case 9:

						salir = false;
						System.out.println("Seleccionaste sacar el promedio de un alumno en una materia");
						
							do
							{
								System.out.println("Ingresa el id del estudiante a promediar:\n" + funciones.mostrarGeneralEstudiantes());
								id = leer.nextLine();
								
								if (funciones.buscarEstudiantePorId(id) == -1)
								{
									System.out.println("No se ha encontrado una estudiante con dicho id, ¡Intenta de nuevo!");
								}
								else
								{
									do
									{
										System.out.println("Ingresa el id de la Materia:\n" + funciones.mostrarMateriasEstudiante(funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id))));
										auxiliar = leer.nextLine();
										
										if (funciones.buscarMateriaPorId(auxiliar) == -1)
										{
											System.out.println("No se ha encontrado una materia con dicho id, ¡Intenta de nuevo!");
										}
										else
										{	
											
											if (funciones.validaExistenCalificaciones(funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id)), funciones.listaMaterias.get(funciones.buscarMateriaAlumnoPorId(auxiliar, funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id))))))
											{
												Estudiante estudiante = funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id));
												Materia materia = funciones.listaMaterias.get(funciones.buscarMateriaAlumnoPorId(auxiliar, estudiante));

												double promedio = funciones.calcularPromedioMateria(estudiante, materia);
												System.out.println("El promedio es: " + promedio);
												salir = true;
											}

											else
											{
												System.out.println("Aún no se han asignado todas las calificaciones para sacar el promedio");
												salir = true;
											}
											
										}
										
									} while (!salir);
								}
								
							} while (!salir);
							
							break;
					
					case 10:

						salir = false;
						System.out.println("Seleccionaste obtener promedio del alumno");

						do
						{
							System.out.println("Ingresa el id del estudiante a promediar:\n" + funciones.mostrarGeneralEstudiantes());
							id = leer.nextLine();

							if (funciones.buscarEstudiantePorId(id) == -1)
							{
								System.out.println("No se ha encontrado una estudiante con dicho id, ¡Intenta de nuevo!");
							}
							else
							{

								Estudiante estudiante = funciones.listaEstudiantes.get(funciones.buscarEstudiantePorId(id));

								double promedio = funciones.calcularPromedioEstudiante(estudiante);
								System.out.println("El promedio del estudiante es: " + promedio);
								salir = true;
							}



						} while (!salir);

						break;

					case 11:
					    salir = false;
					    System.out.println("Seleccionaste obtener promedio del semestre de alumnos de una carrera");

					    do {
					        System.out.println("Ingresa el nombre de la carrera:" + funciones.mostrarCarreras());
					        nombreCarrera = leer.nextLine();
					        Carrera carrera = funciones.buscarCarreraPorNombre(nombreCarrera);

					        if (carrera == null) {
					            System.out.println("No se ha encontrado una carrera con ese nombre, ¡Intenta de nuevo!");
					        } else {
					            System.out.println("Ingresa el número de semestre:");
					            semestre = leer.nextLine();

					            double promedio = funciones.calcularPromedioSemestreCarrera(carrera, Integer.parseInt(semestre));
					            System.out.println("El promedio del semestre " + semestre + " de la carrera " + nombreCarrera + " es: " + promedio);
					            salir = true;
					        }
					    } while (!salir);
					    break;

					case 12:
					    salir = false;
					    System.out.println("Seleccionaste obtener promedio por materia de una carrera en un semestre");

					    do {
					        System.out.println("Ingresa el nombre de la carrera:\n" + funciones.mostrarCarreras());
					        nombreCarrera = leer.nextLine().toUpperCase();
					        Carrera carrera = funciones.buscarCarreraPorNombre(nombreCarrera);

					        if (carrera == null) {
					            System.out.println("No se ha encontrado una carrera con ese nombre, ¡Intenta de nuevo!");
					        } else {
					            System.out.println("Ingresa el número de semestre:");
					            semestre = leer.nextLine();

					            System.out.println("Ingresa el ID de la materia:\n" + funciones.mostrarMaterias());
					            String idMateria = leer.nextLine();

					            double promedio = funciones.calcularPromedioMateriaCarrera(carrera, Integer.parseInt(semestre), idMateria);
					            System.out.println("El promedio de la materia con ID " + idMateria + " en el semestre " + semestre + " de la carrera " + nombreCarrera + " es: " + promedio);
					            salir = true;
					        }
					    } while (!salir);
					    break;

					case 13:
					    salir = false;
					    System.out.println("Seleccionaste obtener promedio por carrera");

					    do {
					        System.out.println("Ingresa el nombre de la carrera:\n" + funciones.mostrarCarreras());
					        nombreCarrera = leer.nextLine().toUpperCase();
					        Carrera carrera = funciones.buscarCarreraPorNombre(nombreCarrera);

					        if (carrera == null) {
					            System.out.println("No se ha encontrado una carrera con ese nombre, ¡Intenta de nuevo!");
					        } else {
					            double promedio = funciones.calcularPromedioCarrera(carrera);
					            System.out.println("El promedio de la carrera " + nombreCarrera + " es: " + promedio);
					            salir = true;
					        }
					    } while (!salir);
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
