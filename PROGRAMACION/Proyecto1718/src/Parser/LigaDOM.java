/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import MisClases.Partido;
import MisClasesBD.GenericoBD;
import Parser.Clases.*;
import java.util.GregorianCalendar;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author 1gdaw10
 */
public class LigaDOM {
    private static Connection con;
    
    public static Integer id;
    
    public static List misLigas;
    Document dom;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, Exception {
        // TODO code application logic here
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        
        //Creando la instancia
        LigaDOM liga = new LigaDOM();

        cargarDatos(id);
        
        //ejecuta el parse
        liga.ejecutar();
    }

    //Constructor
    public LigaDOM() throws Exception {

        misLigas = new ArrayList();
        
        cargarDatos(id);
    }

    public void ejecutar() throws ParserConfigurationException, TransformerException {
        System.out.println("Comenzando... ");
        //Creamos el archivo XML
        crearFicheroXML();
        //Volcamos el fichero xml en memoria como arbol de DOM
        parsearFicheroXML();
        //Creamos los elementos y los agregamos al arbol de DOM
        crearArbolDOM();
        //Escribimos el arbol DOM en el fichero XML
        escribirFicheroXML();
        System.out.println("Fichero modificado correctamente...");
    }

    
    private void crearFicheroXML() throws TransformerConfigurationException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        //Creamos la raiz
        Document XMLdoc = docBuilder.newDocument();
        Element rootEle = XMLdoc.createElement("liga");
        XMLdoc.appendChild(rootEle);
        rootEle.setTextContent("");
        
        //Creamos el documento
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(XMLdoc);
        StreamResult result = new StreamResult(new File("../liga.xml"));
        transformer.transform(source, result);
    }
    private void parsearFicheroXML() {
        //Creamos el DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = db.parse("../liga.xml");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void crearArbolDOM() {

        //Cogemos la referencia al elemento raiz <agenda>
        Element raizLiga = dom.getDocumentElement();

        //Recorremos los contactos del arrayList y los convertimos en elementos
        //DOM y los agregamos a la raiz <agenda>
        Iterator it = misLigas.iterator();
        while (it.hasNext()) {
            jornada j = (jornada) it.next();
            //Obtenemos la información y la pegamos para cada uno de los contactos
            Element jornadaEle = crearElementoJornada(j);
            raizLiga.appendChild(jornadaEle);
        }

    }

    private void escribirFicheroXML() {

        try {
            //Configuramos el formato de salida del fichero
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);

            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File("../liga.xml")), format);

            serializer.serialize(dom);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    private Element crearElementoJornada(jornada j) {

        Element jornadaEle = dom.createElement("jornada");

        //Crear elemento partido, luego lo asociamos al elemento jornada
        Element partEle = dom.createElement("partido");
        jornadaEle.appendChild(partEle);

        //Crear elemento equipo_local y su texto, luego lo asociamos al elemento partido
        Element locEle = dom.createElement("equipo_local");
        Text locText = dom.createTextNode(j.getEquipo_local());
        locEle.appendChild(locText);
        partEle.appendChild(locEle);

        //Crear elemento equipo_visitante y su texto, luego lo asociamos al elemento partido
        Element visiEle = dom.createElement("equipo_visitante");
        Text visiText = dom.createTextNode(j.getEquipo_visitante());
        visiEle.appendChild(visiText);
        partEle.appendChild(visiEle);

        //Crear elemento resultado y su texto, luego lo asociamos al elemento partido
        Element puntLocEle = dom.createElement("resultado");
        Text puntLocText = dom.createTextNode(j.getResultado());
        puntLocEle.appendChild(puntLocText);
        partEle.appendChild(puntLocEle);

        //Crear elemento fecha_inicio y su texto, luego lo asociamos al elemento jornada
        Element fecha_iniEle = dom.createElement("fecha_inicio");
        Text fecha_iniText = dom.createTextNode(j.getFecha_ini());
        fecha_iniEle.appendChild(fecha_iniText);
        jornadaEle.appendChild(fecha_iniEle);

        return jornadaEle;

        
    }
    
    public static Partido cargarDatos(Integer id) throws SQLException {
        //misLigas.add(new jornada());
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        try{
            PreparedStatement sentencia = con.prepareStatement("SELECT id_local,id_visitante,resultado,fecha FROM Partido WHERE  id_partido = ?");
            sentencia.setInt(1, id );
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()){
                
               misLigas.add(new jornada(resultado.getString(3),resultado.getString(4),Integer.toString(resultado.getInt(6)),resultado.getString(2)));
               return null;
                
            }
            else{
                con.close();
                return null;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
}
