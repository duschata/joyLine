package de.toms_toy.joyLine.constant;

public enum Interval {
    _1(0, "1"),
    b2(1, "b2"),
    _2(2, "2"),
    b3(3, "b3"),
    _3(4, "3"),
    _4(5, "4"),
    s4(6, "#4"),
    b5(6, "b5"),
    _5(7, "5"),
    b6(8, "b6"),
    s5(8, "#5"),
    _6(9, "6"),
    _7(10, "7"),
    M7(11, "\u0394"),
    _8(12, "8"),
    b9(13, "b9"),
    _9(14, "9"),
    s9(15, "#9"),
    _11(17, "11"),
    s11(18, "#11"),
    b13(20, "b13"),
    _13(21, "13");

    private int offset;
    private String name;

    private Interval(int offset, String name) {
        this.offset = offset;
        this.name = name;
    }

    public static Interval getByName(String name) {
        for (Interval interval : values()) {
            if (interval.getName().equals(name))
                return interval;
        }
        return null;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
