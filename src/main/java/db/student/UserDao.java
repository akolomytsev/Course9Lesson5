package db.student;

import java.util.List;

public interface UserDao {
    User findById(int id);
    User findByName(String name);
    List<User> findAll();
    void save(User user);
    void updateNameById(int id, String newName);
    void deleteById(int id);
//    void createTable();
}
