package de.toms_toy.joyLine.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chordNote", propOrder = {
        "chromaticDegree",
        "intervalName"
})
public class ChordNote {
    private int chromaticDegree;
    private String intervalName;

    public ChordNote(int chromaticDegree, String intervalName) {
        this.chromaticDegree = chromaticDegree;
        this.intervalName = intervalName;
    }

    public int getChromaticDegree() {
        return chromaticDegree;
    }

    public void setChromaticDegree(int chromaticDegree) {
        this.chromaticDegree = chromaticDegree;
    }

    public String getIntervalName() {
        return intervalName;
    }

    public void setIntervalName(String intervalName) {
        this.intervalName = intervalName;
    }

}
