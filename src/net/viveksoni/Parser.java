package net.viveksoni;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by vivex on 16/5/15.
 */


/**
 * Parser Class.
 * Will deal with the parsing logic
 */
public class Parser {

    /**
     * Will return outbond links included in the document
     * @param doc
     * @return
     */
    public String[] getLinks(Document doc) {
        Elements links = doc.select("a[href]");
        int i = 0;
        String s[] = new String[links.size()];
        for (Element el : links) {
            String str = el.attr("href");
            //System.out.println("Inside Parser"+str);
            if (!str.equals("") && str.matches("^(http|https|ftp)://.*$"))
                s[i++] = str;
        }
        return s;

    }

    /**
     * Will return the media link included in the page
     * @param doc
     * @return
     */
    public String[] getMedia(Document doc) {
        Elements media = doc.select("[src]");

        int i = 0;
        String s[] = new String[media.size()];
        for (Element el : media) {
            String str = el.attr("href");
            //System.out.println("Inside Parser"+str);
            if (!str.equals("") && str.matches("^(http|https|ftp)://.*$"))
                s[i++] = str;
        }
        return s;
    }

    /**
     * Will Return the title of the passed document
     * @param doc
     * @return
     */
    public String getTitle(Document doc) {
        return doc.title();
    }


    /**
     * Will return the text of the page
     * @param doc
     * @return
     */
    public String getText(Document doc) {
        return doc.body().text();
    }
}
