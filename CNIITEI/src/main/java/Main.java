package main.java;

import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.annotation.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main d = new Main();
        d.firstTask();
        d.secondTask();
    }

    public void firstTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
        String dateToCheck = "2010-01-01";
        String idToCheck = "1422396, 1450759, 1449192, 1451562";
        Map<String, String> result = new HashMap<>();
        String[] idArray = idToCheck.split(", ");

        for (String word : idArray) {
            System.out.println(word);
        }

        /**       try {
         File xmlFile = new File("AS_ADDR_OBJ.XML");
         JAXBContext context = JAXBContext.newInstance(AddressObjects.class);
         Unmarshaller unmarshaller = context.createUnmarshaller();
         AddressObjects unmarshal = (AddressObjects) unmarshaller.unmarshal(xmlFile);

         System.out.println("list:"+unmarshal.getList());
         System.out.println("class:"+unmarshal.getClass());
         //            System.out.println("buy:"+unmarshal.getList().get(1));
         //            System.out.println("sale:"+unmarshal.getList().get(2));

         } catch (JAXBException ex) {ex.printStackTrace(System.out);}*/

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("AS_ADDR_OBJ.XML");
            Node root = document.getDocumentElement();

            NodeList entries = root.getOwnerDocument().getElementsByTagName("OBJECT");
            System.out.println(entries.getLength());
            for (int i = 0; i < entries.getLength(); i++) {
                Node entry = entries.item(i);
                if (Arrays.asList(idArray).contains(entry.getAttributes().getNamedItem("OBJECTID").getTextContent())) {
                    result.put(entry.getAttributes().getNamedItem("OBJECTID").getTextContent(), entry.getAttributes().getNamedItem("TYPENAME").getTextContent() + " " + entry.getAttributes().getNamedItem("NAME").getTextContent());
                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        for (String key : result.keySet()) {
            System.out.println(key + ": " + result.get(key));
        }
    }

    public void secondTask() {
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType
    @XmlRootElement(name = "ADDRESSOBJECTS")
    @Getter
    public static class AddressObjects implements Serializable {
        private static final long serialVersionUID = 1L;
        @XmlElementWrapper(name = "ADDRESSOBJECTS")
        @XmlElement
        List<AddressData> list = new ArrayList<AddressData>();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType
    @XmlRootElement()
    @Getter
    public static class AddressData implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlAttribute(name = "OBJECT ISACTIVE")
        private String objectIsActive;
        @XmlAttribute(name = "ISACTUAL")
        private String isActual;
        @XmlAttribute(name = "ENDDATE")
        private String endDate;
        @XmlAttribute(name = "STARTDATE")
        private String startDate;
        @XmlAttribute(name = "UPDATEDATE")
        private String updateDate;
        @XmlAttribute(name = "NEXTID")
        private String nextId;
        @XmlAttribute(name = "PREVID")
        private String prevId;
        @XmlAttribute(name = "OPERTYPEID")
        private String operTypeId;
        @XmlAttribute(name = "LEVEL")
        private String level;
        @XmlAttribute(name = "TYPENAME")
        private String typeName;
        @XmlAttribute(name = "NAME")
        private String name;
        @XmlAttribute(name = "CHANGEID")
        private String changeId;
        @XmlAttribute(name = "OBJECTGUID")
        private String objectGUID;
        @XmlAttribute(name = "OBJECTID")
        private String objectId;
        @XmlAttribute(name = "ID")
        private String id;
    }
}