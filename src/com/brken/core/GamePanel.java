package com.brken.core;

import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, MouseListener,
		MouseMotionListener{
	
	private static final long serialVersionUID = 3322151487816977975L;
	private static long loadTime= 4000;
	private long updatesPerSecond;
	
	private Thread t;
	
	private boolean mouseClicked;
	private boolean mouseClickedOld;
	private boolean mouseOnScreen;
	private boolean isPaused;
	
	private int mouseX;
	private int mouseY;
	
	/**
	 * Sets up the panel and essential variables
	 */
	public GamePanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		
		updatesPerSecond= 20;
		
		t= new Thread(this);
		t.start();
	}
	public GamePanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		updatesPerSecond= 20;
		
		t= new Thread(this);
		t.start();
	}
	public GamePanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		updatesPerSecond= 20;
		
		t= new Thread(this);
		t.start();
	}
	
	/**
	 * Getters and Setters
	 */
	
	public boolean isMouseClicked() {
		return mouseClicked;
	}
	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}
	public boolean isMouseClickedOld() {
		return mouseClickedOld;
	}
	public void setMouseClickedOld(boolean mouseClickedOld) {
		this.mouseClickedOld = mouseClickedOld;
	}
	public boolean isMouseOnScreen() {
		return mouseOnScreen;
	}
	public void setMouseOnScreen(boolean mouseOnScreen) {
		this.mouseOnScreen = mouseOnScreen;
	}
	public boolean isPaused() {
		return isPaused;
	}
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	public int getMouseX() {
		return mouseX;
	}
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	public long getUpdatesPerSecond(){
		return updatesPerSecond;
	}
	public void setUpdatesPerSecond(long updatesPerSecond){
		this.updatesPerSecond= updatesPerSecond;
	}
	
	
	/**
	 * Detects and tells where the mouse is, whether it is pressed and if it leaves.
	 * 
	 */
	//
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		setMouseX(e.getX());
		setMouseY(e.getY());
		setMouseOnScreen(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {/**Unimplemented*/
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		mouseMoved(e);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		setPaused(true);
		setMouseOnScreen(false);
		setMouseClicked(false);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		mouseMoved(e);
		setMouseClicked(true);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseMoved(e);
		setMouseClicked(false);
	}
	
	@Override
	public void run() {
		try{
			repaint();
			Thread.sleep(loadTime);
			while(true){
				repaint();
				Thread.sleep(System.currentTimeMillis()%(1000/updatesPerSecond));
			}
		} catch(Throwable e){
			t.start();
		}
	}
	
}
