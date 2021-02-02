package org.hua.project;

public class Rating {
    String UserID;
    String MovieID;
    String Rating;
    String Timestamp;

    public Rating(String userID, String movieID, String rating, String timestamp) {
        UserID = userID;
        MovieID = movieID;
        Rating = rating;
        Timestamp = timestamp;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getMovieID() {
        return MovieID;
    }

    public void setMovieID(String movieID) {
        MovieID = movieID;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }
}
