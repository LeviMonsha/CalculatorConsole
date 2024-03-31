//package Resourses;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class KeyHandler implements KeyListener {
//    private StringBuilder textArea;
//    private static final int ESC = 27;
//    public static boolean running = true;
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//        char keyChar = e.getKeyChar();
//
//        char c = e.getKeyChar();
//        if (Character.isDigit(c)) {
//            textArea.append(String.valueOf(c));
//        }
//
//        switch (keyChar) {
//            case '+' -> ;
//            case '-' -> ;
//            case '*' -> ;
//            case '/' -> ;
//            case '=' -> ;
//            case ESC -> running = false;
//            default: break;
//        }
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
//}
