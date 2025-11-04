package anders;

/**
 * Created by anders on 4/22/17.
 */
import javax.swing.filechooser.*;

    public class VolumeLabel {
        private VolumeLabel() { }

        public static void main(String[] args) {
            System.out.println("\"" + get(args[0]) + "\"");
        }

        public static String get(String path) {
            try {
                FileSystemView view = FileSystemView.getFileSystemView();
                AHFile dir = new AHFile();
                String name = "";//view.getSystemDisplayName(dir);
                if (name == null) {
                    return null;
                }
                name = name.trim();
                if (name == null || name.length() < 1) {
                    return null;
                }
                int index = name.lastIndexOf(" (");
                if (index > 0) {
                    name = name.substring(0, index);
                }
                return name;
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }
