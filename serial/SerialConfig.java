package serial;

import java.util.stream.IntStream;
import jssc.SerialPort;

public class SerialConfig {
    public static final int DEFAULT_BAUD_RATE = 9600;
    public static final int DEFAULT_DATA_BITS = SerialPort.DATABITS_8;    
    public static final int DEFAULT_PARITY = SerialPort.PARITY_NONE;
    public static final int DEFAULT_STOP_BITS = SerialPort.STOPBITS_1;
    
    public static final int availableBaudRates[] = {
        SerialPort.BAUDRATE_256000,
        SerialPort.BAUDRATE_128000,
        SerialPort.BAUDRATE_115200,
        SerialPort.BAUDRATE_57600,
        SerialPort.BAUDRATE_38400,
        SerialPort.BAUDRATE_19200,
        SerialPort.BAUDRATE_9600,
        SerialPort.BAUDRATE_4800,
        SerialPort.BAUDRATE_300
    };
    
    public static final int availableDataBits[] = {
        SerialPort.DATABITS_8,
        SerialPort.DATABITS_7
    };
    
    public static final int availableParity[] = {
        SerialPort.PARITY_NONE,
        SerialPort.PARITY_ODD,
        SerialPort.PARITY_EVEN
    };
    
    public static final int availableStopBits[] = {
        SerialPort.STOPBITS_1,
        SerialPort.STOPBITS_1_5,
        SerialPort.STOPBITS_2
    };
        
    private int baudRate;
    private int dataBits;
    private int parity;
    private int stopBits;
    
    public SerialConfig() {
        this.baudRate = DEFAULT_BAUD_RATE;
        this.dataBits = DEFAULT_DATA_BITS;
        this.parity = DEFAULT_PARITY;
        this.stopBits = DEFAULT_STOP_BITS;
    }
    
    public int getBaudRate() {
        return baudRate;
    }
    
    
    public int getDataBits() {
        return dataBits;
    }
    
    
    public int getParity() {
        return parity;
    }
    
    
    public int getStopBits() {
        return stopBits;
    }
    
    public void setBaudRate(int baudRate) {
        if (arrayContains(availableBaudRates, baudRate)) {
            this.baudRate = baudRate;
        }
    }
    
    public void setDataBits(int dataBits) {
        if (arrayContains(availableDataBits, dataBits)) {
            this.dataBits = dataBits;
        }
    }
    
    public void setParity(int parity) {
        if (arrayContains(availableParity, parity)) {
            this.parity = parity;
        }
    }
    
    public void setStopBits(int stopBits) {
        if (arrayContains(availableStopBits, stopBits)) {
            this.stopBits = stopBits;
        }
    }
    
    public void reset() {
        this.baudRate = DEFAULT_BAUD_RATE;
        this.dataBits = DEFAULT_DATA_BITS;
        this.parity = DEFAULT_PARITY;
        this.stopBits = DEFAULT_STOP_BITS;
    }
    
    private boolean arrayContains(int array[], int value) {
        return (array.length > 0 && IntStream.of(array).anyMatch(x -> x == baudRate));
    }
}
