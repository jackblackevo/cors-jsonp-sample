package idv.jackblackevo;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JSONPRequestServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    response.setHeader("Content-Type", "application/json;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    String callbackName = request.getParameter("callback");

    JsonObjectBuilder responseJOB = Json.createObjectBuilder();
    responseJOB.add("msg", "Simple Request Success!");

    JsonObject responseJson = responseJOB.build();
    String responseString = callbackName + "(" + responseJson.toString() + ")";
    try (
      PrintWriter out = response.getWriter()
    ) {
      out.println(responseString);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
