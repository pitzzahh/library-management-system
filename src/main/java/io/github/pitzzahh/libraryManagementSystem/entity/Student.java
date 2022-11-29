package io.github.pitzzahh.libraryManagementSystem.entity;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String accountNumber;
    private String name;
    private String address;
}
