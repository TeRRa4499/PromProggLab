package ru.example.model;

import java.util.Objects;

public class Student {
    private Integer studentId;
    private String recordBookNumber;
    private String lastName;
    private String firstName;
    private Integer groupId;

    public Student() {
    }

    public Student(Integer studentId, String recordBookNumber, String lastName, String firstName, Integer groupId) {
        this.studentId = studentId;
        this.recordBookNumber = recordBookNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.groupId = groupId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    public void setRecordBookNumber(String recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{"
                + "studentId=" + studentId
                + ", recordBookNumber='" + recordBookNumber + '\''
                + ", lastName='" + lastName + '\''
                + ", firstName='" + firstName + '\''
                + ", groupId=" + groupId
                + '}';
    }
}
