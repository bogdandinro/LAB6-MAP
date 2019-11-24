package repository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class ReadXMLFile {
    public static void main(String[] args) {
        try {
            File file = new File("D:\\Facultate\\An2\\Semestrul 2\\MAP\\Sorin\\Project\\src\\main\\java\\dataXML\\da.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("entity");
            for(int temp = 0; temp < nodeList.getLength(); temp++){
                Node node = nodeList.item(temp);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    //System.out.println(element.getAttributeNode("id").getTextContent());
                    System.out.println(element.getAttribute("id"));
                    System.out.println(element.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println(element.getElementsByTagName("secondname").item(0).getTextContent());
                    System.out.println(element.getElementsByTagName("group").item(0).getTextContent());
                    System.out.println(element.getElementsByTagName("email").item(0).getTextContent());
                    System.out.println(element.getElementsByTagName("teacher").item(0).getTextContent());
                    }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
