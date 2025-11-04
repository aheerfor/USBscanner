package anders;
import org.apache.log4j.Logger;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static anders.DAO.session;
@Entity
@Table(name = "anders.directory")
@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(
                        name = "ListByDirid",
                        query = "FROM AHDirectory WHERE parentid = :dirid"
                ),
                @org.hibernate.annotations.NamedQuery(
                        name = "ListRoot",
                        query = "FROM AHDirectory WHERE partitionid = :partid AND gen = 0"
                )
        }
)
public class AHDirectory implements Serializable{

        private static final Logger logger = Logger.getLogger(AHDirectory.class.getName());

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        public int id;

        public int partitionid;
        public int parentid;
        public int gen;

        public String name;
        public String path;
        public long size;
        public long crc;

    @Transient
    public boolean exists; // used for checking for deleted files on filesystem
    //@Transient
    //public List<AHDirectory> daughters = new ArrayList<>();
    public AHDirectory() {
        exists = false;
        //daughters = new ArrayList<>();
    }


    public void save() {
        session.save(this);
    }

    public void update() {
        session.update(this);
    }

    public void delete() {
        session.delete(this);
    }

    public static AHDirectory get(int id) {
        return session.get(AHDirectory.class,id);
    }

    public void deleteShadowDir() {
        List<AHFile> flist = DAO.listFiles(id);
        for (AHFile af: flist) {
            af.delete();
        }
        List<AHDirectory> dlist = DAO.listDirs(id);
        for (AHDirectory ad: dlist) {
            ad.deleteShadowDir();
        }
        this.delete();
    }

    public List<AHDirectory> getDaughters() {
        return DAO.listDirs(id);
    }

    public List<AHFile> getSons() {
        return DAO.listFiles(id);
    }

    public static AHDirectory getRoot(int partitionid) {
        return DAO.getRoot(partitionid);
    }


}


