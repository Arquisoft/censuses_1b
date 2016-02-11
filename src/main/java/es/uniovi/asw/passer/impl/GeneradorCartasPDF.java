package es.uniovi.asw.passer.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.passer.GeneradorCartas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneradorCartasPDF implements GeneradorCartas {

	@Override
	public void generarCarta(Votante v) throws FileNotFoundException, DocumentException {
		FileOutputStream archivo = new FileOutputStream(v.getNif()+".pdf");
		Document documento = new Document();
	    PdfWriter.getInstance(documento, archivo);
	    documento.open();
	    documento.add(new Paragraph("Usuario:"+v.getMail()));
	    documento.add(new Paragraph(("Contraseña:"+v.getContraseña())));
	    documento.close();
	}


}
