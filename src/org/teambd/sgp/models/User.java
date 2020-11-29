package org.teambd.sgp.models;

public class User {

    private int id;
    private String username;
    private String password;
    private String permission;
    private boolean isActive;

    public User() {
    }

    public User(int id, String username, String password, String permission, boolean isActive) {
        this.id = id;
        this.setUsername(username);
        this.setPassword(password);
        this.setPermission(permission);
        this.setActive(isActive);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if ( !username.isBlank() ) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Empty username");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if ( !password.isBlank() ) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Empty password");
        }
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        if ( permission.equals("admin") ) {
            this.permission = permission;
        } else {
            throw new IllegalArgumentException("Invalid permission");
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permission='" + permission + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
