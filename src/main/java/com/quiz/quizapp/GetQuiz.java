import com.google.gson.JsonObject;
import com.quiz.quizapp.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/get-quiz")
public class GetQuiz extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String qid = req.getParameter("qid");

        System.out.println(id + " " + qid);
        try {
            JsonObject data = userDao.getQuiz(id, qid);
            System.out.println(data);
            resp.getWriter().write(data.toString());
            System.out.println("s");
        } catch (Exception e) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("error", "Failed to fetch quiz data: " + e.getMessage());
            System.out.println(errorResponse);
            resp.getWriter().write(errorResponse.toString());
        }
    }
}