package com.thinking.machines.pl;
import com.thinking.machines.client.*;
import java.io.File;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
public class FileBrowser extends JPanel implements ActionListener
{
private JProgressBar jb;
private String fileToSend=null;
private String fileToReceive=null;
private JTree tree,serverTree;
private JPanel northPanel,leftPanel,rightPanel,southPanel,backPanel1,backPanel2;
private JScrollPane jsp,serverjsp;
private  JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
private JButton reciveButton,sendButton,backButton1,backButton2;
private JTable table,serverTable;
private FileModel fileModel;
private ServerFileModel serverFileModel;
private JTextField addBar1,addBar2;

public FileBrowser()
{
initComponent();
viewMode();
actionListener();
setApp();
}

public void setApp()
{
table.getColumnModel().getColumn(0).setPreferredWidth(450+55);
table.setRowHeight(55);
table.getTableHeader().setFont(new Font("Verdana",Font.BOLD,14));
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
serverTable.getColumnModel().getColumn(0).setPreferredWidth(450+55);
serverTable.setRowHeight(55);
serverTable.getTableHeader().setFont(new Font("Verdana",Font.BOLD,14));
serverTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
backPanel1.setLayout(new FlowLayout());
backPanel1.add(backButton1);
backPanel1.add(addBar1);
backPanel2.setLayout(new FlowLayout());
backPanel2.add(backButton2);
backPanel2.add(addBar2);
leftPanel.setLayout(new BorderLayout());
leftPanel.add(jsp,"Center");
leftPanel.add(backPanel1,"North");
rightPanel.setLayout(new BorderLayout());
rightPanel.add(serverjsp,"Center");
rightPanel.add(backPanel2,"North");
splitPane.setLeftComponent(leftPanel);
splitPane.setRightComponent(rightPanel);
splitPane.setResizeWeight(0.5);
northPanel.add(sendButton);
northPanel.add(reciveButton);
setLayout(new BorderLayout());
add(splitPane,"Center");
add(northPanel,"North");
}
public void initComponent()
{
jb=new JProgressBar(0,100);
fileModel=new FileModel();
serverFileModel=new ServerFileModel();
table=new JTable(fileModel);
table.setDefaultRenderer(File.class, new MyRenderer());
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

serverTable=new JTable(serverFileModel);  // yaha change hoga
serverTable.setDefaultRenderer(File.class, new MyRenderer());
serverjsp=new JScrollPane(serverTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
serverTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
reciveButton=new JButton("recive");
sendButton=new JButton("Send");
backButton1=new JButton("<---");
backButton2=new JButton("<---");
backPanel1=new JPanel();
backPanel2=new JPanel();
addBar1=new JTextField(38);
addBar2=new JTextField(37);
addBar1.setEnabled(false);
addBar2.setEnabled(false);
northPanel=new JPanel();
southPanel=new JPanel();
leftPanel=new JPanel();
rightPanel=new JPanel();
}

public void actionPerformed(ActionEvent e)
{
if(e.getSource()==backButton1)
{
addBar1.setText(fileModel.goBack());
fileModel.fireTableDataChanged();
}
if(e.getSource()==backButton2)
{
addBar2.setText(serverFileModel.goBack());
serverFileModel.fireTableDataChanged();
}
if(e.getSource()==sendButton)
{
try
{

Client.uploadFile(fileToSend,fileToReceive);
}catch(Exception ee)
{
System.out.println(ee);
}
}
if(e.getSource()==reciveButton)
{
try
{
Client.downloadFile(fileToSend,fileToReceive);
}catch(Exception eee)
{
System.out.println(eee);
}
}
/////////////////////////////////////////////
}
public void viewMode()
{
sendButton.setEnabled(false);
reciveButton.setEnabled(false);
}
///////
public void actionListener()
{
table.addMouseListener(new MouseAdapter() {
public void mousePressed(MouseEvent mouseEvent) {
String string;
String ss="";
File file;
if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) 
{
string=addBar1.getText();
ss=fileModel.updateModel(table.getSelectedRow());
addBar1.setText(ss);
fileModel.fireTableDataChanged();
}

}
});
serverTable.addMouseListener(new MouseAdapter() {
public void mousePressed(MouseEvent mouseEvent) {
String string;
File file;
if (mouseEvent.getClickCount() == 2 && serverTable.getSelectedRow() != -1) 
{
addBar2.setText(serverFileModel.updateModel(serverTable.getSelectedRow()));
serverFileModel.fireTableDataChanged();
}
}
});
sendButton.addActionListener(this);
reciveButton.addActionListener(this);
backButton1.addActionListener(this);
backButton2.addActionListener(this);
table.getSelectionModel().addListSelectionListener(new  ListSelectionListener(){
public void valueChanged(ListSelectionEvent event) 
{
File file;
if (table.getSelectedRow() != -1) 
{
file=fileModel.getFileAt(table.getSelectedRow());
fileToSend=file.getAbsolutePath();
System.out.println(fileToSend);
if(fileToReceive!=null && fileToSend!=null)
{
sendButton.setEnabled(true);
reciveButton.setEnabled(true);
}
}
}
}); 
serverTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
public void valueChanged(ListSelectionEvent event) 
{
File file;
if (serverTable.getSelectedRow() != -1) 
{
file=serverFileModel.getFileAt(serverTable.getSelectedRow());
fileToReceive=file.getAbsolutePath();
System.out.println(fileToReceive);
if(fileToReceive!=null && fileToSend!=null)
{
sendButton.setEnabled(true);
reciveButton.setEnabled(true);
}
}
}
}); 
}
public getProgressBar()
{
sendButton.setEnabled(false);
reciveButton.setEnabled(false);
backButton1.setEnabled(false);
backButton.setEnabled(false);
table.setEnabled(false);
serverTable.setEnabled(false);
// frame ke button ke kam karna he



}


}// class ends