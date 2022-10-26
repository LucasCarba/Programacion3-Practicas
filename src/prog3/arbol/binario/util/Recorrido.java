package prog3.arbol.binario.util;


import prog3.arbol.binario.ArbolBinario;
import prog3.util.*;

public class Recorrido<T> {

	public void imprimirPreOrder(ArbolBinario<T> aBinario) {
		System.out.println(aBinario.getDatoRaiz());
		if(!aBinario.getHijoIzquierdo().esVacio()) {
			imprimirPreOrder(aBinario.getHijoIzquierdo());
		}
		if(!aBinario.getHijoDerecho().esVacio()) {
			imprimirPreOrder(aBinario.getHijoDerecho());
		}	
		

	}

	public void imprimirInOrder(ArbolBinario<T> aBinario) {
		if(!aBinario.getHijoIzquierdo().esVacio()) {
			imprimirInOrder(aBinario.getHijoIzquierdo());
		}
		System.out.println("+"+aBinario.getDatoRaiz());
		if(!aBinario.getHijoDerecho().esVacio()) {
			imprimirInOrder(aBinario.getHijoDerecho());
		}	
		

	}

	public void imprimirPostOrder(ArbolBinario<T> aBinario) {
		if(!aBinario.getHijoIzquierdo().esVacio()) {
			imprimirPostOrder(aBinario.getHijoIzquierdo());
		}
		
		if(!aBinario.getHijoDerecho().esVacio()) {
			imprimirPostOrder(aBinario.getHijoDerecho());
		}	
		
		System.out.println("-"+aBinario.getDatoRaiz());
	}
	 public void imprimirPorNiveles(ArbolBinario<T> aBinario) {
		 ArbolBinario<T> arbol=null;
		 Cola<ArbolBinario<T>> cola =new Cola<ArbolBinario<T>>();
		 cola.encolar(aBinario);
		 cola.encolar(null);
		 while (!cola.esVacia()) {
			 arbol= cola.desencolar();
			 if(arbol!=null) {
				 System.out.println(arbol.getDatoRaiz());
				 if(!arbol.getHijoIzquierdo().esVacio())
					 cola.encolar(arbol.getHijoIzquierdo());
				 if(!arbol.getHijoDerecho().esVacio())
					 cola.encolar(arbol.getHijoDerecho());
			 } else
				 if(!cola.esVacia()){
					 System.out.println();
					 cola.encolar(null);
				 }
					 
			 }
		 }
		 
		 
	 
	 /*No hay que implementarlo, solamente pensarlo.
	  * public boolean completo(ArbolBinario<T> a) {
		 
		 ArbolBinario<T> arbol=null;
		 Cola<ArbolBinario<T>> cola =new Cola<ArbolBinario<T>>();
		 boolean c=true;
		 
		 cola.encolar(a);
		 cola.encolar(null);
		 
		 while (!cola.esVacia() && c) {
			 arbol= cola.desencolar();
			 if(arbol!=null) {
				 
				 System.out.println(arbol.getDatoRaiz());
				 
				 if (arbol.getHijoIzquierdo().esVacio() && !arbol.getHijoDerecho().esVacio())
					 c=false;
			
					 
				if(!arbol.getHijoIzquierdo().esVacio()) 
					cola.encolar(arbol.getHijoIzquierdo());
				if(!arbol.getHijoDerecho().esVacio() && arbol.esHoja()) 
					cola.encolar(arbol.getHijoDerecho());
				 
			}else
				if(!cola.esVacia()){
							cola.encolar(null);
					 }
		 	}
	 return c;	
	 }
		*/
	
	public static void main(String[] args){
		ArbolBinario<Integer> arbolBinarioB=new ArbolBinario<Integer>(1);		
		ArbolBinario<Integer> hijoIzquierdoB=new ArbolBinario<Integer>(2);
		ArbolBinario<Integer> hijoDerechoB=new ArbolBinario<Integer>(6);
		
		hijoIzquierdoB.agregarHijoIzquierdo(new ArbolBinario<Integer>(3));
		hijoIzquierdoB.agregarHijoDerecho(new ArbolBinario<Integer>(4));		
	
		hijoDerechoB.agregarHijoIzquierdo(new ArbolBinario<Integer>(7));
		hijoDerechoB.agregarHijoDerecho(new ArbolBinario<Integer>(8));
		
		arbolBinarioB.agregarHijoIzquierdo(hijoIzquierdoB);
		arbolBinarioB.agregarHijoDerecho(hijoDerechoB);
		
		Recorrido<Integer> recorrido= new Recorrido<Integer>();
		//recorrido.imprimirPreOrder(arbolBinarioB);
		//recorrido.imprimirInOrder(arbolBinarioB);
		//recorrido.imprimirPostOrder(arbolBinarioB);
		//recorrido.imprimirPorNiveles(arbolBinarioB);
		System.out.println(arbolBinarioB.lleno());
		//System.out.println(arbolBinarioB.frontera());
		//System.out.println(recorrido.completo(arbolBinarioB));

	}
}
