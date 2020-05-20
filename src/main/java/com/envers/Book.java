package com.envers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Audited
    private String title;

    @Column
    @Audited
    private Integer pages;

    @Column
    private String author;
}
