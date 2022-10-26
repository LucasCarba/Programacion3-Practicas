package prog3.arbol.binario;

import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;
import prog3.util.Cola;
import prog3.util.ColaGenerica;

public class ArbolBinario<T> {

	private NodoBinario<T> raiz;
	
	public ArbolBinario(){		
		this.raiz = null;
	}
	
	public ArbolBinario(T dato){		
		this.raiz = new NodoBinario<T>(dato);
	}	
	
	private ArbolBinario(NodoBinario<T> nodo){		
		this.raiz = nodo;
	}
	
	private NodoBinario<T> getRaiz(){		
		return raiz;
	}
	
	public T getDatoRaiz(){
		if (this.getRaiz() != null){
			return this.getRaiz().getDato();
		}else{
			return null;
		}
	}
	
	public ArbolBinario<T> getHijoIzquierdo(){		
		return new ArbolBinario<T>(this.raiz.getHijoIzquierdo());
	}
	
	public ArbolBinario<T> getHijoDerecho(){		
		return new ArbolBinario<T>(this.raiz.getHijoDerecho());
	}	
	
	public void agregarHijoIzquierdo(ArbolBinario<T> hijo){		
		this.raiz.setHijoIzquierdo(hijo.getRaiz());
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo){		
		this.raiz.setHijoDerecho(hijo.getRaiz());
	}	
	
	public void eliminarHijoIzquierdo(){		
		this.raiz.setHijoIzquierdo(new NodoBinario<T>(null));
	}
	
	public void eliminarHijoDerecho(){		
		this.raiz.setHijoDerecho(new NodoBinario<T>(null));
	}	
	
	public boolean esVacio(){
		return this.raiz==null;
	}
	
	public boolean esHoja(){
		return this.getDatoRaiz()!=null && this.getHijoIzquierdo().esVacio() && this.getHijoDerecho().esVacio();
	}
	public ListaGenerica<T> frontera() {
		ListaGenerica<T> l = new ListaGenericaEnlazada<T>();
		this.preOrdenFrontera(l,this);
		return l;
	}
	public void preOrdenFrontera (ListaGenerica<T> l, ArbolBinario<T> ab) {
			if (ab.esHoja()) {
				l.agregarFinal(ab.getDatoRaiz());
			}
			if(!ab.getHijoIzquierdo().esVacio()) {
				ab.getHijoIzquierdo().preOrdenFrontera(l, ab.getHijoIzquierdo());
				}
			if(!ab.getHijoDerecho().esVacio()) {
				ab.getHijoDerecho().preOrdenFrontera(l, ab.getHijoDerecho());
				}
		}

	

	public boolean lleno() {
		ArbolBinario<T> arbol=null;
		 ColaGenerica<ArbolBinario<T>> cola =new ColaGenerica<ArbolBinario<T>>();
		 cola.encolar(this);
		 boolean llenoo=true;
		 int cant_nodos=0;
		 cola.encolar(null);
		 int nivel=0;
		 while (!cola.esVacia() && llenoo) {
			 arbol= cola.desencolar();
			 if(arbol!=null) {
				 System.out.println(arbol.getDatoRaiz());
				 if(!arbol.getHijoIzquierdo().esVacio()) {
					 cola.encolar(arbol.getHijoIzquierdo());
					 cant_nodos++;
				 }
				 if(!arbol.getHijoDerecho().esVacio()) {
					 cola.encolar(arbol.getHijoDerecho());
					 cant_nodos++;
				 }
			 } else
				 if(!cola.esVacia()){
					 if(cant_nodos == Math.pow(2, ++nivel)) {
						 cola.encolar(null);
						 cant_nodos=0;
						 System.out.println();
					 }
					 else llenoo=false;
				 }
					 
			 }
		 return true;
		 }
	
	// Trayectoria pesada devuelvo el valor en una lista generica
	// el valor es el camino desde su raiz a sus hojas, y en el camino voy multiplicando el valor del nodo por su nivel
	
	public ListaGenericaEnlazada<Integer> tp( ArbolBinario<Integer> arbol) {
		ListaGenericaEnlazada<Integer> lista = new ListaGenericaEnlazada<Integer>();
		int nivel=0;
		int sumaParcial=0;
		recorrer (arbol, lista, nivel, sumaParcial);
		return lista;
	}

	private void recorrer(ArbolBinario<Integer> a, ListaGenericaEnlazada<Integer> lista, int nivel, int sumaParcial) {
	if (!a.esVacio()) {
		if(a.esHoja())	
			lista.agregarFinal(sumaParcial + a.getDatoRaiz()*nivel );
		if(!a.getHijoIzquierdo().esVacio())
			recorrer(a.getHijoIzquierdo(), lista, nivel + 1, sumaParcial + a.getDatoRaiz()*nivel);
		if(!a.getHijoDerecho().esVacio())
			recorrer(a.getHijoDerecho(), lista, nivel + 1, sumaParcial + a.getDatoRaiz()*nivel);
		}
	}
	
	/*
	 * 
	 * Calculo el mayor retardo posible, en el camino que realiza un
	 * mensaje desde la raíz hasta llegar a las hojas en una red binaria completa.
	 * 
	 */
	public int RedBinaria(ArbolBinario<Integer> arbol) {
		int mayor=0;
		ListaGenericaEnlazada<Integer> lista = new ListaGenericaEnlazada<Integer>();
		buscarMayor (arbol,lista,0);
		for (int i=0; i< lista.tamanio(); i++) {
			if(lista.elemento(i) > mayor)
				mayor=lista.elemento(i);
		}
		return mayor;
	}
	
	private void buscarMayor (ArbolBinario<Integer> a,ListaGenericaEnlazada<Integer> lista, int suma) {
		if (!a.esVacio()) {
			if(a.esHoja())	
				lista.agregarFinal(suma+ a.getDatoRaiz());
		else {
			if(!a.getHijoIzquierdo().esVacio()) 
				buscarMayor(a.getHijoIzquierdo(),lista,suma +a.getDatoRaiz());
			if(!a.getHijoDerecho().esVacio()) 
				buscarMayor(a.getHijoDerecho(),lista,suma +a.getDatoRaiz());
			}
	}
}
	/*
	 * tiene como variable de
instancia un árbol binario de números enteros y un método de instancia
sumaElementosProfundidad (int p) que devuelve la suma de todos los nodos del árbol que
se encuentren en profundidad N (pasada como argumento).
	 */
	
	public int sumaElementosProfundidad(ArbolBinario<Integer> arbol,int p) {
		int[] s = {0};
		suma(arbol, p, 0 , s);
		return s[0];
	}
	private void suma(ArbolBinario<Integer> arbol, int p, int nivel, int[] s) {
		if (nivel == p) {
			s[0]+= arbol.getDatoRaiz();
			}
		 else {
			if (nivel < p) {
				if (!arbol.getHijoIzquierdo().esVacio())
					suma(arbol.getHijoIzquierdo(),p, nivel + 1, s);
				if (!arbol.getHijoDerecho().esVacio())
					suma(arbol.getHijoDerecho(), p, nivel + 1, s);
			}
		}
	}

}
	