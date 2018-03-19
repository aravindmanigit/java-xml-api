package com.aravind.xml.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.aravind.xml.dataprovider.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterData<T> {
	
	public static final String DATADIR = "src//main//resources//data//";
	private final static String RESULT_NODE = "result";
	
	public List<T> getData(String fileName, Class<T> classz)
	{
		//ObjectMapper objectMapper = new ObjectMapper();
		//String resultNode = getResultNode(new File(DATADIR + fileName), objectMapper);
		//List<T> lists = getResultAsList(resultNode , new ObjectMapper());
		//System.out.println(lists);
		//return lists;
		return null;
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

}
