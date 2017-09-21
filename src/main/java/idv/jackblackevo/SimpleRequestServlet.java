package idv.jackblackevo;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleRequestServlet extends HttpServlet {
  private String[] allowOrigins = new String[]{"http://localhost:8080"};

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    response.setHeader("Content-Type", "application/json;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    JsonObjectBuilder responseJOB = Json.createObjectBuilder();

    boolean isAllow = false;
    String originHeader = request.getHeader("Origin");
    for (String allowOrigin : allowOrigins) {
      if (allowOrigin.equalsIgnoreCase(originHeader)) {
        response.setHeader("Access-Control-Allow-Origin", allowOrigin);

        responseJOB.add("msg", "Simple Request Success!");

        isAllow = true;
        break;
      }
    }

    if (!isAllow) {
      responseJOB.add("msg", "Simple Request Fail!");
    }

    JsonObject responseJson = responseJOB.build();
    try (
      PrintWriter out = response.getWriter()
    ) {
      out.println(responseJson);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    doGet(request, response);
  }
}
