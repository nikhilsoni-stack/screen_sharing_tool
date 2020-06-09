package com.thinking.machines.client;
import java.awt.Toolkit;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;
import java.util.*;
public class Client
{

// yaha par process method likna he code x,y,button initial value 0;
public static BufferedImage getFrame()
{
try
{
Socket socket=new Socket("localhost",5000);
byte ack[]=new byte[1];
ack[0]=101; // getFrame
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
byte requestLengthInBytes[]=new byte[4];
byteCount=is.read(requestLengthInBytes);
int requestLength=(requestLengthInBytes[0] & 0xFF) <<24 | (requestLengthInBytes[1] & 0xFF) <<16 |(requestLengthInBytes[2] & 0xFF) <<8 | (requestLengthInBytes[3] & 0xFF);
ack[0]=79;
os.write(ack,0,1);
os.flush();
ByteArrayOutputStream baos=new ByteArrayOutputStream();
int bytesToRead=requestLength;
byte chunk[]=new byte[1024];
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
ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
BufferedImage image=ImageIO.read(bais);
bais.close();
os.close();
is.close();
baos.close();
socket.close();
return image;
}catch(Exception e)
{
System.out.println(e+"asdfa");
}
return null;
}
public static void getMousePressed(int x,int y,int button)
{

try
{
Socket socket=new Socket("localhost",5000);
byte ack[]=new byte[1];
ack[0]=102; // getMousePressed
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+","+y+","+button+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}




}
public static void getMouseRelease(int x,int y,int button)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=103; // getMouseRelease
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+","+y+","+button+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMouseClicked(int x,int y,int button)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=104; // getMouseClicked
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+","+y+","+button+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMouseMoved(int x,int y)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=105; // getMouseMoved
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+","+y+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getMouseWheel(int x)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=106; // getMouseWheel
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyPressed(int x)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=107; // getKeyPressed
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyReleased(int x)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=108; // getKeyReleased
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public static void getKeyTyped(int x)
{

try
{
Socket socket=new Socket("localhost",5000);

byte ack[]=new byte[1];
ack[0]=109; // getKeyTyped
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

String requestString=x+",";
byte requestStringInByte[]=requestString.getBytes();
os.write(requestStringInByte);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
os.close();
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static LinkedList<File> getFilesList(File requestFile)
{
try
{
Socket socket=new Socket("localhost",5000);
byte ack[]=new byte[1];
ack[0]=110; // getFiles
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

ByteArrayOutputStream baos=new ByteArrayOutputStream();
ObjectOutputStream oos=new ObjectOutputStream(baos);
oos.writeObject(requestFile);
oos.flush();
byte requestBytes[]=baos.toByteArray();
int requestSize=requestBytes.length;

byte requestSizeInBytes[]=new byte[4];
requestSizeInBytes[0]=(byte)(requestSize >>24);
requestSizeInBytes[1]=(byte)(requestSize >>16);
requestSizeInBytes[2]=(byte)(requestSize >>8);
requestSizeInBytes[3]=(byte)requestSize;
os.write(requestSizeInBytes,0,4);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
int bytesToSend=requestSize;
int chunkSize=1024;
int i=0;

while(bytesToSend>0)
{
 if(bytesToSend<chunkSize) chunkSize=bytesToSend;
os.write(requestBytes,i,chunkSize);
os.flush();
i=i+chunkSize;
bytesToSend-=chunkSize;
}
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
byte [] responseLengthInBytes=new byte[4];
byteCount=is.read(responseLengthInBytes);
int responseLength;
responseLength=(responseLengthInBytes[0] & 0xFF) << 24 | (responseLengthInBytes[1] & 0xFF) <<16 | (responseLengthInBytes[2] & 0xFF) << 8 | (responseLengthInBytes[3] & 0xFF);
ack[0]=79;
os.write(ack,0,1);
os.flush();


baos=new ByteArrayOutputStream();
byte chunk[]=new byte[1024];
int bytesToRead=responseLength;

while(bytesToRead>0)
{
byteCount=is.read(chunk);
if(byteCount>0)
{
baos.write(chunk,0,byteCount);
baos.flush();
}
bytesToRead-=byteCount;
}
os.write(ack,0,1);
os.flush();
byte responseBytes[]=baos.toByteArray();
ByteArrayInputStream bais=new ByteArrayInputStream(responseBytes);
ObjectInputStream ois=new ObjectInputStream(bais);
LinkedList<File> responseObject=(LinkedList<File>)ois.readObject();
socket.close();
return responseObject;
}catch(Exception e)
{
System.out.println(e);
}
return null;
}
///////////////////////////////////////////////////////////////////////////////////////
public static void uploadFile(String fileToSend,String path)
{
// yaha par dir ke baat karni he download me bhi
String fileName=path+fileToSend.substring(fileToSend.lastIndexOf("\\"));

File file=new File(fileToSend);
try
{
Socket socket=new Socket("localhost",5000);
byte ack[]=new byte[1];
ack[0]=111; // uploadFile
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");

int fileNameLength=fileName.length();
byte fileNameSizeInBytes[]=new byte[4];
fileNameSizeInBytes[0]=(byte)(fileNameLength >>24);
fileNameSizeInBytes[1]=(byte)(fileNameLength >>16);
fileNameSizeInBytes[2]=(byte)(fileNameLength>>8);
fileNameSizeInBytes[3]=(byte)fileNameLength;
os.write(fileNameSizeInBytes,0,4);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
///////////////////////////////////
byte fileNameInBytes[]=fileName.getBytes();
os.write(fileNameInBytes,0,fileNameLength);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
//////////////
byte instruction[]="upload".getBytes();
fileNameLength=instruction.length; // instruction ka naam 
fileNameSizeInBytes[0]=(byte)(fileNameLength >>24);
fileNameSizeInBytes[1]=(byte)(fileNameLength >>16);
fileNameSizeInBytes[2]=(byte)(fileNameLength>>8);
fileNameSizeInBytes[3]=(byte)fileNameLength;
os.write(fileNameSizeInBytes);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
os.write(instruction);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
//////////////
long fileLength=file.length();
byte requestSizeInBytes[]=new byte[8];
requestSizeInBytes[0]=(byte)(fileLength>> 56);
requestSizeInBytes[1]=(byte)(fileLength>> 48);
requestSizeInBytes[2]=(byte)(fileLength>>40);
requestSizeInBytes[3]=(byte)(fileLength>> 32);
requestSizeInBytes[4]=(byte)(fileLength>> 24);
requestSizeInBytes[5]=(byte)(fileLength>> 16);
requestSizeInBytes[6]=(byte)(fileLength>> 8);
requestSizeInBytes[7]=(byte)fileLength;
os.write(requestSizeInBytes);
os.flush();
is=socket.getInputStream();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
//////////////
FileInputStream fis=new FileInputStream(fileToSend);
long bytesToSend=fileLength;
byte requestBytes[]=new byte[1024];
for(long i=0;i<bytesToSend;)
{
byteCount=fis.read(requestBytes);
i=i+(long)byteCount;
os.write(requestBytes,0,byteCount);
os.flush();

}
fis.close();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
////////////////
socket.close();
}catch(Exception e)
{
System.out.println(e);
}
}
///////////////////////////////////////////////////////////////////////////
public static void downloadFile(String path,String fileToRecive)
{
String fileName;
path=path+fileToRecive.substring(path.lastIndexOf("\\"));
fileName=fileToRecive;
try
{
Socket socket=new Socket("localhost",5000);
byte ack[]=new byte[1];
ack[0]=112; // downloadFile
OutputStream os=socket.getOutputStream();
InputStream is=socket.getInputStream();
os.write(ack,0,1);
os.flush();
int byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("not able to get ack");
int fileNameLength=fileName.length();
byte fileNameSizeInBytes[]=new byte[4];
fileNameSizeInBytes[0]=(byte)(fileNameLength >>24);
fileNameSizeInBytes[1]=(byte)(fileNameLength >>16);
fileNameSizeInBytes[2]=(byte)(fileNameLength>>8);
fileNameSizeInBytes[3]=(byte)fileNameLength;
os.write(fileNameSizeInBytes,0,4);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
///////////////////////////////////
byte fileNameInBytes[]=fileName.getBytes();
os.write(fileNameInBytes,0,fileNameLength);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
//////////////
byte instruction[]="download".getBytes();
fileNameLength=instruction.length; // instruction ka naam 
fileNameSizeInBytes[0]=(byte)(fileNameLength >>24);
fileNameSizeInBytes[1]=(byte)(fileNameLength >>16);
fileNameSizeInBytes[2]=(byte)(fileNameLength>>8);
fileNameSizeInBytes[3]=(byte)fileNameLength;
os.write(fileNameSizeInBytes);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
os.write(instruction);
os.flush();
byteCount=is.read(ack);
if(ack[0]!=79) throw new RuntimeException("Unable to receive acknowledgement");
///////////////////
long responseLength;
byte responseLengthInBytes[]=new byte[8];
byteCount=is.read(responseLengthInBytes);
responseLength=(responseLengthInBytes[0] & 0xFF) <<54 | (responseLengthInBytes[1] & 0xFF) <<48 | (responseLengthInBytes[2] & 0xFF) <<40 | (responseLengthInBytes[3] & 0xFF)<<32| (responseLengthInBytes[4] & 0xFF)<<24 | (responseLengthInBytes[5] & 0xFF)<<16 | (responseLengthInBytes[6] & 0xFF)<<8 | (responseLengthInBytes[7] & 0xFF) ;
ack[0]=79;
os.write(ack);
os.flush();
///////
byte chunk[]=new byte[1024];
FileOutputStream fos=new FileOutputStream(path);
for(long i=0;i<responseLength;)
{
byteCount=is.read(chunk);
i=i+byteCount;
fos.write(chunk,0,byteCount);
}
ack[0]=79;
os.write(ack,0,1);
os.flush();
fos.close();
socket.close();
}catch(Exception e)
{
System.out.println(e.getMessage());
}

}

}// class ends