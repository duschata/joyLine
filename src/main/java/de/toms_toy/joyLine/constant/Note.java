package de.toms_toy.joyLine.constant;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "note")
@XmlEnum
public enum Note {
    C(0, "C"),
    D(2, "D"),
    E(4, "E"),
    F(5, "F"),
    G(7, "G"),
    A(9, "A"),
    B(11, "B"),
    C_SHARP_D_FLAT(1, "C\u266f/D\u266D"),
    D_SHARP_E_FLAT(3, "D\u266f/E\u266D"),
    F_SHARP_G_FLAT(6, "F\u266f/G\u266D"),
    G_SHARP_A_FLAT(8, "G\u266f/A\u266D"),
    A_SHARP_B_FLAT(10, "A\u266f/B\u266D");

    private final int degree;
    private final String name;

    private Note(final int degree, final String name) {
        this.degree = degree;
        this.name = name;
    }

    public static Note getChromaticNoteByDegree(int degree) {
        for (Note note : values()) {
            if (note.degree == degree % 12)
                return note;
        }

        return null;

    }


    public static Note getDiatonicNoteByDegree(int degree) {
        for (Note note : values()) {
            if (note.ordinal() == degree % 7)
                return note;
        }

        return null;

    }

    public static Note fromValue(String v) {
        return valueOf(v);
    }

    public String getName() {
        return name;
    }

    public String value() {
        return name();
    }

    public int getChromaticDegree() {
        return degree % 12;
    }


}
