/*

Saya Nabilla Assyfa Ramadhani [2205297]
mengerjakan LP7
dalam mata Desain dan Pemograman Berorientasi Objek
untuk keberkahanNya maka saya tidak melakukan kecurangan
seperti yang telah dispesifikasikan.
Aamiin

*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start extends JPanel implements KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    //image attributes
    Image bakgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    public Start() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setLayout(new GridBagLayout());
        setFocusable(true);
        addKeyListener(this);

        bakgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        // Buat tombol mulai
        JButton startButton = new JButton("Mulai");

        // Mengatur ukuran tombol
        startButton.setPreferredSize(new Dimension(100, 50));

         //Menambahkan action listener jika user menekan tombol mulai
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // menampilkan JFrame Flappy Bird saat tombol mulai ditekan
                JFrame flappyFrame = new JFrame("Flappy Bird");
                flappyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                flappyFrame.setSize(360, 640);
                flappyFrame.setLocationRelativeTo(null);
                flappyFrame.setResizable(false);

                // menambahkan objek FlappyBird ke frame Flappy Bird
                FlappyBird flappyBird = new FlappyBird();
                flappyFrame.add(flappyBird);
                flappyFrame.setVisible(true);

                // Menutup frame Start
                SwingUtilities.getWindowAncestor(Start.this).dispose();
            }
        });

        // Menambahkan tombol mulai ke panel
        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bakgroundImage, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
