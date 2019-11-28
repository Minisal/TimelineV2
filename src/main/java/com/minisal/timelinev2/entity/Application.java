package com.minisal.timelinev2.entity;

import javax.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @Column(name = "app_name",nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    private String owner;

    public Application() {

    }

    public Application(String name, String description, String owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public static class ApplicationBuilder{
        private String name;
        private String description;
        private String owner;

        public ApplicationBuilder name(String name){
            this.name = name;
            return this;
        }

        public ApplicationBuilder description(String description){
            this.description = description;
            return this;
        }

        public ApplicationBuilder owner(String owner){
            this.owner = owner;
            return this;
        }

        public Application build(){
            return new Application(name,description,owner);
        }

    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", description='" + description + '\'' +
                '}';
    }
}
