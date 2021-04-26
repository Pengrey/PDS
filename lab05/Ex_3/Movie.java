import java.util.ArrayList;
import java.util.List;

public class Movie {
    private final String title;
    private final int year;
    private final Person director;
    private final Person writer;
    private final String series;
    private final List<Person> cast;
    private final List<Place> locations;
    private final List<String> languages;
    private final List<String> genres;
    private final boolean isTelevision;
    private final boolean isNetflix;
    private final boolean isIndependent;

    public static class Builder{
        //Required parameters
        private final String title;
        private final int year;
        private final Person director;
        //Optional parameters
        private Person writer = null;
        private String series = "";
        private List<Person> cast = new ArrayList<Person>();
        private List<Place> locations = new ArrayList<Place>();
        private List<String> languages = new ArrayList<String>();
        private List<String> genres = new ArrayList<String>();
        private boolean isTelevision = false;
        private boolean isNetflix = false;
        private boolean isIndependent = false;

        public Builder(String movieTitle, int movieYear, Person movieDirector){
            this.title = movieTitle;
            this.year = movieYear;
            this.director = movieDirector;
        }

        public Builder writer(Person val){
            writer = val;
            return this;
        }

        public Builder series(String val){
            series = val;
            return this;
        }

        public Builder cast(List<Person> val){
            cast = val;
            return this;
        }

        public Builder locations(List<Place> val){
            locations = val;
            return this;
        }

        public Builder languages(List<String> val){
            languages = val;
            return this;
        }

        public Builder genres(List<String> val){
            genres = val;
            return this;
        }

        public Builder isTelevision(boolean val){
            isTelevision = val;
            return this;
        }

        public Builder isNetflix(boolean val){
            isNetflix = val;
            return this;
        }

        public Builder isIndependent(boolean val){
            isIndependent = val;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    private Movie(Builder builder){
        title = builder.title;
        year = builder.year;
        director = builder.director;
        writer = builder.writer;
        series = builder.series;
        cast = builder.cast;
        locations = builder.locations;
        languages = builder.languages;
        genres = builder.genres;
        isTelevision = builder.isTelevision;
        isNetflix = builder.isNetflix;
        isIndependent = builder.isIndependent;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + ", Year: " + this.year + ", Director: " + this.director + ", Writer: " + this.writer + ", Series: " + this.series + ", Cast: " + this.cast + ", Locations: " + this.locations + ", Languages: " + this.languages + ", Genres: " + this.genres + ", isTelevision: " + this.isTelevision + ", isNetflix: " + this.isNetflix + ", isIndependent: " + this.isIndependent;
    }
}