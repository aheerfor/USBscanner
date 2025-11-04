package anders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

public class FileChecksum {
    public static long calculateChecksum(File filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             CheckedInputStream cis = new CheckedInputStream(fis, new CRC32())) {
            byte[] buffer = new byte[8192]; // 8KB buffer for efficiency
            while (cis.read(buffer) != -1) {
                // Read file in chunks, updating checksum
            }
            return cis.getChecksum().getValue();
        }
    }
}