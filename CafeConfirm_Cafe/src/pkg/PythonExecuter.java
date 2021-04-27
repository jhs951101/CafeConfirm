package pkg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

public class PythonExecuter {
	
	public String executePython(String folder, String filename, String[] arguments) {
		String result = "Error: Python Error";
		ByteArrayOutputStream outputStream = null;
		
		try {
            String order = "python";
            String fileroot = folder + "/" + filename;
            
			CommandLine commandLine = CommandLine.parse(order);
			commandLine.addArgument(fileroot);
			
			for(int i=0; i<arguments.length; i++)
				commandLine.addArgument(arguments[i]);

	        outputStream = new ByteArrayOutputStream();
	        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
	        DefaultExecutor executor = new DefaultExecutor();
	        executor.setStreamHandler(pumpStreamHandler);
	        
	        executor.execute(commandLine);
	        
		} catch (IOException e) {
		}
		
		result = outputStream.toString();
    	//System.out.println("output: " + result);
		
		return result;
    }
}
