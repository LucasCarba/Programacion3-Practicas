package prog3.arbol.binario.util;

import prog3.arbol.binario.ArbolBinario;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class TrayectoriaPesada {

	public TrayectoriaPesada() {
	}
	public ListaGenerica<Integer> calcular(ArbolBinario<Integer> arbol) {
		ListaGenerica<Integer> lista = new ListaGenericaEnlazada<Integer>();
		recorrido(arbol, 0, 0, lista);
		return lista;
		}
	private void recorrido(ArbolBinario<Integer> arbol, int nivel, int sumaParcial, ListaGenerica<Integer> lista) {
		if (!arbol.esVacio()) {
			
			procesar(arbol, nivel, sumaParcial, lista);
			
			if (!arbol.getHijoDerecho().esVacio()) {
				recorrido(arbol.getHijoDerecho(), nivel + 1, sumaParcial + nivel * arbol.getDatoRaiz(), lista);
			}
			if (!arbol.getHijoIzquierdo().esVacio()) {
				recorrido(arbol.getHijoIzquierdo(), nivel + 1, sumaParcial + nivel * arbol.getDatoRaiz(), lista);
			}
		}
	}
	private void procesar(ArbolBinario<Integer> arbol, int nivel, int sumaParcial, ListaGenerica<Integer> lista) {
		if (arbol.esHoja()) {
			lista. agregarFinal(sumaParcial + arbol.getDatoRaiz() * nivel);
			}
	}
}



