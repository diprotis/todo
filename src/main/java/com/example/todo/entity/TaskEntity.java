package com.example.todo.entity;

import com.example.todo.model.Person;
import com.example.todo.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing the table - TaskEntity.
 */
@Entity
@Table(name = "task")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long points;

    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity person;

    private LocalDateTime completionDate;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
