package screen;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import game.Board;
import game.GameTextures;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * The class App extends the JavaFX Application class
 * which gives it access to all its functionalities.
 * 
 * 
 * @author AvoQueen
 * @version 0.1
 */

public class App extends Application {

	private static Group sRoot; //container for the nodes
	private static Scene sPrimaryScene; //scene that is put on the primary stage.

	private static GraphicsContext sCtx;
	private static Canvas sCanvas;

	private static Board sPrimaryBoard;

	private static int sWidth = 400;
	private static int sHeight = 400;
	private static String sTitle = "JFX Application";
	
	private App() {}

	/**
	 * @param width		Width of the application-window
	 * @param height	Height of the application-window
	 * @param title		Title of the application
	 */
	public static void initialize(int width, int height, String title) {
		App.sWidth = width;
		App.sHeight = height;
		App.sTitle = title;
	}

	/**
	 * Overrides the start() function that is inherited from the Application class.
	 * This function sets up the primaryScene and adds it to the stage as well as
	 * initializes the graphics, game-variables and event-handlers.
	 * @param primaryStage	The primary stage that shows the Scene (primaryScene)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		//setup the root-container
		App.setRoot(new Group());
		//create a scene 
		App.setPrimaryScene(new Scene(sRoot, sWidth, sHeight));

		//initialization and setup
		App.initGraphics();
		App.initGame();
		App.setupEvents();
		
		//add the scene to the stage
		primaryStage.setTitle(sTitle);
		primaryStage.setScene(sPrimaryScene);

		//render the board and show the application
		sPrimaryBoard.render();
		primaryStage.show();
	}

	/**
	 * Is called by the start() function and initializes and sets up the needed event-handlers.
	 */
	private static void setupEvents() {
		//Adds or Removes an active cell from the GOLAD board (drag).
		App.sPrimaryScene.setOnMouseDragged(event -> {
			App.sPrimaryBoard.handleMouseClick(event.getSceneX(), event.getSceneY(),
					event.getButton() == MouseButton.PRIMARY);
			App.sPrimaryBoard.render();
		});

		//Adds or Removes an active cell from the GOLAD board (single click).
		App.sPrimaryScene.setOnMouseClicked(event -> {
			App.sPrimaryBoard.handleMouseClick(event.getSceneX(), event.getSceneY(),
					event.getButton() == MouseButton.PRIMARY);
			App.sPrimaryBoard.render();
		});

		//iterates one evolution-step.
		App.sPrimaryScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				App.sPrimaryBoard.updateCells();
				App.sPrimaryBoard.render();
			}
		});
	}

	/**
	 * Is called by the start() function and initializes the Canvas, GraphicsContext
	 * and the game textures.
	 */
	private static void initGraphics() {
		//setting up the Canvas and the GraphicsContext
		App.setC(new Canvas(sWidth, sHeight));	
		App.setCtx(sCanvas.getGraphicsContext2D());
		
		//adding the Canvas to root in order to render it on the primaryStage
		App.sRoot.getChildren().add(sCanvas);
		
		//add a reference to the GraphicsContext to the Board class.
		Board.initCTX(sCtx);
		
		//load the Textures
		GameTextures.initializeTextures(sCtx);
	}

	/**
	 * Is called by the start() function and initializes the board for GOLAD.
	 */
	private static void initGame() {
		//create a new GOLAD board
		App.sPrimaryBoard = new Board(10, 10);
		App.sPrimaryBoard.setDimensions(sWidth, sHeight);
	}

	/**
	 * Runs the launch() function that is inherited from Application.
	 */
	public static void run() {
		launch();
	}

	private static void setRoot(Group sRoot) {
		App.sRoot = sRoot;
	}

	private static void setPrimaryScene(Scene sPrimaryScene) {
		App.sPrimaryScene = sPrimaryScene;
	}

	/**
	 * 
	 * @return the width of the application.
	 */
	public static int getWIDTH() {
		return sWidth;
	}

	/**
	 * 
	 * @param width		set the width of the application-window
	 */
	public static void setWIDTH(int width) {
		sWidth = width;
	}

	/**
	 * 
	 * @return the height of the application.
	 */
	public static int getHEIGHT() {
		return sHeight;
	}

	/**
	 * 
	 * @param height	set the height of the application-window
	 */
	public static void setHEIGHT(int height) {
		sHeight = height;
	}

	/**
	 * 
	 * @return the title of the application.
	 */
	public static String getTITLE() {
		return sTitle;
	}

	/**
	 * 
	 * @param title		set the title of the application
	 */
	public static void setTITLE(String title) {
		sTitle = title;
	}

	private static void setCtx(GraphicsContext sCtx) {
		App.sCtx = sCtx;
	}

	private static void setC(Canvas sCanvas) {
		App.sCanvas = sCanvas;
	}

}
