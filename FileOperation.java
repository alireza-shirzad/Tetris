

////// Importing
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileOperation {
    private static FileWriter Playerfile;
    public static void Write(String text) throws IOException {
        String Location =new File(".").getCanonicalPath() + "\\src\\HighScores.json";
        try {
            Playerfile = new FileWriter(Location);
            Playerfile.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Playerfile.flush();
                Playerfile.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static String Read() throws IOException {
        String Location =new File(".").getCanonicalPath() + "\\src\\HighScores.json";
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(Location)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    public static void PlaySound(String Name,boolean repeat){
        try {
            String Location =new File(".").getCanonicalPath() + "\\src\\" + Name;
            File audioFile = new File(Location);
            AudioInputStream audioStream = null;
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = null;
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            if (repeat)
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();
            audioStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }


}
