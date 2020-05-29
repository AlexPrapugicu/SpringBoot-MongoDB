package com.example.todoapp.models;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recipes")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Recipe {
    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String title;

    private String description;
    private String imagePath;
    private Ingredient[] ingredients;


    public Recipe(String id, @NotBlank @Size(max = 100) String title, String description, String imagePath, Ingredient[] ingredients, Date createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.ingredients = ingredients;
//        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    private Date createdAt = new Date();

    public Recipe() {

        super();
    }

    public Recipe(String title) {

        this.title = title;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

//    public Date getCreatedAt() {
//
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//
//        this.createdAt = createdAt;
//    }
}