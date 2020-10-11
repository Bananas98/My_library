package model.entity;

public class Categories {
    private int id;
    private String name;

    public Categories(int id, String name) {
    }
    public Categories(int id) {
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

    public static class Builder implements IBuilder<Categories> {
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

        public Categories build() {
            return new Categories(id, name);
        }
    }

}





