package prog3.arbol.general;

import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;
import prog3.util.ColaGenerica;

public class Empresa {
	//cre
	private ArbolGeneral<Empleado> empleados =new ArbolGeneral<Empleado>();
	public Empresa() {
	}
	
	/*
	 * Empleados por categoria:
	 * Dado un numero de categoria devuelve la cantidad de empleados de dicha categoria.
	 */
	public int empleadosPorCategoria(int categoria) 
	{
		if(categoria==1)
			return 1;
		ColaGenerica<ArbolGeneral<Empleado>> cola = new ColaGenerica <ArbolGeneral<Empleado>>();
		int nivel=1;
		cola.encolar(empleados);
		cola.encolar(null);
		while(!cola.esVacia())
		{
			ArbolGeneral<Empleado> aux = cola.desencolar();
			if (aux != null) {
				ListaGenerica<ArbolGeneral<Empleado>> hijos = aux.getHijos();
				//Encolar Hijos
				hijos.comenzar();
				while (!hijos.fin())
					cola.encolar(hijos.proximo());
			}
			else if (!cola.esVacia()) {
				if (categoria == ++nivel) {
					//Si categoria es igual a ++nivel le devuelvo el tamanio de la cola(que lo hago desencolando todos
					//sus elementos, ya que no puedo usar cola.tamanio() porque no esta definido en la clase ColaGenerica
					
					int cant=0;
					ColaGenerica<ArbolGeneral<Empleado>> colaB = cola;
					while (!colaB.esVacia()) {
						colaB.desencolar();
						cant++;
						}	
					return cant;
				}
				cola.encolar(null);
			}
		}
		return 0;
	}
	/*
	 * Determina la categoria que cuenta con la mayor cantidad de empleados.
		Retorna un numero correspondiente a estos cargos.
		Categoría 1: Presidente
		Categoría 2: Gerentes
		Categoría 3: Sub-gerentes
		Categoría 4: Empleados que no tienen nadie a cargo

	 */
	public int categoriaConMasEmpleados() {
		int [] eCategoria= {0,0,0,0};
		ColaGenerica<ArbolGeneral<Empleado>> cola = new ColaGenerica<ArbolGeneral<Empleado>>();
		int categoria=1;
		cola.encolar(empleados);
		cola.encolar(null);
		while(!cola.esVacia()) 
		{
			ArbolGeneral<Empleado> aux = cola.desencolar();
			if(aux != null) {
				ListaGenerica<ArbolGeneral<Empleado>> hijos = aux.getHijos();
				hijos.comenzar();
				while(!hijos.fin())
					cola.encolar(hijos.proximo());
			}
			else if (!cola.esVacia()) {
				int cant=0;
				cola.encolar(null);
				//Reemplazo el metodo cola.tamanio();
				// creeo una nuevo cola y me quedo con la cantidad de elementos que contiene
				ColaGenerica<ArbolGeneral<Empleado>> colaB = cola;
				while (!colaB.esVacia()) {
					colaB.desencolar();
					cant++;
					}		
			eCategoria[categoria] += cant;
			
			}
		}
		//Busco el maximo en el vector de categorias y lo devuelvo.
		int max=1;
		for (int i=1; i<4;i++) {
			if(eCategoria[i] > max)
				max=eCategoria[i];
		}
		return max;
	}
	 
	
	//Determina la cantidad total de empleados.
	
	public int cantidadTotalDeEmpleados() {
		return cantidadTotalDeEmpleados(empleados);
	}
	private int cantidadTotalDeEmpleados (ArbolGeneral<Empleado> arbol) 
	{
		if (!arbol.esHoja()) {
			int cont=1;
			ListaGenerica<ArbolGeneral<Empleado>> hijos = arbol.getHijos();
			hijos.comenzar();
			while(!hijos.fin()) {
				//en cont cuento la cantidad de veces que se hace la llamada recuriva
				cont+= this.cantidadTotalDeEmpleados(hijos.proximo()); 
			}
			return cont;
		}
		else return 1; // si es una hoja retorno 1, ya que seria solo el presidente;
	}
	/*
	 * Sea la situacion en donde el presidente deja su funcion, reemplazarlo po la persona mas antigua
	 * de sus subordinado, quien a su vez es reemplazada de la misma forma.
	 */
	private void reemplazoPresidente () {
		reemplazoPorSubordinado(empleados);
	}
	public void reemplazoPorSubordinado (ArbolGeneral<Empleado> arbol) {
		ListaGenerica<ArbolGeneral<Empleado>> hijos = arbol.getHijos();
		hijos.comenzar();
		int posMax=0;
		int pos=0;
		ArbolGeneral<Empleado> maximo = hijos.proximo();
		while (!hijos.fin()) {
			ArbolGeneral<Empleado> aux= hijos.proximo();
			pos++;
			if (aux.getDatoRaiz().getAntiguedad() > maximo.getDatoRaiz().getAntiguedad()) {
				posMax=pos;
				maximo=aux;
			}
		}
		//reemplazar.
		arbol.getDatoRaiz().setNombre(maximo.getDatoRaiz().getNombre());
		arbol.getDatoRaiz().setAmbiguedad(maximo.getDatoRaiz().getAntiguedad());
		if (maximo.esHoja())
			hijos.eliminarEn(posMax);
			else
				reemplazoPorSubordinado(maximo);
		
	
	}
	public static void main(String[] args) {
	}

}
