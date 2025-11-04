package anders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DAO {
    //static Session session = null; ...
    //public static CriteriaBuilder builder;
    private static final Logger logger = LogManager.getLogger();
    public static final String DRIVER =
            "com.mysql.cj.jdbc.Driver";
    static int depth = 0;
    public static int connection_count;
    static Query<AHDirectory> listByDirid;
    static Query<AHDirectory> listRoot;
    static Query<AHFile> listFilesByDirid;
    public static Connection con ;
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        sessionFactory.close();
    }


    public void skift_database() {
        //return new Configuration().configure("hibernate-server.cfg.xml").buildSessionFactory();
    }

    public static boolean  openConnection() {
        int pos = -1;
        try {
            depth++;
            if (depth < 2) {
                //logger.trace("Openconnection");
                con = session.doReturningWork(connection -> connection); // Hibernate 5+
                //// session.getSessionFactory().getJdbcConnectionAccess().getConnection(); //Hibernate 6
                connection_count++;
                pos = 1;
                if (!session.getTransaction().isActive()) {
                    pos = 2;
                    session.beginTransaction();
                }
                pos = 3;

                //session.get(Coin.class,1);
                //builder = session.getCriteriaBuilder();
                return true;
            }
            //logger.trace("Open connection depth = "+depth);
            return true;
        } catch (Exception e) {
            logger.error("openConnection "+depth+ " "+e);
            logger.error("DAO open: "+e + " pos="+pos);
            //Breaker.breaker();
        }
        logger.error("Couldnt open connection");
        //Popup.showError("Couldnt open connection");
        return false;

    }

    public static boolean  closeConnection() {
        int pos = -1;
        try {
            depth--;
            if (depth < 1) {
                pos = 1;
                session.getTransaction().commit();
                pos = 3;
                con.close();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.trace("DAO close: "+e+ " pos="+pos);
            Breaker.breaker();
        }
        //Popup.showError("Couldnt close connection");
        return false;
    }

    public static void  ConnectionisOpen(int where) {
        try {
            logger.trace("Closed?="+con.isClosed()+ " "+where);
        } catch (Exception e) {
            logger.trace("DAO closed?: "+e);
            Breaker.breaker();
        }

    }

//    private static final SessionFactory sessionFactory = buildSessionFactory();
    static Session session;
    static EntityManager em;
    static {
        session = DAO.getSessionFactory().openSession();
        //CoinTransaction.updateTimes();
        em = session.getEntityManagerFactory().createEntityManager();

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static List<AHDirectory>  listDirs(int parentid) {
        List<AHDirectory> ret = null;
        try {
            if (listByDirid == null)
                listByDirid = session.getNamedQuery("ListByDirid");
            listByDirid.setParameter("dirid", parentid);
             ret = listByDirid.list();

        } catch (Exception e) {
            System.out.println(e);
        }
        return ret;
    }

    public static List<AHFile>  listFiles( int parentid) {
        List<AHFile> ret = null;
        try {
            if (listFilesByDirid == null)
                listFilesByDirid = session.getNamedQuery("ListFilesByDirid");
            listFilesByDirid.setParameter("dirid", parentid);
            ret = listFilesByDirid.list();

        } catch (Exception e) {
            System.out.println(e);
        }
        return ret;
    }

    public static AHDirectory getRoot(int partitionid) {
        listRoot = session.getNamedQuery("ListRoot");
        listRoot.setParameter("partid", partitionid);
        List<AHDirectory> list = listRoot.list();
        if (list.isEmpty())
            return null;
        if (list.size() > 1) {
            logger.error("Multiple roots "+partitionid);
            return null;
        }
        return list.get(0);
    }
}