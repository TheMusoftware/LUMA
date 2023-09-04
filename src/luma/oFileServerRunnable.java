package luma;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class oFileServerRunnable implements Runnable {
    private String pathName;
    private String ip;
    void setIp(String ip){
        this.ip = ip;
    }
    public void setPathName(String str){
        this.pathName = str;
    }
    @Override
    public void run() {
        try {
            // Initialize Sockets
            ServerSocket ssock = new ServerSocket(4102);
            Socket socket = ssock.accept();

            // The InetAddress specification
            InetAddress IA = InetAddress.getByName(ip);

            // Specify the file
            File file = new File(pathName);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            // Get socket's output stream
            OutputStream os = socket.getOutputStream();

            // Read File Contents into contents array
            byte[] contents;
            long fileLength = file.length();
            long current = 0;
            long start = System.nanoTime();

            while (current != fileLength) {
                int size = 1000000;
                if (fileLength - current >= size)
                    current += size;
                else {
                    size = (int) (fileLength - current);
                    current = fileLength;
                }
                contents = new byte[size];
                bis.read(contents, 0, size);
                os.write(contents);
                System.out.println("Sending file ... " + (current * 100) / fileLength + "% complete!");
            }

            os.flush();

            // File transfer done. Close the socket connection!
            socket.close();
            ssock.close();
            System.out.println("File sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
