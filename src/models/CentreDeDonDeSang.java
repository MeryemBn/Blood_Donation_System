package models;

import javax.swing.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class CentreDeDonDeSang {
	private String nom;
	private String dateCreation;
	private String numeroTelephone;
	private String heureTravail;

	public CentreDeDonDeSang(String nom, String dateCreation, String numeroTelephone, String heureTravail) {
		this.nom = nom;
		this.dateCreation = dateCreation;
		this.numeroTelephone = numeroTelephone;
		this.heureTravail = heureTravail;
	}

	public String getNom() {
		return nom;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public String getHeureTravail() {
		return heureTravail;
	}

	// Implement XML parsing logic here
	public static CentreDeDonDeSang parseXML(File xmlFile) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		Element root = document.getDocumentElement();
		String nom = root.getAttribute("nom");
		String dateCreation = root.getAttribute("dateCreation");
		NodeList nodeList = root.getElementsByTagName("contact");
		Element contact = (Element) nodeList.item(0);
		String numeroTelephone = contact.getAttribute("numero");
		String heureTravail = root.getElementsByTagName("heureTravail").item(0).getTextContent();
		return new CentreDeDonDeSang(nom, dateCreation, numeroTelephone, heureTravail);
	}

}
