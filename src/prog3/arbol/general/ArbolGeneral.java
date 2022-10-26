package prog3.arbol.general;

import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;
import prog3.util.Cola;
import prog3.util.ColaGenerica;

public class ArbolGeneral<T> {

	private NodoGeneral<T> raiz;

	public ArbolGeneral() {

		this.raiz = null;
	}

	public ArbolGeneral(T dato) {

		this.raiz = new NodoGeneral<T>(dato);

	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> lista) {

		this(dato);
		ListaGenerica<NodoGeneral<T>> hijos = new ListaGenericaEnlazada<NodoGeneral<T>>();

		lista.comenzar();
		while (!lista.fin()) {
			ArbolGeneral<T> arbolTemp = lista.proximo();
			hijos.agregarFinal(arbolTemp.getRaiz());

		}

		raiz.setListaHijos(hijos);
	}

	private ArbolGeneral(NodoGeneral<T> nodo) {

		this.raiz = nodo;
	}

	private NodoGeneral<T> getRaiz() {

		return this.raiz;
	}

	public T getDatoRaiz() {

		return this.raiz.getDato();
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {

		ListaGenerica<ArbolGeneral<T>> lista = new ListaGenericaEnlazada<ArbolGeneral<T>>();
		ListaGenerica<NodoGeneral<T>> hijos = (ListaGenerica<NodoGeneral<T>>) this.getRaiz().getHijos();
		lista.comenzar();
		hijos.comenzar();

		while (!hijos.fin()) {
			lista.agregarFinal(new ArbolGeneral<T>(hijos.proximo()));

		}

		return lista;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		NodoGeneral<T> hijo = unHijo.getRaiz();
		this.raiz.getHijos().agregarFinal(hijo);
	}

	public void eliminarHijo(ArbolGeneral<T> unHijo) {

		NodoGeneral<T> hijo = unHijo.getRaiz();
		boolean ok = false;

		ListaGenerica<NodoGeneral<T>> listaHijos = (ListaGenerica<NodoGeneral<T>>) this.getRaiz().getHijos();
		listaHijos.comenzar();

		while (!listaHijos.fin() && !ok) {

			NodoGeneral<T> hijoTemp = listaHijos.proximo();
			if (hijoTemp.getDato().equals(hijo.getDato())) {
				ok = listaHijos.eliminar(hijoTemp);

			}
		}

	}
	public boolean esVacio() {
		return this.getDatoRaiz()==null;
	}
	public boolean esHoja() {
		return this.getDatoRaiz()!=null && this.getHijos().esVacia();
	}
	
	/*
	 * Devuelve la altura del arbol, es decir la longuitud del camino
	 * mas largo desde el nodo raiz hasta una hoja.
	 */

	public Integer altura() {
		return this.altura(0);
	}
	
	private Integer altura(int h) {
		if (this.esHoja()) {
			return h;
		}
		else {
			int max=-1;
			ListaGenerica<ArbolGeneral<T>> lista = this.getHijos();
			//Recorrer la lista
			lista.comenzar();
			while (!lista.fin()) {
				Integer alt = lista.proximo().altura(h+1);
				if(alt > max)
					max=alt;
			}
			return max;
			}
		}
	/*
	 * Retorna true si el dato a buscar esta en el arbol, y false si el nodo
	 * es una hoja y no esta el dato (no lo encontro).
	 * 
	 */
	public boolean include(T dato) {
		boolean encontro=false;
		if (this.getDatoRaiz() == dato)
			return true;
		else if(this.esHoja())
			return false;
			else {
				ListaGenerica<ArbolGeneral<T>> lista= this.getHijos();
				lista.comenzar();
				while (!lista.fin() && !encontro)
					encontro=lista.proximo().include(dato);
			}
		return encontro;
	}
	
	
	/*
	 * Devuelve el nivel donde se encontro el dato en el arbol. El nivel de un nodo
	 * es la longuitud del unico camino al nodo
	 * Return -1 si no escuentra el dato a buscar.
	 */
	public Integer nivel(T dato) {
		int[] n= {0};
		nivel(this,dato,0,n);
		return n[0];
	}
	
	private void nivel(ArbolGeneral<T> a,T dato,int nivel, int[] n) {
		if(a.getDatoRaiz() == dato) {
			n[0]=nivel;
		}
		else {
		ListaGenerica<ArbolGeneral<T>> lista= a.getHijos();
		lista.comenzar();
		while (!lista.fin()) 
			nivel(lista.proximo(), dato, nivel +1 , n);
		
		}
	}
	
	
	/*
	 * Devuelve la amplitud de un arbol, es decir la cantidad de nodos que se encuentran en el nivel que posee mayor
	 * cantidad de nodos.
	 * 
	 
	public Integer ancho() {
		ColaGenerica<ArbolGeneral<T>> cola= new ColaGenerica<ArbolGeneral<T>>();
		int maxAncho=1;
		int anch=0;
		cola.encolar(anc);
		cola.encolar(null);
		while (!cola.esVacia()) {
			ArbolGeneral<T> aux = cola.desencolar();
			if(aux!=null) {
				anch++;
				ListaGenerica<ArbolGeneral<T>> lista= this.getHijos();
				lista.comenzar();
				while(!lista.fin()) 
					cola.encolar(lista.proximo());
			}else {
			if(maxAncho<anch)
				maxAncho=anch;
			if(!cola.esVacia())
				cola.encolar(null);
			anch=0;
		}
		}
		
		return maxAncho;
	}
	 */
	
	public ListaGenericaEnlazada<T> preOrden() {
		ListaGenericaEnlazada<T> lis = new ListaGenericaEnlazada<T>();
		this.preOrden(lis);
		return lis;
	}
	private void preOrden(ListaGenerica<T> l) {
		l.agregarFinal(this.getDatoRaiz());
		ListaGenerica<ArbolGeneral<T>> lHijos = this.getHijos();
		lHijos.comenzar();
		while (!lHijos.fin()) {
			 (lHijos.proximo()).preOrden(l);
			 }
		}
	
	
	
	public int[] promedioArbol(ArbolGeneral<Integer> a) {
		int [] vector = {0,0,0};
		promArbol(a, vector);
		System.out.println( vector[0] );
		System.out.println( vector[1] );
		
		vector[2]= (vector[0]/vector[1]);
		System.out.println( vector[2] );
		return vector;
	}
	private void promArbol(ArbolGeneral<Integer> a, int[] vector) {
		ColaGenerica<ArbolGeneral<Integer>> cola= new ColaGenerica<ArbolGeneral<Integer>>();
		cola.encolar(a);
		cola.encolar(null);
		while (!cola.esVacia()) {
			ArbolGeneral<Integer> aux = cola.desencolar();
			
			if(aux!=null) {
				// sumo todos los datos en la posicion cero
				vector[0] += aux.getDatoRaiz();
				ListaGenerica<ArbolGeneral<Integer>> lista= aux.getHijos();
				lista.comenzar();
				while(!lista.fin()) 
					cola.encolar(lista.proximo());
					vector[1]++;
					
					//sumo en el vector en la posicion 1 cada vex que recorro un hijo
					
			}else {
				if(!cola.esVacia()) {
					cola.encolar(null);
				}
			}
		}
	}

	
	// sumo la cantidad de elementos que hay en el nivel pasado por parametro
	public int cantNivel (ArbolGeneral<Integer> arbol, int n) {
		int[] s= {0};
		
		cantidad (arbol, n, s);

		return s[0];
	}
	private void cantidad (ArbolGeneral<Integer> a, int n, int[] sumatoria) {
		ColaGenerica<ArbolGeneral<Integer>> cola= new ColaGenerica<ArbolGeneral<Integer>>();
		cola.encolar(a);
		int nivel=0;
		int cant_nodos=0;
		cola.encolar(null);
		while (!cola.esVacia()) {
			ArbolGeneral<Integer> aux = cola.desencolar();
			if(aux!=null) {
				ListaGenerica<ArbolGeneral<Integer>> lista= aux.getHijos();
				lista.comenzar();
				while(!lista.fin()) {
					cola.encolar(lista.proximo());
					cant_nodos++;
				}
			
			}else {
				if(!cola.esVacia()) {
					if (n == ++nivel) 
						sumatoria[0]=cant_nodos;
					cant_nodos=0;
					cola.encolar(null);
				}
			}
		}
	}


	
	//Suma todos los elementos del nivel recibido por parametro
	public int sumarEnNivel (ArbolGeneral<Integer> a,int n) {
			int sumatoria= 0;	
			return sumarEnElNivel(a,n,sumatoria);
	}
	private Integer sumarEnElNivel(ArbolGeneral<Integer> a, int n, int sumatoria) {
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
}
