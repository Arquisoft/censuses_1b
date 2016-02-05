package es.uniovi.asw.logica;

public class CartaTXT implements GeneradorCartas {

	@Override
	public String generarCarta(Votante v) {
		return "User: "+v.getMail()+" Pass:"+v.getPass();
	}


}
