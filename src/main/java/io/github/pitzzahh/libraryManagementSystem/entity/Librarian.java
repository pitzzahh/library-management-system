package io.github.pitzzahh.libraryManagementSystem.entity;

public class Librarian {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    public Librarian() {
    }

    public static LibrarianBuilder builder() {
        return new LibrarianBuilder();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Librarian)) return false;
        final Librarian other = (Librarian) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Librarian;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "Librarian(name=" + this.getName() + ")";
    }

    public static class LibrarianBuilder {
        private String name;

        LibrarianBuilder() {
        }

        public LibrarianBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Librarian build() {
            return new Librarian(name);
        }

        public String toString() {
            return "Librarian.LibrarianBuilder(name=" + this.name + ")";
        }
    }
}
