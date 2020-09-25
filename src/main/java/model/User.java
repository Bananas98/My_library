package model;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private List<Role> roles;
    private Integer debt;
    private Boolean admin;
    private Boolean librarian;

    public User() {
    }

    public User(String name, String password, String email, Integer id, List<Role> roles, Integer debt, Boolean admin, Boolean librarian) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.roles = roles;
        this.debt = debt;
        this.admin = admin;
        this.librarian = librarian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getDebt() {
        return debt;
    }

    public void setDebt(Integer debt) {
        this.debt = debt;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getHost() {
        return librarian;
    }

    public void setHost(Boolean librarian) {
        this.librarian = librarian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
