import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
public class Game {
    Utility utility=new Utility();
    private static final int player_SIZE = 50;
    private static int playerX = 1130;
    private static int playerY = 920;
    private static final int playerSpeed=2;
    private static JLabel player=new JLabel();
    private static Rectangle[] obstacles=new Rectangle[2];
    private static Rectangle playerRect=new Rectangle(playerX, playerY, player_SIZE, player_SIZE);
    public Game(JFrame frame,Player playerStats) throws IOException {
        playerX=frame.getWidth()-(frame.getWidth()*3/7);
        playerY=frame.getHeight()-(frame.getHeight()/7);
        frame.getContentPane().removeAll();
        utility.soundPlayer = new Utility.Music();
        utility.setFile("resources/gameSound.wav");
        utility.loopSound();
        JLayeredPane layeredPane = new JLayeredPane();

        ImageIcon imageIcon = new ImageIcon("resources/map.png");
        Image scaledImage = imageIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel map = new JLabel(scaledIcon);
        map.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        layeredPane.add(map, Integer.valueOf(0));

        player.setBounds(playerX, playerY, player_SIZE, player_SIZE);
        if (playerStats.getChampion().equals(Character.drake)) {
            player = new JLabel(Character.getDrakeGraphics(player_SIZE));
        } else {
            player=new JLabel(Character.getCassandraGraphics(player_SIZE));

        }
        layeredPane.add(player, Integer.valueOf(1));

        JLabel soldierNPC=new JLabel(Character.getSoldierNPC(player_SIZE));
        soldierNPC.setBounds(320,270,player_SIZE,player_SIZE);
        Rectangle soldierNPCRect=new Rectangle(soldierNPC.getX(),soldierNPC.getY(),soldierNPC.getWidth(),soldierNPC.getHeight());
        layeredPane.add(soldierNPC,Integer.valueOf(1));

        frame.setContentPane(layeredPane);
        frame.setVisible(true);


        obstacles[0]=soldierNPCRect;
        obstacles[1]=new Rectangle(722,878,722-654,878-756);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        if(playerX > 0) playerX -= playerSpeed;
                        player.setIcon(Character.getPlayerToLeft(playerStats));
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(playerX < frame.getWidth() - player_SIZE) playerX += playerSpeed;
                        player.setIcon(Character.getPlayerToRight(playerStats));
                        break;
                    case KeyEvent.VK_UP:
                        if(playerY > 0) playerY -= playerSpeed;
                        player.setIcon(Character.getPlayerToUp(playerStats));
                        break;
                    case KeyEvent.VK_DOWN:
                        if(playerY < frame.getHeight() - player_SIZE) playerY += playerSpeed;
                        player.setIcon(Character.getPlayerToDown(playerStats));
                        break;
                }
                if(!isColliding(playerRect)){
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    player.setBounds(playerX, playerY, player_SIZE, player_SIZE);
                    //System.out.println("X: "+playerX+"   Y: "+playerY);
                }
                //playerRect.setBounds(playerX,playerY,player_SIZE,player_SIZE);

                if(playerX<200 && playerY<200){
                    gameFinished(frame);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}});
    }
    private boolean isColliding(Rectangle playerRect) {
        for (Rectangle obstacle : obstacles) {
            if (obstacle.intersects(playerX,playerY,player_SIZE,player_SIZE)) {
                return true; // collision detected
            }
        }
        return false;
    }

    private void gameFinished(JFrame frame){
        frame.getContentPane().removeAll();
    }
}