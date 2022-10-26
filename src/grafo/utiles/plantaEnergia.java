package grafo.utiles;


import ciudadMafiosa.sitio;
import grafos.Arista;
import grafos.Grafo;
import grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;
import prog3.util.ColaGenerica;

public class plantaEnergia {

	public ListaGenerica<Integer>  sumistroAlNodoFinal(Grafo<Integer> grafo, Integer origen) {
		boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()];
		boolean[] entro = new boolean[grafo.listaDeVertices().tamanio()];
		ListaGenerica<Vertice<Integer>> vertices = new ListaGenericaEnlazada<Vertice<Integer>>();
		ListaGenerica<Integer> lista = new ListaGenericaEnlazada<Integer>();
		int cant=0;
		int cantidad=0;
		int[] total = new int [grafo.listaDeVertices().tamanio()];

		Vertice<Integer> vIni = null;
		while (!vertices.fin()){
			Vertice<Integer> v = vertices.proximo();
			if(v.dato().equals(origen))
				vIni=v;
		}
		
		dfs(vIni, marca, cant, grafo, lista);
		 return lista;
		
	}

	private void dfs(Vertice<Integer> entrada, boolean[] marca,boolean[] entro, int cant, Grafo<Integer> grafo,  ListaGenerica<Integer> lista) {
		marca[entrada.posicion()]=true;
		ListaGenerica<Arista<Integer>> adyacentes = grafo.listaDeAdyacentes(entrada);
		if (adyacentes.tamanio() == 0 )
		{
			int pos=lista.tamanio();
			if (entro[entrada.posicion()]) {
				
				int total=lista.elemento(pos)+(cant + entrada.dato());
				lista.eliminarEn(pos);
				lista.agregarEn(total, pos);
				
				
			}else {
				
			lista.agregarFinal(cant + entrada.dato());
			marca[entrada.posicion()]=false;
			entro[entrada.posicion()]=true;
			

		}
		adyacentes.comenzar();
			while(!adyacentes.fin()) {
				Arista<Integer> arista = adyacentes.proximo();
				if (!marca[arista.verticeDestino().posicion()]) {
					dfs (arista.verticeDestino(), marca, entro,cant + (entrada.dato()/adyacentes.tamanio()), grafo,lista);
					
				}
			}
		}
	
		
	}

}
