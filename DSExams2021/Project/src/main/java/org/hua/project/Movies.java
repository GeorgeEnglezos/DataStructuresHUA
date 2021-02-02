package org.hua.project;

public class Movies {
    String MovieID;
    String Title;
    String Genres;

    public Movies(String movieID, String title, String genres) {
        MovieID = movieID;
        Title = title;
        Genres = genres;
    }

    public String getMovieID() {
        return MovieID;
    }

    public void setMovieID(String movieID) {
        MovieID = movieID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getGenres() {
        return Genres;
    }

    public void setGenres(String genres) {
        Genres = genres;
    }
}


