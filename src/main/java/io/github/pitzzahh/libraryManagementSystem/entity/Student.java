package io.github.pitzzahh.libraryManagementSystem.entity;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String studentNumber;
    private String firstName;
    private String lastName;
    private Course course;
}
