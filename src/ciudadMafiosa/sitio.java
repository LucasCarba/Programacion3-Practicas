package ciudadMafiosa;

public class sitio {
	private String nombre;
	private boolean tieneMafia;
	
	public sitio() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getTieneMafia() {
		return tieneMafia;
	}

	public void setTieneMafia(boolean tieneMafia) {
		this.tieneMafia = tieneMafia;
	}


	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof sitio)
			return this.getNombre().equals(((sitio)arg0).getNombre());
		return false;
	}
	
}
