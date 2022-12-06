package io.github.pitzzahh.libraryManagementSystem.entity;

import java.util.List;
import java.util.Objects;

public class Student {
    private String studentNumber;
    private String firstName;
    private String lastName;
    private Course course;
    private List<Book> borrowedBooks;

    public Student(String studentNumber, String firstName, String lastName, Course course, List<Book> borrowedBooks) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.borrowedBooks = borrowedBooks;
    }

    public Student() {
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public String getStudentNumber() {
        return this.studentNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Course getCourse() {
        return this.course;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Student)) return false;
        final Student other = (Student) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$studentNumber = this.getStudentNumber();
        final Object other$studentNumber = other.getStudentNumber();
        if (!Objects.equals(this$studentNumber, other$studentNumber))
            return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (!Objects.equals(this$firstName, other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (!Objects.equals(this$lastName, other$lastName)) return false;
        final Object this$course = this.getCourse();
        final Object other$course = other.getCourse();
        if (!Objects.equals(this$course, other$course)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Student;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $studentNumber = this.getStudentNumber();
        result = result * PRIME + ($studentNumber == null ? 43 : $studentNumber.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $course = this.getCourse();
        result = result * PRIME + ($course == null ? 43 : $course.hashCode());
        return result;
    }

    public String toString() {
        return "Student(studentNumber=" + this.getStudentNumber() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", course=" + this.getCourse() + ")";
    }

    public static class StudentBuilder {
        private String studentNumber;
        private String firstName;
        private String lastName;
        private Course course;
        private List<Book> borrowedBooks;

        StudentBuilder() {
        }

        public StudentBuilder studentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }

        public StudentBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StudentBuilder course(Course course) {
            this.course = course;
            return this;
        }

        public StudentBuilder borrowedBooks(List<Book> borrowedBooks) {
            this.borrowedBooks = borrowedBooks;
            return this;
        }

        public Student build() {
            return new Student(studentNumber, firstName, lastName, course, borrowedBooks);
        }

        public String toString() {
            return "Student.StudentBuilder(studentNumber=" + this.studentNumber + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", course=" + this.course + ")";
        }
    }
}
