package ciudadMafiosa;

import grafos.Arista;
import grafos.GrafoImplListAdy;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class mafia {
	GrafoImplListAdy<sitio> gCiudad= new GrafoImplListAdy<sitio>();
	
	private void copiar ( ListaGenerica<sitio> listaAux, ListaGenerica<sitio> listaFinal) {
		int tamanioListaFinal = listaFinal.tamanio();
		for(int i=1; i<=tamanioListaFinal; i++)
			listaFinal.eliminarEn(0);
		int tamanioListaAux= listaAux.tamanio();
		for(int i=0; i< tamanioListaAux; i++)
			listaFinal.agregarFinal(listaAux.elemento(i));
		
	}
	public ListaGenerica<sitio> getRutaMinMafia(){
		
		ListaGenerica<sitio> camino = new ListaGenericaEnlazada<sitio>();
		int mafias = 0;
		
		Vertice<sitio> vIni = null;
		Vertice<sitio> vFin = null;
		
		ListaGenerica<sitio> caminoMin= new ListaGenericaEnlazada<sitio>();
		int[] mafiasMin = { Integer.MAX_VALUE};
			
		
		ListaGenerica<Vertice<sitio>> vertices = gCiudad.listaDeVertices();
		boolean[] visitados = new boolean [vertices.tamanio()];
		vertices.comenzar();
		while (!vertices.fin() &&(vIni==null || vFin==null)) {
			Vertice<sitio> v = vertices.proximo();
			if(v.dato().getNombre().equals("casa del intendente"))
				vIni=v;
			if(v.dato().getNombre().equals("municipalidad"))
				vFin=v;
		}
		
		dfs(vIni,vFin,visitados, camino, caminoMin, mafias, mafiasMin);
		return caminoMin;
	}


	private void dfs(Vertice<sitio> entrada, Vertice<sitio> salida, boolean[] visitados, ListaGenerica<sitio> camino,
		ListaGenerica<sitio> caminoMin, int mafias, int[] mafiasMin) {
		visitados[entrada.posicion()]=true;
		camino.agregarFinal(entrada.dato());
		if(entrada.dato().getTieneMafia())
			mafias++;
		if(entrada.equals(salida)) {
			if(mafias<mafiasMin[0]) {
				mafiasMin[0]=mafias;
				copiar(camino, caminoMin);
			}
		} else {
			ListaGenerica<Arista<sitio>> adyacentes = gCiudad.listaDeAdyacentes(entrada);
			adyacentes.comenzar();
			while(!adyacentes.fin()) {
				Arista<sitio> arista = adyacentes.proximo();
				if (!visitados[arista.verticeDestino().posicion()]) {
					dfs (arista.verticeDestino(), salida, visitados, camino, caminoMin, mafias+arista.peso(), mafiasMin);
					
				}
			}
		}
		visitados[entrada.posicion()]= false;
		camino.eliminarEn(camino.tamanio() - 1);
		
	}
	
	
}
