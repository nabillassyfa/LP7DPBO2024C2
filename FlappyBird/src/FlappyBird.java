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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    //image attributes
    Image bakgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    //player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    Timer gameLoop;
    Timer pipesCooldown;
    boolean tamat = false;  // variabel untuk menandakan game over
    double score = 0;    // variabel untuk menampung score
    int gravity = 1;

    //pipes atribut
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    public FlappyBird(){
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);

        bakgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipe();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);
        draw (g);
    }

    public void draw (Graphics g){
        g.drawImage(bakgroundImage, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        g.setColor(Color.white);

        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (tamat){
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        }else{
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            if (!pipe.passed && player.getPosX() > pipe.getPosX() + pipe.getWidth()) {  // kondisi jika burung melewati pipa
                score += 0.5;                           // menambah 0.5 untuk setiap pipa yang dilewati
                pipe.setPassed(true);
            }

            if (collision(player, pipe)) {  // kondisi jika burung menyentuh pipa
                gameOver (score);  // menampilkan halaman score
                tamat = true;
            }
        }

        if (player.getPosY() > frameHeight){  // kondisi jika burung jatuh kebawah
            gameOver (score);  // menampilkan halaman score
            tamat = true;
        }
    }

    // metode untuk menampilkan layar game over
    public void gameOver(double score){
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        //Menampilkan objek game over
        GameOver gameOver = new GameOver(score);
        frame.add(gameOver);
        frame.setVisible(true);
        SwingUtilities.getWindowAncestor(FlappyBird.this).dispose();
    }

    // metode untuk mendeteksi tabrakan antara burung dan pipa
    boolean collision (Player a, Pipe b){
        return  a.getPosX() < b.getPosX() + b.getWidth() &&  // kondisi jika burung kurang dari posisi X pipa (tabrakan bawah)
                a.getPosX() + a.getWidth() > b.getPosX() &&  //kondisi jika X burung ditambah lebar burung lebih besar dari posisi X pipa
                a.getPosY() < b.getPosY() + b.getHeight() && // kondisi jika y burung kurang dari posisi atas pipa (tabrakan atas)
                a.getPosY() + a.getHeight() > b.getPosY(); //kondisi jika Y burung ditambah lebar burung lebih besar dari posisi Y pipa
    }

    public void placePipe(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowePipe =  new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowePipe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move ();
        repaint();
        if (tamat){
            pipesCooldown.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocityY(-10);

            if (tamat){
                player.setPosY(playerStartPosY);
                player.setVelocityY(0);
                pipes.clear();
                tamat = false;
                score = 0;
                gameLoop.start();
                pipesCooldown.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

    }
}
