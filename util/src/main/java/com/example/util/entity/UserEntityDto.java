package com.example.util.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(onConstructor = @__(@JsonCreator))
@ToString
public class UserEntityDto implements Serializable {
    private String email;
    private String operation;

}
