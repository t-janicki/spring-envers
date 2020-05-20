package com.envers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRevisionDto {
    // metadata.entity.revisionDate
    private LocalDateTime revisionDate;
    // metadata.revisionType
    private String revisionType;
    // book id
    private Long id;
    // book title
    private String title;
    // book pages
    private Integer pages;
    // book author
//    private String author;

}
