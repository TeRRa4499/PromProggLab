package ru.example.model;
import java.util.Objects;

public class Session {
    private Integer sessionId;
    private Integer sessionNumber;
    private Integer year;
    private String semester;

    public Session() {}

    public Session(Integer sessionId, Integer sessionNumber, Integer year, String semester) {
        this.sessionId = sessionId;
        this.sessionNumber = sessionNumber;
        this.year = year;
        this.semester = semester;
    }

    public Integer getSessionId() { return sessionId; }
    public void setSessionId(Integer sessionId) { this.sessionId = sessionId; }

    public Integer getSessionNumber() { return sessionNumber; }
    public void setSessionNumber(Integer sessionNumber) { this.sessionNumber = sessionNumber; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(sessionId, session.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId);
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", sessionNumber=" + sessionNumber +
                ", year=" + year +
                ", semester='" + semester + '\'' +
                '}';
    }
}