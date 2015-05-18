package net.viveksoni.Entity;

import java.util.Map;

/**
 * Created by vivex on 16/5/15.  8553897316
 *
 * Representing the Entity file of dataset
 */
public class Data {
    private String title;
    private String text;
    private String[] links;

    public String[] getLinks() {
        return links;
    }

    public void setLinks(String[] links) {
        this.links = links;
    }

    private String[] mediaList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public String[] getMediaList() {
        return mediaList;
    }

    public void setMediaList(String[] mediaList) {
        this.mediaList = mediaList;
    }

    /**
     * Active Record Design pattern. Let entity save itself
     * TODO: Write code to insert the data into database
     */
    public void save() {
        System.out.format("Title is %s \n Text is %s \n Number of links in this page is %d \n Number of Media is %d",
                this.title, this.text, this.links.length, this.mediaList.length);

    }
}
