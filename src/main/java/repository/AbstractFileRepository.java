package repository;

import domain.Entity;
import domain.validator.Validator;
import exceptions.ValidationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    private String fileName;

    public AbstractFileRepository(Validator<E> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public E save(E entity) throws ValidationException {
        if (super.save(entity) == null) {
            saveData();
            return null;
        }
        return entity;
    }

    @Override
    public E delete(ID id) {
        if (super.delete(id) != null) {
            saveData();
            return super.findOne(id);
        }
        return null;
    }

    @Override
    public E update(E entity) {
        if (super.update(entity) == null) {
            saveData();
            return null;
        }
        return entity;
    }

    protected abstract void deserializeFromXML(Document element);

    private void loadData() {
        try {
            File file = new File(fileName);
            if (file.length() != 0) {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);

                deserializeFromXML(document);
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void serializeToXML(E e, Element root, Document d);

    private void saveData() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //root
            Element root = document.createElement("entity");
            document.appendChild(root);
            for (E e : super.findAll()) {
                serializeToXML(e, root, document);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileName));

            transformer.transform(domSource, streamResult);
            System.out.println("You did it!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }



}
