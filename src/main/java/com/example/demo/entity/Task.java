package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "task")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_gen"
    )
    @SequenceGenerator(
            name ="task_gen",
            sequenceName = "task_seq",
            allocationSize = 1
    )
    Long id ;
    String description;
    boolean status;

}
