package skully.fma.core.enums;

public enum EnumState {

    ON("active"),
    CHARGING("charging"),
    OFF("inactive");


    String name;

    EnumState(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
