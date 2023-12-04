package tp.vrp;

import org.w3c.dom.NodeList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
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
            double quantity = 0;
            Request currentRequest = null;
            Node currentNode = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();

                    switch (elementName) {
                        case "node":
                            currentNode = new Node();
                            Iterator<Attribute> attributes2 = startElement.getAttributes();
                            while (attributes2.hasNext()) {
                                Attribute attribute = attributes2.next();
                                String attrName = attribute.getName().getLocalPart();
                                String attrValue = attribute.getValue();
                                if ("id".equals(attrName)) {
                                    currentNode.setId(Integer.parseInt(attrValue));
                                } else if ("type".equals(attrName)) {
                                    currentNode.setType(Integer.parseInt(attrValue));
                                }
                            }

                            break;
                        case "cx":
                            event = eventReader.nextEvent();
                            if (currentNode != null) {
                                currentNode.setLongitude(Double.parseDouble(event.asCharacters().getData()));
                            }
                            break;
                        case "cy":
                            event = eventReader.nextEvent();
                            if (currentNode != null) {
                                currentNode.setLatitude(Double.parseDouble(event.asCharacters().getData()));
                                nodeList.add(currentNode);
                            }
                            break;

                        case "request":
                            currentRequest = new Request();
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                String attrName = attribute.getName().getLocalPart();
                                String attrValue = attribute.getValue();
                                if ("id".equals(attrName)) {
                                    currentRequest.setId(Integer.parseInt(attrValue));
                                } else if ("node".equals(attrName)) {
                                    currentRequest.setNode(Integer.parseInt(attrValue));
                                }
                            }
                            break;
                        case "quantity":
                            event = eventReader.nextEvent();
                            if (currentRequest != null) {
                                currentRequest.setQuantity(Double.parseDouble(event.asCharacters().getData()));
                                requestList.add(currentRequest);
                            }
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