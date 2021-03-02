package org.geektimes.projects.user.web.controller;

import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 用户注册
 *
 * @author ViJay
 * @date 2021/3/2 23:03
 */
@Path("")
public class RegisterController implements PageController {

    @GET
    @POST
    @Path("/register")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        System.out.println("register....");
        return "login-form.jsp";
    }
}
