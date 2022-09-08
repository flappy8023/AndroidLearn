package com.example.androidlearn.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference<T> {
    private final Type type;

    protected TypeReference() {
        Class<?> parameterizedTypeReferenceSubclass = findParameterizedTypeReferenceSubclass(this.getClass());
        Type type = parameterizedTypeReferenceSubclass.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        this.type = parameterizedType.getActualTypeArguments()[0];
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof TypeReference && this.type.equals(((TypeReference) obj).type);
    }

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "TypeReference<" + this.type + ">";
    }

    private static Class<?> findParameterizedTypeReferenceSubclass(Class<?> child) {
        Class<?> parent = child.getSuperclass();
        if (Object.class == parent) {
            throw new IllegalStateException("Expected TypeReference superclass");
        } else {
            return TypeReference.class == parent ? child : findParameterizedTypeReferenceSubclass(parent);
        }
    }
}