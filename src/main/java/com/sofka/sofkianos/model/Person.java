package com.sofka.sofkianos.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

@Data
@Setter
@Getter
@Document(collection = "person")
public class Person {

    private @Id String id;
    private String name;
    private String lastname;
    private String email;
    private ArrayList<Skill> skills;

}
