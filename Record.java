/**
 * Created by Martin on 15/12/2017.
 */
public class Record {
    private String rank;
    private String song;
    private String artist;
    private String year;
    private String lyrics;
    private String source;
    private int sentiment;

    public void setSentiment(int sentiment) {
        this.sentiment = sentiment;
    }



    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSentiment() {
        return sentiment;
    }


    public Record(String rank, String song, String artist, String year, String lyrics, String source) {

        this.rank = rank;
        this.song = song;
        this.artist = artist;
        this.year = year;
        this.lyrics = lyrics;
        this.source = source;
        this.sentiment = 0;
    }
}
