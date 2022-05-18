package db.student;

import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SessionFactoryUtils sessionFactoryUtils;

    public UserDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public User findById(int id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public User findByName(String name) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session
                    .createQuery("select user from User user where user.name =:name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("select u from User u").getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void save(User user) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateNameById(int id, String newName){
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            user.setName(newName);
            session.getTransaction().commit();
        }
    }



    @Override
    public void deleteById(int id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

//    @Override
//    public void createTable(){
//        try (Session session = sessionFactoryUtils.getSession()){
//            session.beginTransaction();
//            User user = session.createQuery("CREATE TABLE IF NOT EXISTS student1 " +
//                    "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
//                    "name VARCHAR(45) NOT NULL, " +
//                    "mark INT NOT NULL)").(User.class);
//            session.getTransaction().commit();
//        }
//    }
}
