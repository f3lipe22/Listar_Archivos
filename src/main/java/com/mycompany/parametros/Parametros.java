package com.mycompany.parametros;
    
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Parametros {
    public static void main(String[] args) {
         //Verificar la cantidad correcta de argumentos
        //if (args.length != 3) {
            //System.out.println("Uso incorrecto. Debe proporcionar año, mes, seemana, dia y tipo de carpeta, .");
            //System.out.println("Ejemplo: java -jar parametros-1.0-SNAPSHOT.jar 2023 12 1-10 8 xml");
            //System.exit(1);
        //}
        //else{

         //Obtener parámetros de entrada
        //int year = Integer.parseInt(args[0]);
        //int month = Integer.parseInt(args[1]);
        //int week = Integer.parseInt(args[2]);
        //int day = Integer.parseInt(args[3]);
        //String folderType = args[2];

        // Ruta del directorio principal
        String rutaDirectorio = "C:/Users/p1pe0/Documents/trabajo/Noticias/"; // Reemplaza con la ruta del directorio que deseas explorar

        if ( !args[0].isEmpty() ) { 
            rutaDirectorio = rutaDirectorio.concat(args[0]).concat("/");
        }
        if (args.length == 2 && !args[1].isEmpty() ) { 
            rutaDirectorio = rutaDirectorio.concat(args[1]).concat("/"); 
        }
        if ( args.length == 3 && !args[2].isEmpty()){
            rutaDirectorio = rutaDirectorio.concat(args[2].concat("/"));
        }
        
        System.out.println("la nueva ruta:"  + rutaDirectorio);
        // Llamada inicial al método recursivo
        listarDirectorioRecursivo(new File(rutaDirectorio).toString());
        
        //}
    }

    public static void listarDirectorioRecursivo(String directorio) {
        File directorioActual = new File(directorio);

        // Verificar si el objeto representa un directorio válido
        if (directorioActual.isDirectory()) {
            System.out.println("Directorio: " + directorioActual.getAbsolutePath());

            // Listar archivos en el directorio actual
            File[] archivos = directorioActual.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isDirectory()) {
                        // Llamada recursiva para listar subdirectorios
                        listarDirectorioRecursivo(archivo.getAbsolutePath());
                    } else {
                        // Mostrar archivos con extensiones específicas
                        String nombreArchivo = archivo.getName();
                        if (nombreArchivo.endsWith(".xml") || nombreArchivo.endsWith(".java")) {
                            System.out.println("Archivo: " + archivo.getAbsolutePath());
                            mostrarArchivo(archivo.getAbsolutePath());                        }
                    }
                }
            }
        } else {
            System.out.println("La ruta no representa un directorio válido.");
        }
    }

    private static boolean cumpleCriterios(File carpeta, int year , int month , String folderType /*, int week ,*/ /*int day*/) {
        // Implementa la lógica para verificar si la carpeta cumple con los criterios
        // Por ejemplo, puedes extraer información del nombre de la carpeta y comparar con los parámetros
        // En este ejemplo, simplemente se retorna true para todas las carpetas.
        return true;
    }
    
    public static void mostrarArchivo(String rutaArchivo) {

      // Instantiate the Factory
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      try {

          // optional, but recommended
          // process XML securely, avoid attacks like XML External Entities (XXE)
          //dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          // parse XML file
          DocumentBuilder db = dbf.newDocumentBuilder();

          Document doc = db.parse(new File(rutaArchivo));

          // optional, but recommended
          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
          doc.getDocumentElement().normalize();

          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
          System.out.println("------");

          // get <staff>
          NodeList list = doc.getElementsByTagName("property");

          // Mostrar cada parámetro y su valor para elements
            System.out.println("Parámetros del archivo XML:");
          for (int temp = 0; temp < list.getLength(); temp++) {
              Node node = list.item(temp);
              
              if (node.getNodeType() == Node.ELEMENT_NODE) {
                  Element element = (Element) node;
                 
                            String type = element.getAttribute("type");
                            String value = element.getTextContent();
                            
                           System.out.println("Property type: " + type);
                           System.out.println("Property value: " + value);
                          }
                   
            }
          // Recorrer los nodos de <element>
            NodeList elementList = doc.getElementsByTagName("element");
            for (int i = 0; i < elementList.getLength(); i++) {
                Node elementNode = elementList.item(i);
                if (elementNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementElement = (Element) elementNode;

                    String elementType = elementElement.getAttribute("type");
                    String elementValue = elementElement.getTextContent();

                    System.out.println("Element type: " + elementType);
                    System.out.println("Element value: " + elementValue);
                }
            }
            
                          
                            
                  //if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //Element elemento = (Element) node;
                    //String nombre = elemento.getAttribute("type");
                    //String valor = elemento.getTextContent();
                    //System.out.println(nombre + ": " + valor);
                //}
                  //System.out.println(height+ ": " + valor);
                  // get text
                 // String firstname = element.getElementsByTagName("filename").item(0).getTextContent();
                  //String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                  //String nickname = element.getElementsByTagName("nickname").item(0).getTextContent();

                  //NodeList salaryNodeList = element.getElementsByTagName("salary");
                  //String salary = salaryNodeList.item(0).getTextContent();

                  // get salary's attribute
                  //String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                  //System.out.println("Current Element :" + node.getNodeName());
                  //System.out.println("Staff Id : " + id);
                  //System.out.println("First Name : " + firstname);
                  //System.out.println("Last Name : " + lastname);
                  //System.out.println("Nick Name : " + nickname);
                  //System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);

              //}
          //}

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }
  }
}
