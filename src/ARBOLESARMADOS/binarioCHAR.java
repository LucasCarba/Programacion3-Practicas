package ARBOLESARMADOS;

import prog3.arbol.binario.ArbolBinario;

public class binarioCHAR {

public static void preOrdenCaracter(ArbolBinario<Character> a) {
	System.out.println(a.getDatoRaiz());
	if (!a.getHijoIzquierdo().esVacio())
		preOrdenCaracter(a.getHijoIzquierdo());
	if (!a.getHijoDerecho().esVacio())
		preOrdenCaracter(a.getHijoDerecho());
}

	public static void main(String[] args) {
		ArbolBinario<Character> arbolBinarioB=new ArbolBinario<Character>('h');		
		ArbolBinario<Character> hijoIzquierdoB=new ArbolBinario<Character>('f');
		ArbolBinario<Character> hijoDerechoB=new ArbolBinario<Character>('a');
		
			hijoIzquierdoB.agregarHijoIzquierdo(new ArbolBinario<Character>('s'));
			hijoIzquierdoB.agregarHijoDerecho(new ArbolBinario<Character>('q'));		
	
			hijoDerechoB.agregarHijoIzquierdo(new ArbolBinario<Character>('l'));
			hijoDerechoB.agregarHijoDerecho(new ArbolBinario<Character>('r'));
		
		arbolBinarioB.agregarHijoIzquierdo(hijoIzquierdoB);
		arbolBinarioB.agregarHijoDerecho(hijoDerechoB);

		binarioCHAR.preOrdenCaracter(arbolBinarioB);
	}

}
