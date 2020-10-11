package model.entity;

public class Author {
    private int id;
    private String name;


    public Author(int id, String name) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static class Builder implements IBuilder<Author> {
        private int id;
        private String name;


        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Author build() {
            return new Author(id, name);
        }
    }
}
