package edu.washington.escience.warc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Walk the directory tree under containing the clueweb data.
 */
public class WalkPaths extends Configured implements Tool {

	
	@Override
	public int run(String[] args) throws Exception {
	    if (args.length != 2) {
	        System.err.printf("Usage: %s [generic options] <input> <output>\n",
	            getClass().getSimpleName());
	        ToolRunner.printGenericCommandUsage(System.err);
	        return -1;
	      }
	    
	    Configuration conf = this.getConf();
	    FileSystem fs = FileSystem.get(conf);
	    FileStatus[] status = fs.listStatus(new Path("/datasets/clue/Disk[1234]/*/*/*.warc.gz"));
	    Path[] paths = FileUtil.stat2Paths(status);
	    
	    for (Path path : paths) {
	    	System.out.println(path);	    	
	    }
	    return 0;
	}
	    	
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new WalkPaths(), args);
		System.exit(exitCode);
	}
}
