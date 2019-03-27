package game;

import java.io.InputStream;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameTextures {
	
	public enum TextureIndex
	{
		ACTIVE,
		DYING,
		REVIVING;
	}
	
	private static Image sActive = null;
	private static Image sDying = null;
	private static Image sReviving = null;
	
	private static GraphicsContext sCtx = null;
	
	private GameTextures() {}
	
	public static void initializeTextures(GraphicsContext ctx)
	{
		GameTextures.sCtx = ctx;
		
		InputStream input;
		
		input = ClassLoader.getSystemResourceAsStream("active.png");
		sActive = new Image(input);
		
		input = ClassLoader.getSystemResourceAsStream("dying.png");
		sDying = new Image(input);
		
		input = ClassLoader.getSystemResourceAsStream("reviving.png");
		sReviving = new Image(input);
	}
	
	public static Image getActive()
	{
		return sActive;
	}
	
	public static Image getDying()
	{
		return sDying;
	}
	
	public static Image getReviving()
	{
		return sReviving;
	}
	
	public static void drawTexture(TextureIndex index, double x, double y, double width, double height)
	{
		switch(index)
		{
		case ACTIVE:
			sCtx.drawImage(sActive, x, y, width, height);
			break;
		case DYING:
			sCtx.drawImage(sDying, x, y, width, height);
			break;
		case REVIVING:
			sCtx.drawImage(sReviving, x, y, width, height);
			break;
		}
	}
	
	public static void drawActive(double x, double y, double width, double height)
	{
		sCtx.drawImage(sActive, x, y, width, height);
	}
	
	public static void drawDying(double x, double y, double width, double height)
	{
		sCtx.drawImage(sDying, x, y, width, height);
	}
	
	public static void drawReviving(double x, double y, double width, double height)
	{
		sCtx.drawImage(sReviving, x, y, width, height);
	}
}
