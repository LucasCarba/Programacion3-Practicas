package MACBA;

import ciudadMafiosa.sitio;
import grafos.Arista;
import grafos.GrafoImplListAdy;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class recorrido {

	GrafoImplListAdy<Lugar> Galeria= new GrafoImplListAdy<Lugar>();
	
	public ListaGenerica<Lugar> getRuta2Horas(String origen){
		
		ListaGenerica<Lugar> camino = new ListaGenericaEnlazada<Lugar>();
		int tiempo=(2*60);
		Vertice<Lugar> vIni = null;
		ListaGenerica<Lugar> caminoMax= new ListaGenericaEnlazada<Lugar>();
		int cant=0;
		int[] cantMax = { 0 };
		ListaGenerica<Vertice<Lugar>> vertices = Galeria.listaDeVertices();
		boolean[] visitados = new boolean [vertices.tamanio()];
		vertices.comenzar();
		while (!vertices.fin() && (vIni==null)) {
			Vertice<Lugar> v = vertices.proximo();
			if(v.dato().getNombre().equals(origen))
				vIni=v;
		}
	
	
	dfs(vIni,visitados, tiempo,0, camino, caminoMax, cant, cantMax);
	return caminoMax;
}

	
	private void dfs(Vertice<Lugar> entrada,boolean[] visitados, int tiempo,int cantTiempo, ListaGenerica<Lugar> camino, ListaGenerica<Lugar> caminoMax, int cant, int[] cantMax) {

		visitados[entrada.posicion()]=true;
		camino.agregarFinal(entrada.dato());
		
		if (cantTiempo>=tiempo) {
			if(cant>cantMax[0]) {
				cantMax[0]=cant;;
				copiar(camino, caminoMax);
			}
		} else {
			ListaGenerica<Arista<Lugar>> adyacentes = Galeria.listaDeAdyacentes(entrada);
			adyacentes.comenzar();
			while(!adyacentes.fin()) {
				Arista<Lugar> arista = adyacentes.proximo();
				if (!visitados[arista.verticeDestino().posicion()]) {
					dfs (arista.verticeDestino(), visitados, tiempo, (cantTiempo +( arista.peso() + entrada.dato().getTiempo())) , camino,caminoMax, cant + 1, cantMax);
					
				}
			}
		}
		visitados[entrada.posicion()]= false;
		camino.eliminarEn(camino.tamanio() - 1);
		}

	
	private void copiar ( ListaGenerica<Lugar> listaAux, ListaGenerica<Lugar> listaFinal) {
		int tamFinal = listaFinal.tamanio();
		
		//Borro todo lo que tenia anteriormente.
		for(int i=1; i<=tamFinal; i++)
			listaFinal.eliminarEn(0);
		
		// Agrego la nueva lista, para quedarmela en caminoMax.
		int tamanioListaAux= listaAux.tamanio();
		for(int i=0; i< (tamanioListaAux -1 ); i++) // Tamanio lista aux -1 porque no agrego el ultimo, ya que me paso del tiempo.
			listaFinal.agregarFinal(listaAux.elemento(i));
		}
		
	}
		
	
	
	

