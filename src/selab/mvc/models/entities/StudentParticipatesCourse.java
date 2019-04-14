package selab.mvc.models.entities;

import selab.mvc.models.Model;

/**
 * Created by HP PC on 4/14/2019.
 * Project MVC
 */
public class StudentParticipatesCourse implements Model {

    private float score;
    private Student student;
    private Course course;

    public StudentParticipatesCourse(Student student, Course course, float score) {
        this.score = score;
        this.student = student;
        this.course = course;
    }

    @Override
    public String getPrimaryKey() {
        return getCourse().getPrimaryKey() + getStudent().getPrimaryKey();
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public float getScore() {
        return score;
    }
}
