package com.thinking.machines.pl;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

import java.awt.image.*;
import javax.swing.Timer;
public class MainFrame extends JFrame implements ActionListener
{
Timer timer;
private Container c;

private MyCanvas myCanvas;
private JPanel panel;
private JButton button1,button2;
private FileBrowser fileBrowser;
public MainFrame()
{
super("timeViewer");
initComponent();
setApperence();

}
public void initComponent()
{
fileBrowser=new FileBrowser();
c=getContentPane();
panel=new JPanel();
button1=new JButton("Click for FileTransfer");
button2=new JButton("Click for TeamViewer");
button1.addActionListener(this);
button2.addActionListener(this);
myCanvas=new MyCanvas();
timer = new Timer(10000,new ActionListener() {
public void actionPerformed(ActionEvent ae) 
{
myCanvas.repaint();
}
});
timer.start();
}
public void setApperence()
{
setDefaultCloseOperation(EXIT_ON_CLOSE);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setSize(d.width,d.height);  
setLocation(d.width/2-getWidth()/2,d.height/2-getHeight()/2);
setLayout(new BorderLayout());
c.add(button1,"North");
c.add(myCanvas,"Center");
//c.add(button2);
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==button1)
{
c.removeAll();
timer.stop();
c.add(fileBrowser,"Center");
c.add(button2,"North");
SwingUtilities.updateComponentTreeUI(this);
}
if(e.getSource()==button2)
{
timer.start();
c.removeAll();
c.add(myCanvas,"Center");
c.add(button1,"North");
}
}

public static void main(String gg[])
{
new MainFrame().setVisible(true);
}

}