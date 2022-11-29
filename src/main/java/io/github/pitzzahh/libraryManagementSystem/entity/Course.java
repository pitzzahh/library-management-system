package io.github.pitzzahh.libraryManagementSystem.entity;

public enum Course {
    BSIT("Bachelor of Science in Information Technology"),
    BSHM("Bachelor of Science in Hotel Management"),
    BSBA("Bachelor of Science in Business Administration"),
    BSCS("Bachelor of Science in Computer Science"),
    BSCPE("Bachelor of Science in Computer Engineering"),
    BSAIS("Bachelor of Science in Accountancy Information System");

    private String courseName;

    Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}
