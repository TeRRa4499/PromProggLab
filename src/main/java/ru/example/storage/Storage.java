package ru.example.storage;

import ru.example.model.Grade;
import ru.example.model.Group;
import ru.example.model.Session;
import ru.example.model.Student;
import ru.example.model.Subject;
import ru.example.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public final class Storage {
    private static String jdbcDriver = null;
    private static String jdbcUrl = null;
    private static String jdbcUser = null;
    private static String jdbcPassword = null;

    public static void init(String jdbcDriver,
                            String jdbcUrl,
                            String jdbcUser,
                            String jdbcPassword) throws ClassNotFoundException {
        Class.forName(jdbcDriver);
        Storage.jdbcDriver = jdbcDriver;
        Storage.jdbcUrl = jdbcUrl;
        Storage.jdbcUser = jdbcUser;
        Storage.jdbcPassword = jdbcPassword;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    // ==================== GROUP ====================
    public static Collection<Group> readAllGroups() throws SQLException {
        String sql = "SELECT `GroupID`, `GroupName`, `Course`, `Faculty` FROM `Groups`";
        Connection c = null; Statement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.createStatement(); r = s.executeQuery(sql);
            Collection<Group> list = new ArrayList<>();
            while (r.next()) {
                Group g = new Group();
                g.setGroupId(r.getInt("GroupID"));
                g.setGroupName(r.getString("GroupName"));
                g.setCourse(r.getInt("Course"));
                g.setFaculty(r.getString("Faculty"));
                list.add(g);
            }
            return list;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static Group readGroupById(Integer id) throws SQLException {
        String sql = "SELECT `GroupName`, `Course`, `Faculty` FROM `Groups` WHERE `GroupID` = ?";
        Connection c = null; PreparedStatement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, id); r = s.executeQuery();
            Group g = null;
            if (r.next()) {
                g = new Group();
                g.setGroupId(id);
                g.setGroupName(r.getString("GroupName"));
                g.setCourse(r.getInt("Course"));
                g.setFaculty(r.getString("Faculty"));
            }
            return g;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void createGroup(Group group) throws SQLException {
        String sql = "INSERT INTO `Groups` (`GroupName`, `Course`, `Faculty`) VALUES (?, ?, ?)";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setString(1, group.getGroupName());
            s.setInt(2, group.getCourse());
            s.setString(3, group.getFaculty());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void updateGroup(Group group) throws SQLException {
        String sql = "UPDATE `Groups` SET `GroupName` = ?, `Course` = ?, `Faculty` = ? WHERE `GroupID` = ?";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setString(1, group.getGroupName());
            s.setInt(2, group.getCourse());
            s.setString(3, group.getFaculty());
            s.setInt(4, group.getGroupId());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void deleteGroup(Integer id) throws SQLException {
        Connection c = null; PreparedStatement sStudents = null; PreparedStatement sGroup = null;
        try {
            c = getConnection();
            // Cascade: clear group assignment from students
            sStudents = c.prepareStatement("UPDATE `Student` SET `GroupID` = NULL WHERE `GroupID` = ?");
            sStudents.setInt(1, id);
            sStudents.executeUpdate();
            // Delete group
            sGroup = c.prepareStatement("DELETE FROM `Groups` WHERE `GroupID` = ?");
            sGroup.setInt(1, id);
            sGroup.executeUpdate();
        } finally {
            try { sStudents.close(); } catch(NullPointerException | SQLException e) {}
            try { sGroup.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    // ==================== STUDENT ====================
    public static Collection<Student> readAllStudents() throws SQLException {
        String sql = "SELECT `StudentID`, `RecordBookNumber`, `LastName`, `FirstName`, `GroupID` FROM `Student`";
        Connection c = null; Statement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.createStatement(); r = s.executeQuery(sql);
            Collection<Student> list = new ArrayList<>();
            while (r.next()) {
                Student st = new Student();
                st.setStudentId(r.getInt("StudentID"));
                st.setRecordBookNumber(r.getString("RecordBookNumber"));
                st.setLastName(r.getString("LastName"));
                st.setFirstName(r.getString("FirstName"));
                st.setGroupId(r.getObject("GroupID", Integer.class));
                list.add(st);
            }
            return list;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static Student readStudentById(Integer id) throws SQLException {
        String sql = "SELECT `RecordBookNumber`, `LastName`, `FirstName`, `GroupID` FROM `Student` WHERE `StudentID` = ?";
        Connection c = null; PreparedStatement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, id); r = s.executeQuery();
            Student st = null;
            if (r.next()) {
                st = new Student();
                st.setStudentId(id);
                st.setRecordBookNumber(r.getString("RecordBookNumber"));
                st.setLastName(r.getString("LastName"));
                st.setFirstName(r.getString("FirstName"));
                st.setGroupId(r.getObject("GroupID", Integer.class));
            }
            return st;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void createStudent(Student student) throws SQLException {
        String sql = "INSERT INTO `Student` (`RecordBookNumber`, `LastName`, `FirstName`, `GroupID`) VALUES (?, ?, ?, ?)";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setString(1, student.getRecordBookNumber());
            s.setString(2, student.getLastName());
            s.setString(3, student.getFirstName());
            s.setObject(4, student.getGroupId());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE `Student` SET `RecordBookNumber` = ?, `LastName` = ?, `FirstName` = ?, `GroupID` = ? WHERE `StudentID` = ?";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setString(1, student.getRecordBookNumber());
            s.setString(2, student.getLastName());
            s.setString(3, student.getFirstName());
            s.setObject(4, student.getGroupId());
            s.setInt(5, student.getStudentId());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void deleteStudent(Integer id) throws SQLException {
        Connection c = null; PreparedStatement sUsers = null; PreparedStatement sGrades = null; PreparedStatement sStudent = null;
        try {
            c = getConnection();
            // Cascade: SET NULL in Users
            sUsers = c.prepareStatement("UPDATE `Users` SET `StudentID` = NULL WHERE `StudentID` = ?");
            sUsers.setInt(1, id);
            sUsers.executeUpdate();
            // Cascade: DELETE grades
            sGrades = c.prepareStatement("DELETE FROM `Grade` WHERE `StudentID` = ?");
            sGrades.setInt(1, id);
            sGrades.executeUpdate();
            // Delete student
            sStudent = c.prepareStatement("DELETE FROM `Student` WHERE `StudentID` = ?");
            sStudent.setInt(1, id);
            sStudent.executeUpdate();
        } finally {
            try { sUsers.close(); } catch(NullPointerException | SQLException e) {}
            try { sGrades.close(); } catch(NullPointerException | SQLException e) {}
            try { sStudent.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    // ==================== USER ====================
    public static Collection<User> readAllUsers() throws SQLException {
        String sql = "SELECT `UserID`, `Login`, `PasswordHash`, `Role`, `StudentID`, `Email`, `CreatedDate`, `IsActive` FROM `Users`";
        Connection c = null; Statement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.createStatement(); r = s.executeQuery(sql);
            Collection<User> list = new ArrayList<>();
            while (r.next()) {
                User u = new User();
                u.setUserId(r.getInt("UserID"));
                u.setLogin(r.getString("Login"));
                u.setPasswordHash(r.getString("PasswordHash"));
                u.setRole(r.getString("Role"));
                u.setStudentId(r.getObject("StudentID", Integer.class));
                u.setEmail(r.getString("Email"));
                u.setCreatedDate(r.getObject("CreatedDate", LocalDate.class));
                u.setIsActive(r.getBoolean("IsActive"));
                list.add(u);
            }
            return list;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static User readUserById(Integer id) throws SQLException {
        String sql = "SELECT `Login`, `PasswordHash`, `Role`, `StudentID`, `Email`, `CreatedDate`, `IsActive` FROM `Users` WHERE `UserID` = ?";
        Connection c = null; PreparedStatement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, id); r = s.executeQuery();
            User u = null;
            if (r.next()) {
                u = new User();
                u.setUserId(id);
                u.setLogin(r.getString("Login"));
                u.setPasswordHash(r.getString("PasswordHash"));
                u.setRole(r.getString("Role"));
                u.setStudentId(r.getObject("StudentID", Integer.class));
                u.setEmail(r.getString("Email"));
                u.setCreatedDate(r.getObject("CreatedDate", LocalDate.class));
                u.setIsActive(r.getBoolean("IsActive"));
            }
            return u;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void createUser(User user) throws SQLException {
        String sql = "INSERT INTO `Users` (`Login`, `PasswordHash`, `Role`, `StudentID`, `Email`, `CreatedDate`, `IsActive`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPasswordHash());
            s.setString(3, user.getRole());
            s.setObject(4, user.getStudentId());
            s.setString(5, user.getEmail());
            s.setObject(6, user.getCreatedDate());
            s.setBoolean(7, user.getIsActive());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void updateUser(User user) throws SQLException {
        String sql = "UPDATE `Users` SET `Login` = ?, `PasswordHash` = ?, `Role` = ?, `StudentID` = ?, `Email` = ?, `CreatedDate` = ?, `IsActive` = ? WHERE `UserID` = ?";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPasswordHash());
            s.setString(3, user.getRole());
            s.setObject(4, user.getStudentId());
            s.setString(5, user.getEmail());
            s.setObject(6, user.getCreatedDate());
            s.setBoolean(7, user.getIsActive());
            s.setInt(8, user.getUserId());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void deleteUser(Integer id) throws SQLException {
        String sql = "DELETE FROM `Users` WHERE `UserID` = ?";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    // ==================== SUBJECT ====================
    public static Collection<Subject> readAllSubjects() throws SQLException {
        String sql = "SELECT `SubjectID`, `SubjectName` FROM `Subject`";
        Connection c = null; Statement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.createStatement(); r = s.executeQuery(sql);
            Collection<Subject> list = new ArrayList<>();
            while (r.next()) {
                Subject sub = new Subject();
                sub.setSubjectId(r.getInt("SubjectID"));
                sub.setSubjectName(r.getString("SubjectName"));
                list.add(sub);
            }
            return list;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static Subject readSubjectById(Integer id) throws SQLException {
        String sql = "SELECT `SubjectName` FROM `Subject` WHERE `SubjectID` = ?";
        Connection c = null; PreparedStatement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, id); r = s.executeQuery();
            Subject sub = null;
            if (r.next()) {
                sub = new Subject();
                sub.setSubjectId(id);
                sub.setSubjectName(r.getString("SubjectName"));
            }
            return sub;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void createSubject(Subject subject) throws SQLException {
        String sql = "INSERT INTO `Subject` (`SubjectName`) VALUES (?)";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setString(1, subject.getSubjectName());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void updateSubject(Subject subject) throws SQLException {
        String sql = "UPDATE `Subject` SET `SubjectName` = ? WHERE `SubjectID` = ?";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setString(1, subject.getSubjectName());
            s.setInt(2, subject.getSubjectId());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void deleteSubject(Integer id) throws SQLException {
        Connection c = null; PreparedStatement sGrades = null; PreparedStatement sSubject = null;
        try {
            c = getConnection();
            // Cascade: delete related grades
            sGrades = c.prepareStatement("DELETE FROM `Grade` WHERE `SubjectID` = ?");
            sGrades.setInt(1, id);
            sGrades.executeUpdate();
            // Delete subject
            sSubject = c.prepareStatement("DELETE FROM `Subject` WHERE `SubjectID` = ?");
            sSubject.setInt(1, id);
            sSubject.executeUpdate();
        } finally {
            try { sGrades.close(); } catch(NullPointerException | SQLException e) {}
            try { sSubject.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    // ==================== SESSION ====================
    public static Collection<Session> readAllSessions() throws SQLException {
        String sql = "SELECT `SessionID`, `SessionNumber`, `Year`, `Semester` FROM `Session`";
        Connection c = null; Statement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.createStatement(); r = s.executeQuery(sql);
            Collection<Session> list = new ArrayList<>();
            while (r.next()) {
                Session ses = new Session();
                ses.setSessionId(r.getInt("SessionID"));
                ses.setSessionNumber(r.getInt("SessionNumber"));
                ses.setYear(r.getInt("Year"));
                ses.setSemester(r.getString("Semester"));
                list.add(ses);
            }
            return list;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static Session readSessionById(Integer id) throws SQLException {
        String sql = "SELECT `SessionNumber`, `Year`, `Semester` FROM `Session` WHERE `SessionID` = ?";
        Connection c = null; PreparedStatement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, id); r = s.executeQuery();
            Session ses = null;
            if (r.next()) {
                ses = new Session();
                ses.setSessionId(id);
                ses.setSessionNumber(r.getInt("SessionNumber"));
                ses.setYear(r.getInt("Year"));
                ses.setSemester(r.getString("Semester"));
            }
            return ses;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void createSession(Session session) throws SQLException {
        String sql = "INSERT INTO `Session` (`SessionNumber`, `Year`, `Semester`) VALUES (?, ?, ?)";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, session.getSessionNumber());
            s.setInt(2, session.getYear());
            s.setString(3, session.getSemester());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void updateSession(Session session) throws SQLException {
        String sql = "UPDATE `Session` SET `SessionNumber` = ?, `Year` = ?, `Semester` = ? WHERE `SessionID` = ?";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, session.getSessionNumber());
            s.setInt(2, session.getYear());
            s.setString(3, session.getSemester());
            s.setInt(4, session.getSessionId());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void deleteSession(Integer id) throws SQLException {
        Connection c = null; PreparedStatement sGrades = null; PreparedStatement sSession = null;
        try {
            c = getConnection();
            // Cascade: delete related grades
            sGrades = c.prepareStatement("DELETE FROM `Grade` WHERE `SessionID` = ?");
            sGrades.setInt(1, id);
            sGrades.executeUpdate();
            // Delete session
            sSession = c.prepareStatement("DELETE FROM `Session` WHERE `SessionID` = ?");
            sSession.setInt(1, id);
            sSession.executeUpdate();
        } finally {
            try { sGrades.close(); } catch(NullPointerException | SQLException e) {}
            try { sSession.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    // ==================== GRADE ====================
    public static Collection<Grade> readAllGrades() throws SQLException {
        String sql = "SELECT `GradeID`, `StudentID`, `SubjectID`, `SessionID`, `Score` FROM `Grade`";
        Connection c = null; Statement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.createStatement(); r = s.executeQuery(sql);
            Collection<Grade> list = new ArrayList<>();
            while (r.next()) {
                Grade gr = new Grade();
                gr.setGradeId(r.getInt("GradeID"));
                gr.setStudentId(r.getInt("StudentID"));
                gr.setSubjectId(r.getInt("SubjectID"));
                gr.setSessionId(r.getInt("SessionID"));
                gr.setScore(r.getInt("Score"));
                list.add(gr);
            }
            return list;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static Grade readGradeById(Integer id) throws SQLException {
        String sql = "SELECT `StudentID`, `SubjectID`, `SessionID`, `Score` FROM `Grade` WHERE `GradeID` = ?";
        Connection c = null; PreparedStatement s = null; ResultSet r = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, id); r = s.executeQuery();
            Grade gr = null;
            if (r.next()) {
                gr = new Grade();
                gr.setGradeId(id);
                gr.setStudentId(r.getInt("StudentID"));
                gr.setSubjectId(r.getInt("SubjectID"));
                gr.setSessionId(r.getInt("SessionID"));
                gr.setScore(r.getInt("Score"));
            }
            return gr;
        } finally {
            try { r.close(); } catch(NullPointerException | SQLException e) {}
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void createGrade(Grade grade) throws SQLException {
        String sql = "INSERT INTO `Grade` (`StudentID`, `SubjectID`, `SessionID`, `Score`) VALUES (?, ?, ?, ?)";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, grade.getStudentId());
            s.setInt(2, grade.getSubjectId());
            s.setInt(3, grade.getSessionId());
            s.setInt(4, grade.getScore());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void updateGrade(Grade grade) throws SQLException {
        String sql = "UPDATE `Grade` SET `StudentID` = ?, `SubjectID` = ?, `SessionID` = ?, `Score` = ? WHERE `GradeID` = ?";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, grade.getStudentId());
            s.setInt(2, grade.getSubjectId());
            s.setInt(3, grade.getSessionId());
            s.setInt(4, grade.getScore());
            s.setInt(5, grade.getGradeId());
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void deleteGrade(Integer id) throws SQLException {
        String sql = "DELETE FROM `Grade` WHERE `GradeID` = ?";
        Connection c = null; PreparedStatement s = null;
        try {
            c = getConnection(); s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
        } finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
            try { c.close(); } catch(NullPointerException | SQLException e) {}
        }
    }
}