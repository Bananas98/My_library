package model.entity;

public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Double bill;

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String email, String password, Role role, Double bill) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.bill = bill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }
    public static class Builder implements IBuilder<User> {
        private Integer id;
        private String name;
        private String password;
        private String email;
        private Role role;
        private Double bill;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }


        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }
        public Builder setBill(Double bill) {
            this.bill = bill;
            return this;
        }


        public User build() {
            return new User(id, name, password,email, role,bill);
        }
    }
}