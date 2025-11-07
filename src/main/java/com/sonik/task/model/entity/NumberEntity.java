package com.sonik.task.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "numbers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Integer value;

    public NumberEntity(Integer value) {
        this.value = value;
    }
}
