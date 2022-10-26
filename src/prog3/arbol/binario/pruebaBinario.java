package prog3.arbol.binario;

public class pruebaBinario {

	public pruebaBinario() {
		// TODO Auto-generated constructor stub
	}

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
		
		
		//System.out.println(arbolBinarioB.tp(arbolBinarioB));
		//System.out.println(arbolBinarioB.RedBinaria(arbolBinarioB));
		//System.out.println(arbolBinarioB.sumaElementosProfundidad(arbolBinarioB, 2));
		
	}

	
	
}
