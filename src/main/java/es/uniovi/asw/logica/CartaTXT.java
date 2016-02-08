package es.uniovi.asw.logica;

public class CartaTXT implements GeneradorCartas {

	@Override
	public void generarCarta(Votante v) {
		System.out.println( "User: "+v.getMail()+" Pass:"+v.getPass());
	}


}
