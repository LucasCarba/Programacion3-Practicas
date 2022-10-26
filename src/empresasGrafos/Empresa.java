package empresasGrafos;

import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.util.ColaGenerica;

public class Empresa {

	public String[] recorrerRedEmpresa(Grafo<String> grafo){
		boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()];
		int[] total = new int [grafo.listaDeVertices().tamanio()];
		int i1=0,i2=0,i3=0,i4=0,i5=0,m1=0,m2=0,m3=0,m4=0,m5 =0;
		String[] nombreEmpresa= new String[5];
		
		int cantidad;
		for (int i=1; i<=marca.length;i++){
			if (!marca[i]) {
				cantidad=0;
				marca[i]= true;
				recorrido(i, grafo, marca, total, cantidad);
				marca[i]= false;
			}
		 
		}
		for (int i=1; i<=total.length;i++){
			if (total[i] > m1) {
				m1=total[i];
				i1=i;
				m2=m1;
				i2=i1;
				m3=m2;
				i3=i2;
				m4=m3;
				i4=i3;
				m5=m4;
				i5=i4;
			}
			else if (total[i] > m2 ) {
				m2=total[i];
				i2=i;
				m3=m2;
				i3=i2;
				m4=m3;
				i4=i3;
				m5=m4;
				i5=i4;
			}else if (total[i] > m3 ) {
				m3=total[i];
				i3=i;
				m4=m3;
				i4=i3;
				m5=m4;
				i5=i4;
			}else if (total[i] > m4 ) {
				m4=total[i];
				i4=i;
				m5=m4;
				i5=i4;
			}else if (total[i] > m5 ) {
				m5=total[i];
				i5=i;
			}
		}
		nombreEmpresa[0]= grafo.listaDeVertices().elemento(i1).dato();
		nombreEmpresa[1]= grafo.listaDeVertices().elemento(i2).dato();
		nombreEmpresa[2]= grafo.listaDeVertices().elemento(i3).dato();
		nombreEmpresa[3]= grafo.listaDeVertices().elemento(i4).dato();
		nombreEmpresa[4]= grafo.listaDeVertices().elemento(i5).dato();
	return nombreEmpresa;
}
	
private void recorrido(int i, Grafo<String> grafo, boolean[] marca, int[] total, int cantidad ) {
	ListaGenerica<Arista<String>> ady=null;
	ColaGenerica<Vertice<String>> cola = new ColaGenerica<Vertice<String>>();
	cola.encolar(grafo.listaDeVertices().elemento(i));
	while (!cola.esVacia()) {	
		Vertice<String> v = cola.desencolar();
		cantidad++;
		ady=grafo.listaDeAdyacentes(v);
		ady.comenzar();
		while (!ady.fin()) {
			Arista<String> arista = ady.proximo();
			int j= arista.verticeDestino().posicion();
			if (!marca[j]) {
				Vertice<String> w = arista.verticeDestino();
				marca[j]= true;
				cola.encolar(w);
			}
		}
	}
	// Una vez que termino el recorrido lo que hago es agregar a la cantidad al arreglo. 
	total[i]=cantidad;
	}

	
}

