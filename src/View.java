import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private static int WIDTH = 1000;
    private static int HEIGHT = 520;

    private Game game;

    private JPanel panel;
    private JScrollPane scrollPaneText;
    private JScrollPane scrollPaneGame;
    private JButton button;
    private JTextField textField;
    private JTextField raceLeader;
    private List names;

    public View(int numberOfTracks){
        setTitle("Races");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        raceLeader = new JTextField("Все тараканы на старте");
        raceLeader.setEditable(false);

        game = new Game(numberOfTracks, raceLeader);
        scrollPaneGame = new JScrollPane(game);

        /*name panel*/
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 1; i < numberOfTracks + 1; i++) {
            textField = new JTextField();
            textField.setText("Set name " + i);
            textField.setPreferredSize(new Dimension(100, game.getTrackHeight()));
            panel.add(textField);
        }
        scrollPaneText = new JScrollPane(panel);
        /**/

        button = new JButton("Start");

        getContentPane().add(button, BorderLayout.NORTH);
        getContentPane().add(scrollPaneGame, BorderLayout.CENTER);
        getContentPane().add(scrollPaneText, BorderLayout.WEST);
        getContentPane().add(raceLeader, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Game getGame() {
        return game;
    }

    public JButton getButton() {
        return button;
    }
}
