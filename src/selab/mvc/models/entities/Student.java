package selab.mvc.models.entities;

import selab.mvc.models.DataContext;
import selab.mvc.models.Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public float getAverage() {
        // TODO: Calculate and return the average of the points
        return 0;
    }

    public String getCourses() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean firstToken = true;
        for (StudentParticipatesCourse participation :
                DataContext.getInstance().getParticipations().getAll()) {
            if (!participation.getStudent().getPrimaryKey().equals(getPrimaryKey()))
                continue;
            if (!firstToken) {
                stringBuilder.append(", ");

            } else {
                firstToken = false;
            }
            stringBuilder.append(participation.getCourse().toString());
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }
}
