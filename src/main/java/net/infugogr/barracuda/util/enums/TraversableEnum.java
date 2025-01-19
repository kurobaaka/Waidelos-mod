package net.infugogr.barracuda.util.enums;

public interface TraversableEnum<T extends Enum<?>> {
    T next();

    T previous();
}
