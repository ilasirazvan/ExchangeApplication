package com.exchange.entities;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xmlLink" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="baseCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pubDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastBuildDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="item" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="pubDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="baseCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="baseName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="targetCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="targetName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="exchangeRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="inverseRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="inverseDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "title",
        "link",
        "xmlLink",
        "description",
        "language",
        "baseCurrency",
        "pubDate",
        "lastBuildDate",
        "item"
})
@XmlRootElement(name = "channel")
public class Channel {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String link;
    @XmlElement(required = true)
    protected String xmlLink;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String language;
    @XmlElement(required = true)
    protected String baseCurrency;
    @XmlElement(required = true)
    protected String pubDate;
    @XmlElement(required = true)
    protected String lastBuildDate;
    @XmlElement(required = true)
    protected List<Item> item;

    /**
     * Gets the value of the title property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the link property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Gets the value of the xmlLink property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getXmlLink() {
        return xmlLink;
    }

    /**
     * Sets the value of the xmlLink property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setXmlLink(String value) {
        this.xmlLink = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the language property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the baseCurrency property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * Sets the value of the baseCurrency property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBaseCurrency(String value) {
        this.baseCurrency = value;
    }

    /**
     * Gets the value of the pubDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Sets the value of the pubDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPubDate(String value) {
        this.pubDate = value;
    }

    /**
     * Gets the value of the lastBuildDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLastBuildDate() {
        return lastBuildDate;
    }

    /**
     * Sets the value of the lastBuildDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastBuildDate(String value) {
        this.lastBuildDate = value;
    }

    /**
     * Gets the value of the item property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     *
     *
     */
    public List<Item> getItem() {
        if (item == null) {
            item = new ArrayList<Item>();
        }
        return this.item;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", xmlLink='" + xmlLink + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", lastBuildDate='" + lastBuildDate + '\'' +
                ", item=" + item +
                '}';
    }
}