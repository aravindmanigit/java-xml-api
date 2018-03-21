package com.aravind.xml.data;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.aravind.xml.dataprovider.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterData<T> {
	
	public static final String DATADIR = "src//main//resources//data//";
	private final static String RESULT_NODE = "result";
	
	public List<T> getData(String fileName, Class<T> classz)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String resultNode = getResultNode(new File(DATADIR + fileName), objectMapper);
		List<T> lists = getResultAsList(resultNode , new ObjectMapper());
		//System.out.println(lists);
		return lists;
	}
	
	public T[] getDataAsArray(String fileName, Class<T[]> classz)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String resultNode = getResultNode(new File(DATADIR + fileName), objectMapper);
		T[]  lists = getResultAsArray(resultNode, classz);
		//System.out.println(lists);
		return lists;
	}
	
	public String getResultNode(File file, ObjectMapper objectMapper)
	{
		ObjectMapper resultNodeMapper = null;
		if (objectMapper == null)
		{
			resultNodeMapper = new ObjectMapper();
		}
		else
		{
			resultNodeMapper = objectMapper;
		}
		if (file != null && file.exists())
		{
			try {
				JsonNode rootNode = resultNodeMapper.readTree(file);
				JsonNode result = rootNode.findValue(RESULT_NODE);
				return result.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
		return null;
	}
	
	public List<T> getResultAsList(String resultNodeJSON, ObjectMapper objectMapper)
	{
		if (StringUtils.isNotBlank(resultNodeJSON) && objectMapper != null)
		{
			try {
				
				return objectMapper.readValue(resultNodeJSON, new TypeReference<List<T>>() {
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
	
	public T[] getResultAsArray(String resultNodeJSON, Class<T[]> classz)
	{
		if (StringUtils.isNotBlank(resultNodeJSON))
		{
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
				return objectMapper.readValue(resultNodeJSON, classz);
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
		MasterData<Customer> masterData = new MasterData<>();
		Customer[] customers = masterData.getDataAsArray("customers1.json", Customer[].class);
		List<Customer> customersList = Arrays.asList(customers);
		System.out.println(customersList);
	}
}
