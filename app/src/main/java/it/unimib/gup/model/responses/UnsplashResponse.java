package it.unimib.gup.model.responses;

import java.util.HashMap;

public class UnsplashResponse {

    private HashMap<String, String> urls;

    public UnsplashResponse () {}

    public String getRegularImage() {
        return urls.get("regular");
    }
}
