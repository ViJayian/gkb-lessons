package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.InMemoryUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.impl.UserServiceImpl;
import org.geektimes.projects.user.sql.DBConnectionManager;
import org.geektimes.projects.user.sql.JNDIManager;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * 保存用户
 *
 * @author ViJay
 * @date 2021/3/2 23:16
 */
@Path("/user")
public class UserController implements PageController {

    @Path("/save")
    @GET
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        System.out.println("user controller...");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.printf("email = [%s] ,password = [%s]", email, password);

//        InMemoryUserRepository userRepository = new InMemoryUserRepository();

        // JNDI 方式
        DBConnectionManager manager = new JNDIManager();
        UserRepository userRepository = new DatabaseUserRepository(manager);
        UserService userService = new UserServiceImpl(userRepository);

        User user = new User();
        user.setName("zhangsan");
        user.setPhoneNumber("123");
        user.setEmail(email);
        user.setPassword(password);
        try {
            userService.register(user);
            return "success.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("注册失败");
        }
    }
}
