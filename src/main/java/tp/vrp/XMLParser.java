package tp.vrp;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private List<Node> nodeList;
    private List<Request> requestList;

    public XMLParser() {
        this.nodeList = new ArrayList<>();
        this.requestList = new ArrayList<>();
    }

    public void parseXMLFile(String filePath) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(filePath));

            double longitude = 0;
            double latitude = 0;
            int type_Node=0;
            int id_Node = 0;
            int id_Request = 0;
            int node=0;
            double quantity = 0;
            Node currentNode = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();

                    switch (elementName) {
                        case "cx":
                            event = eventReader.nextEvent();
                            longitude = Double.parseDouble(event.asCharacters().getData());
                            break;
                        case "cy":
                            event = eventReader.nextEvent();
                            latitude = Double.parseDouble(event.asCharacters().getData());

                            break;
                        case "type":
                            event = eventReader.nextEvent();
                            type_Node= Integer.parseInt(event.asCharacters().getData());

                            currentNode = new Node(++id_Node, longitude, latitude,type_Node);
                            nodeList.add(currentNode);
                            break;
                        case "request":
                            id_Request++;
                            break;
                        case "quantity":
                            event = eventReader.nextEvent();
                            quantity = Double.parseDouble(event.asCharacters().getData());

                            break;
                        case "node":
                            event = eventReader.nextEvent();
                            node = Integer.parseInt(event.asCharacters().getData());
                            Request request = new Request(quantity,nodeList.get(node),id_Request);
                            requestList.add(request);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public List<Request> getRequestList() {
        return requestList;
    }
}