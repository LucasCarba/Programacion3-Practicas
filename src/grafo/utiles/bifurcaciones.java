package grafo.utiles;

import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class bifurcaciones {
	
	private void copiar (ListaGenerica<String> listaAux, ListaGenerica<String> listaFinal) {
		int tamanioListaFinal = listaFinal.tamanio();
		for(int i=1; i<=tamanioListaFinal; i++)
			listaFinal.eliminarEn(0);
		int tamanioListaAux= listaAux.tamanio();
		for(int i=0; i< tamanioListaAux; i++)
			listaFinal.agregarFinal(listaAux.elemento(i));
		
	}
	
	public ListaGenerica<String> rutaSky(Grafo<String> grafo){
		ListaGenerica<String> camino = new ListaGenericaEnlazada<String>();
		
		ListaGenerica<String> lista = new ListaGenericaEnlazada<String>();
		Vertice<String> vIni = null;
		int cantBifurcacion=0;
		ListaGenerica<Vertice<String>> vertices = grafo.listaDeVertices();
		boolean[] ady = new boolean [vertices.tamanio()]; // va a ser true cuando el vertice no tenga adyacentes.
		boolean[] visitados = new boolean [vertices.tamanio()];
		vertices.comenzar();
		while (!vertices.fin() &&(vIni==null)) {
			Vertice<String> v = vertices.proximo();
			if(v.dato().equals("zona 0"))
				vIni=v;
	}
	dfs(vIni, camino, visitados, cantBifurcacion, lista, ady, grafo);
	return lista;
}

	private void dfs(Vertice<String> entrada, ListaGenerica<String> camino, boolean[] visitados, int cantBifurcacion,
			ListaGenerica<String> lista, boolean[] ady, Grafo<String> grafo) {
		visitados[entrada.posicion()]=true;
		camino.agregarFinal(entrada.dato());
		
		ListaGenerica<Arista<String>> adyacentes = grafo.listaDeAdyacentes(entrada);
		if (adyacentes.tamanio() == 0)
			ady[entrada.posicion()]= true;
		
		if (( cantBifurcacion == 2) && (ady[entrada.posicion()])) {
			copiar(camino, lista);
			
		adyacentes.comenzar();
		while(!adyacentes.fin()) {
			Arista<String> arista = adyacentes.proximo();
			if (!visitados[arista.verticeDestino().posicion()]) {
				dfs (arista.verticeDestino(),  camino,visitados,cantBifurcacion+1, lista, ady, grafo);
				
			}
		}
	}
	visitados[entrada.posicion()]= false;
	camino.eliminarEn(camino.tamanio() - 1);
	}
		


}
		
	
