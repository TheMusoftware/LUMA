package luma;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class oFileClientRunnable implements Runnable {
    private String fileName;
    private String savePath;
    private String ip;
    void setIp(String ip){
        this.ip = ip;
    }
    public oFileClientRunnable(String fileName,String ip) throws IOException{
        this.fileName = fileName;
        savePath = System.getProperty("user.dir") + File.separator + "Shared/"+fileName;
        Path outputPath = Paths.get(savePath);
        System.out.println("Save Path: "+savePath);
        if (!Files.exists(outputPath.getParent())) {
                Files.createDirectories(outputPath.getParent());
            }
        this.ip = ip;
    }
    @Override
    public void run() {
        try {
            // Initialize socket
            Socket socket = new Socket(ip, 4102);
            byte[] contents = new byte[1000000];

            // Initialize the FileOutputStream to the output file's full path.
            FileOutputStream fos = new FileOutputStream(savePath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            InputStream is = socket.getInputStream();

            // No of bytes read in one read() call
            int bytesRead = 0;
            while ((bytesRead = is.read(contents)) != -1) {
                bos.write(contents, 0, bytesRead);
            }
            System.out.println(socket.getInetAddress());
            bos.flush();
            socket.close();
            System.out.println("File saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setFileName(String str){
    }

 
}
