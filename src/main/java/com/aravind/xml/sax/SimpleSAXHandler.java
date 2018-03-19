package com.aravind.xml.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleSAXHandler extends DefaultHandler {
	
	
	public void readDataFromXML(File file)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
				saxParser = factory.newSAXParser();
				saxParser.parse(file, this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Start Document");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("End Document");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("Start Element :: " + qName);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("End Element :: " + qName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("Characters :: ");
	}
	
	public static void main(String[] args) {
		SimpleSAXHandler simpleSAXHandler = new SimpleSAXHandler();
		simpleSAXHandler.readDataFromXML(new File("src//main//resources//data//sax.xml"));
	}
	

}
