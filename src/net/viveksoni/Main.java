package net.viveksoni;


public class Main {
    public static void main(String s[]) throws Exception {

        // ContentFactory c = new ContentFactory("us-il.proxymesh.com", 31280, "vivek00x", "testing");
        //ContentFactory c = new ContentFactory();
        // byte[] b = c.getContent("http://ws.viveksoni.net");
        //System.out.println(new String(b));

        System.out.println("Starting");
        // Taking first argument as link
        Crawler c = new Crawler(s[0]);
        try {
            c.process();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(false);
        }
        System.out.println("Done");
    }

}
