package ru.example.model;
import java.util.Objects;

public class Grade {
    private Integer gradeId;
    private Integer studentId;
    private Integer subjectId;
    private Integer sessionId;
    private Integer score;

    public Grade() {}

    public Grade(Integer gradeId, Integer studentId, Integer subjectId, Integer sessionId, Integer score) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.sessionId = sessionId;
        this.score = score;
    }

    public Integer getGradeId() { return gradeId; }
    public void setGradeId(Integer gradeId) { this.gradeId = gradeId; }

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public Integer getSubjectId() { return subjectId; }
    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }

    public Integer getSessionId() { return sessionId; }
    public void setSessionId(Integer sessionId) { this.sessionId = sessionId; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(gradeId, grade.gradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeId);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", sessionId=" + sessionId +
                ", score=" + score +
                '}';
    }
}