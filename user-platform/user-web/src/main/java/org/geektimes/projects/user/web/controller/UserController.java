package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.web.mvc.FrontControllerServlet;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * 保存用户
 * 使用 {@link FrontControllerServlet#replaceControllerByContainerController()}
 * 替换ServiceLoader加载的Controller，
 * 此时的Controller为JNDI容器中加载的单例 bean/UserController singleton Controller
 *
 * @author ViJay
 * @date 2021/3/2 23:16
 */

@Path("/user")
public class UserController implements PageController {

    @Resource(name = "bean/UserService")
    private UserService userService;

    @Path("/save")
    @GET
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        System.out.println("user controller...");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.printf("email = [%s] ,password = [%s]", email, password);

//        InMemoryUserRepository userRepository = new InMemoryUserRepository();
//        UserRepository userRepository = new DatabaseUserRepository();
//        UserService userService = new UserServiceImpl(userRepository);

        User user = new User();
        user.setName("zhangsan");
        user.setPhoneNumber("13111111111");
        user.setEmail(email);
        user.setPassword(password);
        user.setId(100L);
        try {
//            ComponentContext context = ComponentContext.getInstance();
//            UserService userService = context.getBean("bean/UserService");
            //userService是注入到 bean/UserController里面的，
            // 因此，该Controller必须是容器中的Controller，替换FrontControllerServlet中加载的
            this.userService.register(user);
            return "success.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("注册失败");
        }
    }
}
