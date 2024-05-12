package org.example.movieapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movie extends Blank {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//    private String description;
//    private int year;

    @Column(columnDefinition = "BLOB")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL)
    private List<Review> reviews;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }

    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        return totalRating / reviews.size();
    }
}
