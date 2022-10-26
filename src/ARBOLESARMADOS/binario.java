package ARBOLESARMADOS;
import prog3.arbol.binario.ArbolBinario;
import prog3.listagenerica.ListaGenericaEnlazada;
public class binario {
	
	/*public static ListaGenericaEnlazada<Integer> tp( ArbolBinario<Integer> arbol) {
		ListaGenericaEnlazada<Integer> lista = new ListaGenericaEnlazada<Integer>();
		int nivel=0;
		int sumaParcial=0;
		recorrer (arbol, lista, nivel, sumaParcial);
		return lista;
	}

	private static void recorrer(ArbolBinario<Integer> a, ListaGenericaEnlazada<Integer> lista, int nivel, int sumaParcial) {
	if (!a.esVacio()) {
		if(a.esHoja())	
			lista.agregarFinal(sumaParcial + a.getDatoRaiz()*nivel );
		if(!a.getHijoIzquierdo().esVacio())
			recorrer(a.getHijoIzquierdo(), lista, nivel + 1, sumaParcial + a.getDatoRaiz()*nivel);
		if(!a.getHijoDerecho().esVacio())
			recorrer(a.getHijoDerecho(), lista, nivel + 1, sumaParcial + a.getDatoRaiz()*nivel);
		}
	}
	*/
		public static void main(String[] args) {
		ArbolBinario<Integer> arbolBinarioB=new ArbolBinario<Integer>(1);		
			ArbolBinario<Integer> hijoIzquierdoB=new ArbolBinario<Integer>(2);
			ArbolBinario<Integer> hijoDerechoB=new ArbolBinario<Integer>(6);
			
				hijoIzquierdoB.agregarHijoIzquierdo(new ArbolBinario<Integer>(3));
				hijoIzquierdoB.agregarHijoDerecho(new ArbolBinario<Integer>(4));		
		
				hijoDerechoB.agregarHijoIzquierdo(new ArbolBinario<Integer>(7));
				hijoDerechoB.agregarHijoDerecho(new ArbolBinario<Integer>(8));
			
			arbolBinarioB.agregarHijoIzquierdo(hijoIzquierdoB);
			arbolBinarioB.agregarHijoDerecho(hijoDerechoB);
			
			//System.out.println(binario.tp(arbolBinarioB));

	}

}
