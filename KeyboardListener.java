
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;

public class KeyboardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){
            case VK_LEFT:
                try {
                    Updater.getInstance().LeftKeyboard();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case VK_RIGHT:
                try {
                    Updater.getInstance().RightKeyboard();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case VK_SPACE:
                try {
                    Updater.getInstance().SpaceKeyboard();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
