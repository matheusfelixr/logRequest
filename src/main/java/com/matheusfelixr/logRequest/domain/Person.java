package com.matheusfelixr.logRequest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Person {

    @Id
    private String id;

    private String nome;

    public Person(String nome) {
        this.nome = nome;
    }
    public Person() {
    }
}
