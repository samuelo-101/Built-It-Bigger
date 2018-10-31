package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.urlfetch.HTTPMethod;

import javax.inject.Named;

import javalibrary.four.gradle.udacity.com.jokelib.JokeMaker;

/** An endpoint class we are exposing */
@Api(
        name = "jokesApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData(new JokeMaker().getJoke());

        return response;
    }

    @ApiMethod(name = "fetchJoke", httpMethod = ApiMethod.HttpMethod.GET)
    public MyBean getJoke() {
        MyBean response = new MyBean();
        response.setData(new JokeMaker().getJoke());

        return response;
    }

}
