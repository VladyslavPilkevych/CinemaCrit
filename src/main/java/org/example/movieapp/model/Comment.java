/**
 * Model class representing a comment.
 */
package org.example.movieapp.model;

import java.time.LocalDateTime;

public class Comment extends Blank {
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
