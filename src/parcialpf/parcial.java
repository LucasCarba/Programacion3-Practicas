package parcialpf;

import prog3.arbol.general.ArbolGeneral;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class parcial {

	public ListaGenerica<Integer> resolver(ArbolGeneral<Integer> arbol) {
		ListaGenerica<Integer> lista = new ListaGenericaEnlazada<Integer>();
		preOrden(arbol, lista);
		return lista;
		
	}
	private void preOrden(ArbolGeneral<Integer> a, ListaGenerica<Integer> lista) {
		if (a.getDatoRaiz() != -1) {
			if(!lista.incluye(a.getDatoRaiz())) {
				lista.agregarFinal(a.getDatoRaiz());
				}else {
					lista.eliminar(a.getDatoRaiz());
				}
			ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
			hijos.comenzar();
			while (!hijos.fin()) 
				preOrden(hijos.proximo(),lista);
		}
	}
}
