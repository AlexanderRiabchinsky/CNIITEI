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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main d = new Main();
        d.firstTask();
        System.out.println("\n\n\n");
        d.secondTask();
    }

    public void firstTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
        /**Вводится строковая дата проверки адресов*/
        String dateToCheck = "2010-01-01";
        LocalDate dayToCheck = LocalDate.parse((CharSequence) dateToCheck, formatter);
        /**Вводится строка проверяемых OBJECTID*/
        String idToCheck = "1422396, 1450759, 1449192, 1451562, 1417838";
        Map<String, String> result = new HashMap<>();
        String[] idArray = idToCheck.split(", ");

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("AS_ADDR_OBJ.XML");
            Node root = document.getDocumentElement();
            NodeList entries = root.getOwnerDocument().getElementsByTagName("OBJECT");

            for (int i = 0; i < entries.getLength(); i++) {
                Node entry = entries.item(i);
                LocalDate startDate = LocalDate.parse((CharSequence) entry.getAttributes().getNamedItem("STARTDATE").getTextContent(), formatter);
                LocalDate endDate = LocalDate.parse((CharSequence) entry.getAttributes().getNamedItem("ENDDATE").getTextContent(), formatter);
                if (Arrays.asList(idArray).contains(entry.getAttributes()
                        .getNamedItem("OBJECTID").getTextContent()) && (dayToCheck.isAfter(startDate)
                        && ((dayToCheck.isBefore(endDate) || dayToCheck.equals(endDate))))) {
                    result.put(entry.getAttributes().getNamedItem("OBJECTID").getTextContent(),
                            entry.getAttributes().getNamedItem("TYPENAME").getTextContent() + " "
                                    + entry.getAttributes().getNamedItem("NAME").getTextContent());
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }

        for (String key : result.keySet()) {
            System.out.println(key + ": " + result.get(key));
        }
    }

    public void secondTask() {
        String addressType = "проезд";
        Set<String> result = new HashSet<>();
        Map<String, String> parents = new HashMap<>();
        parents = parentsSearch();
//        for (String key : parents.keySet()) {
//            System.out.println(key + ": " + parents.get(key));
//        }


        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    public Map<String, String> parentsSearch() {
        Map<String, String> result = new HashMap<>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("AS_ADM_HIERARCHY.XML");
            Node root = document.getDocumentElement();
            NodeList entries = root.getOwnerDocument().getElementsByTagName("ITEM");
            for (int i = 0; i < entries.getLength(); i++) {
                Node entry = entries.item(i);

                if (entry.getAttributes()
                        .getNamedItem("ISACTIVE").getTextContent().equals("1")) {
                    result.put(entry.getAttributes().getNamedItem("OBJECTID").getTextContent(),
                            entry.getAttributes().getNamedItem("PARENTOBJID").getTextContent());
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
        return result;
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