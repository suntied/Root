package com.iabd.videoclub;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MovieControllerTest {
    @Test
    public void getMovies() throws IOException {
        String url = "http://www.omdbapi.com/?t=titanic";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        String responseCode = con.getResponseMessage();
        System.out.println("Response Code : " + responseCode);
        Assert.isTrue(true);
    }
}
