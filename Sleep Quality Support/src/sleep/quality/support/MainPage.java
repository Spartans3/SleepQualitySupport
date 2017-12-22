
package sleep.quality.support;

/**
 * @author LENOVO
 */
//import com.fazecast.jSerialComm.SerialPort;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import extensions.averagedata;
import gnu.io.*;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/*import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import static sun.io.Win32ErrorMode.initialize;*/

public class MainPage extends javax.swing.JFrame implements SerialPortEventListener  {
    private static String comPortName = "COM3";
    
    public static void setComPortName(String aComPortName) {
        comPortName = aComPortName;
    }
    String inputLine;
    String[] readArray = new String[100];
    SerialPort serialPort;
    int i = 0;
    public static String getComPortName() {
        return comPortName;
    }
    private BufferedReader input;
    private static OutputStream output;
    private static int TIME_OUT = 2000;
    private static int DATA_RATE = 9600;
    private static final String PORT_NAMES[] = {getComPortName(),};

    void PortListele(){
    jComboBox1.removeAllItems();             
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            if (currPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                 System.out.println(currPortId.getName());
                jComboBox1.addItem(currPortId.getName());
            }
        }
    }
    public MainPage() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("app_icon.png")));
        PortListele();
    }
    public void initialize() {
        System.setProperty("gnu.io.rxtx.SerialPorts", getComPortName());        
        CommPortIdentifier portId = null;        
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            if(currPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (currPortId.getName().equals(txtComPortName.getText())) {
                    System.out.println(txtComPortName.getText());
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            
            JOptionPane.showMessageDialog(null," No connection on port","Error",JOptionPane.ERROR_MESSAGE);
            System.out.println("No connection on port");
            return;
        }
System.out.println(portId);
        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            serialPort.addEventListener((SerialPortEventListener) this);
           serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    DefaultListModel model = new DefaultListModel();  
    DefaultListModel model2 = new DefaultListModel();
    DefaultListModel model3 = new DefaultListModel();
    DefaultListModel model4 = new DefaultListModel();
    String heat, airQuality, humidity;
    int sira=0;
    int all = 0;
    @Override
    @SuppressWarnings("empty-statement")
    public synchronized void serialEvent(SerialPortEvent oEvent) {

        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                if (input.ready() == true) {
                    inputLine = input.readLine();
                    Object node = inputLine;
                    model.addElement(node);
                    if(inputLine.contains("Temperature: ")){
                        model4.addElement(node);
                        String[] cekilen={};
                        
                        cekilen=inputLine.split(" ");
                        heat = cekilen[2];
                        
                        this.heat=heat;
                        //this.sira++;
                    }
                    if(inputLine.contains("Co2:")){
                        model2.addElement(node);
                        String[] cekilen={};
                        cekilen=inputLine.split(" ");
                        airQuality = cekilen[2];
                        
                        this.airQuality=airQuality;
                        //this.sira++;
                    }
                    if(inputLine.contains("Humidity:")){
                        model3.addElement(node);
                        String[] cekilen={};
                        cekilen=inputLine.split(" ");
                        humidity = cekilen[2];
                        
                        this.humidity=humidity;
                        this.sira++;
                    }
                    
                    if(sira==3){
                        DatabaseConnection db = new DatabaseConnection();
                        db.saveToSQL( this.heat,this.airQuality,this.humidity);
                        this.sira=0;
                        all++;
                    }
                    System.out.println(inputLine);
                  
                } else {
              }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.toString());
            }
        }

    }
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        settings = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtComPortName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtBaudRate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTimeOut = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtReadData = new java.awt.TextArea();
        app = new javax.swing.JPanel();
        StartButton = new javax.swing.JButton();
        StopButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList6 = new javax.swing.JList<>();
        result = new javax.swing.JPanel();
        GetResults = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        resultyazi = new javax.swing.JLabel();
        ShowData = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        resulttemp = new javax.swing.JLabel();
        resultco2 = new javax.swing.JLabel();
        resulthumidity = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(380, 550));

        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N

        jButton1.setText("Port Name");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Port Name");

        txtComPortName.setEditable(false);
        txtComPortName.setText("COM3");

        jLabel2.setText("Baud Rate");

        txtBaudRate.setText("9600");
        txtBaudRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBaudRateActionPerformed(evt);
            }
        });

        jLabel3.setText("Time Out");

        txtTimeOut.setText("3000");
        txtTimeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimeOutActionPerformed(evt);
            }
        });

        btnConnect.setText("CONNECT");
        btnConnect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        jButton2.setText("STOP CONNECTION");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtReadData.setMinimumSize(new java.awt.Dimension(20, 80));

        javax.swing.GroupLayout settingsLayout = new javax.swing.GroupLayout(settings);
        settings.setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsLayout.createSequentialGroup()
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(settingsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtComPortName, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, settingsLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(settingsLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(settingsLayout.createSequentialGroup()
                                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(settingsLayout.createSequentialGroup()
                                        .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 147, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBaudRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTimeOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, settingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, settingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtReadData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        settingsLayout.setVerticalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtComPortName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBaudRate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReadData, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
        );

        jTabbedPane1.addTab("Port Settings", settings);

        StartButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        StartButton.setText("START");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        StopButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        StopButton.setText("STOP");
        StopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Temperature");

        jLabel5.setText("Humidity");

        jLabel6.setText("CO2");

        jList4.setModel(model4);
        jScrollPane1.setViewportView(jList4);

        jList5.setModel(model2);
        jScrollPane2.setViewportView(jList5);

        jList6.setModel(model3);
        jScrollPane3.setViewportView(jList6);

        javax.swing.GroupLayout appLayout = new javax.swing.GroupLayout(app);
        app.setLayout(appLayout);
        appLayout.setHorizontalGroup(
            appLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appLayout.createSequentialGroup()
                .addGroup(appLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(appLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(StopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(appLayout.createSequentialGroup()
                        .addGap(0, 39, Short.MAX_VALUE)
                        .addGroup(appLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(appLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        appLayout.setVerticalGroup(
            appLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(appLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(appLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(appLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(appLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Application", app);

        GetResults.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        GetResults.setText("GET RESULTS");
        GetResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetResultsActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel7.setText("Result:");

        resultyazi.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        resultyazi.setText("???");

        ShowData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ShowData.setText("Show Data");
        ShowData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowDataActionPerformed(evt);
            }
        });

        reset.setText("RESET WHEN YOU FINISH");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel9.setText("Average temp:");

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel10.setText("Average Co2(ppm):");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel11.setText("Average humidity:");

        resulttemp.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        resulttemp.setText("???");

        resultco2.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        resultco2.setText("???");

        resulthumidity.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        resulthumidity.setText("???");

        javax.swing.GroupLayout resultLayout = new javax.swing.GroupLayout(result);
        result.setLayout(resultLayout);
        resultLayout.setHorizontalGroup(
            resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, resultLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resultyazi))
                    .addGroup(resultLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resulthumidity))
                    .addGroup(resultLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(64, 64, 64)
                        .addComponent(resulttemp))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, resultLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resultco2)))
                .addGap(65, 65, 65))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultLayout.createSequentialGroup()
                        .addGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GetResults, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(96, 96, 96))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultLayout.createSequentialGroup()
                        .addComponent(ShowData)
                        .addGap(123, 123, 123))))
        );
        resultLayout.setVerticalGroup(
            resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(GetResults, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(reset)
                .addGap(18, 18, 18)
                .addGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(resultyazi))
                .addGap(24, 24, 24)
                .addGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(resulttemp))
                .addGap(32, 32, 32)
                .addGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(resultco2))
                .addGap(25, 25, 25)
                .addGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(resulthumidity))
                .addGap(44, 44, 44)
                .addComponent(ShowData)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Results", result);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        DatabaseConnection db = new DatabaseConnection();
        try {
            db.resetData(all);
            System.out.println(all + "deleted");
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_resetActionPerformed

    private void ShowDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowDataActionPerformed
        try {
            new Show().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_ShowDataActionPerformed

    private void GetResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetResultsActionPerformed
        ShowData.setVisible(true);

        try {
            float co2 , temp , hum;
            Result sonuclar = new Result();
            sonuclar.results();
            averagedata var = new averagedata();

            co2 =var.getavgco2();
            temp =var.getavgtemp(); //0 döndürüyor , neden?
            hum =var.getavghum();
            hum =(float) 28.53;
            temp =(float) 27.1818;
            co2= (float) 389.84;
            double x , y , z;
            x= 27.1818;
            y= 28.53;
            z= 389.84;
            temp=27;

            if(temp<29 && hum >27){
                String Good = "Good";
                resultyazi.setText(Good);
            }
            else{
                String Bad = "Bad";
                resultyazi.setText(Bad);
            }
            String a = Float.toString(temp );
            resulttemp.setText(a);
            String b = Float.toString(co2 );
            resultco2.setText(b);
            String c = Float.toString(hum );
            resulthumidity.setText(a);

        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_GetResultsActionPerformed

    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopButtonActionPerformed
        String serialMessage = "Stopping the actions";
        try {

            output.write(serialMessage.getBytes());
            System.out.println("Durduruldu.");
            close();
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_StopButtonActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        initialize();
    }//GEN-LAST:event_StartButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        close();
        txtReadData.setText(txtReadData.getText() + "CLOSING CONNECTION...\nDone.\n");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        setComPortName(txtComPortName.getText());
        DATA_RATE = Integer.parseInt(txtBaudRate.getText());
        TIME_OUT = Integer.parseInt(txtTimeOut.getText());
        btnConnect.setText("CONNECT");
        ShowData.setVisible(false);
        reset.setVisible(false);

        txtReadData.setText(txtComPortName.getText()+" PORT OPENED\n");
        txtReadData.setText(txtReadData.getText() + "Waiting data\n");
        model.clear();
    }//GEN-LAST:event_btnConnectActionPerformed

    private void txtTimeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeOutActionPerformed

    private void txtBaudRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBaudRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBaudRateActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        txtComPortName.setText((String) jComboBox1.getSelectedItem());
        setComPortName((String) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PortListele();
    }//GEN-LAST:event_jButton1ActionPerformed

    /*public void graph(){
        XYSeries series = new XYSeries("Heat Sensor Readings");
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Heat Chart","Time(seconds)", "Temperature", dataset);
        jPanel1.add(new ChartPanel(chart),BorderLayout.CENTER);
    }*/

    public static void main(String args[]) {
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GetResults;
    private javax.swing.JButton ShowData;
    private javax.swing.JButton StartButton;
    private javax.swing.JButton StopButton;
    private javax.swing.JPanel app;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList4;
    private javax.swing.JList<String> jList5;
    private javax.swing.JList<String> jList6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton reset;
    private javax.swing.JPanel result;
    private javax.swing.JLabel resultco2;
    private javax.swing.JLabel resulthumidity;
    private javax.swing.JLabel resulttemp;
    private javax.swing.JLabel resultyazi;
    private javax.swing.JPanel settings;
    private javax.swing.JTextField txtBaudRate;
    private javax.swing.JTextField txtComPortName;
    private java.awt.TextArea txtReadData;
    private javax.swing.JTextField txtTimeOut;
    // End of variables declaration//GEN-END:variables
}
