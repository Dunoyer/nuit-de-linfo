package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    private static String APIKey = "93cf730f-a372-4b74-8df3-e64bf9c7a817";

    public  Result infos() {
        return ok(infos.render());
    }

    public  Result seisme() {
        return ok(seisme.render());
    }

    public Result tsunami() {
        return ok(tsunami.render());
    }

    public  Result attentat() {
        return ok(attentat.render());
    }

    public Result kits() {
        return ok(kits.render());
    }

    public Result index() {
        return ok(index.render("Your new application is ready. Saadi le magnifique ! test de modification Valentino Team-MIA goulou"));
    }
}
