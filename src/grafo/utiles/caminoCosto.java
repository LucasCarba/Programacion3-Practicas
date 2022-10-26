package grafo.utiles;

import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class caminoCosto {

	
	
	private void copiar (ListaGenerica<String> listaAux, ListaGenerica<String> listaFinal) {
		int tamanioListaFinal = listaFinal.tamanio();
		for(int i=1; i<=tamanioListaFinal; i++)
			listaFinal.eliminarEn(0);
		int tamanioListaAux= listaAux.tamanio();
		for(int i=0; i< tamanioListaAux; i++)
			listaFinal.agregarFinal(listaAux.elemento(i));
		
	}
	
	public ListaGenerica<String> camino(Grafo<String> grafo, String origen, String destino, int costo){
		ListaGenerica<String> camino = new ListaGenericaEnlazada<String>();
		ListaGenerica<String> lista = new ListaGenericaEnlazada<String>();
		ListaGenerica<Vertice<String>> vertices = grafo.listaDeVertices();
		boolean[] visitados = new boolean [vertices.tamanio()];
		vertices.comenzar();
		Vertice<String> vIni = null;
		Vertice<String> vFin = null;
		int total=0;
		while (!vertices.fin() &&(vIni==null)) {
			Vertice<String> v = vertices.proximo();
			if(v.dato().equals(origen))
				vIni=v;
			if(v.dato().equals(destino))
				vFin=v;
	}
		dfs(vIni, vFin, visitados, camino, lista, costo, total, grafo);
		return lista;
}

	private void dfs(Vertice<String> entrada, Vertice<String> salida, boolean[] visitados,ListaGenerica<String> camino,ListaGenerica<String> lista, int costo, int total, Grafo<String> grafo) {
		
		visitados[entrada.posicion()]=true;
		camino.agregarFinal(entrada.dato());
		if (entrada.dato().equals(salida)){
			if (costo==total)
				copiar(camino, lista);
		}
		ListaGenerica<Arista<String>> adyacentes = grafo.listaDeAdyacentes(entrada);
		adyacentes.comenzar();
		while(!adyacentes.fin()) {
			Arista<String> arista = adyacentes.proximo();
			if (!visitados[arista.verticeDestino().posicion()]) {
				dfs (arista.verticeDestino(), salida, visitados,camino, lista,costo, total + arista.peso(),  grafo);
				
			}
		}
		visitados[entrada.posicion()]= false;
		camino.eliminarEn(camino.tamanio() - 1);
		}
		
}
