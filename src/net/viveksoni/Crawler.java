package net.viveksoni;

import net.viveksoni.Entity.Data;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by vivex on 14/5/15.
 */

/**
 * List Of TODO
 * TODO: Add Check for duplicate link
 * TODO: Add Support for multithreading
 *
 * Wanted to use Map But Map have snapshot iterator , modifying it dynamically is not possible.
 * But queue solved my problem.
 *
 * Crawler class
 *
 */
public class Crawler {

    Queue<String> linkQueue;
    ContentFactory contentFactory;

    /**
     * Initiate the crawler
     *
     * @param urlToCrawl
     */
    public Crawler(String urlToCrawl) {
        this.linkQueue = new PriorityQueue<String>();
        this.linkQueue.add(urlToCrawl);
    }

    /**
     * Start the crawler
     */
    public void process() {
        Parser p = new Parser();
        Document doc;
       this.contentFactory = new ContentFactory();
        try {
            while (!this.linkQueue.isEmpty()) {
                String url = this.linkQueue.poll(); //Will remove & Return (Pop) the element from the top of the queue
                doc = contentFactory.getContent(url);
                String[] links = p.getLinks(doc);
                int linkCount = links.length;
                for (String link : links) {
                    if (link != null && !link.equals("")) {
                        // Inserting this link to queue
                        this.linkQueue.add(link);
                    }
                }

                // Insert the data
                Data d = new Data();
                d.setText(p.getText(doc));
                d.setLinks(links);
                d.setMediaList(p.getMedia(doc));
                d.setTitle(p.getTitle(doc));
                d.save();

                int queueSize = this.linkQueue.size();
                System.out.println("Queue Size Is " + queueSize);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
