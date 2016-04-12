package com.beertag;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ItemImpl {

	public static void main(String[] args) throws Exception {

	    Configuration conf = new Configuration();
	    conf.set("keywords", args[2]);
	    
	    Job job = Job.getInstance(conf, "item count");
	    job.setJarByClass(ItemImpl.class);
		job.setMapperClass(ItemMapper.class);
		job.setReducerClass(ItemReducer.class);

	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    
	    DistributedCache.addArchiveToClassPath(new Path("/users/rohit/json.jar"), job.getConfiguration());
	    
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	
	public static class ItemMapper extends Mapper<Object, Text, Text, IntWritable> {

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

	public static class ItemReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values, Context output)
				throws IOException, InterruptedException {

			int wordCount = 0;

			for (IntWritable value : values) {
				wordCount += value.get();
			}

			output.write(key, new IntWritable(wordCount));
		}
	}
}
