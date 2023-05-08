import java.awt.*;
public class Constants {
    public static final int MainMenuWindowWidth=400;
    public static final int MainMenuWindowHeight=600;
    public static final int MainMenuWindowX=0;
    public static final int MainMenuWindowY=0;
    public static final int MainMenuButtonWidth=100;
    public static final int MainMenuButtonHeight=30;
    public static final int MainMenuExitReturnButtonWidth=200;
    public static final int MainMenuExitReturnButtonHeight=60;
    public static final int MainMenuGap=10;

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();        // יש בעיה כי מסך מלא הוא בגודל אחר
    //כל מחשב אז צריך להגדיר לפי קבועים
    public static final int FullScreenMenuWidth=screenSize.width;
    public static final int FullScreenMenuHeight=screenSize.height;
    private static final int FullScreenTitleUsernameWidth=400;
    private static final int FullScreenTitleUsernameHeight=48;
    public static final int FullScreenPlayButtonWidth=300;
    public static final int FullScreenPlayButtonHeight=100;
    public static final int FullScreenExitReturnButtonWidth=250;
    public static final int FullScreenExitReturnButtonHeight=110;
    //public static final int FullScreenProfileButtonWidth;
    //public static final int FullScreenProfileButtonHeight;
    public static final int FullScreenMenuGap=30;




}

