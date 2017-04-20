package Chat;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Feercha
 */
public class Server {

    public static void main(String[] args) {
        FrameServer fs = new FrameServer();
    }

}

class FrameServer extends JFrame implements Runnable{

    JTextArea area;

    public FrameServer() {
        super("Server");
        setSize(300, 400);
        setLayout(new BorderLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        area = new JTextArea();
        area.setBounds(10, 10, 250, 350);
        add(area);
        
        Thread hilo1 = new Thread();
        hilo1.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(9999);
            Socket envio = server.accept();
            
            DataInputStream entrada = new DataInputStream(envio.getInputStream());
            String text = entrada.readUTF();
            area.append(text + "\n");
            
        } catch (IOException ex) {
            //Logger.getLogger(FrameServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
}
