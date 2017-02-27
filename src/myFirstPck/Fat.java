package myFirstPck;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Fat {
	int z=0;
	int year;
   private RandomAccessFile fd;
   private static  int  sectorsize = 512;
   
   public Fat(String fileName) throws FileNotFoundException {
      fd = new RandomAccessFile(fileName,"r");
   }
   
   public void readSec(int sector, byte [] buffer) throws IOException{
      fd.seek(sectorsize * sector);
      fd.read(buffer);
   }
   public void writeSec(String sectorNO, byte [] buffer) throws IOException{
  	
  	 int foo = Integer.parseInt(sectorNO);
  	 fd.seek(sectorsize * foo);
     fd.write(buffer);
  
   }   
  
//  Time Check
   public void checkTime(byte buff[], int start)
   {
  	 int i=buff[start+1]<<8 | buff[start];
  	 String bin=Integer.toBinaryString(i);
  	 int strLeng= bin.length();
  	 int [] myArray= new int[16];
  	 int p=0;
  	 int q=0;
  	 while(p<16)
  	 {
  		 
  		 if(strLeng<16)
  		 {
  			 int k=16-strLeng;
  			 for(int s=0; s<k; s++)
  			 {
  				 myArray[s]=0;
  				 p=p+1;
  				 
  			 }
  			 strLeng=17;
  			 
  		 }
  		 else
  		 {
  			 if (!Character.isDigit(bin.charAt(q))) {
  	        System.out.println("Contains an invalid digit");
  	        break;
  	      }
  	      myArray[p] = Integer.parseInt(String.valueOf(bin.charAt(q)));
  	      q++;
  	      p++;
  	    }
  		 }
	 int[] hour= new int[5];
  	
  	 for(int k=0; k<5; k++)
  	 {
  	 	hour[k]=myArray[k];
  	
  	 }
  	 int [] min= new int[6];
  	 int kkk=0;
  	 while(kkk<6){
  	 for(int k=5; k<11; k++)
  	 {
  	 	min[kkk]=myArray[k];
  	 	kkk++;
  	 }
  	 }
  	 
  	 
  	 int finalhour= convert2int(hour);
  	 int finalMin= convert2int(min);
  	 if(finalhour==0)
  	 {
  		 
  	 }
  	 else
  	 System.out.print("\t"+finalhour+":"+finalMin+"\t\n");
  	 }

   
   public void checkDate(byte buff[], int start)
   {
  	 int i=buff[start+1]<<8 | buff[start];
  	 String bin=Integer.toBinaryString(i);
  	 int strLeng= bin.length();
  	 int [] myArray= new int[16];
  	 int p=0;
  	 int q=0;
  	 while(p<16)
  	 {
  		 
  		 if(strLeng<16)
  		 {
  			 int k=16-strLeng;
  			 for(int s=0; s<k; s++)
  			 {
  				 myArray[s]=0;
  				 p=p+1;
  				 
  			 }
  			 strLeng=17;
  			 
  		 }
  		 else
  		 {
  			 if (!Character.isDigit(bin.charAt(q))) {
  	        System.out.println("Contains an invalid digit");
  	        break;
  	      }
  	      myArray[p] = Integer.parseInt(String.valueOf(bin.charAt(q)));
  	      q++;
  	      p++;
  	    }
  		 }
  	
  	 int[] year= new int[7];
  	
  	 for(int k=0; k<7; k++)
  	 {
  	 	year[k]=myArray[k];
  	
  	 }
  	 int [] month= new int[4];
  	 int kkk=0;
  	 while(kkk<4){
  	 for(int k=7; k<11; k++)
  	 {
  	 	month[kkk]=myArray[k];
  	 	kkk++;
  	 }
  	 }
  	 int [] date= new int[5];
  			 int kkkk=0;
  	 while(kkkk<5){
  	 for(int k=11; k<16; k++)
  	 {
  	 	date[kkkk]=myArray[k];
  	 	kkkk++;
  	 }
  	 }
  	 
  	 int finalIntyear= convert2int(year);
  	 finalIntyear=finalIntyear+1980;
  	 int finalMonth= convert2int(month);
  	 int finalDate=convert2int(date);
  	 if(finalIntyear==1980)
  	 {
  		
  	 }
  	 else
  	 System.out.print("\t"+finalIntyear+"-"+finalMonth+"-"+finalDate+"\t");

  	 }
   
   public int binaryToInteger(String binary) {
     char[] numbers = binary.toCharArray();
     int result = 0;
     for(int i=numbers.length - 1; i>=0; i--)
         if(numbers[i]=='1')
             result += Math.pow(2, (numbers.length-i - 1));
     return result;
 }
   public int convert2int(int [] array)
   {
  	 StringBuilder strNum = new StringBuilder();

   	for (int num : array) 
   	{
   	     strNum.append(num);
   	}
   	 int finalInt = Integer.parseInt(strNum.toString());
   	String str=Integer.toString(finalInt);
 	 int foo = binaryToInteger(str);
   	 return foo;
   }
   public void byte2bit(byte buff[], int start)
   {
  	 int i = buff[start+1]<<8 | buff[start];
  	 if(start==11)
     System.out.print("byte per sector: " + i +"\n");
  	 else if(start==14)
  		 System.out.print("reserved sector for the boot record: " + i +"\n");
  	 else if(start==17)
  		 System.out.print("maximum number of root directory: " + i +"\n");
  	 else if (start==19)
  		 System.out.print("number of logical sector: " + i +"\n");
  	 else if(start==22)
  	 System.out.print("sector per FAT: " + i +"\n");
  	 else if(start==24)
  		 System.out.print("sector per track: " + i +"\n");
  	 else if(start==26)
  		 System.out.print("number of surface: " + i +"\n\n");
   }
   public void byteInbit(byte buff[], int start)
   {
  	 int i = buff[start];
  	if(start==13)
  		 System.out.print("sector per cluster: " + i +"\n");
 	 else if(start==16)
		 System.out.print("number of FAT: " + i +"\n");
 	 else if(start==21)
		 System.out.print("medium descriptor byte: " + i +"\n");
  	
     //System.out.print(" " + i +"\n");
   }
   
   
}
