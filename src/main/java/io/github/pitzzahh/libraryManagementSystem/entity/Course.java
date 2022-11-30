package io.github.pitzzahh.libraryManagementSystem.entity;

public enum Course {
    BSIT("Bachelor of Science in Information Technology"),
    BSHM("Bachelor of Science in Hotel Management"),
    BSBA("Bachelor of Science in Business Administration"),
    BSCS("Bachelor of Science in Computer Science"),
    BSCPE("Bachelor of Science in Computer Engineering"),
    BSAIS("Bachelor of Science in Accountancy Information System");

    private final String COURSE_NAME;

    Course(String courseName) {
        COURSE_NAME = courseName;
    }

    public String getCourseName() {
        return COURSE_NAME;
    }
}
