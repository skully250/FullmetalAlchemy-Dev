package skully.fma.core.enums;


public enum TattooEnumState {

    ON("Reconstruction"),
    CHARGING("charging"),
    OFF("Deconstruction");


    String name;

    TattooEnumState(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
