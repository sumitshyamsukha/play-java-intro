package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by sumit on 5/17/16.
 */
public class Error extends Controller {

    public Result error() { return notFound("<h1> Page Not Found </h1>").as("text/html"); }

    public Result bad() {
        return internalServerError("Oops!");
    }

}
