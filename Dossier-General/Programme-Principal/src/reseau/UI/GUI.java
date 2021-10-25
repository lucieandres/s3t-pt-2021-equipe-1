package UI;
import CD.AppControler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class GUI extends Application {
	
	private static AppControler appC;
	private static long id;
	
	private Button joinUDP,sendUDPM, leaveUDP;
	private TextField tf;
	private TextArea ta;
	private CheckBox cb;
	private ChoiceBox<String> cbNet;
	

	public static void lancement(String[] args, AppControler ac) {
		appC = ac;
		id = appC.getId();
		Application.launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		primaryStage.setTitle("Demo Java Network");
		primaryStage.setWidth(1080);
		primaryStage.setHeight(720);
		
		//Le conteneur principale
		BorderPane root = new BorderPane();
		
		HBox top = new HBox();
		top.setPrefHeight(50);
		top.setBackground(new Background(new BackgroundFill(Color.AQUA,CornerRadii.EMPTY,null)));
		top.setAlignment(Pos.CENTER);
		
		Label title = new Label("Demo Java Network - Server "+id);
		title.setFont(Font.font("Pristina", FontWeight.BOLD, 30));
		top.getChildren().add(title);
		
				
		HBox bottom = new HBox();
		bottom.setPrefHeight(50);
		bottom.setAlignment(Pos.CENTER_RIGHT);
		bottom.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(0), new Insets(0,10,0,0))));
		
				
		Button leave = new Button("Quitter");
		bottom.getChildren().add(leave);
		
		leave.setOnAction(
			event -> 
				Platform.runLater(new Runnable() {
					public void run() {
						appC.exit();
					}
				})
			
		);
				
		VBox center = new VBox();
		center.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,new CornerRadii(0), new BorderWidths(0), new Insets(10,10,0,10))));
		center.setSpacing(10);
		
		HBox serverJ = new HBox();
		serverJ.setSpacing(90);
		
		joinUDP = new Button("Joindre le groupe UDP");
		serverJ.getChildren().add(joinUDP);
		
		leaveUDP = new Button("Quitter le groupe UDP");
		leaveUDP.setDisable(true);
		serverJ.getChildren().add(leaveUDP);
		
		cbNet = new ChoiceBox<>();
		cbNet.setPrefWidth(250);
		cbNet.setItems(FXCollections.observableArrayList(appC.getUpNetworkInterafes()));
		serverJ.getChildren().add(cbNet);
		
		
		cb = new CheckBox("Ignorer ses propres messages multicast");
		serverJ.getChildren().add(cb);
		
		HBox secLine = new HBox();
		secLine.setSpacing(180);
		
		HBox serverM = new HBox();
				
		tf = new TextField();
		tf.setDisable(true);
		serverM.getChildren().add(tf);
		
		sendUDPM = new Button("envoyer un message UDP");
		serverM.getChildren().add(sendUDPM);
		sendUDPM.setDisable(true);
		
		HBox ipBOX = new HBox();
		
		Label ip = new Label("IP : ");
		ip.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		ipBOX.getChildren().add(ip);
		
		Label ipD = new Label(appC.getCurrentIP());
		ipD.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
		ipBOX.getChildren().add(ipD);
		
		secLine.getChildren().add(serverM);
		secLine.getChildren().add(ipBOX);
		
		joinUDP.setOnAction(event -> appC.joinUDPMulticastGroup());
		sendUDPM.setOnAction(event -> appC.sendUDPMessage(tf.getText()));
		tf.setOnAction(event -> appC.sendUDPMessage(tf.getText()));
		leaveUDP.setOnAction(event -> appC.leaveUDPMulticastGroup());
		cbNet.getSelectionModel().selectedItemProperty().addListener( 
				(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				-> {appC.setPreferredNetworkInterafe(newValue);
				ipD.setText(appC.getCurrentIP());}
		);
		
		
		ta = new TextArea();
		ta.setEditable(false);
		ta.setPrefSize(1000, 500);
		
		appC.setGUI(this);
		
		center.getChildren().add(serverJ);
		center.getChildren().add(secLine);
		center.getChildren().add(ta);
		
		root.setTop(top);
		root.setBottom(bottom);
		root.setCenter(center);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public void setConnected() {
		sendUDPM.setDisable(false);
		tf.setDisable(false);
		joinUDP.setDisable(true);
		leaveUDP.setDisable(false);
		cbNet.setDisable(true);
	}

	public void setDisconnected() {
		sendUDPM.setDisable(true);
		tf.setDisable(true);
		tf.clear();
		joinUDP.setDisable(false);
		leaveUDP.setDisable(true);
		cbNet.setDisable(false);
	}

	public void displayLog(String s) {
		ta.appendText(s);
	}

	public boolean isLocalMsgSkip() {
		return cb.isSelected();
	}
}

