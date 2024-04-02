package com.sourcmind.alumni.einvoicing.services;

public interface CodeReadable<T> {
    T readByCode(String code);

    boolean existsByCode(String code);
}
