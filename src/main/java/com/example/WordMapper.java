package com.example;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        
        String line = value.toString();
        String[] words = line.split("\\s+");  

       
        for (String w : words) {
            if (!w.isEmpty()) {  
                word.set(w.replaceAll("[^a-zA-Z0-9]", "").toLowerCase()); 
                context.write(word, one);
            }
        }
    }
}