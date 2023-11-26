package com.cydeo.entity;

import com.cydeo.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter @Setter
@Where(clause = "is_deleted_false")
public class Task extends BaseEntity
{
    private String taskSubject;
    private String taskDetails;

    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @Column(columnDefinition = "DATE")
    private LocalDate assignDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User assignEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
