package org.usfirst.frc.team2186.robot.autonomous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.usfirst.frc.team2186.robot.*;

import java.util.Scanner;
import java.util.stream.*;
public class AutoScript {
	String code;
	InputStream source;
	DriveManager drive;
	public AutoScript(String file){
		source = this.getClass().getClassLoader().getResourceAsStream(file);
		drive = DriveManager.getInstance();
	}
	
	String read = "";
	
	public void interpret(){
		BufferedReader br = new BufferedReader(new InputStreamReader(source));
		StringBuilder sb = new StringBuilder();
		
		try {
			read = br.readLine();
			while(read != null){
				sb.append(read);
				read = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	Scanner scan = new Scanner(read);
	public void execute(){
		if(scan.hasNextLine()){
			String next = scan.nextLine();
			if(next == "f"){
				drive.driveForward(Double.parseDouble(scan.nextLine()), true);
			}
			if(next == "s"){
				drive.update(0, 0, 0);
			}
		}
	}
}
