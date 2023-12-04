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
    private List<Node> data;

    public XMLParser() {
        this.data = new ArrayList<>();
    }

    public void parseXMLFile(String filePath) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(filePath));

            double longitude = 0;
            double latitude = 0;
            int id = 0;

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
                            Node node = new Node(++id, longitude, latitude);
                            data.add(node);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Node> getNodeList() {
        return data;
    }
}