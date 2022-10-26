package grafo.utiles;

import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.util.ColaGenerica;

public class gradosSeparacion {

public int maximoGradoDeSeparacion (Grafo<String> grafo) {
	ListaGenerica<Vertice<String>> vertices = grafo.listaDeVertices();
	boolean[] visitados= new boolean[vertices.tamanio()];
	int mgs=0;
	boolean seguir=true;
	vertices.comenzar();
	while (!vertices.fin() && seguir) {
		Vertice<String> v = vertices.proximo();
		int gsv = grafoDeSeparacionVertice(grafo, v, visitados);
		if (gsv>mgs)
			mgs=gsv;
		if(gsv==0) {
			seguir=false;
			mgs=0;
		}
	}
	return mgs;
}

private int grafoDeSeparacionVertice(Grafo<String> grafo, Vertice<String> v, boolean[] visitados) {
	int cantVis=0;
	ColaGenerica<Vertice<String>> q = new ColaGenerica<Vertice<String>>();
	for (int i=0; i<visitados.length;i++)
		visitados[i]=false;
	q.encolar(v);
	q.encolar(null);
	visitados[v.posicion()]=true;
	cantVis++;
	int gradoSep=0;
	while (!q.esVacia()) {
		Vertice<String> elem =q.desencolar();
		if (elem!=null)
		{
			ListaGenerica<Arista<String>> adyacentes = grafo.listaDeAdyacentes(elem);
			adyacentes.comenzar();
			while(!adyacentes.fin()) {
				Arista<String> arista = adyacentes.proximo();
				if (!visitados[arista.verticeDestino().posicion()]) {
					visitados[arista.verticeDestino().posicion()]=true;
					cantVis++;
					q.encolar(arista.verticeDestino());
					
				}
			}
		}
		else {
			gradoSep++;
			if(!q.esVacia())
				q.encolar(null);
			
		}
	}
	if(cantVis<grafo.listaDeVertices().tamanio())
		return 0;
	else
		return gradoSep;
}

}
