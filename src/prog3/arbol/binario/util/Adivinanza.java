package prog3.arbol.binario.util;

import prog3.arbol.binario.ArbolBinario;
import prog3.listagenerica.ListaGenericaEnlazada;

public class Adivinanza {

	public Adivinanza() {
		
	}
	
	/*
										 ¿4 Patas?
									si			  no
								¿Se Mueve?		¿Tiene Alguna?
							si			  no
						¿Ladra? 			¿Mesa?
					si
				ES UN PERRO
				
				
				
				Voy a hacer un recorrido preorden hasta encontrar la hoja (es un perro)
				y luego devuelvo la lista, ya que es mi camino mas largo.
*/
	public ListaGenericaEnlazada<String> secuenciaConMasPreguntas(ArbolBinario<String> aBinario)
		{
			ListaGenericaEnlazada<String> lista = new ListaGenericaEnlazada<String> ();
			ListaGenericaEnlazada<String> camino = new ListaGenericaEnlazada<String> ();
			recorrido (aBinario, lista, camino);
			System.out.println(camino);
			return camino;
			} 
	private void recorrido (ArbolBinario<String> aBinario, ListaGenericaEnlazada<String> lista,ListaGenericaEnlazada<String> camino) {
		if (aBinario.esHoja()) {
			if (lista.tamanio() > camino.tamanio())
					clonar(lista, camino);
		} else {
			lista.agregarFinal(aBinario.getDatoRaiz());
			if(!aBinario.getHijoIzquierdo().esVacio()) {
				lista.agregarFinal(",SI,");
				secuenciaConMasPreguntas(aBinario.getHijoIzquierdo()); // paso a la siguiente opcion de la izquierda;
			}
			if(!aBinario.getHijoDerecho().esVacio()) {
				lista.agregarFinal(",NO,");
				secuenciaConMasPreguntas(aBinario.getHijoDerecho());
			}
		}
	}
	private void clonar (ListaGenericaEnlazada<String> lista,ListaGenericaEnlazada<String> camino) {
		lista.comenzar();
		while(!lista.fin())
			camino.agregarFinal(lista.proximo());
	}
}

		