
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

public class GameOver extends JPanel implements KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    // Image attributes
    Image bakgroundImage;

    // Score label
    JLabel scoreLabel;
    JLabel valueLabel; // Label untuk nilai skor

    public GameOver(double score) {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setLayout(new GridBagLayout());
        setFocusable(true);
        addKeyListener(this);

        bakgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        // label untuk menampilkan skor
        scoreLabel = new JLabel("Score Anda:");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Atur font dan ukuran

        // label untuk menampilkan nilai skor
        valueLabel = new JLabel(String.valueOf(score));
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Atur font dan ukuran

        // tombol untuk mulai kembali
        JButton startButton = new JButton("Mulai kembali");
        startButton.setPreferredSize(new Dimension(150, 50));

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Menambahkan label skor dan tombol mulai ke panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(scoreLabel, gbc);

        // Menambahkan valueke panel
        gbc.gridy = 1;
        add(valueLabel, gbc);

        // Menambahkan tombol mulai ke panel
        gbc.gridy = 2;
        add(startButton, gbc);
    }

    // metode untuk menampilkan layar flapy bird
    private void startGame() {

        JFrame flappyFrame = new JFrame("Flappy Bird");
        flappyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flappyFrame.setSize(360, 640);
        flappyFrame.setLocationRelativeTo(null);
        flappyFrame.setResizable(false);


        FlappyBird flappyBird = new FlappyBird();
        flappyFrame.add(flappyBird);
        flappyFrame.setVisible(true);

        // Menutup frame Start
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bakgroundImage, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // membuat metode jika user menekan keyboard R
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) { // kondisi jika user menekan keyboard R
            startGame();  // menampilkan layar flapybird
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
