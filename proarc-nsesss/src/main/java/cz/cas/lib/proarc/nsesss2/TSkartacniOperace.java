//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.21 at 01:10:09 AM CET 
//


package cz.cas.lib.proarc.nsesss2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tSkartacniOperace.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tSkartacniOperace">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="trvalé uložení"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tSkartacniOperace", namespace = "http://www.mvcr.cz/nsesss/v2")
@XmlEnum
public enum TSkartacniOperace {

    @XmlEnumValue("trval\u00e9 ulo\u017een\u00ed")
    TRVALÉ_ULOŽENÍ("trval\u00e9 ulo\u017een\u00ed");
    private final String value;

    TSkartacniOperace(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TSkartacniOperace fromValue(String v) {
        for (TSkartacniOperace c: TSkartacniOperace.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}