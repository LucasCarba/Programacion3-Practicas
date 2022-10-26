package prog3.arbol.general;

public class prueba {

	public static void main(String[] args) {
ArbolGeneral<Integer> ag = new ArbolGeneral<Integer>(1);
		
		ArbolGeneral<Integer> a2 = new ArbolGeneral<Integer>(2);
		ArbolGeneral<Integer> a3 = new ArbolGeneral<Integer>(3);
		ArbolGeneral<Integer> a4 = new ArbolGeneral<Integer>(4);
		ArbolGeneral<Integer> a5 = new ArbolGeneral<Integer>(5);
		ArbolGeneral<Integer> a6 = new ArbolGeneral<Integer>(6);
		ArbolGeneral<Integer> a7 = new ArbolGeneral<Integer>(7);
		ArbolGeneral<Integer> a8 = new ArbolGeneral<Integer>(8);
		ArbolGeneral<Integer> a9 = new ArbolGeneral<Integer>(9);
		ArbolGeneral<Integer> a10= new ArbolGeneral<Integer>(10);
		ag.agregarHijo(a2);
		ag.agregarHijo(a3);
		ag.agregarHijo(a4);
		a2.agregarHijo(a5);
		a3.agregarHijo(a6);
		a3.agregarHijo(a7);
		a3.agregarHijo(a8);
		a4.agregarHijo(a9);
		a9.agregarHijo(a10);
/*Arbol usado en las pruebas
			1
2			3			4
5		6	7	8		9
                        10

*/

		System.out.print("se averiguara en q nivel esta el numero ");
		System.out.println("Numero: 5 - ");
		System.out.println(ag.nivel(5));
	}

	}


