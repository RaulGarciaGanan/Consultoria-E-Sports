/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Parser.Clases.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author 1gdaw10
 */
public class ClasificacionSAX extends DefaultHandler {

    private ArrayList<clasificacion> clasificacion;
    private String contenido;
    private clasificacion equipo;

    /**
     * @param args the command line arguments
     */
    public ClasificacionSAX() {
        clasificacion = new ArrayList();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Clasificaciones");
        System.out.println("---");

        ClasificacionSAX c = new ClasificacionSAX();
        c.ejecutar();
    }

    public void ejecutar() {
        parsearDocumento();
        imprimirDocumento();
    }

    private void parsearDocumento() {

        //creamos una factoría de parsers SAX
        SAXParserFactory spf = SAXParserFactory.newInstance();

        try {

            //instanciamos un nuevo parser SAX a partir de la factroría
            SAXParser sp = spf.newSAXParser();

            //parseamos el fichero xml y enviamos la clase para los call backs
            sp.parse("../clasificacion.xml", this);

        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Iteramos a través de la lista y mostramos la información por pantalla
     */
    private void imprimirDocumento() {

        JOptionPane.showMessageDialog(null,"Equipos '" + clasificacion.size() + "'.");

        Iterator it = clasificacion.iterator();
        while (it.hasNext()) {
            equipo = (clasificacion) it.next();
            JOptionPane.showMessageDialog(null,clasificacion.toString());
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //inicializamos
        contenido = "";

        if (qName.equalsIgnoreCase("equipo")) {
            equipo = new clasificacion();
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        contenido = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("equipo")) {
            clasificacion.add(equipo);
        } else if (qName.equalsIgnoreCase("nombre")) {
            equipo.setNombre(contenido);
        } else if (qName.equalsIgnoreCase("referencia")) {
            equipo.setReferencia(contenido);
        }
    }
}
