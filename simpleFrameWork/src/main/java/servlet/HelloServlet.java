package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName HelloServlet
 * @Description TODO
 * @Author ma.kangkang
 * @Date 2020/10/30 16:45
 **/
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = "我的简易框架。。。";
        request.setAttribute("name",name);
        request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(request,response);

    }

}
