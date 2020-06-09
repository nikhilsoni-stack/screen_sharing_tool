package com.thinking.machines.pl;
import java.util.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;


public class FileModel extends AbstractTableModel
{
public static String finalPath="";
public static boolean status=true;
private Stack<LinkedList> stack=new Stack<>();
private String [] title={"File/Folder"};
private File[] files;
private LinkedList<File> linkedList;
public FileModel()
{
initializeDataStructure();
}
public void initializeDataStructure()
{
linkedList=new LinkedList<>();
files=File.listRoots();
for(File file:files)
{
linkedList.add(file);
}
}
public int getRowCount()
{
return linkedList.size();
}
public int getColumnCount()
{
return title.length;
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public Object getValueAt(int rowIndex,int columnIndex)
{
return linkedList.get(rowIndex);
}
public Class getColumnClass(int columnIndex)
{
return File.class;
}
// application specifict methods


public  String updateModel(int index)
{
File file=linkedList.get(index);
if(file.isFile())
{
return finalPath;
}
stack.push(linkedList);
linkedList=new LinkedList<>();
files=file.listFiles();
for(File ff:files)
{
linkedList.add(ff);
}
finalPath=file.getAbsolutePath();
return finalPath;
}

public String goBack()
{
int x=finalPath.lastIndexOf("\\");
if(finalPath.equals("")) 
{
return finalPath;
}
if(finalPath.endsWith(":\\"))
{
linkedList=stack.pop();
finalPath="";
return finalPath;
}
else if(x==2)
{
linkedList=stack.pop();
finalPath=finalPath.substring(0,x+1);
return finalPath;
}
else
{
linkedList=stack.pop();
finalPath=finalPath.substring(0,x);
return finalPath;
}
//linkedList=stack.pop();
}
public File getFileAt(int index)
{
return linkedList.get(index);
}

}
