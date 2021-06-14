/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann.model;

import net.openhft.hashing.LongHashFunction;

import org.threeten.bp.ZonedDateTime;

/**
 * The class.
 *
 * @author Rodolfo Bollmann Checura
 */

public final class News {

    /**
     * Unique id.
     */
    private final Long id;
    /**
     * The Title.
     * Restrictions:
     * not null.
     * -size >2
     */
    private final String title;
    /**
     * The Source.
     */
    private final String source;
    /**
     * The Author.
     */
    private final String author;
    /**
     * The url.
     */
    private final String url;
    /**
     * The ulrImage.
     */
    private final String urlImage;
    /**
     * The description.
     */
    private final String description;

    /**
     * The content.
     */
    private final String content;

    /**
     * The Date of Publish.
     */
    private final ZonedDateTime publishedAt;

    /**
     * The Constructor.
     * @param title
     * @param source
     * @param author
     * @param url
     * @param urlImage
     * @param description
     * @param publishedAt
     */
    public News(String title, String source, String author, String url, String urlImage, String description,String content, ZonedDateTime publishedAt) {
        // FIXME: add the hash (title + source + author)

        //Title replace
       this.title = (title != null && title.length() > 0) ? title : "No Title";

        // Source validation
        if(source == null) {
            throw new IllegalArgumentException("Source was null");
        }
        if (source.length() <= 4){
            throw new IllegalArgumentException("Source size <= 4 [" + source + "]");
        }
        this.source = source;

        //Author
        this.author = (author != null && author.length() > 0) ? author : "No Author";

        // Hash xx (title + source + author)
        this.id = LongHashFunction.xx().hashChars(
                this.getTitle() + "|" + this.getSource() + "|" + this.getAuthor()
        );

        this.url = url;
        this.urlImage = urlImage;
        this.description = description;
        this.content = content;

        //publishedAt validation
        if  (publishedAt == null){
            throw new IllegalArgumentException("The publishedAt needed!");
        }
        this.publishedAt = publishedAt;
    }

    /**
     *
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return The source.
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @return The author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return The url.
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return The url image.
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return The content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @return the publishedAt.
     */
    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }
}
