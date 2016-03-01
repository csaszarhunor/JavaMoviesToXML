package server;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import main.Command;

public class ObjectServer {
	
	static ServerSocket ss;
	static Socket s;
	static InputStream is;
	static OutputStream os;
	static ObjectInputStream ois;
	static ObjectOutputStream oos;
	static final int PORT = 123;
	static final String STOREPATH = "store.ser";
	static ServerMode mode;

	public static void shutDown(){
		try {
			System.out.println("Server is shutting down...");
			os.close();
			is.close();
			s.close();
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void save(Object object){
    	try {
			FileOutputStream fos = new FileOutputStream(STOREPATH, true);
			AppendingObjectOutputStream oos = new AppendingObjectOutputStream(fos);
			oos.writeObject(object);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Object> load(){
		List<Object> o = new ArrayList<Object>();
        FileInputStream fis;
		try {
			fis = new FileInputStream(STOREPATH);
			ObjectInputStream ois = new ObjectInputStream(fis);
	        while (true){
	        	try {
	        		o.add(ois.readObject());
	        	} catch (EOFException e) {
	                break;
	            } catch (StreamCorruptedException e) {
	                break;
	            }
	        }
	        ois.close();
	        fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return o;
	}
	
	public static void main(String[] args) {
		
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server launched, waiting for Client...");
			s = ss.accept();
			System.out.println("Client connected.");

			is = s.getInputStream();
			os = s.getOutputStream();
			ois = new ObjectInputStream(is);
			oos = new ObjectOutputStream(os);
			System.out.println("In and out streams established.");
			
			File file = new File(STOREPATH);
			if (!file.exists())
			{
			    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			    oos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(true){
			try {
				Object inputObj = ois.readObject();
				if (inputObj instanceof Command && inputObj.equals(Command.PUT)){
					mode = ServerMode.SAVE;
					File f = new File(STOREPATH);
                    try {
                        if (!f.exists())
                        {
                            ObjectOutputStream fileHeaderWriter = new ObjectOutputStream(new FileOutputStream(f));
                            fileHeaderWriter.close();
                        }
                        
                        while(ois.read() == 0) {
                            save(ois.readObject());
                        }
                    } catch (IOException e) {
                        System.out.println("put branch io problem");
                    } catch (ClassNotFoundException e) {
                        System.out.println("put branch classnotfound problem");
                    }

                    System.out.println("Objects serialized and stored.");
				}
				
				else if (inputObj instanceof Command && inputObj.equals(Command.GET)){
					List<Object> objsToSend = load();
					oos.writeObject(objsToSend);
				}
				
				else if (inputObj instanceof Command && inputObj.equals(Command.EXIT)){
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}
		}
		shutDown();
	}
}
