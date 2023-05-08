import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class FullScreenMenu extends JFrame {
    Utility utility = new Utility();

    FullScreenMenu() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        int screenWidth = this.getWidth();
        int screenHeight = this.getHeight();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("fallout");
        utility.setWindowIcon(this);
        JLayeredPane layeredPane = new JLayeredPane();
        this.getContentPane().add(layeredPane);

        ImageIcon imageIcon = new ImageIcon("resources/gif.gif");
        Utility.runThisGif(this, layeredPane, imageIcon);
        utility.setFile("resources/soundtrack(WAV).wav");
        utility.loopSound();

        int gap = 30;
        int usernameWidth = 400;
        int usernameHeight = 80;

        JLabel username = new JLabel(utility.getPlayerName());
        username.setBounds(gap, gap, usernameWidth, usernameHeight);
        username.setFont(new Font("Algerian", Font.BOLD, 30));
        username.setBackground(Color.red.darker());
        username.setForeground(Color.WHITE.brighter());
        layeredPane.add(username, Integer.valueOf(1));
        System.out.println(utility.getPlayerName());

        int titleWidth = 400;
        int titleHeight = 80;

        JLabel title = new JLabel("fallout: the new order");
        title.setBounds(gap + username.getX(), gap + username.getHeight(), titleWidth, titleHeight);
        title.setFont(new Font("Algerian", Font.BOLD, 30));
        title.setForeground(Color.YELLOW);
        layeredPane.add(title, Integer.valueOf(2));

        int playButtonWidth = 300;
        int playButtonHeight = 100;
        int playButtonY = title.getHeight() + title.getY() + gap;
        JButton playButton = new JButton("PLAY");
        playButton.setBounds(gap * 2, playButtonY, playButtonWidth, playButtonHeight);
        playButton.setFont(new Font("Stencil", Font.PLAIN, 40));
        playButton.setBackground(Color.black);
        playButton.setForeground(Color.RED);
        playButton.setFocusPainted(false);
        playButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        layeredPane.add(playButton, Integer.valueOf(4));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                revalidate();
                repaint();
                customizePlayer(screenWidth, screenHeight);

            }
        });

        int exitButtonX = gap;
        int exitButtonWidth = 250;
        int exitButtonHeight = 110;
        int exitButtonY = screenHeight - exitButtonHeight - gap;

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight);
        exitButton.setFont(new Font("Stencil", Font.PLAIN, 100));
        exitButton.setBackground(Color.GREEN);
        exitButton.setForeground(Color.ORANGE.darker());
        exitButton.setFocusPainted(false);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
        layeredPane.add(exitButton, Integer.valueOf(3));
        exitButton.addActionListener(e -> System.exit(0));

        this.setVisible(true);

    }

    public void customizePlayer(int screenWidth, int screenHeight) {
        this.setLayout(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);

        int gap = 20;
        int width = 200;
        int height = 50;
        int addWidth = 100;
        int addHeight = 50;

        JTextArea chooseYourChampion = new JTextArea("click on a picture to choose your champion");
        chooseYourChampion.setEditable(false);
        chooseYourChampion.setBounds((screenWidth / 2) - 300, gap, 600, 100);
        chooseYourChampion.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 32));
        chooseYourChampion.setForeground(Color.YELLOW);
        chooseYourChampion.setBackground(null);
        this.add(chooseYourChampion);

        Character drake = Character.drake;
        Character cassandra = Character.cassandra;

        JLabel drakeStats = new JLabel(drake.name() + " stats: ");
        drakeStats.setBounds((screenWidth / 4) - (width + addWidth / 2), height * 12, width + addWidth, height * 2);

        JLabel drakesGender = new JLabel("gender: " + drake.gender());
        drakesGender.setBounds(drakeStats.getX(), gap + drakeStats.getY() + drakeStats.getHeight() / 2, width, height);

        JLabel drakeHealthPoints = new JLabel("health points: " + drake.healthPoints());
        drakeHealthPoints.setBounds(drakesGender.getX(), gap * 2 + drakesGender.getY(), width, height);

        JLabel drakeDamage = new JLabel("damage: " + drake.damage());
        drakeDamage.setBounds(drakeHealthPoints.getX(), gap * 2 + drakeHealthPoints.getY(), width, height);
        //////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////
        JLabel cassandraStats = new JLabel(cassandra.name() + " stats: ");
        cassandraStats.setBounds(screenWidth - drakeStats.getX() - drakeStats.getWidth(), height * 12, width + addWidth, height * 2);

        JLabel cassandraGender = new JLabel("gender: " + cassandra.gender());
        cassandraGender.setBounds(cassandraStats.getX(), gap + cassandraStats.getY() + cassandraStats.getHeight() / 2, width, height);

        JLabel cassandraHealthPoints = new JLabel("health points: " + cassandra.healthPoints());
        cassandraHealthPoints.setBounds(cassandraGender.getX(), gap * 2 + cassandraGender.getY(), width, height);

        JLabel cassandraDamage = new JLabel("damage: " + cassandra.damage());
        cassandraDamage.setBounds(cassandraHealthPoints.getX(), gap * 2 + cassandraHealthPoints.getY(), width, height);

        int firstLabelSize = 26;
        int labelsSize = 20;
        JLabel[] labels = new JLabel[8];
        labels[0] = drakeStats;
        labels[1] = drakesGender;
        labels[2] = drakeHealthPoints;
        labels[3] = drakeDamage;
        labels[4] = cassandraStats;
        labels[5] = cassandraGender;
        labels[6] = cassandraHealthPoints;
        labels[7] = cassandraDamage;
        for (JLabel label : labels) {
            label.setForeground(Color.ORANGE);
            if (label.getText().equals(drake.name() + " stats: ") || label.getText().equals(cassandra.name() + " stats: ")) {
                label.setFont(new Font("Ariel", Font.PLAIN, firstLabelSize));
            } else {
                label.setFont(new Font("Ariel", Font.PLAIN, labelsSize));
            }
            this.add(label);
        }

        this.getContentPane().setBackground(Color.DARK_GRAY.darker());

        JButton drakeProfileButton = new JButton();
        drakeProfileButton.setBounds(drakeStats.getX(), gap * 10, 300, 370);
        Rectangle drakeProfileButtonBounds = drakeProfileButton.getBounds();
        ImageIcon imageIcon1 = new ImageIcon("resources/drake pic (1).png");
        Image drakeProfile = imageIcon1.getImage();
        int drakeProfileImageWidth = drakeProfileButtonBounds.width;
        int drakeProfileImageHeight = drakeProfileButtonBounds.height;
        int drakeOriginalProfileImageWidth = drakeProfile.getWidth(null);
        int drakeOriginalProfileImageHeight = drakeProfile.getHeight(null);
        double scaleDrakeProfileImage = Math.min((double) drakeProfileImageWidth / drakeOriginalProfileImageWidth, (double) drakeProfileImageHeight / drakeOriginalProfileImageHeight);
        int newDrakeProfileImageWidth = (int) (drakeOriginalProfileImageWidth * scaleDrakeProfileImage);
        int newDrakeProfileImageHeight = (int) (drakeOriginalProfileImageHeight * scaleDrakeProfileImage);
        Image scaledDrakeProfileImage = drakeProfile.getScaledInstance(newDrakeProfileImageWidth, newDrakeProfileImageHeight, Image.SCALE_SMOOTH);
        ImageIcon drakeProfileButtonCover = new ImageIcon(scaledDrakeProfileImage);
        drakeProfileButton.setIcon(drakeProfileButtonCover);
        drakeProfileButton.setBorder(BorderFactory.createLineBorder(Color.RED, 8));
        drakeProfileButton.setFocusPainted(false);
        this.add(drakeProfileButton);

        JButton cassandraProfileButton = new JButton();
        cassandraProfileButton.setBounds(cassandraStats.getX(), gap * 10, 300, 370);
        Rectangle cassandraProfileButtonBounds = cassandraProfileButton.getBounds();
        ImageIcon imageIcon2 = new ImageIcon("resources/cassandra.jpg");
        Image cassandraProfile = imageIcon2.getImage();
        int cassandraProfileImageWidth = cassandraProfileButtonBounds.width;
        int cassandraProfileImageHeight = cassandraProfileButtonBounds.height;
        int cassandraOriginalProfileImageWidth = cassandraProfile.getWidth(null);
        int cassandraOriginalProfileImageHeight = cassandraProfile.getHeight(null);
        double scaleCassandraProfileImage = Math.min((double) cassandraProfileImageWidth / cassandraOriginalProfileImageWidth, (double) cassandraProfileImageHeight / cassandraOriginalProfileImageHeight);
        int newCassandraProfileImageWidth = (int) (cassandraOriginalProfileImageWidth * scaleCassandraProfileImage);
        int newCassandraProfileImageHeight = (int) (cassandraOriginalProfileImageHeight * scaleCassandraProfileImage);
        Image scaledCassandraProfileImage = cassandraProfile.getScaledInstance(newCassandraProfileImageWidth, newCassandraProfileImageHeight, Image.SCALE_SMOOTH);
        ImageIcon cassandraProfileButtonCover = new ImageIcon(scaledCassandraProfileImage);
        cassandraProfileButton.setIcon(cassandraProfileButtonCover);
        cassandraProfileButton.setBorder(BorderFactory.createLineBorder(Color.RED, 8));
        cassandraProfileButton.setFocusPainted(false);
        this.add(cassandraProfileButton);


        JButton returnButton = new JButton("return");
        returnButton.setBounds(30, 940, 450, 110);
        returnButton.setFont(new Font("Stencil", Font.PLAIN, 90));
        returnButton.setBackground(Color.GREEN);
        returnButton.setForeground(Color.ORANGE.darker().darker());
        returnButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
        this.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // צריך למלא
            }
        });

        /*JComponent[] components=new JComponent[12];
        components[0]=chooseYourChampion;
        components[1]=drakeStats;
        components[2]=drakesGender;
        components[3]=drakeDamage;
        components[4]=drakeHealthPoints;
        components[5]=cassandraStats;
        components[6]=cassandraHealthPoints;
        components[7]=cassandraDamage;
        components[8]=cassandraGender;
        components[9]=cassandraProfileButton;
        components[10]=drakeProfileButton;
        components[11]=returnButton;*/

        drakeProfileButton.addActionListener(e -> {
            Player player = new Player(MainMenu.username, "0", drake);
            try {
                utility.stopSound();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                new Game(this, player);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        cassandraProfileButton.addActionListener(e -> {
            Player player = new Player(MainMenu.username, "0", cassandra);
            try {
                utility.stopSound();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                Game game = new Game(this, player);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = e.getComponent().getSize();
                if (size.equals(screenSize)) {
                    chooseYourChampion.setBounds((screenWidth / 2) - 300, gap, 600, 100);
                    drakesGender.setBounds(drakeStats.getX(), gap + drakeStats.getY() + drakeStats.getHeight() / 2, width, height);
                    drakeHealthPoints.setBounds(drakesGender.getX(), gap * 2 + drakesGender.getY(), width, height);
                    drakeDamage.setBounds(drakeHealthPoints.getX(), gap * 2 + drakeHealthPoints.getY(), width, height);
                    drakeStats.setBounds((screenWidth / 4) - (width + addWidth / 2), height * 12, width + addWidth, height * 2);
                    cassandraGender.setBounds(cassandraStats.getX(), gap + cassandraStats.getY() + cassandraStats.getHeight() / 2, width, height);
                    cassandraHealthPoints.setBounds(cassandraGender.getX(), gap * 2 + cassandraGender.getY(), width, height);
                    cassandraDamage.setBounds(cassandraHealthPoints.getX(), gap * 2 + cassandraHealthPoints.getY(), width, height);
                    drakeProfileButton.setBounds(drakeStats.getX(), gap * 10, 300, 370);
                    cassandraProfileButton.setBounds(cassandraStats.getX(), gap * 10, 300, 370);
                    returnButton.setBounds(30, 940, 450, 110);
                } else {
                    chooseYourChampion.setBounds((screenWidth / 2) - 300, gap, 600, 100);
                    drakeStats.setBounds((screenWidth / 4) - (width + addWidth / 2), height * 12, width + addWidth, height * 2);
                    drakesGender.setBounds(drakeStats.getX(), gap + drakeStats.getY() + drakeStats.getHeight() / 2, width, height);
                    drakeHealthPoints.setBounds(drakesGender.getX(), gap * 2 + drakesGender.getY(), width, height);
                    drakeDamage.setBounds(drakeHealthPoints.getX(), gap * 2 + drakeHealthPoints.getY(), width, height);
                    cassandraStats.setBounds(screenWidth - drakeStats.getX() - drakeStats.getWidth(), height * 12, width + addWidth, height * 2);
                    cassandraGender.setBounds(cassandraStats.getX(), gap + cassandraStats.getY() + cassandraStats.getHeight() / 2, width, height);
                    cassandraHealthPoints.setBounds(cassandraGender.getX(), gap * 2 + cassandraGender.getY(), width, height);
                    cassandraDamage.setBounds(cassandraHealthPoints.getX(), gap * 2 + cassandraHealthPoints.getY(), width, height);
                    drakeProfileButton.setBounds(drakeStats.getX(), gap * 10, 300, 370);
                    cassandraProfileButton.setBounds(cassandraStats.getX(), gap * 10, 300, 370);
                    returnButton.setBounds(30, 940, 450, 110);
                }

            }
        });


    }
    /*private void reveal(JComponent[] components){
        for(int i=0;i<components.length;i++){
            components[i].setBounds(components[i].getX(),components[i].getY(),components[i].getWidth(),components[i].getHeight());
        }

    }*/

}