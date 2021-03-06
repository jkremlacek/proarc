//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.21 at 01:10:09 AM CET 
//


package cz.cas.lib.proarc.nsesss2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Sada elementů pro popis dokumentů se stejnou charakteristikou, která usnadňuje správu dokumentů stejného typu shodně a stanoveným specifickým způsobem. Typem dokumentu jsou například "faktury", "rozsudky" nebo "webové stránky".
 * 
 * <p>Java class for tTypDokumentu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tTypDokumentu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Identifikator" type="{http://www.mvcr.cz/nsesss/v2}tIdentifikator"/>
 *         &lt;element name="Nazev" type="{http://www.mvcr.cz/nsesss/v2}tNazev"/>
 *         &lt;element name="Komentar" type="{http://www.mvcr.cz/nsesss/v2}tKomentar" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTypDokumentu", namespace = "http://www.mvcr.cz/nsesss/v2", propOrder = {
    "identifikator",
    "nazev",
    "komentar"
})
public class TTypDokumentu {

    @XmlElement(name = "Identifikator", namespace = "http://www.mvcr.cz/nsesss/v2", required = true)
    protected TIdentifikator identifikator;
    @XmlElement(name = "Nazev", namespace = "http://www.mvcr.cz/nsesss/v2", required = true)
    protected String nazev;
    @XmlElement(name = "Komentar", namespace = "http://www.mvcr.cz/nsesss/v2")
    protected String komentar;

    /**
     * Gets the value of the identifikator property.
     * 
     * @return
     *     possible object is
     *     {@link TIdentifikator }
     *     
     */
    public TIdentifikator getIdentifikator() {
        return identifikator;
    }

    /**
     * Sets the value of the identifikator property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIdentifikator }
     *     
     */
    public void setIdentifikator(TIdentifikator value) {
        this.identifikator = value;
    }

    /**
     * Gets the value of the nazev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Sets the value of the nazev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazev(String value) {
        this.nazev = value;
    }

    /**
     * Gets the value of the komentar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKomentar() {
        return komentar;
    }

    /**
     * Sets the value of the komentar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKomentar(String value) {
        this.komentar = value;
    }

}
