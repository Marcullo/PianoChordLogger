package serial;

import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.DefaultListModel;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialTransceiver implements SerialPortEventListener {
    public static final int RECEIVER_QUEUE_SIZE = 1000;
    public final byte MESSAGE_TERMINATOR = 13; //\r
    
    private SerialPort port;

    public String receivedTempString;
    public DefaultListModel<String> receivedMessageList;
    public LinkedBlockingQueue<String> receivedMessageQueue;

    public DefaultListModel<String> transmittedMessageList;
    
    public SerialTransceiver() {
        this.port = new SerialPort(null);
        this.transmittedMessageList = new DefaultListModel<>();
        
        
        this.receivedTempString = "";
        this.receivedMessageList = new DefaultListModel<>();
        this.receivedMessageQueue = new LinkedBlockingQueue<>(RECEIVER_QUEUE_SIZE);
    }
    
    public void putString(String msg) {
        try {
            port.writeString(msg + (char)MESSAGE_TERMINATOR);
            transmittedMessageList.addElement(msg);
        } catch (SerialPortException ex) {
        }
    }
    
    public void setPort(SerialPort port) {
        this.port = port;
    }
    
    public void flush() {
        try {
            this.port.purgePort(SerialPort.PURGE_RXCLEAR);
        } catch (SerialPortException ex) {
        }
    }
    
    @Override
    public void serialEvent(SerialPortEvent event) {
        int eventValue = event.getEventValue();
        
        if(event.isRXCHAR() && eventValue > 0) {
            try {
                byte bytes[] = port.readBytes(eventValue);
                
                for (byte b : bytes) {
                    if (isByteEndOfMessage(b) && isQueueReady(receivedMessageQueue)) {
                        //appendLineSeparator(receivedTempString);
                        receivedMessageQueue.add(receivedTempString);
                        receivedTempString = "";
                    } 
                    else if (isValidMessageByte(b)) {
                        receivedTempString += (char)b;
                    }
                }
            } catch (SerialPortException ex) {
            }
        }
    }
    
    private boolean isByteEndOfMessage(byte b) {
        return b == MESSAGE_TERMINATOR;
    }
    
    private boolean isValidMessageByte(byte b) {
        return (b >= (byte)' ' && b <= (byte)'~');
    }
    
    private boolean isQueueReady(LinkedBlockingQueue<String> queue) {
        return queue.remainingCapacity() > 0;
    }
    
    private void appendLineSeparator(String s) {
        s += System.getProperty("line.separator");
    }
}
