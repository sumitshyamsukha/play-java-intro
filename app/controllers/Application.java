package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.*;
import play.libs.Json;
import play.mvc.*;
import play.db.jpa.*;
import views.html.*;
import models.Person;
import play.data.FormFactory;
import javax.inject.Inject;
import java.util.List;

import static play.libs.Json.*;

public class Application extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index() {
        return ok(index.render());
    }

    public Result test(String text) { return ok(test.render(text)); }

    public Result hello() {
        return ok("hello, world!");
    }

    // Set readOnly to true if method will be running only queries
    @Transactional(readOnly = true)
    public Result getJSON() {
        List<Person> persons = (List<Person>) JPA.em().createQuery("select p from Person p").getResultList();
        return ok(toJson(persons));
    }

    public Result hello_name(String name) {
        return ok("hello, " + name);
    }

    public Result redirect() {
        return redirect("/hello");
    }

    @Transactional
    public Result addPerson() {
        Person person = formFactory.form(Person.class).bindFromRequest().get();
        JPA.em().persist(person);
        return redirect(routes.Application.index());
    }

    @Transactional(readOnly = true)
    public Result getPersons() {
        List<Person> persons = (List<Person>) JPA.em().createQuery("select p from Person p").getResultList();
        return ok(toJson(persons));
    }
}