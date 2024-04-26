/*

Saya Nabilla Assyfa Ramadhani [2205297]
mengerjakan LP7
dalam mata Desain dan Pemograman Berorientasi Objek
untuk keberkahanNya maka saya tidak melakukan kecurangan
seperti yang telah dispesifikasikan.
Aamiin

*/

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //objek panel
        Start mulai = new Start();
        frame.add(mulai);
        mulai.requestFocus();

        frame.setVisible(true);

        //menampilkan tampilan kelas start
        mulai.repaint();
    }
}
