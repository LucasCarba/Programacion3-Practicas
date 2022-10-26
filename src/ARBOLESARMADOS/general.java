package ARBOLESARMADOS;

import prog3.arbol.general.ArbolGeneral;
import prog3.listagenerica.ListaGenerica;
import prog3.util.ColaGenerica;

public class general {
/*
	public static int sumarEnNivel (ArbolGeneral<Integer> a,int n) {
		int sumatoria= 0;	
		return sumarEnElNivel(a,n,sumatoria);
}
private static Integer sumarEnElNivel(ArbolGeneral<Integer> a, int n, int sumatoria) {
	ColaGenerica<ArbolGeneral<Integer>> cola= new ColaGenerica<ArbolGeneral<Integer>>();
	cola.encolar(a);
	int nivel=0;
	cola.encolar(null);
	while (!cola.esVacia()) {
		ArbolGeneral<Integer> aux = cola.desencolar();
		if(aux!=null) {
			if (nivel == n)
				sumatoria+= aux.getDatoRaiz();
			ListaGenerica<ArbolGeneral<Integer>> lista= aux.getHijos();
			lista.comenzar();
			while(!lista.fin()) 
				cola.encolar(lista.proximo());
		}else {
			if(!cola.esVacia()) {
				++nivel;
				cola.encolar(null);
			}
		}
	}
	return sumatoria;
}	
*/
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
		//System.out.println(general.sumarEnNivel(ag, 2));
		
	}

}
