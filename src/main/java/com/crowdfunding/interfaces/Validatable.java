package com.crowdfunding.interfaces;

public interface Validatable<T> {
    boolean validate(T input);

    default void printValidationStatus(T input) {
        if (validate(input)) {
            System.out.println("Validation Passed");
        } else {
            System.out.println("Validation Failed");
        }
    }
}