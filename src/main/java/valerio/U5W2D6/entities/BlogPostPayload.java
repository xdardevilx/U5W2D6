package valerio.U5W2D6.entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostPayload {
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;
    private int idAuthor;

    public BlogPostPayload(String category, String title, String cover, String content, double readingTime, int idAuthor) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readingTime = readingTime;
        this.idAuthor = idAuthor;
    }

}
