package es.uniovi.asw.passer.impl;

import java.math.BigInteger;
import java.security.SecureRandom;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.GeneradorPass;


public class HashedGenerator implements GeneradorPass {

	@Override
	public String generar(Votante v) {
		SecureRandom passCreator = new SecureRandom();
		return new BigInteger(20, passCreator).toString()+v.hashCode();
		
		
	}

}
