package ru.example.model;

import java.util.Objects;

public class Group {
    private Integer groupId;
    private String groupName;
    private Integer course;
    private String faculty;

    public Group() {
    }

    public Group(Integer groupId, String groupName, Integer course, String faculty) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.course = course;
        this.faculty = faculty;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(groupId, group.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }

    @Override
    public String toString() {
        return "Group{"
                + "groupId=" + groupId
                + ", groupName='" + groupName + '\''
                + ", course=" + course
                + ", faculty='" + faculty + '\''
                + '}';
    }
}
