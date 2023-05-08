import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public record Character(String name, String gender, String healthPoints, String damage) {
    public static Character drake = new Character("drake", "male", "100", "50");
    public static Character cassandra = new Character("cassandra", "female", "50", "100");
    public static Character soldierNPC=new Character("soldier","male","100","50");

    private static Image scaledCassandra;
    private static Image scaledDrake;
    public static ImageIcon getDrakeGraphics(int size) {
        ImageIcon drakeImageIcon=new ImageIcon("resources/drake.gif");
        Image scaleImageIcon=drakeImageIcon.getImage().getScaledInstance(size,size,Image.SCALE_DEFAULT);
        scaledDrake=scaleImageIcon;
        return new ImageIcon(scaleImageIcon);
    }
    public static ImageIcon getCassandraGraphics(int size) {
        ImageIcon cassandraImageIcon=new ImageIcon("resources/cassandra.png");
        Image scaleImageIcon=cassandraImageIcon.getImage().getScaledInstance(size,size,Image.SCALE_DEFAULT);
        scaledCassandra=scaleImageIcon;
        return new ImageIcon(scaleImageIcon);
    }
    public static ImageIcon getPlayerToLeft(Player player){
        ImageIcon imageIcon;
        if(player.getChampion().equals(drake)){
            imageIcon=new ImageIcon(rotate(scaledDrake,-180));
        }else {
            imageIcon=new ImageIcon(rotate(scaledCassandra,-180));
        }
        return imageIcon;
    }
    public static ImageIcon getPlayerToRight(Player player){
        ImageIcon imageIcon;
        if(player.getChampion().equals(drake)){
            imageIcon=new ImageIcon(rotate(scaledDrake,0));
        }else {
            imageIcon=new ImageIcon(rotate(scaledCassandra,360));
        }
        return imageIcon;
    }
    public static ImageIcon getPlayerToUp(Player player){
        ImageIcon imageIcon;
        if(player.getChampion().equals(drake)){
            imageIcon=new ImageIcon(rotate(scaledDrake,180));
        }else {
            imageIcon=new ImageIcon(rotate(scaledCassandra,180));
        }
        return imageIcon;
    }
    public static ImageIcon getPlayerToDown(Player player){
        ImageIcon imageIcon;
        if(player.getChampion().equals(drake)){
            imageIcon=new ImageIcon(rotate(scaledDrake,360));
        }else {
            imageIcon=new ImageIcon(rotate(scaledCassandra,360));
        }
        return imageIcon;
    }

    public static ImageIcon getSoldierNPC(int size){
        ImageIcon drakeImageIcon=new ImageIcon("resources/soldierNPC.gif");
        Image scaleImageIcon=drakeImageIcon.getImage().getScaledInstance(size,size,Image.SCALE_DEFAULT);
        return new ImageIcon(scaleImageIcon);
    }
    private static Image rotate(Image image, double angle) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        g2d.translate((height - width) / 2, (height - width) / 2);
        g2d.rotate(angle, width / 2, height / 2);

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }
}

