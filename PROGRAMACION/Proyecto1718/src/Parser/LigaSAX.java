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
public class LigaSAX extends DefaultHandler {

    /**
     * @param args the command line arguments
     */
    private ArrayList<liga> liga;
    private liga jornada;
    private String contenido;
    private liga partido;

    public LigaSAX() {
        liga = new ArrayList();
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
            sp.parse("../liga.xml", this);

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

        JOptionPane.showMessageDialog(null,"Partidos '" + liga.size() + "'.");

        Iterator it = liga.iterator();
        while (it.hasNext()) {
            
            JOptionPane.showMessageDialog(null,liga.toString());
            partido = (liga) it.next();
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //inicializamos
        contenido = "";

        if (qName.equalsIgnoreCase("jornada")) {
            jornada = new liga();
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        contenido = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("jornada")) {
            //liga.add(jornada);
        } else if (qName.equalsIgnoreCase("partido")) {
            
        } else if (qName.equalsIgnoreCase("equipo_local")) {
            jornada.setEquipo_local(contenido);
            liga.add(partido);
        } else if (qName.equalsIgnoreCase("equipo_visitante")) {
            jornada.setEquipo_visitante(contenido);
            liga.add(partido);
        } else if (qName.equalsIgnoreCase("resultado")) {
            jornada.setResultado(contenido);
            liga.add(partido);
        } else if (qName.equalsIgnoreCase("fecha_inicio")) {
            jornada.setFecha_ini(contenido);
        }
    }
}
