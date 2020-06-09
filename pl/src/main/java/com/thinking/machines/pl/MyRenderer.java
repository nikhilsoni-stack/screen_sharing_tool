package com.thinking.machines.pl;
import javax.swing.filechooser.*;
import java.io.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
public class MyRenderer implements TableCellRenderer
{
private JLabel jLabel=new JLabel();
public MyRenderer()
{
super();
}
public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int col)
{
//DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
//Component c=renderer.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, col); 
String tmp;
File file=(File)value;
if(file.getName().equals(""))
{
tmp=file.getAbsolutePath();
}
else
{
tmp=file.getName();
}
FileSystemView view=FileSystemView.getFileSystemView();
Icon icon= view.getSystemIcon(file);
jLabel.setIcon(icon);
jLabel.setText(tmp);
return jLabel;
}
}
