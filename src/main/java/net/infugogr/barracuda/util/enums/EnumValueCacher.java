package net.infugogr.barracuda.util.enums;

public interface EnumValueCacher<T extends Enum<?>> {
    T[] getValues();
}
