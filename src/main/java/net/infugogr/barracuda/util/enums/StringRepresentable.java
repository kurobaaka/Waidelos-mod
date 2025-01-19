package net.infugogr.barracuda.util.enums;

import java.util.Objects;

public interface StringRepresentable {
    String getSerializedName();

    static <T extends Enum<?> & StringRepresentable> T getEnumByName(Class<T> enumClass, String serializedName) {
        return getEnumByName(enumClass.getEnumConstants(), serializedName);
    }

    static <T extends Enum<?> & StringRepresentable> T getEnumByName(T[] values, String serializedName) {
        for (T value : values) {
            if (Objects.equals(value.getSerializedName(), serializedName)) {
                return value;
            }
        }

        return null;
    }
}
