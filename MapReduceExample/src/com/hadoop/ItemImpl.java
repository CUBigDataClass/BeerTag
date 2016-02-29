package com.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ItemImpl {
//Hadoop jar command 
//$HADOOP_HOME/bin/hadoop jar ~/workspace/MapReduceExample/itemcount.jar   /user/rohit/input/test1 /user/rohit/output/

	public static void main(String[] args) throws Exception {

	    Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "item count");
	    job.setJarByClass(ItemImpl.class);
		job.setMapperClass(ItemMapper.class);
		job.setReducerClass(ItemReducer.class);

	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
