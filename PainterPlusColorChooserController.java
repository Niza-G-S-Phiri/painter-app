
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class PainterPlusColorChooserController {

    private enum PenSize{
        SMALL(2),
        MEDIUM(6),
        LARGE(10);

        int radius;
        PenSize(int radius){
            this.radius = radius;
        }

        public int getRadius(){
            return radius;
        }
    }

    @FXML
    private Slider redSlider;

    @FXML
    private Slider greenSlider;

    @FXML
    private Slider blueSlider;

    @FXML
    private Slider alphaSlider;

    @FXML
    private TextField redTextField;

    @FXML
    private TextField greenTextField;

    @FXML
    private TextField blueTextField;

    @FXML
    private TextField alphaTextField;

    @FXML
    private Rectangle colorSampleArea;

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private ToggleGroup sizeToggleButton;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton largeRadioButton;

    @FXML
    private Pane drawingAreaPane;

    //RGBA color system currently choosen
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1;

    //store currently selected brush color and
    private PenSize radius = PenSize.MEDIUM;
    private Color brushColor = Color.rgb(red, green, blue, alpha);

    //initialize controls
    public void initialize(){
        //size radio button
        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);

        //bind the sliders to the appropriate textfield in the color chooser section
        redTextField.textProperty().bind( redSlider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(greenSlider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind(blueSlider.valueProperty().asString("%.0f"));
        alphaTextField.textProperty().bind(alphaSlider.valueProperty().asString("%.1f"));

        //add listerners to the "color" sliders and set the fill/pen color using the rgb()
        redSlider.valueProperty().addListener(
            new ChangeListener<Number>(){
                public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
                    red = newValue.intValue();
                    colorSampleArea.setFill(Color.rgb(red, green, blue, alpha));
                    brushColor = Color.rgb(red, green, blue, alpha);
                }
            }
            );
        greenSlider.valueProperty().addListener(
            new ChangeListener<Number>(){
                public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
                    green = newValue.intValue();
                    colorSampleArea.setFill(Color.rgb(red, green, blue, alpha));
                    brushColor = Color.rgb(red, green, blue, alpha);
                }
            }
            );
        blueSlider.valueProperty().addListener(
            new ChangeListener<Number>(){
                public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
                    blue = newValue.intValue();
                    colorSampleArea.setFill(Color.rgb(red, green, blue, alpha));
                    brushColor = Color.rgb(red, green, blue, alpha);
                }
            }
            );
        alphaSlider.valueProperty().addListener(
            new ChangeListener<Number>(){
                public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
                    alpha = newValue.doubleValue();
                    colorSampleArea.setFill(Color.rgb(red, green, blue, alpha));
                    brushColor = Color.rgb(red, green, blue, alpha);
                }
            }
            );
    }

    @FXML
    //clearing the entire drawing area
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    //creating Circle objects and attaching them to the drawing area as the user dragges the mouse
    void drawingAreaMouseDragged(MouseEvent event) {
        Circle newChild = new Circle(event.getX(), event.getY(), radius.getRadius(), brushColor);
        drawingAreaPane.getChildren().add(newChild);
    }

    @FXML
    //setting the size of the pen
    void sizeRadioButtonSelected(ActionEvent event) {
        radius = (PenSize) sizeToggleButton.getSelectedToggle().getUserData();
    }

    @FXML
    //remove the last piece drawn from the drawing area
    void undoButtonPressed(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();
        if(count > 0){
            drawingAreaPane.getChildren().remove(count - 1);
        }
    }

}
