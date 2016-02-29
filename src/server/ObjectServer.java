package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import main.Command;

public class ObjectServer {

    static ServerMode mode;
    static final String FILEPATH = "store.ser";
    static Socket socket = null;
    static ServerSocket serverSocket = null;
    static ObjectInputStream serverInputStream = null;
    static ObjectOutputStream serverOutputStream = null;
    
    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(1123);
            System.out.println("Server launched and waiting for connection...");
        } catch (IOException e) {
            System.out.println("Port number is wrong.");
            System.exit(1);
        }

        while (true) {

            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Connection failed");
                System.exit(1);
            }
            System.out.println("Connection successful");


            try {
                serverInputStream = new ObjectInputStream(socket.getInputStream());
                serverOutputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.out.println("Stream initialization failed");
            }

            while (true) {
                Object inputObject = null;


                try {
                    inputObject = serverInputStream.readObject();
                } catch (Exception e) {
                    System.out.println("Can't read from ObjectInputStream");
                }

                    if (inputObject.equals(Command.GET)) {
                        mode = ServerMode.LOAD;
                        System.out.println("Server Mode changed to: LOAD");
                        List<Object> results = new ArrayList<>();
                        try {
                            FileInputStream fileInputStream = new FileInputStream("store.ser");
                            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                            while (true) {
                                results.add(objectInputStream.readObject());
                            }
                        } catch (EOFException eofe) {
                            System.out.println("filereading complete");
                        } catch (IOException e) {
                            System.out.println("get branch loading problem");
                        } catch (NullPointerException npe){
                            System.out.println("There is no file");
                        } catch (ClassNotFoundException cnfe){
                            System.out.println("class not found in file");
                        }

                        try {
                            serverOutputStream.writeObject(results);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } else if (inputObject.equals(Command.PUT)) {
                        mode = ServerMode.SAVE;
                        System.out.println("Server Mode changed to: SAVE");
                        File f = new File("store.ser");
                        try {
                            if (!f.exists())
                            {
                                ObjectOutputStream fileHeaderWriter = new ObjectOutputStream(new FileOutputStream(f));
                                fileHeaderWriter.close();
                            }
                            
                            while(serverInputStream.read() == 0) {
                                save(serverInputStream.readObject());
                            }
                        } catch (IOException e) {
                            System.out.println("put branch io problem");
                        } catch (ClassNotFoundException e) {
                            System.out.println("put branch classnotfound problem");
                        }

                        System.out.println("Objects serialized and stored.");
                    } else if (inputObject.equals(Command.EXIT)) {
                        System.out.println("Exit <3");
                        break;
                    }

            }


            try {
                socket.close();
                System.out.println("Disconnected");
            } catch (IOException e) {
                System.out.println("Clientsocket close problem");
            }

        }
    }


    public static Object load() {
        Object o = null;
        try (FileInputStream fis = new FileInputStream("store.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fis))
        {
            o = objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println("load method error, cant read from file");
        }
        return o;
    }


    public static void save(Object obj) {
        try {
        	FileOutputStream fileOutputStream = new FileOutputStream("store.ser", true);
            AppendingObjectOutputStream objectOutputStream = new AppendingObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}