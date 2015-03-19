package net.caspervg.lex4j.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Class that helps with Gson (de)serialisation of generic types
 *
 * @param <X> Type to deserialize
 */
public final class ParameterizedList<X> implements ParameterizedType {
    private Class<?> wrapped;

    public ParameterizedList(Class<X> wrapped) {
        this.wrapped = wrapped;
    }

    public Type[] getActualTypeArguments() {
        return new Type[] {wrapped};
    }

    public Type getRawType() {
        return List.class;
    }

    public Type getOwnerType() {
        return null;
    }

}
