package exam;

import org.apache.log4j.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.text.Normalizer;

/**
 * Hello world!
 */
public class App extends DefaultHandler{
    @SuppressWarnings("unused")
    private static final Class[] shadeHack = {org.apache.log4j.RollingFileAppender.class,
            org.apache.log4j.ConsoleAppender.class,
            PatternLayout.class};

    //Set the logger with the real class name.
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    // Variables
    private int numberMediatheque = 0;
    private boolean isLibCategorie = false;
    private boolean isCodePostalRight = false;
    StringBuilder valueCharacters = new StringBuilder();

    public static void main(String[] args) {
        logger.info("App started.");
        logger.debug("About to talk :");
        System.out.println("Hello world !");

        // Starting the parse of the XML document
        parseWithSAX();
    }

    public static void parseWithSAX(){

        XMLReader xr = null;
        try {
            xr = XMLReaderFactory.createXMLReader();
            App handler = new App();
            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);

            String curDir = System.getProperty("user.dir");
            System.out.println(curDir);

            FileReader r = new FileReader("./data/opendata.xml");
            xr.parse(new InputSource(r));



        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**********************
      SAX HANDLER METHODS
    **********************/

    public void startDocument(){
        System.out.println("Parsing started");
    }

    public void endDocument(){
        System.out.println("Parsing finished");
        System.out.println("Number of Médiathèques : " + numberMediatheque);
    }

    public void startElement (String uri, String name,
                              String qName, Attributes atts) {

        valueCharacters = new StringBuilder();

        if(qName.equalsIgnoreCase("CODE_POSTAL")){
        }

        if(qName.equalsIgnoreCase("LIBTYPE")){
            isLibCategorie = true;
            System.out.println("Début LIBTYPE");
        }

    }

    public void endElement (String uri, String name, String qName) {

        // Resetting the boolean
        if(qName.equalsIgnoreCase("LIBTYPE")){
            isLibCategorie = false;
            isCodePostalRight = false;
            System.out.println("Fin LIBTYPE");
        }

    }

    public void characters (char ch[], int start, int length) {
        valueCharacters.append(ch, start, length);
        if(valueCharacters.toString().equals("44000")){
            isCodePostalRight = true;
            System.out.println("CODE POSTAL DE NANTES");
        }

        if(isCodePostalRight){
            valueCharacters = new StringBuilder();
            if(isLibCategorie){
                valueCharacters.append(ch, start, length);
                System.out.println("Type : " + unaccent(valueCharacters.toString()));
                if(unaccent(valueCharacters.toString()).equals("MAdiathAque"))
                    numberMediatheque++;
            }
        }


        for (int i = start; i < start + length; i++) {
            /*switch (ch[i]) {
                case '\\':
                    System.out.print("\\\\");
                    break;
                case '"':
                    System.out.print("\\\"");
                    break;
                case '\n':
                    System.out.print("\\n");
                    break;
                case '\r':
                    System.out.print("\\r");
                    break;
                case '\t':
                    System.out.print("\\t");
                    break;
                default:
                    System.out.print(ch[i]);
                    break;
            }*/
        }
    }

    public String unaccent(String s) {
        String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
        return normalized.replaceAll("[^\\p{ASCII}]", "");
    }

}
