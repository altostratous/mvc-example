package selab.mvc.controllers.students;

import org.json.JSONObject;
import selab.mvc.controllers.Controller;
import selab.mvc.models.DataContext;
import selab.mvc.models.DataSet;
import selab.mvc.models.entities.Student;
import selab.mvc.models.entities.StudentParticipatesCourse;
import selab.mvc.views.JsonView;
import selab.mvc.views.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RemoveStudentController extends Controller {

    DataSet<Student> students;
    public RemoveStudentController(DataContext dataContext) {
        super(dataContext);
        students = dataContext.getStudents();
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject input = readJson(body);
        String studentNo = input.getString("studentNo");


        ArrayList<String> toDelete = new ArrayList<>();

        for (StudentParticipatesCourse participation :
                dataContext.getParticipations().getAll()) {
            if (participation.getStudent().getPrimaryKey().equals(studentNo)){
                toDelete.add(participation.getPrimaryKey());
            }
        }
        for (String key :
                toDelete) {
            dataContext.getParticipations().delete(key);
        }

        dataContext.getStudents().delete(studentNo);

        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return new JsonView(new JSONObject(result));
    }

    @Override
    protected View getExceptionView(Exception exception) {
        Map<String, String> result = new HashMap<>();
        result.put("message", exception.getMessage());
        return new JsonView(new JSONObject(result));
    }
}
