package sample.animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class ShakeIMP implements Shake{
    private TranslateTransition translateTransition;
    public ShakeIMP(Node node){
        translateTransition=new TranslateTransition(Duration.millis(70), node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
    }
    public void plAnimation(){
        translateTransition.playFromStart();
    }
}
