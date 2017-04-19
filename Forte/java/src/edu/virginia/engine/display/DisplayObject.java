package edu.virginia.engine.display;
import edu.virginia.engine.util.Position;
import edu.virginia.engine.events.EventDispatcher;
import edu.virginia.engine.controller.GamePad;
import edu.virginia.engine.controller.GamePadComponent;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;

/**
 * A very basic display object for a java based gaming engine
 *
 * */
public class DisplayObject extends EventDispatcher {

	/* All DisplayObject have a unique id */
	private String id;


	/* The image that is displayed by this object */
	private BufferedImage displayImage;
	private boolean visible = true;
	private Position position = new Position(0,0);
	private Position pivotPoint = new Position(0,0);
	private double scaleX = 1;
	private double scaleY = 1;
	private double rotation = 0;
	private float alpha = 1;
	private DisplayObject parent;
	private ArrayList<DisplayObject> children = new ArrayList<DisplayObject>();
	private boolean physicsEnabled = false;
	private int velX = 0;
	private int velY = 0;
	private static final int tile_width = 97;
	private static final int tile_height = 141;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		Position p = new Position(0,0);
		this.setPosition(p);
		this.setPivotPoint(p);
		this.setId(id);
	}

	public DisplayObject(String id, String fileName) {
		Position p = new Position(0,0);
		this.setPosition(p);
		this.setPivotPoint(p);
		this.setId(id);
		this.setImage(fileName);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setPosition(int x, int y) {
		Position p = new Position(x, y);
		this.position = p;
	}

	public void rePosition(int x, int y) {
		int a = position.getX() + x;
		int b = position.getY() + y;
		setPosition(a, b);
	}

	public Position getPivotPoint() {
		return pivotPoint;
	}

	public void setPivotPoint(Position pivotPoint) {
		this.pivotPoint = pivotPoint;
	}

	public void setPivotPoint(int x, int y) {
		Position p = new Position(x, y);
		this.pivotPoint = p;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public double getScaleX() {
		return scaleX;
	}

	public void setScaleXY(double scale) {
		this.scaleX = this.scaleY = scale;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public DisplayObject getParent() {
		return this.parent;
	}

	public void setParent(DisplayObject d) {
		this.parent = d;
	}

	public ArrayList<DisplayObject> getChildren() {
		return children;
	}

	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public int getScaledWidth() {
		if(displayImage == null) return 0;
		return (int) (displayImage.getWidth() * scaleX);
	}

	public int getScaledHeight() {
		if(displayImage == null) return 0;
		return (int) (displayImage.getHeight() * scaleY);
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	public void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}

	public boolean isPhysicsEnabled() {
		return physicsEnabled;
	}

	public void setPhysicsEnabled(boolean physicsEnabled) {
		this.physicsEnabled = physicsEnabled;
	}

	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}

	public void addChild(DisplayObject d) {
		children.add(d);
		d.setParent(this);
		d.setPivotPoint(this.pivotPoint);
	}

	public void addChildAt(int i, DisplayObject d) {
		if ((i > 0) && (i < children.size())) {
			children.add(d);
			d.setParent(this);
			d.setPivotPoint(this.pivotPoint);
		}
	}

	public void removeChild(DisplayObject d) {
		children.remove(d);
	}

	public void removeChildAt(int i, DisplayObject d) {
		if ((i > 0) && (i < children.size())) {
			children.remove(i);
		}
	}

	public void removeAll() {
		children.clear();
	}

	public boolean contains(DisplayObject d) {
		return children.indexOf(d) != -1;
	}

	public int getPosX() {
		return position.getX();
	}

	public int getPosY() {
		return position.getY();
	}

	/**
	 * Returns an array containing vertices of the hitbox
	 * Hitbox array visualized (0 = top-left, 1 = top-right, etc.):
	 * [0]--[1]
	 *  |	 |
	 * [2]--[3] 
	 * */
	public Position[] getHitbox() {
		Position[] hitbox = new Position[4];
		hitbox[0] = this.getPosition();
		hitbox[1] = new Position( hitbox[0].getX() + (int)(this.getScaledWidth()), hitbox[0].getY() );
		hitbox[2] = new Position( hitbox[0].getX(), (int)(hitbox[0].getY() + this.getScaledHeight()) );
		hitbox[3] = new Position( hitbox[1].getX(), hitbox[2].getY() );
		return hitbox;
	}
	/**
	 * Return true is there is a collision
	 * */
	public boolean collidesWith(DisplayObject d) {
		Position[] h1 = this.getHitbox();
		Position[] h2 = d.getHitbox();

		if (h1[0].getX() > h2[1].getX() || h1[1].getX() < h2[0].getX()) return false;
		if (h1[0].getY() > h2[2].getY() || h1[2].getY() < h2[0].getY()) return false;
		return true;
	}

	/**
	 * More specific version of collidesWith method
	 * @param x - horizontal offset (positive = moving right)
	 * @param y - vertical offset (positive = moving down)
	 */
	public boolean collidesWith(DisplayObject d, int x, int y) {
		Position[] h1 = this.getHitbox();
		Position[] h2 = d.getHitbox();

		if (h1[0].getX() + x > h2[1].getX() || h1[1].getX() + x < h2[0].getX()) return false;
		if (h1[0].getY() + y > h2[2].getY() || h1[2].getY() + y < h2[0].getY()) return false;
		return true;
	}

	public boolean nearby(DisplayObject d) {
		if (Math.abs(d.getPosX() - this.getPosX()) < this.getScaledWidth() + d.getScaledWidth()) {
			if (Math.abs(d.getPosY() - this.getPosY()) < this.getScaledHeight() + d.getScaledHeight())
				return true;
		}
		return false;
	}

	public BufferedImage getSprite(int xGrid, int yGrid) {

		if (displayImage == null) {
			this.setImage("Spritesheet.png");
		}

		return displayImage.getSubimage(xGrid * tile_width, yGrid * tile_height, tile_width, tile_height);
	}

	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<String> pressedKeys, ArrayList<GamePad> gamePads) {

	}

	public void keyPressed(KeyEvent e) {

	}

	public void MouseClicked(MouseEvent e){

	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {

		if (displayImage != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			if (isVisible()) {
				g2d.drawImage(displayImage, 0, 0,
						(int) (getUnscaledWidth() * scaleX),
						(int) (getUnscaledHeight() * scaleY), null);
			}

			for (DisplayObject child : children) {
				child.draw(g);
			}
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.translate(position.getX(), position.getY());
		g2d.rotate(Math.toRadians(rotation), pivotPoint.getX(), pivotPoint.getY());
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		g2d.rotate(Math.toRadians(-rotation), pivotPoint.getX(), pivotPoint.getY());
		g2d.translate(-position.getX(), -position.getY());
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}

}