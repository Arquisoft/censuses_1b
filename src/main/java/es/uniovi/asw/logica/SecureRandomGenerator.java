package es.uniovi.asw.logica;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SecureRandomGenerator implements GeneradorPass {

	@Override
	public String generar(Votante v) {
		String pass="";
		SecureRandom passCreator = new SecureRandom();
		return new BigInteger(10, passCreator).toString();
		
	}

}
