import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class Utility {
    private static String userName;
    public void setWindowIcon(JFrame frame){
        ImageIcon windowIcon=new ImageIcon("resources/skull1.jpg");
        frame.setIconImage(windowIcon.getImage());
    }
    public JTextArea[] guideTextLines=new JTextArea[20];
    public String[] guideDescriptionText(){
        String[] guideDescriptionTextLines=new String[20];
        guideDescriptionTextLines[0]="this is a tutorial & simulator for a game";
        guideDescriptionTextLines[1]="that doesnt exists...";
        guideDescriptionTextLines[2]="the purpose of the tutorial is to teach";
        guideDescriptionTextLines[3]="the fundamentals and mechanics of";
        guideDescriptionTextLines[4]="movement.";
        guideDescriptionTextLines[5]="its a walking simulator int a top down";
        guideDescriptionTextLines[6]="view.";
        guideDescriptionTextLines[7]="you have 4 buttons at your disposal.";
        guideDescriptionTextLines[8]="Button A- move left";
        guideDescriptionTextLines[9]="Button D- move right";
        guideDescriptionTextLines[10]="Button W- move up";
        guideDescriptionTextLines[11]="Button S- move down";
        guideDescriptionTextLines[12]="you can choose between two characters";
        guideDescriptionTextLines[13]="(male & female)";
        guideDescriptionTextLines[14]="your tutorial environment was meant to";
        guideDescriptionTextLines[15]="to resemble a post apocalyptic world";
        guideDescriptionTextLines[16]="when you first arrive to the map";
        guideDescriptionTextLines[17]="you can see your team mate in the ";
        guideDescriptionTextLines[18]="upper left side of the screen";
        guideDescriptionTextLines[19]="reach him to end the tutorial";
        return guideDescriptionTextLines;
    }
    public JTextArea[] getGuideTextAreas(){
        String[] guideDescriptionTextLines= guideDescriptionText();
        int y=90;
        int x=10;
        int width=360;
        int height=30;
        for(int i=0;i< guideTextLines.length;i++){
            guideTextLines[i]=new JTextArea(guideDescriptionTextLines[i]);
            guideTextLines[i].setBounds(x,y,width,height);
            guideTextLines[i].setOpaque(false);
            guideTextLines[i].setForeground(Color.WHITE);
            guideTextLines[i].setFont(new Font("Bell MT", Font.PLAIN, 20));
            guideTextLines[i].setVisible(false);
            y+=15;
        }
        return guideTextLines;
    }
    public void revealGuideTextLines(){
        for (JTextArea guideTextLine : guideTextLines) {
            guideTextLine.setVisible(true);
        }
    }
    public void hideGuideTextLines(){
        for (JTextArea guideTextLine : guideTextLines) {
            guideTextLine.setVisible(false);
        }
    }
    public static void runThisGif(JFrame frame,JLayeredPane layeredPane,ImageIcon imageIcon){
        frame.setSize(frame.getWidth(), frame.getHeight());
        frame.setUndecorated(true);
        frame.setResizable(false);
        Image scaledImage = imageIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0,0,frame.getWidth(),frame.getHeight());
        layeredPane.add(label,Integer.valueOf(0));
        frame.setVisible(true);
    }
    Music soundPlayer =new Music();
    public static class Music{
        Clip clip;
        AudioInputStream sound;
        public void setFile(String soundFileName){
            try{
                File file=new File(soundFileName);
                sound=AudioSystem.getAudioInputStream(file);
                clip=AudioSystem.getClip();
                clip.open(sound);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error");
            }
        }
        public void stop()throws IOException {
            sound.close();
            clip.close();
            clip.stop();
        }
        public void loop(){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

    }
    public void setFile(String filePath){
        soundPlayer.setFile(filePath);
    }
    public void loopSound(){
        soundPlayer.loop();
    }
    public void stopSound() throws IOException {
        soundPlayer.stop();
    }
    public void setPlayerName(String currentPlayerName){
        userName=currentPlayerName;
    }
    public String getPlayerName(){
        return userName;
    }
}