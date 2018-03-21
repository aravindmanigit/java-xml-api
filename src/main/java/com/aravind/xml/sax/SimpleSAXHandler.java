package com.aravind.xml.sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.aravind.xml.dataprovider.model.Customer;

public class SimpleSAXHandler extends DefaultHandler {
	
	private List<Customer> data;
	private String currentElement = "";
	private Customer customer;
	
	public List<Customer> readDataFromXML(File file)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
				saxParser = factory.newSAXParser();
				saxParser.parse(file, this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void startDocument() throws SAXException {
		data = new ArrayList<Customer>();
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("End Document");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentElement = qName;
		
		switch (currentElement)
		{
			case "customers":
				break;
				
			case "customer":
				customer = new Customer();
				String idAsString = attributes.getValue("id");
				customer.setId(Integer.parseInt(idAsString));
				data.add(customer);
				break;
				
			default :
				break;
		}
		
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		currentElement = "";
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("Characters :: ");
	}
	
	public static void main(String[] args) {
		SimpleSAXHandler simpleSAXHandler = new SimpleSAXHandler();
		List<Customer> customers = simpleSAXHandler.readDataFromXML(new File("src//main//resources//data//sax.xml"));
		System.out.println(customers);
	}
	

}
