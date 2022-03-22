package com.example;

import java.io.UnsupportedEncodingException;

import com.fazecast.jSerialComm.*;



/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        
        final SerialPort comPort = SerialPort.getCommPort("COM3");
        comPort.openPort();
        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(newData, newData.length);
                try {
                    String bruh = new String(newData, "UTF-8");
                    System.out.println(bruh);
                } catch (UnsupportedEncodingException e) {
                    
                }
                
            }
        });
    }

}