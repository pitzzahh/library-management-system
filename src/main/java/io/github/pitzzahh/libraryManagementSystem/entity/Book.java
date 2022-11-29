package io.github.pitzzahh.libraryManagementSystem.entity;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bookId;
    private String title;
    private String author;
    private Category category;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;
}
