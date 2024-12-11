package application;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Tvseries {
    @Id
    @Column(name = "TVS_name")
    protected String TVS_name;
    @Column(name = "directors")
    protected String directors;
    @Column(name = "writers")
    protected String writers;
    @Column(name = "genres")
    protected String genres;
    @Column(name = "imdb_ratings")
    protected float imdb_ratings;
    @Column(name = "rel_date")
    protected String rel_date;
    @Column(name = "country_or")
    protected String country_or;
    @Column(name = "languages")
    protected String languages;
    @Column(name = "prod_company")
    protected String prod_company;
    @Column(name = "runtime")
    protected String runtime;
    @Column(name = "no_episodes")
    protected int no_episodes;


    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public float getImdb_ratings() {
        return imdb_ratings;
    }

    public void setImdb_ratings(float imdb_ratings) {
        this.imdb_ratings = imdb_ratings;
    }

    public String getRel_date() {
        return rel_date;
    }

    public void setRel_date(String rel_date) {
        this.rel_date = rel_date;
    }

    public String getCountry_or() {
        return country_or;
    }

    public void setCountry_or(String country_or) {
        this.country_or = country_or;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getProd_company() {
        return prod_company;
    }

    public void setProd_company(String prod_company) {
        this.prod_company = prod_company;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public int getNo_episodes() {
        return no_episodes;
    }

    public void setNo_episodes(int no_episodes) {
        this.no_episodes = no_episodes;
    }

    public String getTVS_name() {
        return TVS_name;
    }

    public void setTVS_name(String TVS_name) {
        this.TVS_name = TVS_name;
    }
}
