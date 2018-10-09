import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Random;

public class Cockroach extends JPanel implements Runnable{
    private Thread cockroachThread;
    private int coordX, coordY;
    private String name;
    private int finishX;
    private Random random;
    private Game game;

    private boolean finished = false;

    public Cockroach(String name, int x, int y, int finish, Game game){
        random = new Random();
        this.name = name;
        this.coordX = x;
        this.coordY = y;
        this.finishX = finish;
        this.game = game;
    }

    private int generateShift(){
       // return (int) Math.sqrt(random.nextInt(100));
        return random.nextInt(100);
    }

    public void step(){
        coordX += generateShift();
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            while (coordX < finishX) {
                game.repaint();
                step();
                //print();//---
                Thread.sleep(1000);
            }
            if (coordX >= finishX) {
                System.out.println("таракан " + getName() + " финишировал - x = " + getCoordX() + " finish = " + getFinishX());//---
                finished = true;
                game.addFinisher();
                //System.out.println(game.getFinished());
                if (game.getFinished() == 1)
                    System.out.println("таракан " + getName() + " ПОБЕДИЛ - x = " + getCoordX() + " finish = " + getFinishX());
                if (game.getFinished() == game.getNumberOfTracks()) {
                    game.delFinished();
                    System.out.println("все тараканы финишировали");
                    // восстановить изображение
                    game.repaint();
                    game.setGameStarted(false);
                }
                setInStartPosition();
                // убить поток текущего таракана
                stopCockroachThread();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g){
        // перерисовать таракана из его текущих координат
        ImageIcon cockroachIcon = new ImageIcon(this.getClass().getResource("cockroach.jpg"));
        Image cockroachImg = cockroachIcon.getImage();

        g.drawImage(cockroachImg, getCoordX(), getCoordY(), null);
    }

    private void print(){
        System.out.println("( " + name + " " + coordX + " )");
    }

    public Thread getCockroachThread() {
        return cockroachThread;
    }

    public int getFinishX() {
        return finishX;
    }
    public void setInStartPosition(){
        this.coordX = 0;
    }

    public void setCockroachThread() {
        this.cockroachThread = new Thread(this);
    }
    public void stopCockroachThread(){
        cockroachThread.stop();
        setInStartPosition();
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
