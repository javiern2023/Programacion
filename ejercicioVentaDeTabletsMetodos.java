package manuelmesa;

import java.util.Scanner;

public class ejercicioVentaDeTabletsMetodos {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		String codigo,DNI;
		final int filas=50;
		final int columnasTablets=6;
		final int columnasClientes=4;
		boolean tablet_exists=false, cliente_exists=false;
		String tablets [][]= new String [filas][columnasTablets];
		String clientes [][]= new String [filas][columnasClientes];
		int opcion;
		
		mostrarMenu();
		opcion=sc.nextInt();
		switch (opcion) {
		case 1: System.out.println("Escribe el codigo de la tablet ");
				codigo=sc.next();
				if (!comprobarExisteTablet(codigo,tablets)) {
					darAltaTablet(codigo,tablets,sc);
				}
			break;
		case 2: System.out.println("Escribe el DNI del cliente: ");
				DNI=sc.next();
				if(!comprobarExisteCliente(DNI,clientes)) {
					darAltaCliente(DNI,clientes,sc);
				}
			break;
				
		case 3: System.out.println("Escribe el DNI del cliente: ");
				DNI=sc.next();
				//Buscamos que el cliente existe para hacer la venta.
				if(comprobarExisteCliente(DNI,clientes)) {
					System.out.println("Escribe el codigo de la tablet ");
					codigo=sc.next();
					if(comprobarExisteTablet(codigo,tablets)) {
						hacerVenta(DNI,codigo,clientes,tablets);
					}
					else System.out.print("La tablet no existe");
				}
				else System.out.print("El cliente con dni "+DNI+" no existe");
			
		case 4: mostrarTabletsDisponibles(tablets);
		  break;
		case 5:	System.out.println("Escribe el DNI del cliente: ");
				DNI=sc.next();
				//Compruebo si el clientes existe
				if(comprobarExisteCliente(DNI,clientes)) {
					mostrarTabletsUnCliente(DNI,tablets);
				}
			break;
		case 6: System.out.print("Hasta pronto");
			break;
		default: System.out.print("Opcion incorrecta");
		}	

	}
	
	public static void mostrarMenu() {
		System.out.println("Menu de opciones: ");
		System.out.println("1.-Dar de alta una tablet ");
		System.out.println("2.-Dar de alta un cliente ");
		System.out.println("3.-Vender una tablet a un cliente ");
		System.out.println("4.-Mostrar tablets disponibles para la venta ");
		System.out.println("5.-Mostrar tablets compradas por un cliente en concreto ");
		System.out.println("6.- Salir ");
	}
	public static boolean comprobarExisteTablet(String codigo, String tablets [][]) {
		boolean existe=false;
		for(int i=0;i<tablets.length;i++) {
			if(tablets [i][0] !=null && tablets [i][0].equals(codigo)) {
				System.out.println("¡Error! El código introducido ya existe en la base de datos");
				existe=true;
				break;
			}
		}
		return existe;
	}
	public static void darAltaTablet(String codigo, String tablets [][], Scanner sc) {
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
	public static boolean comprobarExisteCliente(String dni, String clientes[][]) {
		boolean existe=false;
		for(int i=0;i<clientes.length;i++) {
			if(clientes[i][0] !=null && clientes[i][0].equals(dni)) {
				System.out.println("No se puede dar de alta. Ya está en la base de datos");
				existe=true;
				break;
			}
		}
		return existe;
	}
	public static void darAltaCliente(String dni, String clientes [][], Scanner sc) {
		int edadcorrecta;
		for(int i=0;i<clientes.length;i++) {
			if(clientes[i][0]==null) {
				clientes[i][0]=dni;
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
	public static void hacerVenta(String dni, String codigo, String clientes[][], String tablets[][]) {
		for(int i=0;i<clientes.length;i++) {
			if(clientes[i][0] !=null && clientes[i][0].equals(dni)) {
				//Buscamo que la tablet existe y no se ha vendido a ningún cliente.
				for(int j=0;j<tablets.length;j++) {
					if(tablets[j][0]!=null && tablets[j][0].equals(codigo) && tablets[j][5]==null) {
						tablets[j][5]=dni;
						System.out.println("La tablet ha sido vendida correctamente al cliente con DNI "+ dni);
					}
					else if(tablets [j][0] !=null && tablets [j][0].equals(codigo)&& tablets[j][5]!=null) {
						System.out.println("La tablet con codigo"+ codigo + "ya ha sido vendida");
					}
					break;
				}
			}
		}
	}
	public static void mostrarTabletsDisponibles(String tablets [][]) {
		boolean tablets_libres=false;
		for(int i=0;i<tablets.length;i++) {
			if(tablets[i][0]!=null && tablets[i][5]==null ) {
				System.out.println("Tablet modelo "+tablets[i][1]+" marca "+tablets[i][2]+" de color "+tablets[i][3]);
				tablets_libres=true;
			}
		}
		if(!tablets_libres) {
			System.out.println("No hay tablets disponibles para la venta");
		} 
		
	}
	public static void mostrarTabletsUnCliente(String dni,String tablets[][]) {
		boolean compras=false;
		for(int i=0;i<tablets.length;i++) {
			if(tablets[i][5] !=null && tablets[i][5].equals(dni)) {
				System.out.println("Tablet modelo "+tablets[i][1]+" marca "+tablets[i][2]+" de color "+tablets[i][3]);
				compras=true;
			}	
		}
	
		if(!compras) {
			System.out.println("El usuario indicado no ha comprado ninguna tablet");
		}	
	}
}
