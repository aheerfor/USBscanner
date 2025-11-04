package anders;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;

import static anders.DAO.session;

    @Entity
    @Table(name = "anders.file")

    @org.hibernate.annotations.NamedQueries(
            {
                    @org.hibernate.annotations.NamedQuery(
                            name = "ListFilesByDirid",
                            query = "FROM AHFile WHERE dirid = :dirid"
                    )
            }
    )


    public class AHFile implements Serializable {

        private static final Logger logger = Logger.getLogger(AHFile.class.getName());

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        public int id;
        public int dirid;
        public long size;
        public int partitionid;


        public String name;
        public long crc;


        @Transient
        boolean exists; // used for checking for deleted files on filesystem

        public AHFile() {
            exists = false;
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

        public static AHFile get(int id) {
            return session.get(AHFile.class,id);
        }


    }



