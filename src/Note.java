import java.io.Serializable;

public class Note implements Serializable {
    private String content;
    private String title;
    private String date;

    /**
     * class constructor
     * @param title note title
     * @param content note content
     * @param date note date as a string
     */
    public Note(String title, String content, String date) {
        this.content = content;
        this.title = title;
        this.date = date;
    }

    /**
     * get note's content
     * @return note's content
     */
    public String getContent() {
        return content;
    }

    /**
     * get note's title
     * @return note's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * get note's date
     * @return note's date (string)
     */
    public String getDate() {
        return date;
    }
}
