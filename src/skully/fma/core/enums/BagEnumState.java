package skully.fma.core.enums;

public enum BagEnumState {
	
    ON("Holding"), CHARGING("charging"), OFF("Taking");
    

    String name;

    BagEnumState(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
