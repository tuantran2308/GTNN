package brai.java;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;

import javax.swing.JFrame;
public class Communicator implements SerialPortEventListener {

	
	
	JFrame windows = new JFrame("Braille");

    //for containing the ports that will be found
    private Enumeration ports = null;
    //map the port names to CommPortIdentifiers
    private HashMap portMap = new HashMap();

    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;

    //input and output streams for sending and receiving data
    private InputStream input = null;
    private OutputStream output = null;

    //just a boolean flag that i use for enabling
    //and disabling buttons depending on whether the program
    //is connected to a serial port or not
    private boolean bConnected = false;

    //the timeout value for connecting with the port
    final static int TIMEOUT = 2000;

    //some ascii values for for certain things
    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;

    //a string for recording what goes on in the program
    //this string is written to the GUI
    String logText = "";
    String availaPorts[]=new String[10];
  //search for all the serial ports
    //pre style="font-size: 11px;": none
    //post: adds all the found ports to a combo box on the GUI
    //JComboBox<String> cboxPort = new JComboBox<String>();
    public void searchForPorts()
    {
        ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements())
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

            //get only serial ports
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
            	int i = 0;
            	//System.out.printf(curPort.getName());
            	availaPorts[i]=curPort.getName();
            	i++;
                //cboxPorts.addItem(curPort.getName());
                portMap.put(curPort.getName(), curPort);
            }
        }
    }
    public String[] getAvailablePorts(){
    	return availaPorts;
    }
    
    //connect to the selected port in the combo box
    //pre style="font-size: 11px;": ports are already found by using the searchForPorts
    //method
    //post: the connected comm port is stored in commPort, otherwise,
    //an exception is generated
    public void setConnected(boolean a)
    {
    	bConnected= a;
    }
    
    public boolean getConnected()
    {
    	return bConnected;
    }
    
    public void connect(String comName)
    {
        String selectedPort = comName;
        selectedPortIdentifier = (CommPortIdentifier)portMap.get(selectedPort);
        System.out.println(selectedPort);
        CommPort commPort = null;

        try
        {
            //the method below returns an object of type CommPort
            commPort = selectedPortIdentifier.open("TigerControlPanel", TIMEOUT);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort)commPort;

            //for controlling GUI elements
            setConnected(true);

            //logging
            logText = selectedPort + " opened successfully.";

            //CODE ON SETTING BAUD RATE ETC OMITTED
            //XBEE PAIR ASSUMED TO HAVE SAME SETTINGS ALREADY

            //enables the controls on the GUI if a successful connection is made
        }
        catch (PortInUseException e)
        {
            logText = selectedPort + " is in use. (" + e.toString() + ")";

        }
        catch (Exception e)
        {
            logText = "Failed to open " + selectedPort + "(" + e.toString() + ")";
           // System.out.print("Error");
         }
    }
    
  //open the input and output streams
    //pre style="font-size: 11px;": an open port
    //post: initialized input and output streams for use to communicate data
    public boolean initIOStream()
    {
        //return value for whether opening the streams is successful or not
        boolean successful = false;

        try {
            //
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();
            //writeData(new byte[]);

            successful = true;
            return successful;
        }
        catch (IOException e) {
            logText = "I/O Streams failed to open. (" + e.toString() + ")";
            return successful;
        }
    }
    
  //starts the event listener that knows whenever data is available to be read
    //pre style="font-size: 11px;": an open serial port
    //post: an event listener for the serial port that knows when data is received
    public void initListener()
    {
        try
        {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e)
        {
            logText = "Too many listeners. (" + e.toString() + ")";
        }
    }
   
  //disconnect the serial port
    //pre style="font-size: 11px;": an open serial port
    //post: closed serial port
    public void disconnect()
    {
        //close the serial port
        try
        {
            //writeData(0, 0);

            serialPort.removeEventListener();
            serialPort.close();
            input.close();
            output.close();
            setConnected(false);

            logText = "Disconnected.";
        }
        catch (Exception e)
        {
            logText = "Failed to close " + serialPort.getName()
                              + "(" + e.toString() + ")";
        }
    }
    
    //what happens when data is received
    //pre style="font-size: 11px;": serial event is triggered
    //post: processing on the data it reads
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE)
        {
            try
            {
                byte singleData = (byte)input.read();

                if (singleData != NEW_LINE_ASCII)
                {
                    logText = new String(new byte[] {singleData});
                }
                else
                {
                }
            }
            catch (Exception e)
            {
                logText = "Failed to read data. (" + e.toString() + ")";
            }
        }
    }
    
    
    //method that can be called to send data
    //pre style="font-size: 11px;": open serial port
    //post: data sent to the other device
    public void writeData(byte[] data)
    {
        try
        {
            output.write(data);
            output.flush();
           
        }
        catch (Exception e)
        {
            logText = "Failed to write data. (" + e.toString() + ")";
        }
    }
    
}


