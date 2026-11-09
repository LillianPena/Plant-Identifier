package PlantIdentifier;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


/**
 * JavaFX App
 */
public class App extends Application {

    File selectedFile;
    private AudioClip plantSong;

    @Override
    public void start(Stage stage) {
        plantSong = new AudioClip(getClass().getResource("/PlantSong.mp3").toExternalForm());

        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        var AIOutput = new TextArea();
        AIOutput.setEditable(false);
        AIOutput.setWrapText(true);
        AIOutput.setPromptText("Plant name and if you can eat it will appear here...");

        var uploadButton = new Button("Upload Image");
        uploadButton.setOnAction(e ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select an Image");
            fileChooser.getExtensionFilters()
                    .add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg", "*jpeg", "*webp"));
            selectedFile = fileChooser.showOpenDialog(stage);
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        });

        var analyzeButton = new Button("What is it and can I eat it?");
        analyzeButton.setOnAction(e ->{
            try{
                AIOutput.setText(AIHandler.analyzeImage(selectedFile.getAbsolutePath()));
            }catch(Exception ex){
                AIOutput.setText("Error analyzing image: " + ex.getMessage());
            }
        });

        var musicButton = new Button("wanna hear ai sing?");
        musicButton.setOnAction(e ->{
            plantSong.play();
        });

        var vbox = new VBox(uploadButton, analyzeButton, imageView, AIOutput, musicButton);
        var scene = new Scene(vbox, 600, 650);
        stage.setScene(scene);
        stage.show();
        //make the box for the user to put the pic in

        //make the box the output will be in

        //make the submit button

        //put output in output box
    }

    public static void main(String[] args) {
        launch();
    }

}