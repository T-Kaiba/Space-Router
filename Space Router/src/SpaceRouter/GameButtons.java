package SpaceRouter;

import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;

/**
 * Class for the buttons used in the game
 */

public class GameButtons extends Button {

    public GameButtons(String text){

        super(text);

        this.setOnMouseEntered(mouseEvent -> {

            this.setEffect(new Reflection());
            this.setStyle("-fx-font-size: 25px;");
        });
        this.setOnMouseExited(mouseEvent -> {

            this.setEffect(null);
            this.setStyle("-fx-font-size: 14px;");
        });

    }

}
