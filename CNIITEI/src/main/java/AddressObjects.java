import lombok.Getter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement(name = "ADDRESSOBJECTS")
@Getter
public class AddressObjects {

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
