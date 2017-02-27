package myFirstPck;
//import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
public class Floppy {
   public static void main(String [] args)throws FileNotFoundException, IOException {
      Fat floppyOBJ = new Fat("//Volumes//2707790859//workspace//Assigm1//src//myFirstPck//floppy2");
      byte [] buff = new byte[512];
      
///////////////////////////////////////////////////////////////////////////////////////
      System.out.print("\n\t***Question Number One****\n\n");
      floppyOBJ.readSec(0,buff);
      floppyOBJ.byte2bit(buff, 11);
      floppyOBJ.byteInbit(buff, 13);
      floppyOBJ.byte2bit(buff, 14);
      floppyOBJ.byteInbit(buff, 16);
      floppyOBJ.byteInbit(buff, 21);
      floppyOBJ.byte2bit(buff, 17);
      floppyOBJ.byte2bit(buff, 19);
      floppyOBJ.byte2bit(buff, 22);
      floppyOBJ.byte2bit(buff, 24);
      floppyOBJ.byte2bit(buff, 26);
      
///////////////////Name of File Directery
       System.out.print("\n\t***Question Number Two && Four****\n\n");
       floppyOBJ.readSec(19,buff);
if(buff[11]>31)
{  
	////////////////////////////////////////////////////
	
	for(int j=0;j<16;j++)
	{
		int jk=j*32;

		
		///name
	   if(buff[jk+11]==15 ){
	  	 continue;
		   }
	   
		   for(int jj=jk; jj<jk+8; jj++){
    
			char a=(char) buff[jj];
      	 System.out.print( a);
      }
		 //extension
		   for(int jj=jk+8; jj<jk+11; jj++){
		     
					char a=(char) buff[jj];
		      	 System.out.print( a);
		      } 
		   
		//attribute
		   if(buff[jk+11]==16){
		    System.out.print("\t"+ "<DIR>"+"\t");
		   }
		   else
		  	 	System.out.print("\t \t");
		   System.out.print("\t ");
    int b=0 ;
    //size of file
  for(int jj=jk+28; jj<jk+32; jj++){
 	 int a=buff[jj];
  	  b= a+b;
    }
    System.out.print("" + b+" \t");
//Time and Date      
    b=0;
    floppyOBJ.checkDate(buff, jk+24);
    floppyOBJ.checkTime(buff, jk+22);

	
	}
	//////////////////////////////////////////////////
	
}
if(buff[11]==16)
{ 

	floppyOBJ.readSec(33,buff);
	
	
	for(int j=0;j<16;j++)
	{
		int jk=j*32;

		
		///name
	   if(buff[jk+11]==15 ){
	  	 continue;
		   }
	   
		   for(int jj=jk; jj<jk+8; jj++){
    
			char a=(char) buff[jj];
      	 System.out.print("" + a+"");
      } 
		//extension
		   for(int jj=jk+8; jj<jk+11; jj++){
		     
					char a=(char) buff[jj];
		      	 System.out.print( a);
		      } 
		   ///attribute
		   if(buff[jk+11]==16){
		    System.out.print("\t"+ "<DIR>"+"\t");
		   }
		   else
		  	 	System.out.print("\t \t");
		   System.out.print("\t ");
    int b=0 ;
    //size of file
  for(int jj=jk+28; jj<jk+32; jj++){
 	 int a=buff[jj];
  	  b= a+b;
    }
    System.out.print("" + b+" \t");
//Time and Date      
    b=0;
    floppyOBJ.checkDate(buff, jk+24);
    floppyOBJ.checkTime(buff, jk+22);

	
	}
  	}
   


////////////////////////////////////////////////////////////////////////////////////       
       System.out.print("\n\t***Question Number Three****\n\n");
floppyOBJ.readSec(19, buff);

	char[] myString=new char[8];
	for(int ii=0; ii<8; ii++)
	{
		char a=(char) buff[ii];
 	  myString[ii]=a;
 	}
	String killer= String.valueOf(myString);
	LinkedList<String> list = new LinkedList<String>();
	list.add(killer);
	char[] myString1=new char[4];
	for(int jj=8; jj<11; jj++){
		char a=(char) buff[jj];
		myString1[jj]=a;
    }
	String killer1= String.valueOf(myString1);
	list.add(killer1);
	int b=0;
  for(int jj=28; jj<32; jj++){
  	 int a=buff[jj];
   	  b= a+b;
     }
	String killer2= String.valueOf(b);
	list.add(killer2);
	////////////////////////Find**FREE**SECTOR**
	for(int oi=33; oi<512; oi++)
	{
		floppyOBJ.readSec(oi, buff);
		for(int ki=0; ki<16; ki=ki+32)
		{
			//int kl=ki*32;
			for(int i=0; i<8; i++)
			{
////////////////////////////////////////////WRITE TO SECTOR 
				if(buff[i]==32){
					floppyOBJ.writeSec(list.get(i), buff);
				}
				for(int il=8; il<11; il++)
				{
					if(buff[il]==32){
						floppyOBJ.writeSec(list.get(il), buff);
					}
					for(int ill=28; ill<32; ill++)
					{
						if(buff[ill]==32){
							floppyOBJ.writeSec(list.get(ill), buff);
						}
						
			}
		}
	}
   }
  }
 }
}

   
