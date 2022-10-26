package grafo.utiles;

import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;
import prog3.util.ColaGenerica;

public class recorridos<T> {

	 public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo){
		 boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()];
		 ListaGenerica<Vertice<T>> lista = new ListaGenericaEnlazada<Vertice<T>>();
		 for (int i=1; i<=grafo.listaDeVertices().tamanio();i++){
			 if (!marca[i])
				 this.dfs(lista,i, grafo, marca);
		 }
		 return lista;
		 }
		private void dfs(ListaGenerica<Vertice<T>> lista, int i, Grafo<T> grafo, boolean[] marca){
		 marca[i] = true;
		 Vertice<T> v = grafo.listaDeVertices().elemento(i);
		 lista.agregarFinal(v);
		 System.out.println(v);
		 ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(v);
		 ady.comenzar();
		 while(!ady.fin()){
			 int j = ady.proximo().verticeDestino().posicion();
			 if(!marca[j]){
				 this.dfs(lista,j, grafo, marca);
			 	}
		 	}
		 }
		//--------------------------------------------------------------------------------------------------------------
		public ListaGenerica<Vertice<T>> bfs(Grafo<T> grafo){
				boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
				ListaGenerica<Vertice<T>> lista = new ListaGenericaEnlazada<Vertice<T>>();
				for (int i=1; i<=grafo.listaDeVertices().tamanio();i++){
					if (!marca[i])
					 this.bfs(lista,i, grafo, marca);
				 }
			return lista;
		}
		private void bfs(ListaGenerica<Vertice<T>> lista, int i, Grafo<T> grafo, boolean[] marca ) {
			ColaGenerica<Vertice<T>> cola = new ColaGenerica<Vertice<T>>();
			cola.encolar(grafo.vertice(i));
			marca[i]= true;
			while (!cola.esVacia()) {
				Vertice<T> aux = cola.desencolar();
				lista.agregarFinal(aux);
				ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(aux);
				adyacentes.comenzar();
				while (!adyacentes.fin()) {
					Vertice<T> v = adyacentes.proximo().verticeDestino();
					if (!marca[v.posicion()]) {
						marca[v.posicion()]= true;
						cola.encolar(v);
					}
				}
			}
		}
		
		//--------------------------------------------------------------------------------------------------------------
		
		 public boolean subgrafoCuadrado(Grafo<T> grafo){
			 boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
			 boolean cuadrado = false;
			 for (int i=1; i<=grafo.listaDeVertices().tamanio();i++){
				 if (!marca[i]) {
					 marca[i] = true;
					 // marco como true el vertice de donde lo voy a lanzar, y si hay algun cuadrado que termine en este 
					 // vertice voy a devolver true, y asi hago con todos los vertices.
					 this.dfsCuadrado(1,cuadrado,i, grafo, marca);
					 marca[i]= false;
				 }
			 }
			 return cuadrado;
			 }
		 
		private void dfsCuadrado(int cant, boolean cuadrado, int i, Grafo<T> grafo, boolean[] marca){
				 Vertice<T> v = grafo.listaDeVertices().elemento(i);
				 ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(v);
				 ady.comenzar();
				 while(!ady.fin()){
					 int j = ady.proximo().verticeDestino().posicion();
					 if(!marca[j]){
						 marca[j]= true;
						 this.dfsCuadrado(cant+1, cuadrado, j, grafo, marca);
						 marca[j]=false;
				 		}
					 else {
						 if (cant == 4 && marca[j]) 
							 cuadrado = true;
					 }
			 	}
	}
		
		//--------------------------------------------------------------------------------------------------------------
		

}



