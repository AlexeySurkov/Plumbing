package ru.company.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sanitary_table")
public class SanitaryWare {
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        BATH, FAUCET, SINK, TOILET
    }
}
