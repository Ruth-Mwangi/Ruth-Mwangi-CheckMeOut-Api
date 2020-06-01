import com.google.gson.Gson;
import dao.Sql2oGoodsDao;
import models.Goods;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.route.HttpMethod;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

//import static spark.Spark.get;
import static spark.Spark.*;

//import static spark.route.HttpMethod.get;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        String connectionString = "jdbc:postgresql://ec2-3-222-150-253.compute-1.amazonaws.com:5432/d6hqhvhegco7o0";
        Sql2o sql2o = new Sql2o(connectionString, "rzgxxgujegmach", "b209e0e27738362cc973be7c6c8b2e7b8667a973d1af3d7aa04fc482245ffbcd");
        Sql2oGoodsDao sql2oGoodsDao = new Sql2oGoodsDao(sql2o);
        Connection conn;
        Gson gson = new Gson();


        staticFileLocation("/public");



        get("/goods/:id", "application/json", (request, response) -> {
            //int id=Integer.parseInt(request.params("id"));
            String code=request.params("id");
            System.out.println(code);
            return gson.toJson(sql2oGoodsDao.getGood(code));
        });
        post("/item/new","application/json",(request, response) -> {
            Goods good=gson.fromJson(request.body(),Goods.class);
            sql2oGoodsDao.addItem(good);
            response.status(201);
            return gson.toJson(good);
        });


        after((request, response) ->{
            response.type("application/json");
        });


    }



}
