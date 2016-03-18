package com.hadoop.total.count;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author rohit
 *
 */
public class CountMapper extends Mapper<Object, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);

	@Override
	public void map(Object key, Text value, Context output) throws IOException,
			InterruptedException {

		String keywordCountString = value.toString();

		if (keywordCountString != null && !keywordCountString.equals("")) {
			
			System.out.println(keywordCountString);
			String[] keyValuePair = keywordCountString.split("\t");

			String keyword = keyValuePair[0];
			int count = Integer.parseInt(keyValuePair[1]);

			output.write(new Text(keyword), new IntWritable(count));
		}

	}

}
