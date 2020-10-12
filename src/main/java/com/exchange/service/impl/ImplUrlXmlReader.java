package com.exchange.service.impl;

import com.exchange.entities.Channel;

import com.exchange.service.interfaces.IUrlXmlReader;

import javax.enterprise.context.RequestScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

@RequestScoped
public class ImplUrlXmlReader implements IUrlXmlReader {

    private static final String FILE_NAME = "parseThis.xml";

    @Override
    public Channel readUmlFile() {

        Channel dataSet = null;
        try {

            InputStreamReader inputStreamReader = readHttpURL("http://www.floatrates.com/daily/ron.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Channel.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            dataSet = (Channel) jaxbUnmarshaller.unmarshal(inputStreamReader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return dataSet;
    }

    private InputStreamReader readHttpURL(String httpUrl) {
        try {
            URLConnection connection = new URL(httpUrl).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            return new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8"));
        } catch (Exception ex) {
            ex.getStackTrace();
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
