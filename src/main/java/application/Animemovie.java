package application;

import jakarta.persistence.*;

@Entity
public class Animemovie extends Movie{
    @Column(name = "mal_ratings")
    protected float mal_ratings;
    @Column(name = "characters")
    protected String characters;

    public float getMal_ratings() {
        return mal_ratings;
    }

    public void setMal_ratings(float mal_ratings) {
        this.mal_ratings = mal_ratings;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "Animemovie{" +
                "mal_ratings=" + mal_ratings +
                ", characters='" + characters + '\'' +
                ", M_name='" + M_name + '\'' +
                ", directors='" + directors + '\'' +
                ", writers='" + writers + '\'' +
                ", genres='" + genres + '\'' +
                ", imdb_ratings=" + imdb_ratings +
                ", rel_date='" + rel_date + '\'' +
                ", country_or='" + country_or + '\'' +
                '}';
    }
}
