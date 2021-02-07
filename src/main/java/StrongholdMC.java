import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.stage.WindowEvent;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import java.io.FileInputStream;
import java.util.ArrayList;

public class StrongholdMC extends Application implements NativeKeyListener {

    private TextField firstThrowInput;
    private TextField firstThrowInputEye;
    private TextField secondThrowInput;
    private TextField secondThrowInputEye;

    private Text resultText;
    private Text resultCertaintyDeg;
    private Text resultBlocksAway;

    private boolean pressedF3;

    ArrayList<TextField> textFields;
    private int globalCounter;

    private CartesianCalculator calculator;

    @Override
    public void start(Stage window) throws Exception {
        // WIP
        globalCounter = 0;
        pressedF3 = false;
        textFields = new ArrayList<>();
        calculator = new CartesianCalculator();


        // Determine window style
        double size = 500; // height 499
        window.setHeight(size);
        window.setWidth(size);
        window.setMaxHeight(size);
        window.setMaxWidth(size);
        window.setMinHeight(size);
        window.setMinWidth(size);
        window.initStyle(StageStyle.UTILITY);
        window.setAlwaysOnTop(true);


        // Loading resources
        FileInputStream inputStream = new FileInputStream("C:\\Users\\48606\\IdeaProjects\\StrongholdMC\\src\\main\\resources\\bg2.png");
        FileInputStream fontStream = new FileInputStream("C:\\Users\\48606\\IdeaProjects\\StrongholdMC\\src\\main\\resources\\fonts\\MinecraftRegular.otf");
        FileInputStream strongholdStream = new FileInputStream("C:\\Users\\48606\\IdeaProjects\\StrongholdMC\\src\\main\\resources\\fonts\\MinecraftRegular.otf");
        FileInputStream degreeStream = new FileInputStream("C:\\Users\\48606\\IdeaProjects\\StrongholdMC\\src\\main\\resources\\fonts\\MinecraftRegular.otf");


        // Creating Pane and setting background
        BackgroundImage myBI = new BackgroundImage(new Image(inputStream,500,499,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(myBI));


        // Initializing fields
        Font bgFont = Font.loadFont(fontStream, 24);
        Font coordFont = Font.loadFont(strongholdStream, 40);
        Font degreeCert = Font.loadFont(degreeStream, 24);

        Text firstThrowText = new Text("First throw and pearl XZ");
        firstThrowText.setFont(bgFont);
        firstThrowText.setFill(Color.WHITESMOKE);
        TextFlow textFlowFirst = new TextFlow();
        textFlowFirst.getChildren().add(firstThrowText);
        textFlowFirst.setTextAlignment(TextAlignment.CENTER);

        firstThrowInput = new TextField("");
        firstThrowInputEye = new TextField("");
        HBox firstData = new HBox();
        firstData.getChildren().add(firstThrowInput);
        firstData.getChildren().add(firstThrowInputEye);
        firstData.setAlignment(Pos.CENTER);

        Text secondThrowText = new Text("Second throw and pearl XZ");
        secondThrowText.setFont(bgFont);
        secondThrowText.setFill(Color.WHITESMOKE);
        TextFlow textFlowSecond = new TextFlow();
        textFlowSecond.getChildren().add(secondThrowText);
        textFlowSecond.setTextAlignment(TextAlignment.CENTER);

        secondThrowInput = new TextField("");
        secondThrowInputEye = new TextField("");
        HBox secondData = new HBox();
        secondData.getChildren().add(secondThrowInput);
        secondData.getChildren().add(secondThrowInputEye);
        secondData.setAlignment(Pos.CENTER);

        resultText = new Text("0 - 0 - 0");
        resultText.setFont(coordFont);
        resultText.setFill(Color.WHITESMOKE);
        TextFlow resultTextFlow = new TextFlow();
        resultTextFlow.setPadding(new Insets(72,0,0,0));
        resultTextFlow.getChildren().add(resultText);
        resultTextFlow.setTextAlignment(TextAlignment.CENTER);

        resultCertaintyDeg = new Text("0");
        resultCertaintyDeg.setFont(degreeCert);
        resultCertaintyDeg.setFill(Color.WHITESMOKE);
        resultBlocksAway = new Text("0");
        resultBlocksAway.setFont(degreeCert);
        resultBlocksAway.setFill(Color.WHITESMOKE);

        HBox belowCoordsData = new HBox();
        belowCoordsData.getChildren().add(resultCertaintyDeg);
        belowCoordsData.getChildren().add(resultBlocksAway);
        belowCoordsData.setAlignment(Pos.CENTER);
        belowCoordsData.setSpacing(20);


        // Initializing and adding to container
        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(50, 0, 0 , 0));
        vbox1.setSpacing(10);
        vbox1.getChildren().add(textFlowFirst);
        vbox1.getChildren().add(firstData);
        vbox1.getChildren().add(textFlowSecond);
        vbox1.getChildren().add(secondData);
        vbox1.getChildren().add(resultTextFlow);
        vbox1.getChildren().add(belowCoordsData);
        vbox1.setAlignment(Pos.TOP_CENTER);


        // Adding text fields to a list used to iterate inputs
        textFields.add(firstThrowInput);
        textFields.add(firstThrowInputEye);
        textFields.add(secondThrowInput);
        textFields.add(secondThrowInputEye);


        // Global listener
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(this);


        // Unhook key listening on application close
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException e1) {
                    e1.printStackTrace();
                }
            }
        });


        // Finalizing
        borderPane.setCenter(vbox1);
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.setTitle("StrongholdMC");
        window.show();
    }

    public static void main(String[] args) {
        launch(StrongholdMC.class);
    }

    public static double[] readInput(String input) {
        String[] arr = input.split(" ");
        double[] result = new double[2];
        result[0] = Double.valueOf(arr[6]);
        result[1] = Double.valueOf(arr[8]);
        return result;
    }

    public static String readClipboard() throws Exception {
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_F3) {
            this.pressedF3 = true;
        }

        // Copy to the program on F3 + C
        if (e.getKeyCode() == NativeKeyEvent.VC_C) {
            if (pressedF3 == true) {
                try {
                    Thread.sleep(100);
                    String clipboard = readClipboard();
                    try {
                        double[] toPaste = readInput(clipboard);
                        textFields.get(globalCounter).setText(toPaste[0] + "," + toPaste[1]);

                        if (globalCounter == 3) {
                            // Get data
                            String firstData = firstThrowInput.getText();
                            String firstDataEye = firstThrowInputEye.getText();
                            String secondData = secondThrowInput.getText();
                            String secondDataEye = secondThrowInputEye.getText();

                            // Calculate stronghold location
                            double[] strongholdLocation = calculator.calculateStronghold(firstData, firstDataEye, secondData, secondDataEye);
                            int res1 = (int) Math.round(strongholdLocation[0]);
                            int res2 = (int) Math.round(strongholdLocation[1]);

                            // Calculate angle of intersection
                            int resultDeg = (int) Math.round(strongholdLocation[2]);

                            // Calculate blocks away
                            int resultBlocks = (int) Math.round(calculator.calculateBlocksAway(secondDataEye, strongholdLocation[0], strongholdLocation[1]));

                            // Paste results
                            resultText.setText(res1 + " 0 " + res2);
                            resultCertaintyDeg.setText("" + resultDeg + " degrees");
                            resultBlocksAway.setText("" + resultBlocks + " blocks away");

                            // Clean fields
                            for (TextField placeholder : textFields) {
                                placeholder.setText("");
                            }
                        }

                        // Iterate input field counter
                        if (globalCounter == 3) {
                            globalCounter = 0;
                        } else {
                            globalCounter++;
                        }

                    } catch (Exception exp) {
                        System.out.println("Error");

                        for (TextField placeholder : textFields) {
                            placeholder.setText("ERROR");
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_F3) {
            this.pressedF3 = false;
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        //System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }
}
