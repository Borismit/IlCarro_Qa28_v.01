package models;

public class User {
    String name;
    String lastName;
    String email;
    String password;
    String phone;

    //генерируем все сетеры, чтобы записывать в поля. Все set меняем на with
    public User withName(String name) {
        this.name = name;
        return this;//метод сетер должен возврашать сам себя, т. е. он не может быть void, поэтому void меняем на User
    }

    public User withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public User withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    //генерируем все гетеры, чтобы брать с полей
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
