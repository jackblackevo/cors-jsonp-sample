package idv.jackblackevo;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CredentialedRequestServlet extends HttpServlet {
  private String[] allowOrigins = new String[]{"http://localhost:8080"};
  private int count = 0;

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
        response.setHeader("Access-Control-Allow-Credentials", "true");

        Cookie newCookieToUser = new Cookie("invokedCount", String.valueOf(++count));
        response.addCookie(newCookieToUser);

        responseJOB.add("msg", "Credentialed Request Success!");

        Cookie[] receviedCookies = request.getCookies();
        if (receviedCookies != null) {
          Cookie receviedCookie = receviedCookies[0];
          String receviedCookieName = receviedCookie.getName();
          String receviedCookieValue = receviedCookie.getValue();

          responseJOB.add(
            "receviedUserCookie",
            Json.createObjectBuilder()
              .add("name", receviedCookieName)
              .add("value", receviedCookieValue)
          );
        }

        isAllow = true;
        break;
      }
    }

    if (!isAllow) {
      responseJOB.add("msg", "Credentialed Request Fail!");
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
