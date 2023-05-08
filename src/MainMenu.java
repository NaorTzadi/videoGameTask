import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class MainMenu extends JFrame {
    public static String username;
    MainMenu(){
        Utility utility=new Utility();
        ImageIcon symbol =new ImageIcon("resources/skull3.jpg");
        this.setSize(Constants.MainMenuWindowWidth,Constants.MainMenuWindowHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        utility.setWindowIcon(this);
        this.setTitle("fallout");
        this.setLayout(null);
        this.setResizable(false);
        this.setFocusable(true);
        this.setUndecorated(true);

        JLabel skull=new JLabel(symbol);
        this.setContentPane(skull);

        JLabel title=new JLabel("fallout: the new order");
        title.setBounds(Constants.MainMenuWindowX+Constants.MainMenuGap, Constants.MainMenuWindowY, Constants.MainMenuButtonWidth*3, Constants.MainMenuButtonHeight);
        title.setFont(new Font("Algerian",Font.BOLD,18));
        title.setForeground(Color.YELLOW);
        this.add(title);

        JButton newGameButton=new JButton("NEW GAME");
        newGameButton.setBounds(Constants.MainMenuWindowX+Constants.MainMenuButtonHeight,title.getHeight(),Constants.MainMenuButtonWidth, Constants.MainMenuButtonHeight);
        newGameButton.setBackground(Color.YELLOW);
        newGameButton.setFocusPainted(false);
        this.add(newGameButton);

        JButton continueButton =new JButton("continue");
        continueButton.setBounds(Constants.MainMenuWindowX+Constants.MainMenuButtonHeight*2, newGameButton.getY()+newGameButton.getHeight(),Constants.MainMenuButtonWidth, Constants.MainMenuButtonHeight);
        continueButton.setBackground(Color.ORANGE);
        continueButton.setFocusPainted(false);
        this.add(continueButton);

        JButton settingsButton=new JButton("Settings");
        settingsButton.setBounds(Constants.MainMenuWindowX+Constants.MainMenuButtonHeight*3,continueButton.getY()+continueButton.getHeight(), Constants.MainMenuButtonWidth, Constants.MainMenuButtonHeight);
        settingsButton.setBackground(Color.GREEN);
        settingsButton.setFocusPainted(false);
        this.add(settingsButton);

        JButton guideButton=new JButton("guide");
        guideButton.setBounds(Constants.MainMenuWindowX+Constants.MainMenuButtonHeight*4,settingsButton.getY()+settingsButton.getHeight(), Constants.MainMenuButtonWidth, Constants.MainMenuButtonHeight);
        guideButton.setBackground(Color.white);
        guideButton.setFocusPainted(false);
        this.add(guideButton);

        JButton exitButton=new JButton();
        exitButton.setBounds(Constants.MainMenuGap*2,510,Constants.MainMenuExitReturnButtonWidth,Constants.MainMenuExitReturnButtonHeight);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.black,2));
        this.add(exitButton);

        JButton enterButton=new JButton("ENTER");
        enterButton.setBounds(150,230,Constants.MainMenuButtonWidth,Constants.MainMenuButtonHeight);
        enterButton.setBackground(Color.GRAY);
        enterButton.setVisible(false);
        this.add(enterButton);

        JButton returnButton= new JButton("return");
        returnButton.setBounds(20,510,Constants.MainMenuExitReturnButtonWidth,Constants.MainMenuExitReturnButtonHeight);
        returnButton.setBackground(Color.GREEN);
        returnButton.setVisible(false);
        this.add(returnButton);

        JTextArea promptUserName=new JTextArea("PLAYER NAME: ");
        promptUserName.setEditable(false);
        promptUserName.setBounds(100,60,200,50);
        promptUserName.setForeground(Color.YELLOW);
        promptUserName.setBackground(Color.BLACK);
        promptUserName.setFont(new Font("Ariel",Font.BOLD,26));
        promptUserName.setVisible(false);
        this.add(promptUserName);

        JLabel usernameErrorComment1=new JLabel("* user name must be with no less then 4 characters");
        JLabel usernameErrorComment2=new JLabel("* and no more then 12 characters");
        JLabel usernameErrorComment3=new JLabel("* must contain at least one of:  @ , # , *");
        usernameErrorComment1.setBounds(60,40,300,30);
        usernameErrorComment2.setBounds(100,70,200,30);
        usernameErrorComment3.setBounds(100,100,300,30);
        usernameErrorComment1.setForeground(Color.WHITE);
        usernameErrorComment2.setForeground(Color.WHITE);
        usernameErrorComment3.setForeground(Color.WHITE);
        usernameErrorComment1.setVisible(false);
        usernameErrorComment2.setVisible(false);
        usernameErrorComment3.setVisible(false);
        this.add(usernameErrorComment1);
        this.add(usernameErrorComment2);
        this.add(usernameErrorComment3);

        JTextField usernameTextField=new JTextField();
        usernameTextField.setBounds(150,160,100,30);
        usernameTextField.setEditable(true);
        usernameTextField.setVisible(false);
        this.add(usernameTextField);

        JTextArea usernameText = new JTextArea(username);
        this.add(usernameText);

        JLabel createUsernameCommentLine1=new JLabel("*it appears you are a new player");
        JLabel createUsernameCommentLine2=new JLabel("*press the new game button");
        createUsernameCommentLine1.setBounds(200,40,300,30);
        createUsernameCommentLine2.setBounds(210,70,200,30);
        createUsernameCommentLine1.setForeground(Color.WHITE);
        createUsernameCommentLine2.setForeground(Color.WHITE);
        createUsernameCommentLine1.setVisible(false);
        createUsernameCommentLine2.setVisible(false);
        this.add(createUsernameCommentLine1);
        this.add(createUsernameCommentLine2);

        JLabel description=new JLabel("description:");
        description.setBounds(Constants.MainMenuWindowX+Constants.MainMenuGap,Constants.MainMenuWindowY+Constants.MainMenuGap*4, Constants.MainMenuButtonWidth*2, Constants.MainMenuButtonHeight);
        description.setFont(new Font("Ariel",Font.BOLD,14));
        description.setForeground(Color.YELLOW);
        description.setVisible(false);
        this.add(description);

        JTextArea[] guideTextAreas= utility.getGuideTextAreas();
        for(int i=0;i<guideTextAreas.length;i++){
            this.add(guideTextAreas[i]);
        }
        Rectangle exitButtonBounds=exitButton.getBounds();
        ImageIcon imageIcon1=new ImageIcon("resources/exit.jpg");
        Image exitImage=imageIcon1.getImage();
        int exitImageWidth=exitButtonBounds.width;
        int exitImageHeight=exitButtonBounds.height;
        int originalExitImageWidth=exitImage.getWidth(null);
        int originalExitImageHeight=exitImage.getHeight(null);
        double scaleExitImage=Math.min((double) exitImageWidth/ originalExitImageWidth,(double) exitImageHeight/originalExitImageHeight);
        int newExitImageWidth=(int)(originalExitImageWidth*scaleExitImage);
        int newExitImageHeight=(int)(originalExitImageHeight*scaleExitImage);
        Image scaledExitImage=exitImage.getScaledInstance(newExitImageWidth,newExitImageHeight,Image.SCALE_SMOOTH);
        ImageIcon exitButtonCover=new ImageIcon(scaledExitImage);
        exitButton.setIcon(exitButtonCover);

        JLabel constructionNotification=new JLabel();
        constructionNotification.setBounds(15,Constants.MainMenuWindowHeight/3,400,100);
        ImageIcon constructionIcon=new ImageIcon("resources/construction.png");
        constructionNotification.setIcon(constructionIcon);
        constructionNotification.setVisible(false);
        this.add(constructionNotification);

        JButton[] buttons=new JButton[5];
        buttons[0]=newGameButton;
        buttons[1]=continueButton;
        buttons[2]=settingsButton;
        buttons[3]=guideButton;
        buttons[4]=exitButton;

        ArrayList<JComponent> mainMenuComponentsList=new ArrayList<>();
        mainMenuComponentsList.add(newGameButton);
        mainMenuComponentsList.add(continueButton);
        mainMenuComponentsList.add(settingsButton);
        mainMenuComponentsList.add(settingsButton);
        mainMenuComponentsList.add(guideButton);
        mainMenuComponentsList.add(exitButton);
        mainMenuComponentsList.add(createUsernameCommentLine1);
        mainMenuComponentsList.add(createUsernameCommentLine2);

        ArrayList<JComponent> subMenuComponentsList=new ArrayList<>();
        subMenuComponentsList.add(enterButton);
        subMenuComponentsList.add(returnButton);
        subMenuComponentsList.add(promptUserName);
        subMenuComponentsList.add(usernameErrorComment1);
        subMenuComponentsList.add(usernameErrorComment2);
        subMenuComponentsList.add(usernameErrorComment3);
        subMenuComponentsList.add(createUsernameCommentLine1);
        subMenuComponentsList.add(createUsernameCommentLine2);
        subMenuComponentsList.add(usernameTextField);
        subMenuComponentsList.add(description);
        subMenuComponentsList.add(constructionNotification);
        subMenuComponentsList.add(returnButton);

        newGameButton.addActionListener(e -> {
            clearMainComponents(mainMenuComponentsList);
            enterButton.setVisible(true);
            promptUserName.setVisible(true);
            returnButton.setVisible(true);
            usernameTextField.setVisible(true);
        });

        enterButton.addActionListener(e -> {
            String userInput=usernameTextField.getText();
            if(userInput.length()<4 || userInput.length()>12 || (!userInput.contains("@")&&!userInput.contains("*")&&!userInput.contains("#"))) {
                promptUserName.setVisible(false);
                usernameErrorComment1.setVisible(true);
                usernameErrorComment2.setVisible(true);
                usernameErrorComment3.setVisible(true);
            }else {
                username=usernameTextField.getText();
                utility.setPlayerName(usernameTextField.getText());
                reset(buttons);
                clearSubComponents(subMenuComponentsList,utility);
            }
        });
        
        usernameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    enterButton.doClick();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        continueButton.addActionListener(e -> {
            if(username!=null) {
                utility.setPlayerName(username);
                new FullScreenMenu();
                this.dispose();
            }else{
                createUsernameCommentLine1.setVisible(true);
                createUsernameCommentLine2.setVisible(true);
            }
        });

        settingsButton.addActionListener(e -> {
            clearMainComponents(mainMenuComponentsList);
            constructionNotification.setVisible(true);
            returnButton.setVisible(true);
        });

        guideButton.addActionListener(e -> {
            clearMainComponents(mainMenuComponentsList);
            utility.revealGuideTextLines();
            description.setVisible(true);
            returnButton.setVisible(true);
        });
        returnButton.addActionListener(e -> {
            reset(buttons);
            clearSubComponents(subMenuComponentsList,utility);
        });
        exitButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
        this.setVisible(true);

    }
    public static void clearMainComponents(ArrayList<JComponent> componentsList){
        for (JComponent component : componentsList) {
            component.setVisible(false);
        }
    }
    public static void clearSubComponents(ArrayList<JComponent> componentList, Utility utility){
        for(JComponent component:componentList){
            component.setVisible(false);
        }
        utility.hideGuideTextLines();
    }
    public static void reset(JButton[] array){
        for (JButton button : array) {
            button.setVisible(true);
        }
    }
    public static void refresh(JFrame jFrame){
        jFrame.revalidate();
        jFrame.repaint();
    }

}

