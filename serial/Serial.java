package serial;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Serial {
   
    public enum ReadEventState { READ_EVT_OFF, READ_EVT_ON };
    
    public SerialTransceiver transceiver;
    
    private SerialConfig portConfig;
    private String[] portNames;
    private String portName;
    private SerialPort port;
    private boolean opened;
      
    public Serial() {
        this.transceiver = new SerialTransceiver();
        this.portName = new String();
        this.port = new SerialPort(null);
        this.opened = false;
    }
    
    public boolean isAnyPortAvailable() {
        return portNames.length > 0;
    }
    
    public boolean isValidPort(String portName) {
        for (String s : portNames) {
            if (s != null && s.equals(portName)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSelectedPortValid() {
        return isValidPort(this.portName);
    }
    
    public boolean isOpened() {
        return opened;
    }
    
    public String[] getPortNames() {
        return portNames;
    }
    
    public SerialPort getSelectedPort() {
        return port;
    }
    
    public String getSelectedPortName() {
        return portName;
    }
    
    public void updatePortNames() {
        portNames = SerialPortList.getPortNames();
    }
    
    public void selectPort(String portName) {
        this.portName = portName;
    }
    
    public void Configure(SerialPort port, SerialConfig cfg, ReadEventState readEventState) {
        this.portConfig = cfg;
        this.port = port;
        this.portName = port.getPortName();
        
        try {
            port.openPort();
            port.setParams(
                    portConfig.getBaudRate(), portConfig.getDataBits(), 
                    portConfig.getStopBits(), portConfig.getParity());

            port.setEventsMask(SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR);
            
            if (readEventState == ReadEventState.READ_EVT_ON) {
                port.addEventListener(this.transceiver);
            }
            
            this.opened = true;
        } 
        catch (SerialPortException ex) {
        }
    }
    
    public void Close() {
        if (this.opened) {
            try {
                port.closePort();
                this.transceiver = null;
                this.portConfig = null;
                this.portNames = null;
                this.port = null;
                this.opened = false;
                
                this.transceiver = new SerialTransceiver();                
            }
            catch (SerialPortException ex) {
            }
        }
    }
}
