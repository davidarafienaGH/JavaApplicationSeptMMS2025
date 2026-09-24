
package abstraction;

public class MainInterface {
    public static void main(String[] args){
        SmartPhone smartPhone = new SmartPhone();
        
        Nokia3310 nokia3310 = new Nokia3310();
        
        System.out.println("\nSmartPhone interace");
        smartPhone.makeCall();
        smartPhone.playGame();
        smartPhone.playMusic();
        smartPhone.playMovie();
        smartPhone.takePicture();
        smartPhone.connectToWiFi();
        
        System.out.println("====================================");
        
        System.out.println("Nokia3310 interace");
        nokia3310.makeCall();
        nokia3310.playGame();
        nokia3310.playMusic();
    }
}
