package tp.vrp;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



    public class XMLParser {
        private List<String> data;

        public XMLParser() {
            this.data = new ArrayList<>();
        }

        public void parseXMLFile(String filePath) {
            try {
                File xmlFile = new File(filePath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                NodeList nList = doc.getElementsByTagName("yourElementTag"); // Remplacez par le nom de votre balise

                for (int i = 0; i < nList.getLength(); i++) {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        data.add(node.getTextContent());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public List<String> getData() {
            return data;
        }
    }

