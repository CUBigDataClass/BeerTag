package com.hadoop.keyword.count;

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
public class ItemMapper extends Mapper<Object, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	JSONParser parser = new JSONParser();

	@Override
	public void map(Object key, Text value, Context output) throws IOException,
			InterruptedException {

		JSONObject tweetObject = null;

		String[] keywords = this.getKeyWords(output);

		try {
			tweetObject = (JSONObject) parser.parse(value.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (tweetObject != null) {
			String tweetText = (String) tweetObject.get("text");

			if(tweetText == null){
				return;
			}
			
			tweetText = tweetText.toLowerCase();
/*			StringTokenizer st = new StringTokenizer(tweetText);

			ArrayList<String> tokens = new ArrayList<String>();

			while (st.hasMoreTokens()) {
				tokens.add(st.nextToken());
			}*/

			for (String keyword : keywords) {
				keyword = keyword.toLowerCase();
				if (tweetText.contains(keyword)) {
					output.write(new Text(keyword), one);
				}
			}
			output.write(new Text("count"), one);
		}

	}

	String[] getKeyWords(Mapper<Object, Text, Text, IntWritable>.Context context) {

		Configuration conf = (Configuration) context.getConfiguration();
		String param = conf.get("keywords");

		return param.split(",");

	}
}
