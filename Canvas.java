import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Canvas extends JComponent {
    private JButton ExitButton=new JButton("Exit");
    private JButton UndoButton=new JButton("Undo");
    private JButton PauseButton=new JButton("Pause");
    private JButton RestartButton=new JButton("Restart");
    private Updater updater;
    private  GameState gameState;
    private Drawer drawer;
    private static Canvas canvas;
    private Canvas() throws IOException {
        PauseButton.setText("Start");
        gameState = GameState.getInstance() ;
        addKeyListener(new KeyboardListener());
        updater = Updater.getInstance();
        drawer = new Drawer((Graphics2D) getGraphics());
        Timer timer = new Timer();
        TimerTask timerTask = new Ticker();
        timer.scheduleAtFixedRate(timerTask,Constants.TimerDelay,Constants.RenderingPeriod);
    }
    public static Canvas getInstance() throws IOException {
        if(canvas==null) { canvas = new Canvas();}
        return canvas;
    }

    public JButton getRestartButton() {
        return RestartButton;
    }

    public void setRestartButton(JButton restartButton) {
        RestartButton = restartButton;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        if (drawer==null) drawer = new Drawer(g2D) ;
        requestFocus();
        drawer.setGraphics2D(g2D);
        try {
            drawer.DrawField();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (gameState.getMovingTetromino()!=null)
        drawer.DrawTetromino(gameState.getMovingTetromino());
        drawer.DrawSidePannel();
        if (gameState.isExit()) {
            drawer.DrawExit();
        }
        if(gameState.isGameOver()){
            drawer.DrawGameOver();
        }
    }

    public void CreatePauseButton(JFrame frame){
        PauseButton.setBounds(Constants.FieldWidth+49,Constants.WindowHeight/4,95,30);
        PauseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (gameState.isPause()) {
                    PauseButton.setText("Pause");
                    gameState.setPause(false);
                }else{
                    PauseButton.setText("Start");
                    gameState.setPause(true);
                }
            }
        });
        frame.add(PauseButton);
    }
    public void CreateUndoButton(JFrame frame){
        UndoButton.setBounds(Constants.FieldWidth+49,Constants.WindowHeight/4+90,95,30);
        UndoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    updater.Undo();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(UndoButton);
    }
    public void CreateExitButton(JFrame frame){
        ExitButton.setBounds(Constants.FieldWidth+49,Constants.WindowHeight/4+30,95,30);
        ExitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                updater.Exit();
            }
        });
        frame.add(ExitButton);
    }
    public void CreateRestartButton(JFrame frame){
        RestartButton.setBounds(Constants.FieldWidth+49,Constants.WindowHeight/4+60,95,30);
        RestartButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    updater.Restart();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(RestartButton);
    }


    private class Ticker extends TimerTask {
        boolean result;
        @Override
        public void run() {
            if (!gameState.isPause()&!gameState.isExit()&!gameState.isGameOver()) {
                result = updater.Regularpdate();
            }
                repaint();
            if (!gameState.isPause()&!gameState.isExit()&!gameState.isGameOver()) {
                try {
                    updater.UpdateTetrominos(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(gameState.isExit()){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }else if (gameState.isGameOver()){
                FileOperation.PlaySound("GameOver.wav",false);
                gameState.setPause(true);
                gameState.setScore(0);
                gameState.setFixedSquares( new Square[Constants.NumOfHeightSquares][Constants.NumOfWidthSquares]);
                gameState.setGameOver(false);
            }
        }
    }

    public JButton getExitButton() {
        return ExitButton;
    }

    public void setExitButton(JButton exitButton) {
        ExitButton = exitButton;
    }

    public JButton getPauseButton() {
        return PauseButton;
    }

    public void setPauseButton(JButton pauseButton) {
        PauseButton = pauseButton;
    }
}
