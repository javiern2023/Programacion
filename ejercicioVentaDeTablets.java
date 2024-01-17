package manuelmesa;

import java.util.Scanner;

public class ejercicioVentaDeTablets {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		String codigo, modelo,marca,color,DNI,nombre,apellidos,email;
		final int filas=50;
		final int columnasTablets=6;
		final int columnasClientes=4;
		boolean tablet_exists=false, cliente_exists=false, tablets_libres=false, compras=false;
		String tablets [][]= new String [filas][columnasTablets];
		String clientes [][]= new String [filas][columnasClientes];
		int opcion, edadcorrecta;
		
		do {
			System.out.println("Menu de opciones: ");
			System.out.println("1.-Dar de alta una tablet ");
			System.out.println("2.-Dar de alta un cliente ");
			System.out.println("3.-Vender una tablet a un cliente ");
			System.out.println("4.-Mostrar tablets disponibles para la venta ");
			System.out.println("5.-Mostrar tablets compradas por un cliente en concreto ");
			System.out.println("6.- Salir ");
			opcion=sc.nextInt();
			switch (opcion) {
			case 1: System.out.println("Escribe el codigo de la tablet ");
					codigo=sc.next();
					//Antes de dar de alta tengo que comprobar si existe la tablet ya o no.
					for(int i=0;i<tablets.length;i++) {
						if(tablets [i][0] !=null && tablets [i][0].equals(codigo)) {
							System.out.println("¡Error! El código introducido ya existe en la base de datos");
								tablet_exists=true;
								break;
						}
					}
					//Si no existe la tablet, entonces la damos de alta.
					if (!tablet_exists) {
						for(int i=0;i<tablets.length;i++) {
							if(tablets[i][0]==null) {
								tablets[i][0]=codigo;
								System.out.println("Escribe el modelo: ");
								tablets[i][1]=sc.next();
								System.out.println("Escribe la marca: ");
								tablets[i][2]= sc.next();
								System.out.println("Escribe el color: ");
								tablets[i][3]=sc.next();
								System.out.println("La tablet ha sido dada de alta exitosamente");
								break;
							}
						}
					}
					//Reinicio la variable para volver a empezar.
					tablet_exists=false;
				break;
			case 2: System.out.println("Escribe el DNI del cliente: ");
					DNI=sc.next();
					//Aantes de dar de alta a un cliente, tengo que comprobar que existe.
					for(int i=0;i<clientes.length;i++) {
						if(clientes[i][0] !=null && clientes[i][0].equals(DNI)) {
							System.out.println("No se puede dar de alta. Ya está en la base de datos");
							cliente_exists=true;
							break;
						}
					}
					//Si el cliente no existe, entonces lo damos de alta.
					if(!cliente_exists) {
						for(int i=0;i<clientes.length;i++) {
							if(clientes[i][0]==null) {
								clientes[i][0]=DNI;
								System.out.println("Escribe el nombre: ");
								clientes[i][1]=sc.next();
								System.out.println("Escribe los apellidos: ");
								sc.nextLine();
								clientes[i][2]=sc.nextLine();
								do {
									System.out.println("Escribe la edad: ");
									edadcorrecta=sc.nextInt();
									if (edadcorrecta>18 && edadcorrecta<100) {
										clientes [i][3]=String.valueOf(edadcorrecta);
									}
									else {
										System.out.println("Edad incorrecta. Debes tener entre 0 y 100 años");
									}
								}while (edadcorrecta<18 || edadcorrecta>100);
								System.out.println("Escribe el email: ");
								clientes[i][4]=sc.next();
								System.out.println("El cliente ha sido dado de alta exitosamente");
								break;
							}
						}
					}
					cliente_exists=false;
				break;
					
			case 3: System.out.println("Escribe el DNI del cliente: ");
					DNI=sc.next();
					//Buscamos que el cliente existe para hacer la venta.
					for(int i=0;i<clientes.length;i++) {
						if(clientes[i][0] !=null && clientes[i][0].equals(DNI)) {
							cliente_exists=true;
							System.out.println("Escribe el código de la tablet que desea comprar: ");
							codigo=sc.next();
							//Buscamo que la tablet existe y no se ha vendido a ningún cliente.
							for(int j=0;j<tablets.length;j++) {
								if(tablets[j][0]!=null && tablets[j][0].equals(codigo) && tablets[j][5]==null) {
									tablet_exists=true;
									tablets[j][5]=DNI;
									System.out.println("La tablet ha sido vendida correctamente al cliente con DNI "+ DNI);
								}
								else if(tablets [j][0] !=null && tablets [j][0].equals(codigo)&& tablets[j][5]!=null) {
									System.out.println("La tablet con codigo"+ codigo + "ya ha sido vendida");
								}
								break;
							}
						}
					}
	
				if(!cliente_exists) {
					System.out.println("El cliente no se ha dado de alta. Debe darse de alta antes de comprar");
				}
				cliente_exists=false;
				if(!tablet_exists) {
					System.out.println("La tablet no se ha dado de alta. Debe darse de alta antes de hacer la compra");
				}
				tablet_exists=false;
			  break;
				
			case 4: for(int i=0;i<tablets.length;i++) {
						if(tablets[i][0]!=null && tablets[i][5]==null ) {
							System.out.println("Tablet modelo "+tablets[i][1]+" marca "+tablets[i][2]+" de color "+tablets[i][3]);
							tablets_libres=true;
						}
					}
					if(!tablets_libres) {
						System.out.println("No hay tablets disponibles para la venta");
					} 
					tablets_libres=false;
			  break;
			case 5:	System.out.println("Escribe el DNI del cliente: ");
					DNI=sc.next();
					//Compruebo si el clientes existe
					for(int i=0;i<clientes.length;i++) {
						if(clientes[i][0] != null && clientes[i][0].equals(DNI)) {
							cliente_exists=true;
							break;
						}
					}
					if(cliente_exists) {
						for(int i=0;i<tablets.length;i++) {
							if(tablets[i][5] !=null && tablets[i][5].equals(DNI)) {
								System.out.println("Tablet modelo "+tablets[i][1]+" marca "+tablets[i][2]+" de color "+tablets[i][3]);
								compras=true;
							}	
						}
					
						if(!compras) {
							System.out.println("El usuario indicado no ha comprado ninguna tablet");
						}	
					}
					cliente_exists=false;
					compras=false;
					break;
				case 6: System.out.print("Hasta pronto");
					break;
				default: System.out.print("Opcion incorrecta");
			}	
		}while(opcion!=6);
	}	
}
