package anders;

import java.io.Serializable;

import javax.persistence.*;

import static anders.DAO.session;
import org.apache.log4j.Logger;

@Entity
@Table(name = "anders.partition")
public class Partition implements Serializable{



    private static final Logger logger = Logger.getLogger(Partition.class.getName());

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    public String idstring;
    public String label;
    public String comment;




    public void save() {
        session.save(this);
    }

    public void update() {
        session.update(this);
    }

    public void delete() {
        session.delete(this);
    }

    public static Partition get(int id) {
        return session.get(Partition.class,id);
    }


}

