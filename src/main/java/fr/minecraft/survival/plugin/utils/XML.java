package fr.minecraft.survival.plugin.utils;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XML {
    public static final String xmlFilePath = ".\\config.xml";

    public  static final      String filePath = ".\\config.xml";

    public static void create_xml(String uuid, String points) {

        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("Players");
            document.appendChild(root);

            // employee element
            if (uuid != null) {
                Element employee = document.createElement("Player");

                root.appendChild(employee);

                // set an attribute to staff element
                Attr attr = document.createAttribute("UUID");
                attr.setValue(uuid);
                employee.setAttributeNode(attr);

                //you can also use staff.setAttribute("id", "1") for this


                // department elements
                Element department = document.createElement("Points");
                department.appendChild(document.createTextNode(points));
                employee.appendChild(department);
                Element departments = document.createElement("Max_Home");
                department.appendChild(document.createTextNode("2"));
                employee.appendChild(departments);
                Element departmentse = document.createElement("Home_Cree");
                department.appendChild(document.createTextNode("0"));
                employee.appendChild(departmentse);


            }
            // create the xml file
            //transform the DOM Object to an XML File
            File f = new File(xmlFilePath);
            if (!f.exists()) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();


                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));
                transformer.transform(domSource, streamResult);
                System.out.println("Done creating XML File");
            } else {
                System.out.println("XML FILE existant");

            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void test() {
        String filePath = ".\\config.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            // parse xml file and load into document
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // write the updated document to file or console
            writeXMLFile(doc);

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    public  void addPlayer(File xml, String UUID) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document document = db.parse(xml);
        int faisable = 0;
        if (document.getElementsByTagName("UUID") != null) {
            for (int j = 0; j < document.getElementsByTagName("UUID").getLength(); j++) {

                if (document.getElementsByTagName("UUID").item(j).getTextContent().equals(UUID)) {
                    faisable = 1;
                }
            }
        }
        if( document.getElementsByTagName("UUID").item(0) != null) {
            if (faisable == 0) {
                Element cd = document.createElement("Player");

                Element root = document.getDocumentElement();
                root.appendChild(cd);


                Element ID = document.createElement("UUID");
                Element Points = document.createElement("Points");
                Element max_Home = document.createElement("max_Home");
                Element home_cree = document.createElement("Home_Cree");


                ID.appendChild(document.createTextNode(UUID));
                Points.appendChild(document.createTextNode("0"));
                max_Home.appendChild(document.createTextNode("2"));
                home_cree.appendChild(document.createTextNode("0"));


                cd.appendChild(ID);
                cd.appendChild(Points);
                cd.appendChild(max_Home);
                cd.appendChild(home_cree);


                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File("config.xml"));
                transformer.transform(domSource, streamResult);

                DOMSource source = new DOMSource(document);
            }
        }else{
            Element cd = document.createElement("Player");

            Element root = document.getDocumentElement();
            root.appendChild(cd);


            Element ID = document.createElement("UUID");
            Element Points = document.createElement("Points");
            Element max_Home = document.createElement("max_Home");
            Element home_cree = document.createElement("Home_Cree");

            ID.appendChild(document.createTextNode(UUID));
            Points.appendChild(document.createTextNode("0"));
            max_Home.appendChild(document.createTextNode("2"));
            home_cree.appendChild(document.createTextNode("0"));


            cd.appendChild(ID);
            cd.appendChild(Points);
            cd.appendChild(max_Home);
            cd.appendChild(home_cree);


            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("config.xml"));
            transformer.transform(domSource, streamResult);

            DOMSource source = new DOMSource(document);
        }

    }

    private  void writeXMLFile(Document doc)
            throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(".\\config.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("XML file updated successfully");
    }



    public  void updatePoints(String UUID, String Points) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        //Read XML file.
        File inputFile = new File(filePath);

        //Create DocumentBuilderFactory object.
        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();

        //Get DocumentBuilder object.
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        //Parse XML file.
        Document document = dBuilder.parse(inputFile);

        //Get element by tag name.
        NodeList players =
                document.getElementsByTagName("Player");

        for (int i = 0 ; i < document.getElementsByTagName("UUID").getLength() ; i ++){
            if(document.getElementsByTagName("UUID").item(i).getTextContent().equals(UUID)){

                // get first staff
                Node player = players.item(i);
                if (player.getNodeType() == Node.ELEMENT_NODE) {


                    NodeList childNodes = player.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node item = childNodes.item(j);
                        if (item.getNodeType() == Node.ELEMENT_NODE) {

                            System.out.println("Test 1");
                            if ("Points".equalsIgnoreCase(item.getNodeName())) {
                                System.out.println("Done");
                                item.setTextContent(Points);
                            }

                        }


                    }
                }
            }
        }
        writeXMLFile(document);

    }


    public  void updateHomeCree(String UUID, String Points) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        //Read XML file.
        File inputFile = new File(filePath);

        //Create DocumentBuilderFactory object.
        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();

        //Get DocumentBuilder object.
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        //Parse XML file.
        Document document = dBuilder.parse(inputFile);

        //Get element by tag name.
        NodeList players =
                document.getElementsByTagName("Player");

        for (int i = 0 ; i < document.getElementsByTagName("UUID").getLength() ; i ++){
            if(document.getElementsByTagName("UUID").item(i).getTextContent().equals(UUID)){

                // get first staff
                Node player = players.item(i);
                if (player.getNodeType() == Node.ELEMENT_NODE) {


                    NodeList childNodes = player.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node item = childNodes.item(j);
                        if (item.getNodeType() == Node.ELEMENT_NODE) {

                            System.out.println("Test 1");
                            if ("Home_Cree".equalsIgnoreCase(item.getNodeName())) {
                                System.out.println("Done");
                                item.setTextContent(Points);
                            }

                        }


                    }
                }
            }
        }
        writeXMLFile(document);

    }


    public  String get_points (String UUID){


        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String filePath = ".\\config.xml";
        String points = "";
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(filePath));

            doc.getDocumentElement().normalize();


            NodeList list = doc.getElementsByTagName("UUID");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;


                    NodeList id = doc.getElementsByTagName("UUID");
                    if (id.item(temp).getFirstChild().getTextContent().equals(UUID)) {

                        points  = doc.getElementsByTagName("Points").item(temp).getFirstChild().getTextContent();
                    }

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

        }

        return points;
    }
    public  String get_max_homes (String UUID){


        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String filePath = ".\\config.xml";
        String points = "";
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(filePath));

            doc.getDocumentElement().normalize();


            NodeList list = doc.getElementsByTagName("UUID");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;


                    NodeList id = doc.getElementsByTagName("UUID");
                    if (id.item(temp).getFirstChild().getTextContent().equals(UUID)) {

                        points  = doc.getElementsByTagName("max_Home").item(temp).getFirstChild().getTextContent();
                    }

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

        }

        return points;
    }
    public  String get_home_cree (String UUID){


        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String filePath = ".\\config.xml";
        String points = "";
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(filePath));

            doc.getDocumentElement().normalize();


            NodeList list = doc.getElementsByTagName("UUID");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;


                    NodeList id = doc.getElementsByTagName("UUID");
                    if (id.item(temp).getFirstChild().getTextContent().equals(UUID)) {

                        points  = doc.getElementsByTagName("Home_Cree").item(temp).getFirstChild().getTextContent();
                    }

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

        }

        return points;
    }




    public XML(){

    }
}