package Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Feercha
 */
public class Client {

    public static void main(String[] args) {
        FrameCliente fc = new FrameCliente();
    }
}

class FrameCliente extends JFrame {

    JTextArea campo;
    JButton guardar;

    public FrameCliente() {
        super("Client");
        setSize(300, 400);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        campo = new JTextArea();
        guardar = new JButton("Enviar");

        campo.setBounds(10, 10, 250, 100);
        add(campo);

        guardar.setBounds(100, 130, 100, 20);
        add(guardar);

        Enviar bh = new Enviar();
        guardar.addActionListener(bh);
    }

    private class Enviar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket envio = new Socket("ip", 9999);

                DataOutputStream salida = new DataOutputStream(envio.getOutputStream());
                salida.writeUTF(campo.getText());
                salida.close();

            } catch (IOException ex) {
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
    }
}

