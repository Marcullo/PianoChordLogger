package gui;

import chord.ChordDecoder;
import chord.ChordLogger;
import chord.Note;
import chord.NoteDecoder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.Timer;
import jssc.SerialPort;
import serial.Serial;
import serial.SerialConfig;
import serial.SerialDecoder;
import serial.SerialTransceiver;

public class GUI extends javax.swing.JFrame {
    public static final int SERIAL_RECEIVER_REFRESH_RATE_MS = 1;
    
    private SerialConfig serialConfig;
    private Serial serial;
    private SerialTransceiver transceiver;
    private SerialDecoder serialDecoder;
    
    private ChordDecoder chordDecoder;
    private NoteDecoder noteDecoder;
    private DefaultListModel<String> chordLogList;
    private ChordLogger chordLogger;
    
    private Timer receiverMonitorTimer;
    
    private boolean chordDeleted = false;
    
    public GUI() {
        initComponents();
        initConfigurationFrame();
        initSerial();
        initSerialConfiguration();
        
        serialDecoder = new SerialDecoder();
        chordDecoder = new ChordDecoder();
        noteDecoder = new NoteDecoder();
        chordLogList = new DefaultListModel<>();
        chordLogger = new ChordLogger();
    }

    private void initSerial() {
        serial = new Serial();
        transceiver = serial.transceiver;
    }
    
    private void initConfigurationFrame() {
        JConfigurationFrame.setSize(900, 418);
        JConfigurationFrame.setResizable(false);
        JConfigurationFrame.setAlwaysOnTop(true);
        JConfigurationFrame.setUndecorated(true);
        JConfigurationFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    }
    
    private void initSerialConfiguration() {
        serialConfig = new SerialConfig();
        updateSerialConfigurationWithDesc();        
    }
    
    private void updatePortNameDesc() {
        String portName = serial.getSelectedPortName();
        jPortNameDesc.setText(portName + ":");
    }
    
    private void resetPortNameDesc() {
        jPortNameDesc.setText("");
    }
    
    private void updateSerialConfigurationWithDesc() {
        int baudRate = Integer.valueOf((String) jBaudRateBox.getSelectedItem());
        int dataBits = Integer.valueOf((String) jDataBitsBox.getSelectedItem());  
        int parity = getIntParity((String) jParityBox.getSelectedItem());
        int stopBits = getIntStopBits((String) jStopBitsBox.getSelectedItem());
 
        updateSerialConfiguration(baudRate, dataBits, parity, stopBits);
        updateSerialConfigurationDesc(baudRate, dataBits, parity, stopBits);
    }
    
    private int getIntParity(String parityTxt) {
        int parity = SerialPort.PARITY_NONE;
        switch (parityTxt) {                
            case "odd": 
                parity = SerialPort.PARITY_ODD;
                break;
                
            case "even": 
                parity = SerialPort.PARITY_EVEN;
                break;
                
            default:
                break;
        }
        return parity;        
    }
    
    private int getIntStopBits(String stopBitsTxt) {
        int stopBits = SerialPort.STOPBITS_1;
        switch (stopBitsTxt) {                
            case "1.5": 
                stopBits = SerialPort.STOPBITS_1_5;
                break;
                
            case "2": 
                stopBits = SerialPort.STOPBITS_2;
                break;
                
            default:
                break;
        }
        
        return stopBits;
    }
    
    private void updateSerialConfiguration(int baudRate, int dataBits, int parity, int stopBits) {
        serialConfig.setBaudRate(baudRate);
        serialConfig.setDataBits(dataBits);
        serialConfig.setParity(parity);
        serialConfig.setStopBits(stopBits);
    }
 
    private void updateSerialConfigurationDesc(int baudRate, int dataBits, int parity, int stopBits) {                
        String parityTxt = "N";
        switch (parity) {
            case SerialPort.PARITY_ODD:
                parityTxt = "O";
                break;
            
            case SerialPort.PARITY_EVEN:
                parityTxt = "E";
                break;
                
            default:
                break;
        }
        
        String stopBitsTxt = "1";
        switch (stopBits) {
            case SerialPort.STOPBITS_1_5:
                stopBitsTxt = "1.5";
                break;
            
            case SerialPort.STOPBITS_2:
                stopBitsTxt = "2";
                break;
                
            default:
                break;
        }
        
        jSerialConfigurationDesc.setText(baudRate + ", " + dataBits + parityTxt + stopBitsTxt);
    }
    
    public void scrollDownLog(javax.swing.JScrollPane log) {
        JScrollBar vsb = log.getVerticalScrollBar();
        vsb.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent event) {
                event.getAdjustable().setValue(event.getAdjustable().getMaximum());
                vsb.removeAdjustmentListener(this);
            }
        });
    }
    
    private void showSerialConfigurationPanel() {
        jSerialConfigurationPanel.setVisible(true);
        jFileOperationPanel.setVisible(false);
        showConfigurationFrame();
    }
    
    private void showFileOperationPanel() {
        jSerialConfigurationPanel.setVisible(false);
        jFileOperationPanel.setVisible(true);
        showConfigurationFrame();
        
    }
    
    private void showConfigurationFrame() {
        this.setAlwaysOnTop(true);
        this.setEnabled(false);
        JConfigurationFrame.setLocation(this.getX() + 3, this.getY() + 122);
        JConfigurationFrame.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JConfigurationFrame = new javax.swing.JFrame();
        jConfigurationPanel = new javax.swing.JPanel();
        jSerialConfigurationPanel = new javax.swing.JPanel();
        jSerialPanelTitle = new javax.swing.JLabel();
        jConfigPanel = new javax.swing.JPanel();
        jBaudRateBox = new javax.swing.JComboBox<>();
        jDataBitsBox = new javax.swing.JComboBox<>();
        jParityBox = new javax.swing.JComboBox<>();
        jStopBitsBox = new javax.swing.JComboBox<>();
        jAcceptConfigurationButton = new javax.swing.JButton();
        jBaudRateDesc = new javax.swing.JLabel();
        jBaudRateDesc1 = new javax.swing.JLabel();
        jBaudRateDesc2 = new javax.swing.JLabel();
        jBaudRateDesc3 = new javax.swing.JLabel();
        jCancelConfigurationButton = new javax.swing.JButton();
        jFileOperationPanel = new javax.swing.JPanel();
        jFileChooserPanelTitle = new javax.swing.JLabel();
        jFileChooser = new javax.swing.JFileChooser();
        jChooseFileButton = new javax.swing.JButton();
        jSaveChordsButton = new javax.swing.JButton();
        jCancelSaveButton = new javax.swing.JButton();
        jFileOperationDialog = new javax.swing.JLabel();
        jFileName = new javax.swing.JLabel();
        jMainPanel = new javax.swing.JPanel();
        jTitleLabel = new javax.swing.JLabel();
        jSerialPortPanel = new javax.swing.JPanel();
        jOpenPortButton = new javax.swing.JButton();
        jClosePortButton = new javax.swing.JButton();
        jConfigurePortButton = new javax.swing.JButton();
        jPortNameDesc = new javax.swing.JLabel();
        jSerialConfigurationDesc = new javax.swing.JLabel();
        jAvailablePortsBox = new javax.swing.JComboBox<>();
        jChordsPanel = new javax.swing.JPanel();
        jCurrentChord = new javax.swing.JTextField();
        jClearChordLogButton = new javax.swing.JButton();
        jExportChordsButton = new javax.swing.JButton();
        jChordLogScroll = new javax.swing.JScrollPane();
        jChordLog = new javax.swing.JList<>();
        jValidChordPanel = new javax.swing.JPanel();
        jNotesPanel = new javax.swing.JPanel();
        jNote0 = new javax.swing.JTextField();
        jNote1 = new javax.swing.JTextField();
        jNote2 = new javax.swing.JTextField();
        jNoteValue0 = new javax.swing.JTextField();
        jNoteValue1 = new javax.swing.JTextField();
        jNoteValue2 = new javax.swing.JTextField();
        jStrengthDesc = new javax.swing.JLabel();

        JConfigurationFrame.setAutoRequestFocus(false);
        JConfigurationFrame.setBackground(new java.awt.Color(204, 204, 255));
        JConfigurationFrame.setResizable(false);
        JConfigurationFrame.setType(java.awt.Window.Type.POPUP);

        jConfigurationPanel.setBackground(new java.awt.Color(204, 204, 255));
        jConfigurationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSerialConfigurationPanel.setBackground(new java.awt.Color(204, 204, 255));
        jSerialConfigurationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSerialPanelTitle.setFont(new java.awt.Font("Segoe UI Light", 0, 32)); // NOI18N
        jSerialPanelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jSerialPanelTitle.setText("Select Port Configuration");

        jConfigPanel.setBackground(new java.awt.Color(204, 204, 255));

        jBaudRateBox.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jBaudRateBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9600", "256000", "128000", "115200", "57600", "38400", "19200", "14400", "4800", "300" }));

        jDataBitsBox.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jDataBitsBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "7" }));

        jParityBox.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jParityBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none", "odd", "even" }));

        jStopBitsBox.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jStopBitsBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "1.5", "2" }));

        jAcceptConfigurationButton.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jAcceptConfigurationButton.setText("Accept");
        jAcceptConfigurationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAcceptConfigurationButtonActionPerformed(evt);
            }
        });

        jBaudRateDesc.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jBaudRateDesc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jBaudRateDesc.setText("Baud rate:");

        jBaudRateDesc1.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jBaudRateDesc1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jBaudRateDesc1.setText("Data bits:");

        jBaudRateDesc2.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jBaudRateDesc2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jBaudRateDesc2.setText("Parity:");

        jBaudRateDesc3.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jBaudRateDesc3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jBaudRateDesc3.setText("Stop bits:");

        jCancelConfigurationButton.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jCancelConfigurationButton.setForeground(new java.awt.Color(102, 102, 255));
        jCancelConfigurationButton.setText("Cancel");
        jCancelConfigurationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelConfigurationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jConfigPanelLayout = new javax.swing.GroupLayout(jConfigPanel);
        jConfigPanel.setLayout(jConfigPanelLayout);
        jConfigPanelLayout.setHorizontalGroup(
            jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jConfigPanelLayout.createSequentialGroup()
                .addGroup(jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jConfigPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jBaudRateDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jConfigPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jBaudRateDesc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jConfigPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jBaudRateDesc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jConfigPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBaudRateDesc3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCancelConfigurationButton, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jAcceptConfigurationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBaudRateBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDataBitsBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jParityBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jStopBitsBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jConfigPanelLayout.setVerticalGroup(
            jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBaudRateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBaudRateDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDataBitsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBaudRateDesc1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jParityBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBaudRateDesc2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jStopBitsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBaudRateDesc3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAcceptConfigurationButton)
                    .addComponent(jCancelConfigurationButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jSerialConfigurationPanelLayout = new javax.swing.GroupLayout(jSerialConfigurationPanel);
        jSerialConfigurationPanel.setLayout(jSerialConfigurationPanelLayout);
        jSerialConfigurationPanelLayout.setHorizontalGroup(
            jSerialConfigurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSerialConfigurationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jSerialConfigurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jSerialConfigurationPanelLayout.createSequentialGroup()
                        .addComponent(jSerialPanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(jSerialConfigurationPanelLayout.createSequentialGroup()
                        .addComponent(jConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jSerialConfigurationPanelLayout.setVerticalGroup(
            jSerialConfigurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSerialConfigurationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSerialPanelTitle)
                .addGap(18, 18, 18)
                .addComponent(jConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jFileOperationPanel.setBackground(new java.awt.Color(204, 204, 255));
        jFileOperationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jFileChooserPanelTitle.setFont(new java.awt.Font("Segoe UI Light", 0, 32)); // NOI18N
        jFileChooserPanelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jFileChooserPanelTitle.setText("Export List Of Chords");

        jFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooser.setApproveButtonText("OK");
        jFileChooser.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooserActionPerformed(evt);
            }
        });

        jChooseFileButton.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jChooseFileButton.setText("Select file");
        jChooseFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChooseFileButtonActionPerformed(evt);
            }
        });

        jSaveChordsButton.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jSaveChordsButton.setText("Save");
        jSaveChordsButton.setEnabled(false);
        jSaveChordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveChordsButtonActionPerformed(evt);
            }
        });

        jCancelSaveButton.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jCancelSaveButton.setForeground(new java.awt.Color(102, 102, 255));
        jCancelSaveButton.setText("Close");
        jCancelSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelSaveButtonActionPerformed(evt);
            }
        });

        jFileOperationDialog.setFont(new java.awt.Font("Segoe UI Symbol", 2, 13)); // NOI18N
        jFileOperationDialog.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jFileOperationDialog.setText(" ");

        jFileName.setFont(new java.awt.Font("Segoe UI Symbol", 2, 13)); // NOI18N
        jFileName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jFileName.setText(" ");

        javax.swing.GroupLayout jFileOperationPanelLayout = new javax.swing.GroupLayout(jFileOperationPanel);
        jFileOperationPanel.setLayout(jFileOperationPanelLayout);
        jFileOperationPanelLayout.setHorizontalGroup(
            jFileOperationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jFileOperationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFileOperationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFileChooserPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jFileOperationPanelLayout.createSequentialGroup()
                        .addComponent(jChooseFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jSaveChordsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jCancelSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFileOperationPanelLayout.createSequentialGroup()
                        .addComponent(jFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFileOperationDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jFileOperationPanelLayout.setVerticalGroup(
            jFileOperationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFileOperationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooserPanelTitle)
                .addGap(18, 18, 18)
                .addComponent(jFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jFileOperationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jChooseFileButton)
                    .addComponent(jSaveChordsButton)
                    .addComponent(jCancelSaveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFileOperationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFileOperationDialog)
                    .addComponent(jFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jFileChooser.setVisible(false);

        javax.swing.GroupLayout jConfigurationPanelLayout = new javax.swing.GroupLayout(jConfigurationPanel);
        jConfigurationPanel.setLayout(jConfigurationPanelLayout);
        jConfigurationPanelLayout.setHorizontalGroup(
            jConfigurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jConfigurationPanelLayout.createSequentialGroup()
                .addComponent(jSerialConfigurationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jFileOperationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jConfigurationPanelLayout.setVerticalGroup(
            jConfigurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSerialConfigurationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jFileOperationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JConfigurationFrameLayout = new javax.swing.GroupLayout(JConfigurationFrame.getContentPane());
        JConfigurationFrame.getContentPane().setLayout(JConfigurationFrameLayout);
        JConfigurationFrameLayout.setHorizontalGroup(
            JConfigurationFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jConfigurationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JConfigurationFrameLayout.setVerticalGroup(
            JConfigurationFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jConfigurationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTitleLabel.setFont(new java.awt.Font("Segoe UI Light", 0, 40)); // NOI18N
        jTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTitleLabel.setText("Piano Chord Logger");

        jOpenPortButton.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jOpenPortButton.setText("Open");
        jOpenPortButton.setEnabled(false);
        jOpenPortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenPortButtonActionPerformed(evt);
            }
        });

        jClosePortButton.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jClosePortButton.setText("Close");
        jClosePortButton.setEnabled(false);
        jClosePortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClosePortButtonActionPerformed(evt);
            }
        });

        jConfigurePortButton.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jConfigurePortButton.setText("Configure");
        jConfigurePortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConfigurePortButtonActionPerformed(evt);
            }
        });

        jPortNameDesc.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jPortNameDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPortNameDesc.setText(" ");

        jSerialConfigurationDesc.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jSerialConfigurationDesc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jSerialConfigurationDesc.setText("9600, 8N1");

        jAvailablePortsBox.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jAvailablePortsBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Port" }));
        jAvailablePortsBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jAvailablePortsBoxItemStateChanged(evt);
            }
        });
        jAvailablePortsBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jAvailablePortsBoxPopupMenuWillBecomeVisible(evt);
            }
        });

        javax.swing.GroupLayout jSerialPortPanelLayout = new javax.swing.GroupLayout(jSerialPortPanel);
        jSerialPortPanel.setLayout(jSerialPortPanelLayout);
        jSerialPortPanelLayout.setHorizontalGroup(
            jSerialPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jConfigurePortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jSerialPortPanelLayout.createSequentialGroup()
                .addGroup(jSerialPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAvailablePortsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jSerialPortPanelLayout.createSequentialGroup()
                        .addComponent(jOpenPortButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jClosePortButton))
                    .addGroup(jSerialPortPanelLayout.createSequentialGroup()
                        .addComponent(jPortNameDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSerialConfigurationDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jSerialPortPanelLayout.setVerticalGroup(
            jSerialPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jSerialPortPanelLayout.createSequentialGroup()
                .addComponent(jAvailablePortsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jConfigurePortButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jSerialPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSerialConfigurationDesc)
                    .addComponent(jPortNameDesc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jSerialPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jOpenPortButton)
                    .addComponent(jClosePortButton)))
        );

        jCurrentChord.setEditable(false);
        jCurrentChord.setBackground(new java.awt.Color(204, 204, 255));
        jCurrentChord.setFont(new java.awt.Font("Segoe UI Light", 0, 50)); // NOI18N
        jCurrentChord.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jCurrentChord.setToolTipText("");
        jCurrentChord.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jClearChordLogButton.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jClearChordLogButton.setText("Clear");
        jClearChordLogButton.setEnabled(false);
        jClearChordLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClearChordLogButtonActionPerformed(evt);
            }
        });

        jExportChordsButton.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jExportChordsButton.setText("Export");
        jExportChordsButton.setEnabled(false);
        jExportChordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExportChordsButtonActionPerformed(evt);
            }
        });

        jChordLogScroll.setAutoscrolls(true);
        jChordLogScroll.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jChordLog.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jChordLog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jChordLog.setDragEnabled(true);
        jChordLogScroll.setViewportView(jChordLog);

        jValidChordPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jValidChordPanelLayout = new javax.swing.GroupLayout(jValidChordPanel);
        jValidChordPanel.setLayout(jValidChordPanelLayout);
        jValidChordPanelLayout.setHorizontalGroup(
            jValidChordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jValidChordPanelLayout.setVerticalGroup(
            jValidChordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jChordsPanelLayout = new javax.swing.GroupLayout(jChordsPanel);
        jChordsPanel.setLayout(jChordsPanelLayout);
        jChordsPanelLayout.setHorizontalGroup(
            jChordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jChordsPanelLayout.createSequentialGroup()
                .addComponent(jCurrentChord, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jChordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jClearChordLogButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jExportChordsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jValidChordPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jChordLogScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jChordsPanelLayout.setVerticalGroup(
            jChordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jChordsPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jValidChordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jChordLogScroll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jExportChordsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jClearChordLogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jChordsPanelLayout.createSequentialGroup()
                .addComponent(jCurrentChord, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jNote0.setEditable(false);
        jNote0.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jNote0.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jNote0.setText("-");

        jNote1.setEditable(false);
        jNote1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jNote1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jNote1.setText("-");

        jNote2.setEditable(false);
        jNote2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jNote2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jNote2.setText("-");

        jNoteValue0.setEditable(false);
        jNoteValue0.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jNoteValue0.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jNoteValue0.setText("-");

        jNoteValue1.setEditable(false);
        jNoteValue1.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jNoteValue1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jNoteValue1.setText("-");

        jNoteValue2.setEditable(false);
        jNoteValue2.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jNoteValue2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jNoteValue2.setText("-");

        jStrengthDesc.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        jStrengthDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jStrengthDesc.setText("strength");

        javax.swing.GroupLayout jNotesPanelLayout = new javax.swing.GroupLayout(jNotesPanel);
        jNotesPanel.setLayout(jNotesPanelLayout);
        jNotesPanelLayout.setHorizontalGroup(
            jNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jNotesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jNote1)
                    .addComponent(jNote0)
                    .addComponent(jNote2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jNoteValue2)
                    .addComponent(jNoteValue0)
                    .addComponent(jNoteValue1)
                    .addComponent(jStrengthDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap())
        );
        jNotesPanelLayout.setVerticalGroup(
            jNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jNotesPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jStrengthDesc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jNoteValue2)
                    .addComponent(jNote2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jNote1)
                    .addComponent(jNoteValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jNote0)
                    .addComponent(jNoteValue0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jMainPanelLayout = new javax.swing.GroupLayout(jMainPanel);
        jMainPanel.setLayout(jMainPanelLayout);
        jMainPanelLayout.setHorizontalGroup(
            jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMainPanelLayout.createSequentialGroup()
                        .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jNotesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jMainPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jSerialPortPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jChordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jMainPanelLayout.setVerticalGroup(
            jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTitleLabel)
                .addGap(30, 30, 30)
                .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMainPanelLayout.createSequentialGroup()
                        .addComponent(jSerialPortPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jNotesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jChordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jAvailablePortsBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jAvailablePortsBoxItemStateChanged
        serial.selectPort((String) jAvailablePortsBox.getSelectedItem());

        if (serial.isSelectedPortValid()) {
            updatePortNameDesc();
            jOpenPortButton.setEnabled(true);
        } else {
            resetPortNameDesc();
            jOpenPortButton.setEnabled(false);
        }
    }//GEN-LAST:event_jAvailablePortsBoxItemStateChanged

    private void jAvailablePortsBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jAvailablePortsBoxPopupMenuWillBecomeVisible
        serial.updatePortNames();
        if (serial.isAnyPortAvailable()) {
            jAvailablePortsBox.removeAllItems();
            for (String s : serial.getPortNames()) {
                jAvailablePortsBox.addItem(s);
            }
        } else {
            jAvailablePortsBox.removeAllItems();
            jAvailablePortsBox.addItem("Select Port");
        }
    }//GEN-LAST:event_jAvailablePortsBoxPopupMenuWillBecomeVisible

    private void jConfigurePortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConfigurePortButtonActionPerformed
        showSerialConfigurationPanel();
    }//GEN-LAST:event_jConfigurePortButtonActionPerformed

    private void jAcceptConfigurationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAcceptConfigurationButtonActionPerformed
        updateSerialConfigurationWithDesc();
        this.setAlwaysOnTop(false);
        this.setEnabled(true);
        JConfigurationFrame.setVisible(false);
    }//GEN-LAST:event_jAcceptConfigurationButtonActionPerformed

    private void jCancelConfigurationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelConfigurationButtonActionPerformed
        this.setEnabled(true);
        JConfigurationFrame.setVisible(false);
    }//GEN-LAST:event_jCancelConfigurationButtonActionPerformed

    private void jOpenPortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenPortButtonActionPerformed
        serial.updatePortNames(); 
        if (serial.isSelectedPortValid()) {
            SerialPort port = new SerialPort(serial.getSelectedPortName());
            serial.Configure(port, serialConfig, Serial.ReadEventState.READ_EVT_ON);
            transceiver.setPort(serial.getSelectedPort());
            
            receiverMonitorTimer = new Timer(SERIAL_RECEIVER_REFRESH_RATE_MS, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (transceiver.receivedMessageQueue.isEmpty()) {
                        return;
                    }
                    
                    try {
                        String msg = transceiver.receivedMessageQueue.take();

                        serialDecoder.updateMessageFrame(msg);
                        if (serialDecoder.isLastFullMessageReady()) {
                            int[] elements = serialDecoder.getElementsOfLastFullMessage();
                            serialDecoder.clearLastFullMessage();
                            noteDecoder.matchNotes(elements);
                            if (noteDecoder.isAnyNoteFound()) {
                                DefaultListModel<Note> matchingNotes = noteDecoder.getMatchings();
                                
                                DefaultListModel<Integer> matchingValues = noteDecoder.getMatchingsValues();
                                
                                jNote0.setText(matchingNotes.get(0).getName() );
                                jNoteValue0.setText(matchingValues.get(0).toString());
                                
                                if (noteDecoder.getMatchings().size() > 1) {
                                    jNote1.setText(matchingNotes.get(1).getName() );
                                    jNoteValue1.setText(matchingValues.get(1).toString());
                                }
                                if (noteDecoder.getMatchings().size() > 2) {
                                   jNote2.setText(matchingNotes.get(2).getName() );
                                   jNoteValue2.setText(matchingValues.get(2).toString());
                                }
                                
                                chordDecoder.findChord(matchingNotes);
                                if (chordDecoder.isChordFound() && !chordDecoder.isChordSpecial()) {
                                    jValidChordPanel.setBackground(Color.green);
                                    jCurrentChord.setText(chordDecoder.getNameOfLastChord());
                                    System.out.println("- valid chord: " + jCurrentChord.getText());
                                    chordDeleted = false;
                                } else if (chordDecoder.isChordSpecialForNext() && jCurrentChord.getText().length() > 0) {
                                    jValidChordPanel.setBackground(Color.BLUE);

                                    chordLogList.addElement(jCurrentChord.getText());
                                    jChordLog.setModel(chordLogList);
                                    scrollDownLog(jChordLogScroll);
                                    
                                    jCurrentChord.setText("");
                                } else if (chordDecoder.isChordSpecialForPrevious() && !chordLogList.isEmpty() && !chordDeleted) {
                                    jValidChordPanel.setBackground(Color.gray);
                                    
                                    int lastIndex = chordLogList.getSize() - 1;
                                    chordLogList.remove(lastIndex);
                                    jChordLog.setModel(chordLogList);
                                    scrollDownLog(jChordLogScroll);
                                    
                                    jCurrentChord.setText("");
                                    chordDeleted = true;
                                } else {
                                    jValidChordPanel.setBackground(Color.red);
                                }
                            }
                        }
                    } catch (InterruptedException ex) {
                    }

                    jClearChordLogButton.setEnabled(true);
                }
            });
            
            transceiver.flush();
            receiverMonitorTimer.start();
            
            jExportChordsButton.setEnabled(false);
            jOpenPortButton.setEnabled(false);
            jConfigurePortButton.setEnabled(false);
            jAvailablePortsBox.setEnabled(false);
            jClosePortButton.setEnabled(true);
            
         } else {
            jAvailablePortsBox.removeAllItems();
            jAvailablePortsBox.addItem("Select Port");
            jOpenPortButton.setEnabled(false);            
        }
    }//GEN-LAST:event_jOpenPortButtonActionPerformed

    private void jClosePortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClosePortButtonActionPerformed
        serial.Close();
        
        if (!serial.isOpened()) {
            transceiver = serial.transceiver;
            serial.updatePortNames();
            
            receiverMonitorTimer.stop();
            receiverMonitorTimer = null;
            
            jClosePortButton.setEnabled(false);
            jConfigurePortButton.setEnabled(true);
            jAvailablePortsBox.setEnabled(true);
            jOpenPortButton.setEnabled(true);
            jClearChordLogButton.setEnabled(false);
            
            if (!chordLogList.isEmpty()) {
                jExportChordsButton.setEnabled(true);
            }
        }
    }//GEN-LAST:event_jClosePortButtonActionPerformed

    private void jClearChordLogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClearChordLogButtonActionPerformed
        chordLogList.removeAllElements();
        jChordLog.setModel(new DefaultListModel<>());
        scrollDownLog(jChordLogScroll);
        
        jClearChordLogButton.setEnabled(false);
    }//GEN-LAST:event_jClearChordLogButtonActionPerformed

    private void jFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooserActionPerformed
        if (evt.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
            File file = jFileChooser.getSelectedFile();
            chordLogger.setLogAbsolutePath(file.getAbsolutePath());
            if (chordLogger.isSelectedPathWithFile()) {
                jFileOperationDialog.setText("File exists. Saving will overwrite...");
            }
            jFileName.setText(file.getName());
            jFileChooser.setVisible(false);
            jSaveChordsButton.setEnabled(true);
            jChooseFileButton.setVisible(true);
            jSaveChordsButton.setVisible(true);
        } else if (evt.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
            jFileChooser.setVisible(false);
            jChooseFileButton.setVisible(true);
            jSaveChordsButton.setVisible(true);
        }

    }//GEN-LAST:event_jFileChooserActionPerformed

    private void jCancelSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelSaveButtonActionPerformed
        this.setEnabled(true);
        JConfigurationFrame.setVisible(false);
    }//GEN-LAST:event_jCancelSaveButtonActionPerformed

    private void jExportChordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExportChordsButtonActionPerformed
        showFileOperationPanel();
    }//GEN-LAST:event_jExportChordsButtonActionPerformed

    private void jChooseFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChooseFileButtonActionPerformed
        jFileChooser.setVisible(true);
        jChooseFileButton.setVisible(false);
        jSaveChordsButton.setVisible(false);
    }//GEN-LAST:event_jChooseFileButtonActionPerformed

    private void jSaveChordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveChordsButtonActionPerformed
        if (!chordLogger.getSelectedAbsolutePath().equals("")) {
            chordLogger.save(chordLogList, "Koncert chopina");
            jFileOperationDialog.setText(chordLogList.getSize() + " chords saved!");
        }
    }//GEN-LAST:event_jSaveChordsButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Metal look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Metal (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame JConfigurationFrame;
    private javax.swing.JButton jAcceptConfigurationButton;
    private javax.swing.JComboBox<String> jAvailablePortsBox;
    private javax.swing.JComboBox<String> jBaudRateBox;
    private javax.swing.JLabel jBaudRateDesc;
    private javax.swing.JLabel jBaudRateDesc1;
    private javax.swing.JLabel jBaudRateDesc2;
    private javax.swing.JLabel jBaudRateDesc3;
    private javax.swing.JButton jCancelConfigurationButton;
    private javax.swing.JButton jCancelSaveButton;
    private javax.swing.JButton jChooseFileButton;
    private javax.swing.JList<String> jChordLog;
    private javax.swing.JScrollPane jChordLogScroll;
    private javax.swing.JPanel jChordsPanel;
    private javax.swing.JButton jClearChordLogButton;
    private javax.swing.JButton jClosePortButton;
    private javax.swing.JPanel jConfigPanel;
    private javax.swing.JPanel jConfigurationPanel;
    private javax.swing.JButton jConfigurePortButton;
    private javax.swing.JTextField jCurrentChord;
    private javax.swing.JComboBox<String> jDataBitsBox;
    private javax.swing.JButton jExportChordsButton;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jFileChooserPanelTitle;
    private javax.swing.JLabel jFileName;
    private javax.swing.JLabel jFileOperationDialog;
    private javax.swing.JPanel jFileOperationPanel;
    private javax.swing.JPanel jMainPanel;
    private javax.swing.JTextField jNote0;
    private javax.swing.JTextField jNote1;
    private javax.swing.JTextField jNote2;
    private javax.swing.JTextField jNoteValue0;
    private javax.swing.JTextField jNoteValue1;
    private javax.swing.JTextField jNoteValue2;
    private javax.swing.JPanel jNotesPanel;
    private javax.swing.JButton jOpenPortButton;
    private javax.swing.JComboBox<String> jParityBox;
    private javax.swing.JLabel jPortNameDesc;
    private javax.swing.JButton jSaveChordsButton;
    private javax.swing.JLabel jSerialConfigurationDesc;
    private javax.swing.JPanel jSerialConfigurationPanel;
    private javax.swing.JLabel jSerialPanelTitle;
    private javax.swing.JPanel jSerialPortPanel;
    private javax.swing.JComboBox<String> jStopBitsBox;
    private javax.swing.JLabel jStrengthDesc;
    private javax.swing.JLabel jTitleLabel;
    private javax.swing.JPanel jValidChordPanel;
    // End of variables declaration//GEN-END:variables
}
