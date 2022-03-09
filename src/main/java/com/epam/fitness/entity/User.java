package com.epam.fitness.entity;

import java.io.Serializable;

public class User implements Serializable {
    private long id;
    private String login;
    private String email;
    private String passwordHash;
    private String name;
    private String surname;
    private Role role;
    private Status status;
    private int payment;

    public enum Role {
        GUEST, USER, ADMIN, VIP
    }

    public enum Status {
        ACTIVATED, BLOCKED, NOT_CONFIRMED
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(passwordHash).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", role=").append(role);
        sb.append(", status=").append(status);
        sb.append(", payment=").append(payment);
        sb.append('}');
        return sb.toString();
    }

    public static class UserBuilder {
        private User user = new User();

        public UserBuilder() {

        }

        public UserBuilder setId(long id) {
            user.setId(id);
            return this;
        }

        public UserBuilder setRole(Role role) {
            user.setRole(role);
            return this;
        }

        public UserBuilder setStatus(Status status) {
            user.setStatus(status);
            return this;
        }

        public UserBuilder setLogin(String login) {
            user.setLogin(login);
            return this;
        }

        public UserBuilder setPassword(String password) {
            user.setPasswordHash(password);
            return this;
        }

        public UserBuilder setName(String firstName) {
            user.setName(firstName);
            return this;
        }

        public UserBuilder setSurname(String lastName) {
            user.setSurname(lastName);
            return this;
        }

        public UserBuilder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public User buildUser() {
            return user;
        }
    }
}
