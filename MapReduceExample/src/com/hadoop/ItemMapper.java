package com.hadoop;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
public class ItemMapper extends Mapper<Object, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	JSONParser parser = new JSONParser();

	@Override
	public void map(Object key, Text value, Context output) throws IOException,
			InterruptedException {

		JSONObject tweetObject = null;

		ArrayList<String> keywords = this.getKeyWords();

		try {
			tweetObject = (JSONObject) parser.parse(value.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (tweetObject != null) {
			String tweetText = (String) tweetObject.get("text");

			StringTokenizer st = new StringTokenizer(tweetText);

			ArrayList<String> tokens = new ArrayList<String>();

			while (st.hasMoreTokens()) {
				tokens.add(st.nextToken());
			}

			for (String keyword : keywords) {

				for (String token : tokens) {
					if (token.equals(keyword) || token.contains(keyword)) {
						output.write(new Text(keyword), one);
						break;
					}
				}
			}

		}
		output.write(new Text("count"), one);

	}

	ArrayList<String> getKeyWords() {

		ArrayList<String> keywords = new ArrayList<String>();

		keywords.add("vodka");
		keywords.add("tequila");
		keywords.add("mojito");
		keywords.add("margarita");
		
		return keywords;

	}
}