package com.bavon.app.utility;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BuildInfo {

    @Produces
    double buildNumber = 2.1;

    @Produces
    List<String> developers(){
        List<String> devs = new ArrayList<>();
        devs.add("Annliza");
        devs.add("Jonathan");
        devs.add("Musili");

        return devs;
    }
}
