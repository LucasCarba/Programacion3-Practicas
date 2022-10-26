package prog3.arbol.avl;

import prog3.arbol.binario.ArbolBinario;
import prog3.listaenteros.ListaDeEnterosEnlazada;
import prog3.listagenerica.ListaGenericaEnlazada;

public class avl {
//Implemente un metodo que busque valores menores al valor pasado por parametro y retorne una lista de los mismos.
	
public ListaDeEnterosEnlazada menoresOrdenados (ArbolBinario <Integer> ab, int valor) {
	ListaDeEnterosEnlazada lista = new ListaDeEnterosEnlazada();
	menoresOrdenados (ab, lista, valor);
	return lista;
}
private void menoresOrdenados (ArbolBinario<Integer> ab, ListaDeEnterosEnlazada lista, int valor) {
	if (!ab.esVacio()) {
		menoresOrdenados (ab.getHijoIzquierdo(),lista,valor);
		if (ab.getDatoRaiz() < valor) {
			lista.agregarFinal(ab.getDatoRaiz());
			menoresOrdenados(ab.getHijoDerecho(),lista, valor);
		}
		
	}
}

//Retorna una lista con los valores (camino) que suman tal dato. 
//ej 26 retorna {(15,1,10)}
public ListaGenericaEnlazada<Integer> sumaCaminos (ArbolBinario <Integer> ab, int dato){
	ListaGenericaEnlazada<Integer> lista = new ListaGenericaEnlazada<Integer>();
	ListaDeEnterosEnlazada camino = new ListaDeEnterosEnlazada();
	camino.agregarFinal(ab.getDatoRaiz());
	lista.comenzar();
	sumaCaminos(ab, lista, camino, 0, dato);
	return lista;
}
private void sumaCaminos(ArbolBinario<Integer> ab, ListaGenericaEnlazada<Integer> lista,ListaDeEnterosEnlazada camino, int acc, int dato){
	if (!ab.esVacio()) {
		acc+= ab.getDatoRaiz();
		if(acc == dato) {
			while (!camino.fin()) {
				lista.agregarFinal(camino.proximo());
			}
		
		} else
		{
			if(acc<dato) {
				ListaDeEnterosEnlazada caminoIz = new ListaDeEnterosEnlazada();
				ListaDeEnterosEnlazada caminoDer = new ListaDeEnterosEnlazada();
				camino.comenzar();
				while (!camino.fin()) {
					Integer valor= camino.proximo();
					caminoIz.agregarFinal(valor);
					caminoDer.agregarFinal(valor);
				}
				caminoIz.agregarFinal(ab.getHijoIzquierdo().getDatoRaiz());
				caminoDer.agregarFinal(ab.getHijoDerecho().getDatoRaiz());
				sumaCaminos(ab.getHijoIzquierdo(),lista,caminoIz,acc,dato);
				sumaCaminos(ab.getHijoDerecho(),lista,caminoDer,acc,dato);
			}
		}
	}
}


}
