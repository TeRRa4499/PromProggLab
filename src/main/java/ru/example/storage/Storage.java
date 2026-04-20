package ru.example.storage;

import ru.example.model.Group;
import ru.example.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Storage {
    private static final Map<Integer, Group> groups = new HashMap<>();
    private static final Map<Integer, Student> students = new HashMap<>();

    static {
        createGroup(new Group(null, "PI-101", 1, "FCS"));
        createGroup(new Group(null, "PI-202", 2, "FCS"));
        createGroup(new Group(null, "IS-301", 3, "FIS"));

        createStudent(new Student(null, "RB-0001", "Ivanov", "Ivan", 1));
        createStudent(new Student(null, "RB-0002", "Petrova", "Anna", 1));
        createStudent(new Student(null, "RB-0003", "Sidorov", "Petr", 2));
    }

    private Storage() {
    }

    public static Collection<Group> readAllGroups() {
        return groups.values();
    }

    public static Group readGroupById(Integer id) {
        return groups.get(id);
    }

    public static void createGroup(Group group) {
        Integer id = nextId(groups.keySet());
        group.setGroupId(id);
        groups.put(id, group);
    }

    public static void updateGroup(Group group) {
        groups.put(group.getGroupId(), group);
    }

    public static void deleteGroup(Integer id) {
        groups.remove(id);
        students.values().removeIf(student -> id.equals(student.getGroupId()));
    }

    public static Collection<Student> readAllStudents() {
        return students.values();
    }

    public static Student readStudentById(Integer id) {
        return students.get(id);
    }

    public static void createStudent(Student student) {
        Integer id = nextId(students.keySet());
        student.setStudentId(id);
        students.put(id, student);
    }

    public static void updateStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public static void deleteStudent(Integer id) {
        students.remove(id);
    }

    private static Integer nextId(Set<Integer> ids) {
        Integer id = 1;
        if (!ids.isEmpty()) {
            id += Collections.max(ids);
        }
        return id;
    }
}
