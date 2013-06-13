package mods.fullmetalalchemy.core.implement;

import mods.fullmetalalchemy.core.enums.EnumState;


public interface IStatedItem {

    String getState();

    void setState(EnumState state);
}
