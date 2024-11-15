package com.badlogic.gdx;

import com.badlogic.gdx.net.*;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool.Poolable;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Net {

    interface HttpResponse {

        byte[] getResult();

        String getResultAsString();

        InputStream getResultAsStream();

        HttpStatus getStatus();

        String getHeader(String name);

        Map<String, List<String>> getHeaders();

    }

    interface HttpMethods {

        String HEAD = "HEAD";
        String GET = "GET";
        String POST = "POST";
        String PUT = "PUT";
        String PATCH = "PATCH";
        String DELETE = "DELETE";

    }

    class HttpRequest implements Poolable {

        private String httpMethod;
        private String url;
        private final Map<String, String> headers;
        private int timeOut = 0;

        private String content;
        private InputStream contentStream;
        private long contentLength;

        private boolean followRedirects = true;

        private boolean includeCredentials = false;

        public HttpRequest() {
            this.headers = new HashMap<>();
        }

        public HttpRequest(String httpMethod) {
            this();
            this.httpMethod = httpMethod;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setHeader(String name, String value) {
            headers.put(name, value);
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setContent(InputStream contentStream, long contentLength) {
            this.contentStream = contentStream;
            this.contentLength = contentLength;
        }

        public void setTimeOut(int timeOut) {
            this.timeOut = timeOut;
        }

        public void setFollowRedirects(boolean followRedirects) throws IllegalArgumentException {
            if (followRedirects)
                this.followRedirects = true;
            else
                throw new IllegalArgumentException("Following redirects can't be disabled using the GWT/WebGL backend!");
        }

        public void setIncludeCredentials(boolean includeCredentials) {
            this.includeCredentials = includeCredentials;
        }

        public void setMethod(String httpMethod) {
            this.httpMethod = httpMethod;
        }

        public int getTimeOut() {
            return timeOut;
        }

        public String getMethod() {
            return httpMethod;
        }

        public String getUrl() {
            return url;
        }

        public String getContent() {
            return content;
        }

        public InputStream getContentStream() {
            return contentStream;
        }

        public long getContentLength() {
            return contentLength;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public boolean getFollowRedirects() {
            return followRedirects;
        }

        public boolean getIncludeCredentials() {
            return includeCredentials;
        }

        @Override
        public void reset() {
            httpMethod = null;
            url = null;
            headers.clear();
            timeOut = 0;

            content = null;
            contentStream = null;
            contentLength = 0;

            followRedirects = true;
        }

    }

    interface HttpResponseListener {

        void handleHttpResponse(HttpResponse httpResponse);

        void failed(Throwable t);

        void cancelled();

    }

    void sendHttpRequest(HttpRequest httpRequest, @Null HttpResponseListener httpResponseListener);

    void cancelHttpRequest(HttpRequest httpRequest);

    boolean isHttpRequestPending(HttpRequest httpRequest);

    enum Protocol {
        TCP
    }

    ServerSocket newServerSocket(Protocol protocol, String hostname, int port, ServerSocketHints hints);

    ServerSocket newServerSocket(Protocol protocol, int port, ServerSocketHints hints);

    Socket newClientSocket(Protocol protocol, String host, int port, SocketHints hints);

    boolean openURI(String URI);

}
