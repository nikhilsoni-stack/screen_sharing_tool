package com.thinking.machines.pl;
import com.thinking.machines.client.*;
import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.*;
public class MyCanvas extends Canvas  implements MouseListener, MouseMotionListener,KeyListener,MouseWheelListener	
{ 
public MyCanvas() 
{
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setSize(d.width,d.height); 
addMouseListener(this); 
addKeyListener(this);
addMouseWheelListener(this);
}
public void paint(Graphics g)  
{
 super.paint(g);
try
{
g.drawImage(Client.getFrame(),0,0,this); // null ke palce par this he
}catch(Exception ioe)
{
System.out.println("hello"+ioe);
}
}  
 public void mousePressed(MouseEvent e)
{
Client.getMousePressed(e.getXOnScreen(),e.getYOnScreen(),e.getButton());
}
public void mouseReleased(MouseEvent e)
{
Client.getMouseRelease(e.getXOnScreen(),e.getYOnScreen(),e.getButton());
}
public void mouseClicked(MouseEvent e)
{
//Client.getMouseClicked(e.getXOnScreen(),e.getYOnScreen(),e.getButton());
}
public void mouseEntered(MouseEvent e)
{
// do if required
}
  public void mouseExited(MouseEvent e)
{
// do if required
}
public void mouseMoved(MouseEvent e)
{
//Client.getMouseMoved(e.getX(),e.getY());
}
public void mouseDragged(MouseEvent e)
{
//Client.getMouseDragged(e.getX(),e.getY(),e.getButton());
}
 public void mouseWheelMoved(MouseWheelEvent e)
{
Client.getMouseWheel(e.getScrollAmount());
}
public void keyPressed(KeyEvent e) 
{ 
Client.getKeyPressed(e.getKeyCode());
}
 public void keyReleased(KeyEvent e) 
{  
Client.getKeyReleased(e.getKeyCode());
}  
public void keyTyped(KeyEvent e) 
{
Client.getKeyTyped(e.getKeyCode());
}
}
