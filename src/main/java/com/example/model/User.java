package com.example.model;


import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Builder(toBuilder = true)
@ToString
@Table("user")
@AllArgsConstructor
public class User {

    @PrimaryKey
    int id;

    @Column
    String name;

    @Column
    String email;

}
