package selab.mvc.controllers;

import org.json.JSONObject;
import selab.mvc.models.DataContext;
import selab.mvc.models.entities.Student;
import selab.mvc.models.entities.StudentParticipatesCourse;
import selab.mvc.views.JsonView;
import selab.mvc.views.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddStudentToCourseController extends Controller {

    public AddStudentToCourseController(DataContext dataContext) {
        super(dataContext);
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject input = readJson(body);
        String studentNo = input.getString("studentNo");
        String courseNo = input.getString("courseNo");
        String points = input.getString("points");

        getDataContext().getParticipations().add(new StudentParticipatesCourse(
                getDataContext().getStudents().get(studentNo),
                getDataContext().getCourses().get(courseNo),
                Float.parseFloat(points)
        ));

        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return new JsonView(new JSONObject(result));
    }
}
