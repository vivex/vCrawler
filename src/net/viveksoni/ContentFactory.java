package net.viveksoni;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by vivex on 16/5/15.
 *
 * Content factory will deal with HTTPConnection to text data conversion
 *
 */
public class ContentFactory {

    String proxyHost;
    int proxyPort;
    String proxyUser;
    String proxyPassword;
    HttpClient client;

    /**
     * Basic Constructor(Without proxy)
     */
    public ContentFactory() {

    }

    /**
     * Proxy Support  Constructor
     *
     * @param proxyHost
     * @param proxyPort
     * @param proxyUser
     * @param proxyPassword
     */
    public ContentFactory(String proxyHost, int proxyPort, String proxyUser, String proxyPassword) {
        this.proxyHost = proxyHost;
        this.proxyPassword = proxyPassword;
        this.proxyPort = proxyPort;
        this.proxyUser = proxyUser;

    }

    /**
     * Will return the content of passed url
     *
     * @param url
     * @return Document
     */
    public Document getContent(String url) {
        //System.out.println("Inside get content");
        try {
            this.getClient();
            HttpMethod method = new GetMethod(url);
            int statusCode = this.client.executeMethod(method);
            @SuppressWarnings("all")
            byte[] responseBody = method.getResponseBody();
            String contentStr = new String(responseBody);
            Document doc = Jsoup.parse(contentStr);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR URL WAS" + url);
        }
        return null;
    }

    /**
     * Making it singlton so that for all request only oneinstance of this will be served
     *
     * @return HttpClient object
     */
    protected HttpClient getClient() {
        if (true || this.client == null) {
            HttpClient client = new HttpClient();
            if (this.proxyHost != null && !this.proxyHost.equals("") && this.proxyPort != 0) {
                client.getHostConfiguration().setProxy(this.proxyHost, this.proxyPort);
            }
            if (this.proxyUser != null && !this.proxyUser.equals("") && !this.proxyPassword.equals("")) {
                HttpState state = new HttpState();
                state.setProxyCredentials(null, null, new UsernamePasswordCredentials(this.proxyUser, this.proxyPassword));
                client.setState(state);
            }
            this.client = client;
        }
        return this.client;
    }
}
