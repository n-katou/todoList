package com.naoto.todolist.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;



@Data
@Entity

public class TodoList {

    @Id
    @GeneratedValue
    private Long id;

    
    @NotBlank
    @Size(max = 120)
    private String content;

    @NotNull
    private boolean todo;
}
