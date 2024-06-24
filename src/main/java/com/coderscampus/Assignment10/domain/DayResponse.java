package com.coderscampus.Assignment10.domain;


import java.util.List;
import java.util.Objects;

public class DayResponse {
    private List<Meal> meals;
    private Nutrients nutrients;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    // getters and setters

    public static class Meal {
        private int id;
        private String title;
        private String imageType;
        private String readyInMinutes;
        private int servings;
        private String sourceUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageType() {
            return imageType;
        }

        public void setImageType(String imageType) {
            this.imageType = imageType;
        }

        public String getReadyInMinutes() {
            return readyInMinutes;
        }

        public void setReadyInMinutes(String readyInMinutes) {
            this.readyInMinutes = readyInMinutes;
        }

        public int getServings() {
            return servings;
        }

        public void setServings(int servings) {
            this.servings = servings;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        // getters and setters



        @Override
        public String toString() {
            return "Meal{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", imageType='" + imageType + '\'' +
                    ", readyInMinutes='" + readyInMinutes + '\'' +
                    ", servings=" + servings +
                    ", sourceUrl='" + sourceUrl + '\'' +
                    '}';
        }
    }

    public static class Nutrients {
        private double calories;
        private double protein;
        private double fat;
        private double carbohydrates;

        public double getCalories() {
            return calories;
        }

        public void setCalories(double calories) {
            this.calories = calories;
        }

        public double getProtein() {
            return protein;
        }

        public void setProtein(double protein) {
            this.protein = protein;
        }

        public double getFat() {
            return fat;
        }

        public void setFat(double fat) {
            this.fat = fat;
        }

        public double getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(double carbohydrates) {
            this.carbohydrates = carbohydrates;
        }

        // getters and setters



        @Override
        public String toString() {
            return "Nutrients{" +
                    "calories=" + calories +
                    ", protein=" + protein +
                    ", fat=" + fat +
                    ", carbohydrates=" + carbohydrates +
                    '}';
        }
    }
}
