package model.entity;

public enum Role {

    READER("reader"), LIBRARIAN("librarian"), ADMIN("admin");

    private String localeKey;

    Role(String localeKey) {
        this.localeKey = localeKey;
    }

    public String getLocaleKey() {
        return localeKey;
    }

}
