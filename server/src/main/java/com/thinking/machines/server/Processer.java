package com.thinking.machines.server;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.InputEvent;
import java.net.*;
import java.util.*;
public class Processer extends Thread
{
private Robot robot;
private Socket client;
public Processer()
{
try
{
robot=new Robot();
}catch(Exception e)
{
System.out.println(e);
}
}
public  void startProcesser(Socket client)
{
this.client=client;
start();

}
public void run()
{
try
{
byte functionToPerform[]=new byte[1];
byte ack[]=new byte[1];
InputStream is=client.getInputStream();
OutputStream os=client.getOutputStream();
int byteCount=is.read(functionToPerform);
ack[0]=79;
os.write(ack,0,1);
os.flush();
if(functionToPerform[0]==101)
{
getFrame(client,is,os,robot);
}
if(functionToPerform[0]==102)
{
getMousedPressed(client,is,os,robot);
}
if(functionToPerform[0]==103)
{
getMousedRelease(client,is,os,robot);
}
if(functionToPerform[0]==104)
{
getMousedClicked(client,is,os,robot);
}if(functionToPerform[0]==105)
{
getMousedMoved(client,is,os,robot);
}
if(functionToPerform[0]==106)
{
getMousedWheel(client,is,os,robot);
}
if(functionToPerform[0]==107)
{
getKeyPressed(client,is,os,robot);
}
if(functionToPerform[0]==108)
{
getKeyRelease(client,is,os,robot);
}
if(functionToPerform[0]==109)
{
getKeyTyped(client,is,os,robot);
}
if(functionToPerform[0]==110)
{
getFiles(client,is,os);
}
if(functionToPerform[0]==111 || functionToPerform[0]==112)
{
uploadDownload(client,is,os);
}
client.close();
}catch(Exception e)
{
System.out.println("asdfad"+e);
}
}// run ends

public static void getFrame(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{
Rectangle rect=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
BufferedImage bufferedImage = robot.createScreenCapture(rect);
ByteArrayOutputStream baos=new ByteArrayOutputStream();
ImageIO.write(bufferedImage,"png",baos);
//byte requestBytes[]=baos.toByteArray();
ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
int requestSize=baos.size();
byte requestSizeInBytes[]=new byte[4];
requestSizeInBytes[0]=(byte)(requestSize >>24);
requestSizeInBytes[1]=(byte)(requestSize >>16);
requestSizeInBytes[2]=(byte)(requestSize >>8);
requestSizeInBytes[3]=(byte)requestSize;
os.write(requestSizeInBytes,0,4);
os.flush();
byte ack[]=new byte[1];
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
int bytesToSend=requestSize;
int chunkSize=1024;
byte chunk[]=new byte[1024];
int i=0;
while(i<bytesToSend)
{
byteCount=bais.read(chunk); 
os.write(chunk,0,byteCount);
os.flush();
i=i+byteCount;

}
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
client.close();
}catch(Exception e)
{
throw new RuntimeException("serious problem:::"+e);
}
} // getFrame ends
public static void getMousedPressed(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();

String splits[]=requestString.split(",");
int x,y,button;
x=Integer.parseInt(splits[0]);
y=Integer.parseInt(splits[1]);
button=Integer.parseInt(splits[2]);


robot.mouseMove(x,y);

if(button==1) 
{
robot.mousePress(InputEvent.BUTTON1_MASK);
}
if(button==2) 
{
robot.mousePress(InputEvent.BUTTON2_MASK);

}
if(button==3) 
{
robot.mousePress(InputEvent.BUTTON3_MASK);
}
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}


}
public static void getMousedRelease(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();

String splits[]=requestString.split(",");
int x,y,button;
x=Integer.parseInt(splits[0]);
y=Integer.parseInt(splits[1]);
button=Integer.parseInt(splits[2]);


robot.mouseMove(x,y);

if(button==1) 
{
robot.mouseRelease(InputEvent.BUTTON1_MASK);
}
if(button==2) 
{
robot.mouseRelease(InputEvent.BUTTON2_MASK);

}
if(button==3) 
{
robot.mouseRelease(InputEvent.BUTTON3_MASK);
}
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMousedClicked(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();

String splits[]=requestString.split(",");
int x,y,button;
x=Integer.parseInt(splits[0]);
y=Integer.parseInt(splits[1]);
button=Integer.parseInt(splits[2]);
robot.mouseMove(x,y);
if(button==1) 
{
robot.mousePress(InputEvent.BUTTON1_MASK);
robot.mouseRelease(InputEvent.BUTTON1_MASK);

}
if(button==2) 
{
robot.mousePress(InputEvent.BUTTON2_MASK);
robot.mouseRelease(InputEvent.BUTTON2_MASK);

}
if(button==3) 
{
robot.mousePress(InputEvent.BUTTON3_MASK);
robot.mouseRelease(InputEvent.BUTTON3_MASK);
}
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMousedMoved(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x,y,button;
x=Integer.parseInt(splits[0]);
y=Integer.parseInt(splits[1]);
robot.mouseMove(x,y);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMousedWheel(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x;
x=Integer.parseInt(splits[0]);
robot.mouseWheel(x);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyPressed(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x;
x=Integer.parseInt(splits[0]);
robot.keyPress(x);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyRelease(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x;
x=Integer.parseInt(splits[0]);
robot.keyRelease(x);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyTyped(Socket client,InputStream is,OutputStream os,Robot robot)
{
try
{

byte requestByte[]=new byte[1024];
int byteCount=is.read(requestByte);
String requestString=new String(requestByte);

byte ack[]=new byte[1];
ack[0]=79;
os.write(ack);
os.flush();
String splits[]=requestString.split(",");
int x;
x=Integer.parseInt(splits[0]);
robot.keyPress(x);
robot.keyRelease(x);
client.close();
os.close();
is.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getFiles(Socket client,InputStream is,OutputStream os)
{
 try
{

LinkedList<File> linkedList=new LinkedList<>();
String filePath;
File[] files;
File file;
byte requestLengthInBytes[]=new byte[4];
int requestLength;
int byteCount;
int bytesToRead;
int bytesToWrite;
byte ack[]=new byte[1];
ByteArrayOutputStream baos;
byte requestBytes[];
byte chunk[]=new byte[1024];
ByteArrayInputStream bais;
ObjectInputStream ois;
ObjectOutputStream oos;
String response;
byte responseBytes[];
byte responseLengthInBytes[];
int responseLength;
int chunkSize;
byteCount=is.read(requestLengthInBytes);
requestLength=(requestLengthInBytes[0] & 0xFF) <<24 | (requestLengthInBytes[1] & 0xFF) <<16 |
(requestLengthInBytes[2] & 0xFF) <<8 | (requestLengthInBytes[3] & 0xFF);
ack[0]=79;
os.write(ack,0,1);
os.flush();
baos=new ByteArrayOutputStream();
bytesToRead=requestLength;
while(bytesToRead>0)
{
byteCount=is.read(chunk);
if(byteCount>0)
{
baos.write(chunk,0,byteCount);
}
bytesToRead-=byteCount;
} 
ack[0]=79;
os.write(ack,0,1);
os.flush();
requestBytes=baos.toByteArray();
bais=new ByteArrayInputStream(requestBytes);
ois=new ObjectInputStream(bais);
file=(File)ois.readObject();
if(file==null)
{
files=File.listRoots();

}
else
{
files=file.listFiles();
}
for(File f:files)
{
linkedList.add(f);
}

baos=new ByteArrayOutputStream();
oos=new ObjectOutputStream(baos);
oos.writeObject(linkedList);
oos.flush();
responseBytes=baos.toByteArray();
responseLength=responseBytes.length;
responseLengthInBytes=new byte[4];
responseLengthInBytes[0]=(byte)(responseLength>>24);
responseLengthInBytes[1]=(byte)(responseLength>>16);
responseLengthInBytes[2]=(byte)(responseLength>>8);
responseLengthInBytes[3]=(byte)responseLength;
os.write(responseLengthInBytes,0,4);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
bytesToWrite=responseLength;
chunkSize=1024;
int i=0;
while(bytesToWrite>0)
{ 
if(bytesToWrite<chunkSize) chunkSize=bytesToWrite;
os.write(responseBytes,i,chunkSize);
os.flush();
i+=chunkSize;
bytesToWrite-=chunkSize;
}
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
client.close();
}

catch(Exception e)
{
System.out.println(e);
}
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void uploadDownload(Socket client,InputStream is,OutputStream os)
{
try
{
int fileNameLength=0;
byte fileNameSizeInBytes[]=new byte[4];
byte requestLengthInBytes[]=new byte[8];
long requestLength;
long fileLength;
FileOutputStream fos;
FileInputStream fis;
int byteCount;
int bytesToRead;
int bytesToWrite;
byte ack[]=new byte[1];
byte requestBytes[];
byte chunk[]=new byte[1024];
byte responseSizeInBytes[]=new byte[8];
int responseLength;
long chunkSize;
String path;
///////////////////
is=client.getInputStream();
byteCount=is.read(fileNameSizeInBytes);
fileNameLength=(fileNameSizeInBytes[0] & 0xFF) <<24 | (fileNameSizeInBytes[1] & 0xFF) <<16 | (fileNameSizeInBytes[2] & 0xFF) <<8 | (fileNameSizeInBytes[3] & 0xFF);
ack[0]=79;
os=client.getOutputStream();
os.write(ack,0,1);
os.flush();
////////
byte fileNameInBytes[]=new byte[fileNameLength];
byteCount=is.read(fileNameInBytes);
String fileName=new String(fileNameInBytes);
os.write(ack,0,1);
os.flush();
////////////// instuction ka part he

byteCount=is.read(fileNameSizeInBytes);
fileNameLength=(fileNameSizeInBytes[0] & 0xFF) <<24 | (fileNameSizeInBytes[1] & 0xFF) <<16 | (fileNameSizeInBytes[2] & 0xFF) <<8 | (fileNameSizeInBytes[3] & 0xFF);
ack[0]=79;
os.write(ack,0,1);
os.flush();

byte instructionInBytes[]=new byte[fileNameLength];
byteCount=is.read(instructionInBytes);
String instruction=new String(instructionInBytes);
os.write(ack,0,1);
os.flush();
////////////////////////////
File file=new File(fileName);
if(instruction.equalsIgnoreCase("upload"))
{
if(file.exists())
{
throw new RuntimeException("file Allready Exists");
//file.delete();
//file.createNewFile();
}
fos=new FileOutputStream(fileName);
byteCount=is.read(requestLengthInBytes);
requestLength=(requestLengthInBytes[0] & 0xFF) <<54 | (requestLengthInBytes[1] & 0xFF) <<48 | (requestLengthInBytes[2] & 0xFF) <<40 | (requestLengthInBytes[3] & 0xFF)<<32| (requestLengthInBytes[4] & 0xFF)<<24 | (requestLengthInBytes[5] & 0xFF)<<16 | (requestLengthInBytes[6] & 0xFF)<<8 | (requestLengthInBytes[7] & 0xFF) ;
ack[0]=79;
os.write(ack,0,1);
os.flush();
for(long i=0;i<requestLength;)
{
byteCount=is.read(chunk);
i=i+byteCount;
fos.write(chunk,0,byteCount);
}
ack[0]=79;
os.write(ack,0,1);
os.flush();
///////////////
fos.close();
client.close();
return;
}
if(instruction.equalsIgnoreCase("download"))
{
if(!file.exists())
{
throw new RuntimeException("File does not exists");
}

fileLength=file.length();
responseSizeInBytes[0]=(byte)(fileLength>> 56);
responseSizeInBytes[1]=(byte)(fileLength>> 48);
responseSizeInBytes[2]=(byte)(fileLength>>40);
responseSizeInBytes[3]=(byte)(fileLength>> 32);
responseSizeInBytes[4]=(byte)(fileLength>> 24);
responseSizeInBytes[5]=(byte)(fileLength>> 16);
responseSizeInBytes[6]=(byte)(fileLength>> 8);
responseSizeInBytes[7]=(byte)fileLength;
os.write(responseSizeInBytes);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
fis=new FileInputStream(fileName);
long bytesToSend=fileLength;
for(long i=0;i<bytesToSend;)
{
byteCount=fis.read(chunk);
i=i+byteCount;
os.write(chunk,0,byteCount);
os.flush();
}
fis.close();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
client.close();
return;
}
client.close();
}catch(Exception e)
{
try
{
client.close();
}catch(Exception ee)
{
throw new RuntimeException(e.getMessage());
}
throw new RuntimeException(e.getMessage());
}

}
}// class ends