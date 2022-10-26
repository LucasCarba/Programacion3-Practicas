package mapa;

import grafos.Arista;
import grafos.Grafo;
import grafos.GrafoImplListAdy;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class mapas {
	Grafo<String> mapa= new GrafoImplListAdy<String>();
	
	
	
	public  ListaGenerica<String> devolverCamino(String ciudad1, String ciudad2){
		Vertice<String> origen=null;
		 ListaGenerica<Vertice<String>> lista = mapa.listaDeVertices();
		 ListaGenerica<String> camino = new ListaGenericaEnlazada<String>();
		 boolean[] marca = new boolean[lista.tamanio()];
		 lista.comenzar();
		 while (!lista.fin() && origen==null) {
			 Vertice<String> aux = lista.proximo();
			 if(aux.dato().equals(ciudad1)){
				 origen=aux;
			 }
		 }
		boolean encontro= dfsDevolverCamino(origen, ciudad2,marca,camino);
		 if (encontro)
		 	return camino;
		 else
			 return null;
	}
	private boolean dfsDevolverCamino(Vertice<String> origen, String ciudad2, boolean[] marca, ListaGenerica<String> camino) {
		if(origen.dato().equals(ciudad2)) {
			camino.agregarInicio(origen.dato());
			return true;
		} else {
			ListaGenerica<Arista<String>> adyacentes = mapa.listaDeAdyacentes(origen);
			boolean encontro=false;
			adyacentes.comenzar();
			while (!adyacentes.fin() && !encontro) {
				Vertice<String> aux = adyacentes.proximo().verticeDestino();
				if (!marca[aux.posicion()]) {
					marca[aux.posicion()]=true;
					encontro= dfsDevolverCamino(aux, ciudad2, marca, camino);
				}
			}
		}
	return false;
}
	
	
//	------------------------------------------------------------------------------------------------------------------------------
	public ListaGenerica<String> devolverCaminoExceptuando(String ciudad1, String ciudad2, ListaGenerica<String> ciudades){
		Vertice<String> origen= null;
		ListaGenerica <Vertice<String>> lista= mapa.listaDeVertices();
		ListaGenerica<String> camino = new ListaGenericaEnlazada<String>();
		 boolean[] marca = new boolean[lista.tamanio()];
		 lista.comenzar();
		 while (!lista.fin() && origen==null) {
			 Vertice<String> aux = lista.proximo();
			 if(aux.dato().equals(ciudad1)){
				 origen=aux;
			 }		 
		 }
		 boolean encontro = DfsdevolverCaminoExceptuando (origen, ciudad2,ciudades, marca, camino);
		 if (encontro)
			 return camino;
		 else
			 return null;
	}
private boolean DfsdevolverCaminoExceptuando(Vertice<String> origen, String ciudad2,ListaGenerica<String> ciudades, boolean[] marca, ListaGenerica<String> camino) {
	if(origen.dato().equals(ciudad2)) {
		camino.agregarInicio(origen.dato());
		return true;
	
	}else {
		ListaGenerica<Arista<String>> adyacentes = mapa.listaDeAdyacentes(origen);
		boolean encontro=false;
		adyacentes.comenzar();
		while (!adyacentes.fin() && !encontro) {
			Vertice<String> aux = adyacentes.proximo().verticeDestino();
			if (!marca[aux.posicion()] && !ciudades.incluye(aux.dato())) {
				marca[aux.posicion()]=true;
				encontro= DfsdevolverCaminoExceptuando(aux, ciudad2,ciudades, marca, camino);
			}
		}
	}
	return false;
}

//------------------------------------------------------------------------------------------------------------------------------------------





}
