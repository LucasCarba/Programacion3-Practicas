package ARBOLESARMADOS;

import prog3.arbol.general.ArbolGeneral;
import prog3.listagenerica.ListaGenerica;

public class generalSTRING {
	private static void preOrdenString(ArbolGeneral<String> a) {
		if (a.getDatoRaiz().equals("hola")) {
			System.out.println("SI");
		}
		ListaGenerica<ArbolGeneral<String>> lHijos = a.getHijos();
		lHijos.comenzar();
		while (!lHijos.fin()) {
			 preOrdenString(lHijos.proximo());
			 }
		}

	public static void main(String[] args) {
		ArbolGeneral<String> ag = new ArbolGeneral<String>("hola");
		
		ArbolGeneral<String> a2 = new ArbolGeneral<String>("ters");
		
		ArbolGeneral<String> a3 = new ArbolGeneral<String>("tasa");
		
		ArbolGeneral<String> a4 = new ArbolGeneral<String>("agas");
		
		
		ArbolGeneral<String> a5 = new ArbolGeneral<String>("gaea");
		
		ArbolGeneral<String> a6 = new ArbolGeneral<String>("gadh");
		ArbolGeneral<String> a7 = new ArbolGeneral<String>("khj");
		ArbolGeneral<String> a8 = new ArbolGeneral<String>("khj");
		
		ArbolGeneral<String> a9 = new ArbolGeneral<String>("ighj");
		
		
		ArbolGeneral<String> a10= new ArbolGeneral<String>("ghjg");
		
		ag.agregarHijo(a2);
		ag.agregarHijo(a3);
		ag.agregarHijo(a4);
		a2.agregarHijo(a5);
		a3.agregarHijo(a6);
		a3.agregarHijo(a7);
		a3.agregarHijo(a8);
		a4.agregarHijo(a9);
		a9.agregarHijo(a10);
		generalSTRING.preOrdenString(ag);

}
}
