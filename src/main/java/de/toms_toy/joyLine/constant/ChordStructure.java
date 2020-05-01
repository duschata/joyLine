package de.toms_toy.joyLine.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import static de.toms_toy.joyLine.constant.Interval.*;

@XmlType(name = "chordStructure")
@XmlEnum
public enum ChordStructure {
    @XmlEnumValue("Minor6Arp")
    Minor6Arp("Minor6 Arp", "-6", _1, b3, _5, _6),
    @XmlEnumValue("Minor6_7Arp")
    Minor6_7Arp("Minor6/7 Arp", "-6/7", _1, b3, _5, _6, _7),
    @XmlEnumValue("Minor7Arp")
    Minor7Arp("Minor7 Arp", "-7", _1, b3, _5, _7),
    @XmlEnumValue("Minor7_9Arp")
    Minor7_9Arp("Minor7/9 Arp", "-7/9", _1, _9, b3, _5, _7),
    @XmlEnumValue("Minor6_7_9Arp")
    Minor6_7_9Arp("Minor6/7/9 Arp", "-6/7/9", _1, _9, b3, _5, _6, _7),
    @XmlEnumValue("Minor7_5bArp")
    Minor7_5bArp("Minor7b5 Arp", "-7b5", _1, b3, b5, _7),
    @XmlEnumValue("Dominant7Arp")
    Dominant7Arp("Dominant7 Arp", " 7 ", _1, _3, _5, _7),
    @XmlEnumValue("Dominant7Arp")
    DominantSus4Arp("DominantSus4 Arp", " sus ", _1, _4, _5, _7),
    @XmlEnumValue("Dominant7_b9Arp")
    Dominant7_b9Arp("Dominant7b9 Arp", " 7/b9 ", b9, _3, _5, _7),
    @XmlEnumValue("Dominant7_#5Arp")
    Dominant7_s5Arp("Dominant7#5 Arp", "7/#5", _1, _3, s5),
    @XmlEnumValue("Major7Arp")
    Major7Arp("Major7 Arp", " \u0394 ", _1, _3, _5, M7),
    @XmlEnumValue("Minor7Full")
    Minor7Full("Minor7 Full ", "-7", _1, b3, _5, _6, _7),
    @XmlEnumValue("Dominant7Full")
    Dominant7Full("Dominant7 Full", " 7 ", _1, _3, b5, _5, s5, _7, b9, _9, s9),
    @XmlEnumValue("Alt")
    Alt("Alt", "alt", _1, b9, s9, _3, b5, s5, _7),
    @XmlEnumValue("Major7Full")
    Major7Full("Major7 Full", " \u0394 ", _1, _3, _5, M7, _9);

    private final String value;
    private Interval[] intervals;
    private String shortName;
    private String displayedName;

    private ChordStructure(String displayedName, String shortName,
                           Interval... intervals) {

        this.setDisplayedName(displayedName);
        this.setShortName(shortName);
        this.setIntervals(intervals);
        this.value = this.toString();
    }

    ChordStructure(String v) {
        value = v;
    }

    public static ChordStructure fromValue(String v) {
        for (ChordStructure c : ChordStructure.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public Interval[] getIntervals() {
        return intervals;
    }

    public void setIntervals(Interval[] intervals) {
        this.intervals = intervals;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String name) {
        this.shortName = name;
    }

    public String value() {
        return value;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }
}
