package com.petruboulescu.dto;

import java.util.ArrayList;
import java.util.List;

public class ExceptionDto {

    private List<String> fieldNames = new ArrayList<>();

    private String errorMessage;

    public ExceptionDto(List<String> fieldNames, String errorMessage) {
        this.fieldNames = fieldNames;
        this.errorMessage = errorMessage;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
