package com.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class ItemReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

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
