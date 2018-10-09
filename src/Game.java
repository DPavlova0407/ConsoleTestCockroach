import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel implements Runnable {
    //private Thread gameThread;
    private JTextField raceLeader;

    private List<Cockroach> cockroaches;
    private int numberOfTracks;

    private int trackHeight;
    private int HEIGHT = 434;
    private int WIDTH = 800;
    private int finishX = WIDTH;

    private int finished = 0;

    private boolean gameStarted = false;

    public Game(int numberOfTracks, JTextField leader){
        this.numberOfTracks = numberOfTracks;
        this.raceLeader = leader;

        int h = HEIGHT/numberOfTracks;
        trackHeight = Math.max(h, 50);
        HEIGHT = trackHeight*numberOfTracks;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        createCockroaches();
    }

    public void paint(Graphics g){
        //
        checkLeader();
        g.fillRect(0, 0, WIDTH, HEIGHT);
        ImageIcon finishIcon = new ImageIcon(this.getClass().getResource("finish.jpg"));
        Image finishImg = finishIcon.getImage();

        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        g.setFont(newFont);

        for (int i = 0; i < numberOfTracks; i++){
            Color c1 = Color.pink;
            Color c2 = Color.orange;
            if (i % 2 == 0)
                g.setColor(c1);
            else
                g.setColor(c2);
            g.fillRect(0, i * trackHeight, WIDTH, trackHeight);
            g.setColor(Color.BLACK);
            g.drawRect(0, i * trackHeight, WIDTH, trackHeight);

            g.drawString(Integer.toString(i+1), 10, i * trackHeight + trackHeight/2 + 10);

            // отрисовка таракана
            cockroaches.get(i).paint(g);

            // отрисовка финишной линии
            g.setColor(Color.WHITE);
            g.fillRect(WIDTH + 1, i * trackHeight,100, trackHeight);
            g.drawImage(finishImg, WIDTH + 1, i * trackHeight, this);
        }
    }

    @Override
    public void run(){
        for (int i = 0; i < cockroaches.size(); i++){
            setGameStarted(true);
            cockroaches.get(i).setCockroachThread();
            cockroaches.get(i).getCockroachThread().start();
        }
    }

    public int getTrackHeight() {
        return trackHeight;
    }
    public void addFinisher(){
        finished += 1;
    }

    public void delFinished() {
        this.finished = 0;
    }

    public int getFinished() {
        return finished;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public List<Cockroach> getCockroaches() {
        return cockroaches;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void createCockroaches(){
        cockroaches = new ArrayList<>();
        for (int i = 0; i < numberOfTracks; i++){
            Cockroach c = new Cockroach(Integer.toString(i+1), 0, i * trackHeight + trackHeight/3, finishX, this);
            cockroaches.add(c);
        }
    }
    public void checkLeader(){
        if(getFinished() == numberOfTracks || !isGameStarted())
            this.raceLeader.setText("Все тараканы на старте");
        else {
            Cockroach leader = cockroaches.get(0);
            for (Cockroach c : cockroaches) {
                if (!c.isFinished() && c.getCoordX() > leader.getCoordX())
                    leader = c;
            }
            this.raceLeader.setText("Лидирует " + leader.getName());
        }
    }
    public void checkNames(){
        for (Cockroach c : cockroaches) {

        }
    }
}
