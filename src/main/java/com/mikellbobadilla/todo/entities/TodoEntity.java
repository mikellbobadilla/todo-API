package com.mikellbobadilla.todo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "todos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TodoEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    @Column(name = "creation_date")
    private Date creationDate;
    private Boolean completed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;
}
