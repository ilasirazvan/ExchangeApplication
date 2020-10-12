package com.exchange.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

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
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pubDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="baseCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="baseName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="exchangeRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="inverseRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="inverseDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "title",
        "link",
        "description",
        "pubDate",
        "baseCurrency",
        "baseName",
        "targetCurrency",
        "targetName",
        "exchangeRate",
        "inverseRate",
        "inverseDescription"
})
public class Item {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String link;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String pubDate;
    @XmlElement(required = true)
    protected String baseCurrency;
    @XmlElement(required = true)
    protected String baseName;
    @XmlElement(required = true)
    protected String targetCurrency;
    @XmlElement(required = true)
    protected String targetName;
    protected double exchangeRate;
    protected double inverseRate;
    @XmlElement(required = true)
    protected String inverseDescription;

    /**
     * Gets the value of the title property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the link property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the pubDate property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Sets the value of the pubDate property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPubDate(String value) {
        this.pubDate = value;
    }

    /**
     * Gets the value of the baseCurrency property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * Sets the value of the baseCurrency property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBaseCurrency(String value) {
        this.baseCurrency = value;
    }

    /**
     * Gets the value of the baseName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * Sets the value of the baseName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBaseName(String value) {
        this.baseName = value;
    }

    /**
     * Gets the value of the targetCurrency property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTargetCurrency() {
        return targetCurrency;
    }

    /**
     * Sets the value of the targetCurrency property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTargetCurrency(String value) {
        this.targetCurrency = value;
    }

    /**
     * Gets the value of the targetName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * Sets the value of the targetName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTargetName(String value) {
        this.targetName = value;
    }

    /**
     * Gets the value of the exchangeRate property.
     */
    public double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Sets the value of the exchangeRate property.
     */
    public void setExchangeRate(double value) {
        this.exchangeRate = value;
    }

    /**
     * Gets the value of the inverseRate property.
     */
    public double getInverseRate() {
        return inverseRate;
    }

    /**
     * Sets the value of the inverseRate property.
     */
    public void setInverseRate(double value) {
        this.inverseRate = value;
    }

    /**
     * Gets the value of the inverseDescription property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getInverseDescription() {
        return inverseDescription;
    }

    /**
     * Sets the value of the inverseDescription property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInverseDescription(String value) {
        this.inverseDescription = value;
    }

}

