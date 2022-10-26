package parcialGrafos;

import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class Parcial {

	private void copiar(ListaGenerica<String> listaAux, ListaGenerica<String> listaFinal) {
		int tamanioListaFinal = listaFinal.tamanio();
		for(int i=1; i<=tamanioListaFinal; i++)
			listaFinal.eliminarEn(0);
		int tamanioListaAux= listaAux.tamanio();
		for(int i=0; i< tamanioListaAux; i++)
			listaFinal.agregarFinal(listaAux.elemento(i));
	}
	
	public ListaGenerica<String> resolver(Grafo<Ciudades> ciudades, String origen, String destino, int minFase){
		ListaGenerica<String> camino = new ListaGenericaEnlazada<String>();
		ListaGenerica<String> lista = new ListaGenericaEnlazada<String>();
		ListaGenerica<Vertice<Ciudades>> vertices = ciudades.listaDeVertices();
		boolean[] visitados = new boolean [vertices.tamanio()];
		vertices.comenzar();
		Vertice<Ciudades> A = null;//origen.
		Vertice<Ciudades> B = null;//destino
		while (!vertices.fin() && (A==null) && (B==null)) {
			Vertice<Ciudades> v = vertices.proximo();
			if(v.dato().equals(origen));
				A=v;
			if (v.dato().equals(destino));
				B=v;
		}
		
		dfs (A, B,visitados, camino, lista, minFase, ciudades);
		
	// retorno la lista, si esta vacia(no encontro camino) devuelve una lista vacia, sino el camino correspondiente).
	return lista;
	
}

	private void dfs(Vertice<Ciudades> entrada, Vertice<Ciudades> b,boolean[] visitados, ListaGenerica<String> camino, ListaGenerica<String> lista, int minFase, Grafo<Ciudades> grafo) {
		visitados[entrada.posicion()]=true;
		camino.agregarFinal(entrada.dato().getNombre());
		
		if (entrada.dato() == b.dato()) {
			copiar(camino, lista);
		}
		
		ListaGenerica<Arista<Ciudades>> adyacentes = grafo.listaDeAdyacentes(entrada);
		adyacentes.comenzar();
		while(!adyacentes.fin()) {
			Arista<Ciudades> arista = adyacentes.proximo();
			if (!visitados[arista.verticeDestino().posicion()]) {
				
				if(arista.peso() == 0) {
					// si el peso es 0, es accesible. Si es distinto el camino se encontrara bloqueado por los vecinos.
					if (arista.verticeDestino().dato().getFase() >= minFase) {
						
							dfs (arista.verticeDestino() ,b, visitados, camino, lista, minFase, grafo);
					}
				}
					
			}
		}
		visitados[entrada.posicion()]= false;
		camino.eliminarEn(camino.tamanio() - 1);
		}	
		
	}


