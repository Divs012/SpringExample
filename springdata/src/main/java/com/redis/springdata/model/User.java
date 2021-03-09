package com.redis.springdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //Getter Setter
@AllArgsConstructor //Argument Constructor
@NoArgsConstructor //Non-Argument Constructor
public class User  implements Serializable {
    private Long id;
    private String name;
    private int age;



}
