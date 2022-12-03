package io.github.pitzzahh.libraryManagementSystem.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private Category category;
    private Student student;
    private LocalDate dateBorrowed;
    private LocalDate dateReturned;

    public Book(String bookId, String title, String author, Category category, Student student, LocalDate dateBorrowed, LocalDate dateReturned) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.student = student;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    public Book() {
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public String getBookId() {
        return this.bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public Category getCategory() {
        return this.category;
    }

    public Student getStudent() {
        return this.student;
    }

    public LocalDate getDateBorrowed() {
        return this.dateBorrowed;
    }

    public LocalDate getDateReturned() {
        return this.dateReturned;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setDateBorrowed(LocalDate dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) return false;
        final Book other = (Book) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$bookId = this.getBookId();
        final Object other$bookId = other.getBookId();
        if (!Objects.equals(this$bookId, other$bookId)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (!Objects.equals(this$title, other$title)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (!Objects.equals(this$author, other$author)) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (!Objects.equals(this$category, other$category)) return false;
        final Object this$student = this.getStudent();
        final Object other$student = other.getStudent();
        if (!Objects.equals(this$student, other$student)) return false;
        final Object this$dateBorrowed = this.getDateBorrowed();
        final Object other$dateBorrowed = other.getDateBorrowed();
        if (!Objects.equals(this$dateBorrowed, other$dateBorrowed))
            return false;
        final Object this$dateReturned = this.getDateReturned();
        final Object other$dateReturned = other.getDateReturned();
        if (!Objects.equals(this$dateReturned, other$dateReturned))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Book;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $bookId = this.getBookId();
        result = result * PRIME + ($bookId == null ? 43 : $bookId.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final Object $student = this.getStudent();
        result = result * PRIME + ($student == null ? 43 : $student.hashCode());
        final Object $dateBorrowed = this.getDateBorrowed();
        result = result * PRIME + ($dateBorrowed == null ? 43 : $dateBorrowed.hashCode());
        final Object $dateReturned = this.getDateReturned();
        result = result * PRIME + ($dateReturned == null ? 43 : $dateReturned.hashCode());
        return result;
    }

    public String toString() {
        return "Book(bookId=" + this.getBookId() + ", title=" + this.getTitle() + ", author=" + this.getAuthor() + ", category=" + this.getCategory() + ", student=" + this.getStudent() + ", dateBorrowed=" + this.getDateBorrowed() + ", dateReturned=" + this.getDateReturned() + ")";
    }

    public static class BookBuilder {
        private String bookId;
        private String title;
        private String author;
        private Category category;
        private Student student;
        private LocalDate dateBorrowed;
        private LocalDate dateReturned;

        BookBuilder() {
        }

        public BookBuilder bookId(String bookId) {
            this.bookId = bookId;
            return this;
        }

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public BookBuilder student(Student student) {
            this.student = student;
            return this;
        }

        public BookBuilder dateBorrowed(LocalDate dateBorrowed) {
            this.dateBorrowed = dateBorrowed;
            return this;
        }

        public BookBuilder dateReturned(LocalDate dateReturned) {
            this.dateReturned = dateReturned;
            return this;
        }

        public Book build() {
            return new Book(bookId, title, author, category, student, dateBorrowed, dateReturned);
        }

        public String toString() {
            return "Book.BookBuilder(bookId=" + this.bookId + ", title=" + this.title + ", author=" + this.author + ", category=" + this.category + ", student=" + this.student + ", dateBorrowed=" + this.dateBorrowed + ", dateReturned=" + this.dateReturned + ")";
        }
    }
}
