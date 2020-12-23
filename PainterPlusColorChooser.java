
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class PainterPlusColorChooser extends Application{
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException{
		stage.setTitle("Painter Plus Color Chooser");
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("PainterPlusColorChooser.fxml"))));
		stage.show();
	}
}
