package com.app.client;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.app.properties.ClientPropertiesRead;

import org.apache.log4j.Logger;

public class Client implements ClientInterface {

    private int _PORT;
    private String _HOST = "";
    private Socket _CLIENTSOCKET = null;
    private static final int BUFFER_SIZE = 100;
    private JTextArea _OUTPUT = null;
    private String _TYPE = null;
    private String _FILE = null;
    ObjectInputStream datainput = null;
    ObjectOutputStream dataoutput = null;
    private JTextArea clientTextArea=null;
    private final static Logger logger = Logger.getLogger(Client.class);

    public Client() {
        try {
            _PORT = (Integer.parseInt(ClientPropertiesRead.getProperty("PORT")));
            _HOST = ClientPropertiesRead.getProperty("HOST");
        } catch (NumberFormatException nbe) {
            logger.info("" + nbe.getMessage());
        }
    }

   
    
    public Client(JTextArea output) {
        this._OUTPUT = output;

        try {
            _PORT = (Integer.parseInt(ClientPropertiesRead.getProperty("PORT")));
            _HOST = ClientPropertiesRead.getProperty("HOST");
        } catch (NumberFormatException nbe) {
            logger.info("" + nbe.getMessage());
        }
    }

    
    
    
    
    @Override
    public Socket createCon() {

        try {
            _CLIENTSOCKET = new Socket(_HOST, _PORT);
            if(datainput==null)    
          datainput = new ObjectInputStream(_CLIENTSOCKET.getInputStream());
        if(dataoutput==null)
          dataoutput = new ObjectOutputStream(_CLIENTSOCKET.getOutputStream());
            //datainput = new ObjectInputStream(_CLIENTSOCKET.getInputStream());
            //dataoutput = new ObjectOutputStream(_CLIENTSOCKET.getOutputStream());

        ServerMessageReader smreader=new ServerMessageReader(datainput,clientTextArea);
        smreader.start();
        } catch (ConnectException e) {
            
            JOptionPane.showMessageDialog(null, "Connection problem occured reason is" + e.getMessage());
            logger.info(e.getMessage());
            logger.info("Check server starts or not " + "\n");
            logger.error(e);
            _CLIENTSOCKET = null;
            System.exit(0);

        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Connection problem occured reason is" + e.getMessage());
            logger.info(e.getMessage() + "\n");
            logger.error(e);
            _CLIENTSOCKET = null;
            System.exit(0);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Connection problem occured reason is" + e.getMessage());
            logger.error(e);
            logger.info(e.getMessage() + "\n");
            _CLIENTSOCKET = null;
            System.exit(0);

        }
        return _CLIENTSOCKET;
    }

    @Override
    public void closeCon() {

        if (_CLIENTSOCKET != null) {
            try {
                dataoutput.writeObject("close");
                dataoutput.close();
                datainput.close();
                _CLIENTSOCKET.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataoutput = null;
            dataoutput = null;
            _CLIENTSOCKET = null;
        }
    }

    
    @Override
    public void sendMessage(String message)
    {
        try
        {
        
        dataoutput.writeObject(message);
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public void setClientTextArea(JTextArea clientTextArea)
    {
        this.clientTextArea=clientTextArea;
    }
    
    
    
    @Override
    public void fileTransfer(String filename, String typeofdata) {

        this._FILE = filename;
        this._TYPE = typeofdata;
        File file = new File(_FILE);
        boolean readflag = true;
        try {
            showoutput("File Transfer from client to server method comes");
            logger.info("File Transfer from client to server method comes");
            logger.info("File Transfer from client to server method comes filename is " + filename);
			//ObjectInputStream datainput = new ObjectInputStream(_CLIENTSOCKET.getInputStream());  
            //ObjectOutputStream dataoutput = new ObjectOutputStream(_CLIENTSOCKET.getOutputStream());  
            dataoutput.writeObject("filesend");
            dataoutput.writeObject(file.getName());
            FileInputStream fis = new FileInputStream(_FILE);
            byte[] buffer = new byte[BUFFER_SIZE];
            Integer bytesRead = 0;

            dataoutput.writeObject(_TYPE);
            Object o = datainput.readObject();
            if (o instanceof String) {
                showoutput(o.toString());

            }
            while ((bytesRead = fis.read(buffer)) > 0) {
                dataoutput.writeObject(bytesRead);
                dataoutput.writeObject(Arrays.copyOf(buffer, buffer.length));
            }
            logger.info("File Trnasfer from client to server Completed");
            showoutput("File Trnasfer from client to server Completed");
            showoutput("Start Compiling & Run Process");
            logger.info("Start Compiling & Run Process");
            while (readflag) {
                o = datainput.readObject();
                if (o instanceof String) {
                    showoutput(o.toString());

                } else {
                    readflag = false;
                }
            }
            showoutput("END Compiling & Run Process");
            logger.info("END Compiling & Run Process");
            dataoutput.flush();

        } catch (EOFException e) {
            readflag = false;
            logger.info("Reached File Ending");

        } catch (Exception e) {
            logger.error(getSTRING_EXCEPTION(e));

        } finally {
            logger.info("Client work is completed");
            //closeCon();
        }

    }

    public static String getSTRING_EXCEPTION(Exception exception) {
        java.io.StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public void showoutput(String log) {
        if (_OUTPUT != null) {
            _OUTPUT.append(log + "\n");

        }
    }
}
