package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    private static String APIKey = "93cf730f-a372-4b74-8df3-e64bf9c7a817";

    public Result index() {
        return ok(index.render("Your new application is ready. Saadi le magnifique ! test de modification Valentino Team-MIA goulou"));
    }

}
