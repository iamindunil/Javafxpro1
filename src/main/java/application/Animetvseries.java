package application;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Animetvseries extends Tvseries{
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
}
