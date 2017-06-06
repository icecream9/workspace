package mahout.fansy.utils.transform;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.common.AbstractJob;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

/**
 * --* transform text data to vectorWritable data --* @author fansy --* --
 */
public class Text2VectorWritable extends AbstractJob {
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new Text2VectorWritable(), args);
	}

	@Override
	public int run(String[] arg0) throws Exception {
		addInputOption();
		addOutputOption();
		if (parseArguments(arg0) == null) {
			return -1;
		}
		Path input = getInputPath();
		Path output = getOutputPath();
		Configuration conf = getConf();
		// set job information
		Job job = new Job(conf, "text2vectorWritableCopy with input:" + input.getName());
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		job.setMapperClass(Text2VectorWritableMapper.class);
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(VectorWritable.class);
		job.setReducerClass(Text2VectorWritableReducer.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(VectorWritable.class);
		job.setJarByClass(Text2VectorWritable.class);
		FileInputFormat.addInputPath(job, input);
		SequenceFileOutputFormat.setOutputPath(job, output);
		if (!job.waitForCompletion(true)) { // wait for the job is done
			throw new InterruptedException("Canopy Job failed processing " + input);
		}
		return 0;
	}

	/**
	 * --* Mapper ：main procedure --* @author fansy --* --
	 */
	public static class Text2VectorWritableMapper extends Mapper<LongWritable, Text, LongWritable, VectorWritable> {
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] str = value.toString().split("\\s{1,}");
			// split data use one or more blanker
			Vector vector = new RandomAccessSparseVector(str.length);
			for (int i = 0; i < str.length; i++) {
				vector.set(i, Double.parseDouble(str[i]));
			}
			VectorWritable va = new VectorWritable(vector);
			context.write(key, va);
		}
	}

	/**
	 * --* Reducer: do nothing but output --* @author fansy --* --
	 */
	public static class Text2VectorWritableReducer
			extends Reducer<LongWritable, VectorWritable, LongWritable, VectorWritable> {
		public void reduce(LongWritable key,Iterable<VectorWritable> values,Context context)throws IOException,InterruptedException{
         for(VectorWritable v:values){
            context.write(key, v);
        }
      }
 }
}