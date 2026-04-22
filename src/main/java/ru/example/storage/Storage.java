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
        createGroup(new Group(null, "ПИ24_1Д", 1, "ФМиИТ"));
        createGroup(new Group(null, "ПИ25_1Д", 2, "ФМиИТ"));
        createGroup(new Group(null, "ПИ23_1Д", 3, "ФМиИТ"));

        createStudent(new Student(null, "12154543", "Иванов", "Иван", 1));
        createStudent(new Student(null, "12154542", "Петров", "Пётр", 1));
        createStudent(new Student(null, "12154541", "Сидоров", "Сидор", 2));
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
