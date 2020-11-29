package org.teambd.sgp.models;

public class Brand {

    private int id;
    private String name;

    public Brand() {
    }

    public Brand(int id, String name) {
        this.id = id;
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if ( !name.isBlank() ) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Empty name");
        }
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
