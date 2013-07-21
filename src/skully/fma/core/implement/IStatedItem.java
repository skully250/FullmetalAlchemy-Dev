package skully.fma.core.implement;

import skully.fma.core.enums.EnumState;


public interface IStatedItem {

    String getState();

    void setState(EnumState state);
}
