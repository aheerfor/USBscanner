package anders;

import org.apache.log4j.Logger;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    long minspeed = 1000000L;
    long maxspeed = 0L;
    int files = 0;
    int maxlen = 0;
    int dirs = 0;
    int maxfilelength = 0;
    int maxfilenamelength = 0;
    List<String> renamelist = new ArrayList<>();


    public static void main(String[] args) {
        // write your code here
        Main main = new Main();
        main.scanner();
    }


    public boolean scanDesktop() {
        boolean ok = true;
        String dirname = "/home/anders";
        int partitionid = 1;
        //ok = scanDirectory(new File(dirname), 0, dirname,null, partitionid,null);
        DAO.openConnection();
        crc_dir(DAO.getRoot(partitionid),0);
        DAO.closeConnection();
        return ok;
    }

    public boolean scanBackupUSB() {
        boolean ok = true;
        String dirname = "/media/anders/SAM";
        int partitionid = 2;
        //ok = scanDirectory(new File(dirname), 0, dirname,null, partitionid,null);
        crc_dir(DAO.getRoot(partitionid),0);
        return ok;
    }

    public boolean scanSynology() {
        boolean ok = true;
        String dirname = "/mnt/nfs_share/aheerfor";
        int partitionid = 3;
        //delete_empty_dirs(new File(dirname), 0);
        //ok = scanDirectory(new File(dirname), 0, dirname,null, partitionid,null);
        DAO.openConnection();
        crc_dir(DAO.getRoot(partitionid),0);
        DAO.closeConnection();
        return ok;
    }

    public boolean scanSeagate() {
        boolean ok = true;
        String dirname = "/media/anders/Seagate Expansion Drive";
        int partitionid = 4;
        //delete_empty_dirs(new File(dirname), 0);
        //rename(new File(dirname),0);
        //ok = scanDirectory(new File(dirname), 0, dirname,null, partitionid,null);
        DAO.openConnection();
        crc_dir(DAO.getRoot(partitionid),0);
        DAO.closeConnection();
        return ok;
    }

    public boolean scan2TB() {
        boolean ok = true;
        String dirname = "/media/anders/2TB";
        int partitionid = 5;
        //delete_empty_dirs(new File(dirname), 0);
        //rename(new File(dirname),0);
        //ok = scanDirectory(new File(dirname), 0, dirname,null, partitionid,null);
        DAO.openConnection();
        crc_dir(DAO.getRoot(partitionid),0);
        DAO.closeConnection();
        return ok;
    }

    public boolean scanSeagate2() {
        boolean ok = true;
        String dirname = "/media/anders/Seagate Expansion Drive";
        int partitionid = 6;
        //delete_empty_dirs(new File(dirname), 0);
        //rename(new File(dirname),0);
        //ok = scanDirectory(new File(dirname), 0, dirname,null, partitionid,null);
        DAO.openConnection();
        crc_dir(DAO.getRoot(partitionid),0);
        DAO.closeConnection();
        return ok;
    }

    public boolean scanKingston() {
        boolean ok = true;
        String dirname = "/media/anders/ExtUSB_Kingston";
        int partitionid = 7;
        delete_empty_dirs(new File(dirname), 0);
        rename(new File(dirname),0);
        ok = scanDirectory(new File(dirname), 0, dirname,null, partitionid,null);
        crc_dir(DAO.getRoot(partitionid),0);
        return ok;
    }

    public boolean scanWD160() {
        boolean ok = true;
        String dirname = "/media/anders/WD160";
        int partitionid = 8;
        delete_empty_dirs(new File(dirname), 0);
        rename(new File(dirname),0);
        ok = scanDirectory(new File(dirname), 0, dirname,null, partitionid,null);
        crc_dir(DAO.getRoot(partitionid),0);
        return ok;
    }

    public boolean scanDiskA() {
        boolean ok = true;
        String dirname = "/media/anders/A";
        int partitionid = 9;
        delete_empty_dirs(new File(dirname), 0);
        rename(new File(dirname),0);
        ok = scanDirectory(new File(dirname), 0, dirname,null, partitionid,null);
        crc_dir(DAO.getRoot(partitionid),0);
        return ok;
    }


    public void scanner() {
        scanDiskA();
// do chmod -R a+w *
        //scanDesktop();
//        scanSynology();
        //scanSeagate2();
        //scanBackupUSB();
        //scanSeagate();
        //scanKingston();
        //scanWD160();
        logger.info("dirs  =" + dirs);
        logger.info("files =" + files);
        logger.info("maxdirnamelen =" + maxlen);
        logger.info("maxfilelength " + maxfilelength);
        logger.info("maxfilenamelength " + maxfilenamelength);
        for (String x: renamelist) {
            logger.error("RENAME FAILED  =" + x);
        }
    }

    public void printtab(int gen) {
        for (int i = 0; i < gen; i++)
            System.out.print("  ");
    }

    public boolean ignore_dir(File dir) {
        if (!dir.isDirectory()) {
            logger.error("NOT a directory "+dir.getAbsolutePath());
            return true;
        }
        String dirname = dir.getName();
        if (dirname.equals("etc")) return true;
        if (dirname.equals("tinyb-master")) return true;
        if (dirname.equals("FoxletRipple-2.6.0-linux-x64")) return true;
        if (dirname.equals("PoltergeistLinux_2.7.4")) return true;
        if (dirname.equals("Poltergeist_2.8.4")) return true;
        if (dirname.equals("Poltergeist_2.8.6")) return true;
        if (dirname.equals("Poltergeist_2.8.8")) return true;

        if (dirname.equals("swiftrng-master")) return true;
        if (dirname.equals("$RECYCLE.BIN")) return true;
        if (dirname.equals("RECYCLE.BIN")) return true;
        if (dirname.equals("System Volume Information")) return true;
        if (dirname.equals("Android")) return true;
        if (dirname.equals("snap")) return true;
        if (dirname.equals("IdeaProjects")) return true;
        if (dirname.equals("Downloads")) return true;
        if (dirname.equals("tmp")) return true;
        if (dirname.equals("pCloudDrive")) return true;
        if (dirname.equals(".m2")) return true;
        if (dirname.equals(".java")) return true;
        if (dirname.equals(".jdks")) return true;
        if (dirname.equals(".config")) return true;
        if (dirname.equals("Javascript")) return true;
        if (dirname.equals("Resources")) return true;
        if (dirname.startsWith(".")) return true;
        return false;
    }

    public boolean scanDirectory(File dir, int gen, String orgpath, AHDirectory parentdir, int partitionid, List<AHDirectory> sisters) {
        boolean ok;
        int fileno = 0; // for finding empty directories
        if (gen == 0) {
            logger.info("BOOT "+dir.getAbsolutePath());
            sisters = new ArrayList<>();
            parentdir = new AHDirectory();
            AHDirectory sister = AHDirectory.getRoot(partitionid);
            if (sister != null)
                sisters.add(sister);
        }
        if (ignore_dir(dir)) {
            logger.info("Ignoring "+dir.getAbsolutePath());
            return true;
        }
        File[] flist = dir.listFiles();
        if (flist == null) {
            logger.error("listFiles failed " + dir.getAbsolutePath());
            return false;
        }

        try {
            String dirname = dir.getName();
            dirs++;
            String path = dir.getPath();
            if (gen == 0) {
                path = "/";
            } else if (!path.startsWith(orgpath) && gen > 0) {
                logger.error("Bad path "+path);
                return false;
            } else {
                path = path.substring(orgpath.length() + 1);
            }
            if (maxlen < path.length()) {
                maxlen = path.length();
            }
            if (path.length() > 256) {
                path = path.substring(0, 256);
            }
            AHDirectory inst = null;
            if (sisters != null)
                for (AHDirectory d : sisters) {
                    if (d.name.equals(dirname)) {
                        inst = d;
                        d.exists = true;
                        break;
                    }
                }
            DAO.openConnection();
            if (inst == null) {
                inst = new AHDirectory();
                inst.gen = gen;
                inst.path = path;
                inst.name = dirname;
                if (parentdir != null)
                    inst.parentid = parentdir.id;
                else
                    inst.parentid = 0;
                inst.partitionid = partitionid;
                inst.save();
                logger.info("saved dir to DB "+dir.getAbsolutePath());
            }
            DAO.closeConnection();


            //printtab(gen);
            List<AHFile> brothers = DAO.listFiles(inst.id);
            List<AHDirectory> sisters2 = DAO.listDirs(inst.id);
            // scanning for dirs
            for (File file : flist) {
                if (file.isDirectory()) {
                    dirs++;
                    ok = scanDirectory(file, gen + 1, orgpath, inst, partitionid, sisters2);
                    if (!ok) {
                        logger.error("ABORTING dir "+dir.getAbsolutePath());
                        return false;
                    }
                }
            }
            if (gen > 0)  // to dangerous to delete whole tree
            for (AHDirectory dd: sisters2) {
                if (!dd.exists) {
                    logger.info("Deleting showdir "+ dd.name + " "+ dd.path);
                    dd.deleteShadowDir();
                }
            }
            DAO.openConnection();

            for (File file : flist) {
                if (file.isFile()) { //it is a file
                    fileno++;
                    ok = scanFile(file, inst, brothers);
                    if (!ok) {
                        logger.error("ABANDONING dir "+dir.getAbsolutePath());
                        logger.error("because of  "+file.getAbsolutePath());
                        return false;
                    }
                }
            }
            for (AHFile af: brothers) {
                if (!af.exists) {
                    logger.info("Deleting shadow file "+af.name+ " in dir: " + dir.getAbsolutePath());
                    af.delete();
                }
            }
            DAO.closeConnection();
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public boolean rename(File dir, int gen) {
        boolean ok;
        int fileno = 0; // for finding empty directories
        if (!dir.isDirectory()) {
            logger.error("NOT a DIRECTORY "+dir.getAbsolutePath());
            return false;
        }

        if (gen == 0) {
            logger.info("RENAME "+dir.getAbsolutePath());
        }
        if (ignore_dir(dir)) {
            logger.info("Ignoring "+dir.getAbsolutePath());
            return true;
        }
        File[] flist = dir.listFiles();
        if (flist == null) {
            logger.error("listFiles failed " + dir.getAbsolutePath());
            return false;
        }

        try {
            // scanning for dirs
            for (File file : flist) {
                if (file.isDirectory()) {
                    dirs++;
                    ok = rename(file, gen + 1);
                    if (!ok) {
                        logger.error("ABORTING dir "+dir.getAbsolutePath());
                        return false;
                    }
                }
                if (file.isFile()) {
                    String oldfname = file.getName();
                    String newfname = cleanname(oldfname);
                    if (!oldfname.equals(newfname)) {
                        String newfpath = file.getParent() + "/" + newfname;
                        File newfile = new File(newfpath);
                        boolean success = file.renameTo(newfile);
                        if (!success) {
                            logger.error("Rename failed from "+ oldfname);
                            logger.error("Rename failed to   "+ newfname);
                            renamelist.add(file.getAbsolutePath());
                        }
                    }
                }
            }
            String oldname = dir.getName();
            String newname = oldname;
            if (gen > 0) {
                newname = cleanname(oldname);
            }
            if (!newname.equals(oldname)) {
                String newpath = dir.getParent() + "/" + newname;
                File newDir = new File(newpath);
                // Rename the directory
                boolean success = dir.renameTo(newDir);
                if (!success) {
                    logger.error("Rename failed from "+ oldname);
                    logger.error("Rename failed to   "+ newname);
                    renamelist.add(dir.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public boolean delete_empty_dirs(File dir, int gen) {
        try {
            if (!dir.isDirectory()) {
                logger.error("NOT a DIRECTORY 3 "+dir.getAbsolutePath());
                return false;
            }
            if (ignore_dir(dir)) {
                logger.info("ignoring "+ dir.getAbsolutePath());
                return true;
            }
            File[] flist = dir.listFiles();
            if (flist == null) {
                logger.error("listFiles failed 3 " + dir.getAbsolutePath());
                return false;
            }
            for (File f: flist) {
                if (f.isDirectory()) {
                    delete_empty_dirs(f, gen+1);
                }
            }
            flist = dir.listFiles();
            if (flist == null) {
                logger.error("listFiles failed 4 " + dir.getAbsolutePath());
                return false;
            }
            if (flist.length == 0) {
                logger.info("Deleting empty dir 4 " + dir.getAbsolutePath());
                try {
                    dir.delete();
                } catch (Exception e0) {
                    logger.error("Failed to delete 4 "+ dir.getAbsolutePath());
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            logger.error("delete empty dirs "+e);
        }
        return true;
    }

    // udregner CRC for en fil, og laver bogholderiet i databasen
    public boolean scanFile(File source, AHDirectory dir, List<AHFile> brothers) {
        //logger.info("looking at file "+source.getAbsolutePath());
        byte[] buffer = new byte[4096 * 16];
        int n;
        long bytes = 0;
        long speed = 0L;
        long length = source.length();
        int namelength = source.getName().length();
        String md5 = "";
        String filename = source.getName();
        if (filename.equals("md5sum.txt")) {
            return true;
        }
        if (filename.equals("md5sum.ok")) {
            return true;
        }
        if (maxfilenamelength < namelength)
            maxfilenamelength = namelength;
        try {
            md5 = ""; //MD5Checksum.getMD5Checksum(source);
            if (maxfilelength < length) {
                maxfilelength = (int)length;
            }
            AHFile inst = null;
            String fname = source.getName();
            for (AHFile brother: brothers) {
                if (brother.name.equals(fname)) {
                    inst = brother;
                    brother.exists = true;
                    break;
                }
            }
            if (inst == null) { // må ikke blive kaldt efter dette program laver en rename
                //md5 = MD5Checksum.getMD5Checksum(source);
                inst = new AHFile();
                inst.dirid = dir.id;
                inst.crc = FileChecksum.calculateChecksum(source);
                inst.name = fname;
                inst.size = (int) length;
                inst.partitionid = dir.partitionid;
                inst.save();
                logger.info("Saved CRC in DB "+source.getAbsolutePath());
            }
            if (inst.crc == 0) {
                inst.crc = FileChecksum.calculateChecksum(source);
                inst.update();
            }
            return true;
        } catch (Exception e) {
            logger.error("filenamelength = " + filename.length());
            logger.error("scanFile " + source.getAbsolutePath() + " " + e);
            return true;
        }
    }

    public boolean setPartiionId(AHDirectory dir) {
        try {
            List<AHDirectory> dirs = DAO.listDirs(dir.id);
            List<AHFile> files = DAO.listFiles(dir.id);
            for (AHFile f : files) {
                f.partitionid = dir.partitionid;
                f.update();
            }
            for (AHDirectory d : dirs) {
                if (d.partitionid != dir.partitionid) {
                    logger.error("Bad partitionid");
                    return false;
                }
                boolean ok = setPartiionId(d);
                if (!ok) {
                    logger.error("aborting " + dir.name + " " + dir.id);
                    return false;
                }
            }
        } catch (Exception e) {
            logger.error("Set partiion "+e);
        }

        return true;
    }

    // summerer CRCer for filer og gemmer dem i DB-entriet for directoriet
        public long crc_dir(AHDirectory dir, int gen ) {
            if (gen <2 ) DAO.openConnection();
            dir.crc = 0;
            dir.size = 0;
            if (gen < 2)
                logger.info("crc_dir "+dir.partitionid + " "+dir.gen+ " "+dir.id+ " "+dir.name);
            try {
                List<AHDirectory> dirs = DAO.listDirs(dir.id);
                List<AHFile> files = DAO.listFiles(dir.id);
                for (AHFile f : files) {
                    if (f.name.equals("md5sum.txt"))
                        continue;
                    if (f.name.equals("md5sum.ok"))
                        continue;
                    dir.crc ^= f.crc;
                    dir.size += f.size;
                }
                for (AHDirectory d : dirs) {
                    dir.crc ^= crc_dir(d, gen+1);
                    dir.size += d.size;

                }
                dir.update();
            } catch (Exception e) {
                logger.error("Set partiion "+e);
            }
            if (gen <2 ) DAO.closeConnection();
            return dir.crc;
        }

    public void scanCopyFile(File source) {
        long t1 = System.currentTimeMillis();
        long t2 = System.currentTimeMillis();
        try {
            t1 = System.currentTimeMillis();

            logger.info("Copying: " + source.getName());
            copy(Paths.get(source.getAbsolutePath()),
                    Paths.get(targetpath(source)),
                    StandardCopyOption.REPLACE_EXISTING);
            t2 = System.currentTimeMillis();

        } catch (Exception e) {
            logger.error("scanFile " + source.getAbsolutePath() + " " + e);
        } finally {
            logger.error("Copied " + source.getAbsolutePath() + " " + (t2 - t1));
        }
    }

    public String targetpath(File source) {
        String sourcepath = source.getAbsolutePath();
        String targetpath = sourcepath.replace("/media/anders/1C22-3138", "/tmp/USB1");
        return targetpath;
    }


    public void scanCopyDirectory(File dir, int gen, int dirid) {
        try {
            if (!dir.isDirectory()) return;
            printtab(gen);
            logger.info("Starting " + dir.getName());
            long t1 = System.currentTimeMillis();
            //scanFile(dir, dirid, dir);
            File[] fList = dir.listFiles();
            if (fList == null) {
                logger.info("listFiles failed");
                return ;
            }
            for (File file : fList) {
                if (file.isDirectory()) {
                    scanCopyDirectory(file, gen + 1, dirid);
                } else {
                    //scanFile(file, dirid, dir);
                }
            }
            long t2 = System.currentTimeMillis();
            printtab(gen);
            logger.info("Finished " + dir.getName() + " dt=" + (t2 - t1));
        } catch (Exception e) {
            logger.info("scancopydyr " + dir.getName() + " " + e);
        }
    }

    public static String cleanname(String name) {
        StringBuffer outname = new StringBuffer();
        int len = name.length();
        if (len > 256)
            len = 256;
        for (int i = 0; i < len; i++) {
            char ch = name.charAt(i);
            switch (ch) {
                case '`':
                case '#':
                case ' ':
                case '\r':
                case ':':
                case '|':
                case '?':
                case '*':
                case '<':
                case '>':
                case '/':
                case '\\':
                case (char) 8212:
                case (char) 8217:
                case (char) 8216:
                case (char) 180:
                case (char) 8220:
                case (char) 8221:
                case '\"':
                case '\'':
                case '&':
                case '(':
                case ')':
                case '$':
                case ';':
                case ',':
                case (char) 168:
                case (char) 146:
                case (char) 8230:
                case (char) 174:
                case (char) 183:
                case (char) 171:
                case (char) 187:
                    outname.append('-');
                    break;
                case (char) 145:
                    outname.append('æ');
                    break;
                case (char) 155:
                    outname.append('ø');
                    break;
                case (char) 127:
                    outname.append('ø');
                    break;
                case (char) 26:
                    outname.append('ø');
                    break;
                case (char) 157:
                    outname.append('Ø');
                    break;
                case (char) 134:
                    outname.append('å');
                    break;
                case (char) 160:
                    outname.append('a');
                    break;
                case (char) 162:
                    outname.append('c');
                    break;
                case (char) 143:
                    outname.append('Å');
                    break;
                case (char) 129:
                    outname.append('ü');
                    break;
                case (char) 130:
                    outname.append('é');
                    break;
                case (char) 8482:
                    outname.append("T");
                    break;
                case (char) 176:
                    outname.append('o');
                    break;
                case (char) 148:
                    outname.append('ö');
                    break;
                case (char) 153:
                    outname.append('Ö');
                    break;
                case (char) 154:
                    outname.append('Ü');
                    break;
                case (char) 132:
                    outname.append('ä');
                    break;
                case (char) 169:
                    outname.append('C');
                    break;
                case (char) 8470:
                    outname.append('ü');
                    break;
                default:
                    if (Character.isAlphabetic(ch)) {
                        outname.append(ch);
                    } else {
                        int chv = (int) ch;
                        if ((chv > 126 && chv < 128 + 32) || chv < 32) {
                            logger.info("suspicious name " + chv + " " + name);
                        }
                        if ((int) ch < 256)
                            outname.append(ch);
                        else {
                            logger.info("suspicious name " + chv + " " + name);
                            outname.append("-");
                        }
                    }
                    break;
            }
        }
        return outname.toString();
    }
}