package com.aravind.xml.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.aravind.xml.dataprovider.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerData extends MasterData<Customer>{
	
	public List<Customer> getData()
	{
		//List<Customer> customers = this.getData("customers1.json", Customer.class);
		//System.out.println(customers);
		ObjectMapper objectMapper = new ObjectMapper();
		String resultNode = getResultNode(new File(DATADIR + "customersLarge.json"), objectMapper);
		List<Customer> lists = getResultAsList(resultNode , new ObjectMapper());
		System.out.println(lists.size());
		return lists;
	}
	
	public List<Customer> getResultAsList(String resultNodeJSON, ObjectMapper objectMapper)
	{
		if (StringUtils.isNotBlank(resultNodeJSON) && objectMapper != null)
		{
			try {
				
				return objectMapper.readValue(resultNodeJSON, new TypeReference<List<Customer>>() {
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
		return null;
	}
	
	public static void main(String[] args) {
		CustomerData customerData = new CustomerData();
		customerData.getData();
	}

}
