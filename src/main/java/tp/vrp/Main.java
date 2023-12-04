package tp.vrp;

import java.util.List;

public class Main {
        public static void main(String[] args) {
                XMLParser parser = new XMLParser();
                parser.parseXMLFile("C:/Users/brotc/Downloads/JDD03.xml"); // Remplacez par le chemin de votre fichier

                List<String> data = parser.getData();
                for (int i = 0; i < Math.min(10, data.size()); i++) {
                        System.out.println(data.get(i));
                }
        }
}