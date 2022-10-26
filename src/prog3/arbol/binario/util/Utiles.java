package prog3.arbol.binario.util;

import prog3.arbol.binario.ArbolBinario;
import prog3.listaenteros.ListaDeEnterosEnlazada;
import prog3.util.Cola;

public class Utiles {

	public Utiles() {
			} 
	/* VALORES DE SUMA
	  							* Suma = 0
	  							* Suma = 1
	  					* Suma = 1 + 2 | 1 + 6
	  			* Suma=  1+2+3 y 1+2+4 | 1+6+7 y 1+6+8
	  	Me quedo con 1+6+8=15 que es la maxima suma.
	  * 
	  */
	 public int sumaMaximaVertical (ArbolBinario<Integer> a, int suma) {
			if (!a.esVacio()) {
				return 0;
			}
			suma+=a.getDatoRaiz();
			if(!a.esHoja()) {	
				int sumaIzquierda = sumaMaximaVertical (a.getHijoIzquierdo(), suma);
				int sumaDerecha = sumaMaximaVertical (a.getHijoDerecho(), suma);
				suma= Math.max(sumaIzquierda, sumaDerecha);
				
			}
			
			return suma+a.getDatoRaiz();
		}
		
	public int sumaMaximaHorizontal (ArbolBinario<Integer> a) {
		 ArbolBinario<Integer> aux=null;
		 Cola<ArbolBinario<Integer>> cola =new Cola<ArbolBinario<Integer>>();
		 ListaDeEnterosEnlazada lista= new ListaDeEnterosEnlazada();
		 lista.agregarInicio(0);
		 cola.encolar(a);
		 cola.encolar(null);
		 while (!cola.esVacia()) {
			 aux= cola.desencolar();
			 if(aux!=null) {
				 lista.agregarInicio(lista.elemento(0) + aux.getDatoRaiz());
				 if(!aux.getHijoIzquierdo().esVacio())
					 cola.encolar(aux.getHijoIzquierdo());
				 if(!aux.getHijoDerecho().esVacio())
					 cola.encolar(aux.getHijoDerecho());
			 } else
				 if(!cola.esVacia()){
					 lista.agregarInicio(0);
					 cola.encolar(null);
				 }
			 int max=-100;
			 while (!lista.fin()) {
				 int dato=lista.proximo();
				 if(dato > max)
					 max=dato;
			 }
			 return max;
		 }
	return 0;
	}
	}