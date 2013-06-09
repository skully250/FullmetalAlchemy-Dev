package mods.fullmetalalchemy.core.enums;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Deprecated
public enum TattooEnumState {

    ON("Reconstruction"), CHARGING("charging"), OFF("Deconstruction");
    

    String name;

    TattooEnumState(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
