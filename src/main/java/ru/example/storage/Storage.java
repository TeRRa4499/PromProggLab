package ru.example.storage;
import ru.example.model.Grade;
import ru.example.model.Group;
import ru.example.model.Session;
import ru.example.model.Student;
import ru.example.model.Subject;
import ru.example.model.User;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Storage {
    private static final Map<Integer, Group> groups = new HashMap<>();
    private static final Map<Integer, Student> students = new HashMap<>();
    private static final Map<Integer, User> users = new HashMap<>();
    private static final Map<Integer, Subject> subjects = new HashMap<>();
    private static final Map<Integer, Session> sessions = new HashMap<>();
    private static final Map<Integer, Grade> grades = new HashMap<>();

    static {
        // Groups
        createGroup(new Group(null, "ПИ24_1Д", 1, "ФМиИТ"));
        createGroup(new Group(null, "ПИ25_1Д", 2, "ФМиИТ"));
        createGroup(new Group(null, "ПИ23_1Д", 3, "ФМиИТ"));

        // Students
        createStudent(new Student(null, "12154543", "Иванов", "Иван", 1));
        createStudent(new Student(null, "12154542", "Петров", "Пётр", 1));
        createStudent(new Student(null, "12154541", "Сидоров", "Сидор", 2));

        // Users
        createUser(new User(null, "admin", "$2a$10$hash...", "admin", null, "admin@univ.ru", LocalDate.now(), true));
        createUser(new User(null, "ivanov", "$2a$10$hash...", "student", 1, "ivanov@univ.ru", LocalDate.now(), true));

        // Subjects
        createSubject(new Subject(null, "Высшая математика"));
        createSubject(new Subject(null, "Программирование на Java"));
        createSubject(new Subject(null, "Базы данных"));

        // Sessions
        createSession(new Session(null, 1, 2024, "autumn"));
        createSession(new Session(null, 2, 2025, "spring"));

        // Grades
        createGrade(new Grade(null, 1, 1, 1, 5));
        createGrade(new Grade(null, 1, 2, 1, 4));
        createGrade(new Grade(null, 2, 3, 1, 5));
    }

    private Storage() {}

    // ==================== GROUP ====================
    public static Collection<Group> readAllGroups() { return groups.values(); }
    public static Group readGroupById(Integer id) { return groups.get(id); }
    public static void createGroup(Group group) {
        Integer id = nextId(groups.keySet());
        group.setGroupId(id);
        groups.put(id, group);
    }
    public static void updateGroup(Group group) { groups.put(group.getGroupId(), group); }
    public static void deleteGroup(Integer id) {
        groups.remove(id);
        students.values().removeIf(s -> id.equals(s.getGroupId()));
    }

    // ==================== STUDENT ====================
    public static Collection<Student> readAllStudents() { return students.values(); }
    public static Student readStudentById(Integer id) { return students.get(id); }
    public static void createStudent(Student student) {
        Integer id = nextId(students.keySet());
        student.setStudentId(id);
        students.put(id, student);
    }
    public static void updateStudent(Student student) { students.put(student.getStudentId(), student); }
    public static void deleteStudent(Integer id) {
        students.remove(id);
        // ON DELETE SET NULL для Users
        users.values().forEach(u -> { if (id.equals(u.getStudentId())) u.setStudentId(null); });
        // ON DELETE CASCADE для Grades
        grades.values().removeIf(g -> id.equals(g.getStudentId()));
    }

    // ==================== USER ====================
    public static Collection<User> readAllUsers() { return users.values(); }
    public static User readUserById(Integer id) { return users.get(id); }
    public static void createUser(User user) {
        Integer id = nextId(users.keySet());
        user.setUserId(id);
        users.put(id, user);
    }
    public static void updateUser(User user) { users.put(user.getUserId(), user); }
    public static void deleteUser(Integer id) { users.remove(id); }

    // ==================== SUBJECT ====================
    public static Collection<Subject> readAllSubjects() { return subjects.values(); }
    public static Subject readSubjectById(Integer id) { return subjects.get(id); }
    public static void createSubject(Subject subject) {
        Integer id = nextId(subjects.keySet());
        subject.setSubjectId(id);
        subjects.put(id, subject);
    }
    public static void updateSubject(Subject subject) { subjects.put(subject.getSubjectId(), subject); }
    public static void deleteSubject(Integer id) {
        subjects.remove(id);
        grades.values().removeIf(g -> id.equals(g.getSubjectId()));
    }

    // ==================== SESSION ====================
    public static Collection<Session> readAllSessions() { return sessions.values(); }
    public static Session readSessionById(Integer id) { return sessions.get(id); }
    public static void createSession(Session session) {
        Integer id = nextId(sessions.keySet());
        session.setSessionId(id);
        sessions.put(id, session);
    }
    public static void updateSession(Session session) { sessions.put(session.getSessionId(), session); }
    public static void deleteSession(Integer id) {
        sessions.remove(id);
        grades.values().removeIf(g -> id.equals(g.getSessionId()));
    }

    // ==================== GRADE ====================
    public static Collection<Grade> readAllGrades() { return grades.values(); }
    public static Grade readGradeById(Integer id) { return grades.get(id); }
    public static void createGrade(Grade grade) {
        Integer id = nextId(grades.keySet());
        grade.setGradeId(id);
        grades.put(id, grade);
    }
    public static void updateGrade(Grade grade) { grades.put(grade.getGradeId(), grade); }
    public static void deleteGrade(Integer id) { grades.remove(id); }

    // ==================== HELPER ====================
    private static Integer nextId(Set<Integer> ids) {
        Integer id = 1;
        if (!ids.isEmpty()) {
            id += Collections.max(ids);
        }
        return id;
    }
}
