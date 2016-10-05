package com.tmikic.core.files;

import java.io.*;


public class datastoreage {
	static File data=new File( "c:\\drawline");
	public void datastoreage(){
		if(!data.exists()){
			data.mkdirs();
		}
	}
	public static void writefile(String count){
		File ds = new File(data+"\\data");
		
        if(!ds.exists()){
        	ds.mkdirs();
        }
        try ( Writer   wrtier= new BufferedWriter(new FileWriter(ds+"\\data.txt",false))) {
        	wrtier.write(count);
        	
        } catch (IOException e) {
            e.printStackTrace();
        } 
		}
	
   public static String s;
    public  String read(){
    	
    	File ds = new File(data+"\\data");
    	try (BufferedReader br = new BufferedReader(new FileReader(ds+"\\data.txt")))
		{

			System.out.println(ds+"\\data.txt");
    		 String line = null;
    		    while ((line = br.readLine()) != null) {
    		        s = line;
    		    }
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
    }
	
     
}
