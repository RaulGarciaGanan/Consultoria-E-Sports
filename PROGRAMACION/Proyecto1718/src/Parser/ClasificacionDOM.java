/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import MisClases.Equipo;
import MisClasesBD.GenericoBD;
import Parser.Clases.*;
import static Parser.LigaDOM.misLigas;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
public class ClasificacionDOM {
    private static Connection con;
    
    public static int id;
    
    public static List<equipo> misClasificacion;
    Document dom;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws ParserConfigurationException, TransformerException, Exception {
        // TODO code application logic here
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        
        //Creando la instancia
        ClasificacionDOM clasificacion = new ClasificacionDOM();

        //
        ClasificacionDOM.cargarDatos(id);
        //ejecuta el parse
        clasificacion.ejecutar();
    }

    //Constructor
    public ClasificacionDOM() throws Exception {

        misClasificacion = new ArrayList();

        cargarDatos(id);
    }

    public void ejecutar()  throws ParserConfigurationException, TransformerException {
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
        Element raiz = dom.createElement("clasificacion");
        dom.appendChild(raiz);
        //recorrer el arraylist de equipos (objetos Equipo) con los puntos que tienen en ese momento
        for(equipo e : misClasificacion){
            //System.out.println(misClasificacion.get(x));
            //dentro del bucle, por cada equipo, crear un elemento equipo (DOM) a partir de los objetos Equipo
            Element equipoEle = crearElementoClasificacion(e);

            //Una vez tenemos el elemento equipo lo añadimos al documento DOM
            raiz.appendChild(equipoEle);
        }
        //Creamos el documento
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(XMLdoc);
        StreamResult result = new StreamResult(new File("../clasificacion.xml"));
        transformer.transform(source, result);
    }
    
    private void parsearFicheroXML() {
        //Creamos el DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = db.parse("../clasificacion.xml");

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
        Element raizClasificacion = dom.getDocumentElement();

        //Recorremos los contactos del arrayList y los convertimos en elementos
        //DOM y los agregamos a la raiz <Liga>
        Iterator it = misClasificacion.iterator();
        while (it.hasNext()) {
            equipo e = (equipo) it.next();
            //Obtenemos la información y la pegamos para cada uno de los contactos
            Element equipoEle = crearElementoClasificacion(e);
            raizClasificacion.appendChild(equipoEle);
        }

    }

    private void escribirFicheroXML() {

        try {
            //Configuramos el formato de salida del fichero
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);

            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File("../clasificacion.xml")), format);

            serializer.serialize(dom);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    private Element crearElementoClasificacion(equipo e) {

        Element equiEle = dom.createElement("equipo");
        
        //Crear elemento nombre y su texto, luego lo asociamos al elemento equipo
        Element nombEle = dom.createElement("nombre");
        Text nombText = dom.createTextNode(e.getNombre());
        nombEle.appendChild(nombText);
        equiEle.appendChild(nombEle);
        
        //Crear elemento refenrencia y su texto, luego lo asociamos al elemento equipo
        Element refeEle = dom.createElement("referencia");
        Text refeText = dom.createTextNode(e.getReferencia());
        refeEle.appendChild(refeText);
        equiEle.appendChild(refeEle);
        
        //Falta el subelemento Puntos
        Element puntuEle = dom.createElement("puntuacion");
        Text puntuText = dom.createTextNode(e.getResultado());
        puntuEle.appendChild(puntuText);
        equiEle.appendChild(puntuEle);

        return equiEle;
    }

    public static LinkedList<clasificacion> cargarDatos(int id) throws SQLException {
        //misClasificacion.add(new equipo( "Alaves", "Amancio", 15));
        GenericoBD gbd = new GenericoBD();
        con = gbd.abrirConexion(con);
        LinkedList<clasificacion> listaClasificacion=new LinkedList<clasificacion>();
        try{
            Statement st = con.createStatement();
            ResultSet sentencia = st.executeQuery("SELECT nombre,referencia FROM Equipo e JOIN Partido p on p.resultado");
            while (sentencia.next())
            { 
               clasificacion clasi = new clasificacion();
               clasi.setNombre(sentencia.getString("nombre"));
               clasi.setReferencia(sentencia.getString("referencia"));
               clasi.setResultado(sentencia.getString("resultado"));
               
               misClasificacion.add(new equipo(sentencia.getString("nombre"),sentencia.getString("referencia"),sentencia.getString("resultado")));
               
               listaClasificacion.add(clasi);
            }
            sentencia.close();
            sentencia.close();

            st.close();
            con.close();   
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        } 
        return null;
    }
}
