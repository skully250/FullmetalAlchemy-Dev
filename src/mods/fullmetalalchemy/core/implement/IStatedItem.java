package mods.fullmetalalchemy.core.implement;

import mods.fullmetalalchemy.core.enums.EnumState;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Deprecated //Replacement Coming Soon
public interface IStatedItem {

    String getState();

    void setState(EnumState state);
}
