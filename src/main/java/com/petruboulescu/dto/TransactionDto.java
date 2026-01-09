package com.petruboulescu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.ZonedDateTime;
import java.util.UUID;

public class TransactionDto {

    private String id;
    @Min(value = 10)
    @Max(value = 100000)
    private int amount;
    @NotBlank
    private String reference;
    private String slogan;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmZ")
    private ZonedDateTime timestamp;

    public TransactionDto() {
    }

    public TransactionDto(int amount, String reference, ZonedDateTime timestamp, String slogan) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.reference = reference;
        this.timestamp = timestamp;
        this.slogan = slogan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
