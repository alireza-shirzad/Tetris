import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, UnsupportedAudioFileException {
        Canvas canvas = Canvas.getInstance();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setSize(Constants.WindowWidth, Constants.WindowHeight);
        frame.setTitle(Constants.GameTitle);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setLayout(null);
        canvas.CreatePauseButton(frame);
        canvas.CreateExitButton(frame);
        canvas.CreateRestartButton(frame);
        //canvas.CreateUndoButton(frame);
        FileOperation.PlaySound("theme.wav",true);
    }
}
