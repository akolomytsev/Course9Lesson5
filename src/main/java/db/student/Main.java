package db.student;


public class Main {
    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try{

            UserDao userDao = new UserDaoImpl(sessionFactoryUtils);
//            for (int i = 0; i < 1000; i++) {
//                userDao.save(new User("Markiz", i));
//
//
//            }
            userDao.updateNameById(5,"Alexander");
            //userDao.deleteById(7);

            System.out.println(userDao.findAll());
            System.out.println(userDao.findByName("MarkII"));


        } catch (Exception e){
            e.printStackTrace();
        }finally {
            sessionFactoryUtils.shutdown();
        }
    }
}

