package exam;

import exam.controllers.EquipementCulturelManager;
import exam.database.EntityManager;
import exam.model.EquipementCulturel;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistingMain {

    public static void main(String[] args) {
        parseWithSAX();

        // Playing with database
        EquipementCulturelManager ecm = new EquipementCulturelManager();

        // Get equipment by ID
        EquipementCulturel ec1 = ecm.findEquipmentById("1809");
        System.out.println("Found equipment with id 1809 : " + ec1.toString());

        // Get all equipments - displaying only the size
        List<EquipementCulturel> equipementCulturelList = new ArrayList<>();
        equipementCulturelList = ecm.getAllEquipments();
        System.out.println("Found " + equipementCulturelList.size() + " equipments in total");

        // Get equipment by name
        List<EquipementCulturel> ec2 = ecm.findEquipmentByCity("NANTES");
        System.out.println("Found " + ec2.size() + " equipments in Nantes");
    }

        public static void parseWithSAX(){




        XMLReader xr = null;
        try {
            xr = XMLReaderFactory.createXMLReader();
            App handler = new App();
            xr.setContentHandler(new ContentHandler() {

                List<EquipementCulturel> listFetchedEquipments = new ArrayList<>();
                EquipementCulturel fetchedEquipment = new EquipementCulturel();
                boolean isNameActive = false;
                boolean isIdActive = false;
                boolean isCityActive = false;
                StringBuilder valueCharacters;

                @Override
                public void setDocumentLocator(Locator locator) {

                }

                @Override
                public void startDocument() throws SAXException {
                    System.out.println("Starting document");
                }

                @Override
                public void endDocument() throws SAXException {
                    System.out.println("Ending document");
                    System.out.println("Now persisting fetched data");
                    System.out.println(listFetchedEquipments.size() + " equipments to persist");
                    for(EquipementCulturel eq : listFetchedEquipments){
                        EntityManager.getInstance().getEntityTransaction().begin();
                        EntityManager.getInstance().getEntityManager().persist(eq);
                        EntityManager.getInstance().getEntityTransaction().commit();
                    }
                }

                @Override
                public void startPrefixMapping(String prefix, String uri) throws SAXException {

                }

                @Override
                public void endPrefixMapping(String prefix) throws SAXException {

                }

                @Override
                public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

                    valueCharacters = new StringBuilder();

                    switch(qName){
                        case "name":
                            isNameActive = true;
                            break;
                        case "_IDOBJ":
                            isIdActive = true;
                            break;
                        case "COMMUNE":
                            isCityActive = true;
                            break;
                        case "element":
                            fetchedEquipment = new EquipementCulturel();
                            break;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {

                    switch(qName){
                        case "name":
                            isNameActive = false;
                            break;
                        case "_IDOBJ":
                            isIdActive = false;
                            break;
                        case "COMMUNE":
                            isCityActive = false;
                            break;
                        case "element":
                            listFetchedEquipments.add(fetchedEquipment);
                            break;
                    }

                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {

                    valueCharacters.append(ch, start, length);

                    if(isCityActive)
                        fetchedEquipment.setCity(valueCharacters.toString());
                    if(isNameActive)
                        fetchedEquipment.setName(valueCharacters.toString());
                    if(isIdActive)
                        fetchedEquipment.setId(valueCharacters.toString());

                }

                @Override
                public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

                }

                @Override
                public void processingInstruction(String target, String data) throws SAXException {

                }

                @Override
                public void skippedEntity(String name) throws SAXException {

                }
            });
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
}
