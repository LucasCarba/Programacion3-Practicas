package grafo.utiles;

import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;

public class delta {
	//maxIslasDistintas(Grafo<String> grafo) : int // Retorna el número máximo de islas distintas
	//que se pueden recorrer en el grafo comprando un único boleto.

// Tengo que contar la cantidad de islas que hay. 
	 public int maxIslasDistintas(Grafo<String> grafo){
		 boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()];
		 int cant=1;
		 // recorro solo una vez, xq salgo desde el delta, que vendria a ser mi nodo "1".
		 this.maxIslasDistintas(cant,0, grafo, marca);
		 return cant;
		 }
	 
		private void maxIslasDistintas( int cant,int i, Grafo<String> grafo, boolean[] marca){	
		 marca[i] = true;
		 Vertice<String> v = grafo.listaDeVertices().elemento(i);
		 ListaGenerica<Arista<String>> ady = grafo.listaDeAdyacentes(v);
		 ady.comenzar();
		 while(!ady.fin()){
			 int j = ady.proximo().verticeDestino().posicion();
			 if(!marca[j]){
				 this.maxIslasDistintas(cant++,j, grafo, marca);
			 	}
		 	}
		 }
		
		
		
		public ListaGenerica<String> caminoMasCorto(Grafo<String> grafo,String islaO,String islaD){
			ListaGenerica<Vertice<String>> lista= grafo.listaDeVertices();
			int tabla[][] = new int[lista.tamanio()] [3]; // DV,PV,Conocido.
			// inicializacion
			for(int i=0,len=lista.tamanio(); i<len;i++) {
				tabla[i][0]=Integer.MAX_VALUE;
				tabla[i][1]=-1;
				tabla[i][2]=0;
			}
			//Busqueda Ciudad
			int pos=-1;
			for(int i=0,len=lista.tamanio(); i<len;i++) {
				if(lista.elemento(i).dato()==islaO) {
					pos=i;
					break;
				}
			}
			if (pos == -1)
				return null; //No encontro la ciudad;
			tabla[pos][0]=0; //Empieza de la islao;
			lista.comenzar();
			while (!lista.fin()) {
				int posU=0;
				int minD= tabla[posU][0];
				Vertice<String> u;
				for(int i=0, len=tabla.length; i<len; i++) {
					if (tabla[i][2]==0) { //Si es desconocido
						if(tabla[i][0]<minD) {
						minD=tabla[i][0];
						posU=i;
						}
					}
				}
				
				tabla[posU][2]=1; // Marco Como conocido;
				u=lista.elemento(posU);
				ListaGenerica<Arista<String>> listaAdyacentes = grafo.listaDeAdyacentes(u);
				int posW=0;//Posicion de W en la matriz
				listaAdyacentes.comenzar();
				while (!listaAdyacentes.fin()) { // Por cada adyacente ubica su pos en la matriz.
					Arista<String> arista =listaAdyacentes.proximo();
					for(int i=0,len=lista.tamanio(); i<len;i++) { //Recorro Matriz
						if (lista.elemento(i)== arista.verticeDestino()) {
							posW=i;
							break;
						}
					}
					if (tabla[posW][2]==0) {
						if(tabla[posW][0] > tabla[posU][0] + arista.peso()) {
							tabla[posW][0]= tabla[posU][0] + arista.peso();
							tabla[posW][1]=posU;
						}
					}
				}
				
			}
			ListaGenerica<String> camino= new ListaGenericaEnlazada<String>();
			camino.agregarInicio(islaD);
			int pos2=-1; // posicion de ciudad en la lista de vertices.
			for (int i=0; i<lista.tamanio();i++) {
				if (lista.elemento(i).dato()==islaD) {
					pos=i;
					break;
				}
			}
			while (tabla[pos2][1] != pos) {
				//Mientra el pw sea diferente de la posicion en islao y mientras no llegue a un extremo.
				camino.agregarInicio(lista.elemento(tabla[pos2][1]).dato());
				pos2=tabla[pos2][1];
			}
			if (tabla[pos2][1] != -1) {
				camino.agregarInicio(islaO);
				return camino;
			
			} else {
				return new ListaGenericaEnlazada<String>();
				
			}
		}
// 

}
