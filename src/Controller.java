import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;

    public Controller(View viewN){
        view = viewN;
        view.getButton().addActionListener(new Button());
    }
    class Button implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // при нажатии на кнопку во время забега, забег будет остановлен
            // для начала нового забега повторно нажать на кнопку
            if (view.getGame().isGameStarted()){
                // убить потоки
                // вернуть изначальное состояние
                for (Cockroach c : view.getGame().getCockroaches()) {
                    c.stopCockroachThread();
                    //System.out.println("поток остановлен--------------------------Controller");
                }
                System.out.println("Забег был прерван");
                //восстановить картинку
                view.getGame().repaint();
                view.getGame().delFinished();
                view.getGame().setGameStarted(false);
            } else {
                view.getGame().run();// по нажатию кнопки запустить забег
            }
        }
    }
}
