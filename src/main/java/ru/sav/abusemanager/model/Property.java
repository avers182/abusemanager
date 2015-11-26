package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "o_property")
public class Property {
    public static final String BUSINESS_GROUP_RESOURCE_TYPE_NAME = "BusinessGroup";
    public static final String COURSE_MODULE_RESOURCE_TYPE_NAME = "CourseModule";
    public static final String TIME_LIMIT_NAME = "time_limit";

    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "lastmodified")
    private Date lastModified;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "resourcetypename")
    private String resourceTypeName;

    @Column(name = "resourcetypeid")
    private Long resourceTypeId;

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

    @Column(name = "floatvalue")
    private Double floatValue;

    @Column(name = "longvalue")
    private Long longValue;

    @Column(name = "stringvalue")
    private String stringValue;

    @Column(name = "textvalue")
    private String textValue;

    @JoinColumn(name = "identity")
    @ManyToOne()
    private Identity identity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    public Long getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(Long resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(Double floatValue) {
        this.floatValue = floatValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }
}
