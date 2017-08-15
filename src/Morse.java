
import java.util.ArrayList;

public class Morse {

    public static void main(String[] args) {
        String morseCode = ".... . -.--   .--- ..- -.. .";
        String[] txt = morseCode.trim().replace("   ", "  ").split(" ");
        for (String l : txt) {
            System.out.println(l);
        }
    }
    
    public static String decode(String txt){
        switch (txt.length()) {
            
        }
        return null;
    }
}
