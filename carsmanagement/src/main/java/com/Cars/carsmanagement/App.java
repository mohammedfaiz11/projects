package com.Cars.carsmanagement;

import java.sql.*;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import carsdetails.carsdetail;
public class App 
{
	public int[] store_carid() throws SQLException
	{
		String url="jdbc:mysql://localhost:3306/faizdb? autoReconnect=true & useSSL=false", un="root", pass="fa123";
		//Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,un,pass);
		//Creating connection
		Statement st = con.createStatement();
		con.setAutoCommit(false);
		String sqll = "select * from carsdetail";
		//Storing the command as String for retrieving details of table.
		ResultSet rs1 = st.executeQuery(sqll);
		// Resultset is used to store the table data.
		int a[] =new int[100],carid[],n=0;
		while(rs1.next())
		{
			a[n]=rs1.getInt(1);
			n++;
		}
		carid=new int[n];
		for(int i=0;i<n;i++)
		{
			carid[i]=a[i];
		}
		return carid;
	}
	
    public static void main( String[] args ) throws SQLException
    {
    	Configuration cfg = new Configuration();
    	cfg = cfg.configure();
    	Scanner sc = new Scanner(System.in);
    	System.out.println("CFG is ready.");
    	SessionFactory sf = cfg.buildSessionFactory();
    	System.out.println("Session factory is ready.");
    	Session s = sf.openSession();
    	Transaction tr = s.beginTransaction();
    	App ap = new App();
    	boolean run=true;
    	carsdetail c;
    	int carid[];
    	while(run)
    	{
    		System.out.println();
    		System.out.println("Cars management.");
    		System.out.println("CRUD OPERATION");
    		System.out.println();
    		System.out.println("1. Insert"
    				+ "\n2. Update"
    				+ "\n3. Delete"
    				+ "\n4. Show Data"
    				+ "\n5. Exit");
    		int ch=sc.nextInt();
    		switch(ch)
    		{
    		case 1: 
    			{
						try
						{
					System.out.println("Insert the following details.");
					System.out.println("Car Name : ");
					String cn = sc.next();
					System.out.println("Car Price : ");
					int price = sc.nextInt();
					System.out.println("Car Model Year");
					String yr =  sc.next();
					c= new carsdetail(cn, yr, price);
					s.save(c);
					tr.commit();
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
						}
						break;
					}
    			
    		case 2 :
    			try
    				{
    					System.out.println("Update car details.");
    					carid=ap.store_carid();
    		   			System.out.println("Enter the car ID  : ");
    		   			int bid=sc.nextInt();
    		   			boolean r=true;
    		   			mainfor:
							while(r)
							{
								for(int i=0;i<carid.length;i++)
								{
									if(bid==carid[i])
										break mainfor;
								}
									System.out.println(+bid+" ID Does not Exist.\n\t\tEnter the proper ID : ");
									bid=sc.nextInt();
							}
    		   			carsdetail b1 = s.get(carsdetail.class, bid);
    		   			System.out.println("Enter the following to update. : ");
    		   			System.out.println("Car Name : ");
    					String cn = sc.next();
    					System.out.println("Car Price : ");
    					int pr = sc.nextInt();
    					System.out.println("Car model year");
    					String yr =  sc.next();
    		   			b1.setCarname(cn);
    		   			b1.setCarprice(pr);
    		   			b1.setModelyear(yr);
    		   			s.save(b1);
    		   			
    		   		}
    			catch(Exception e)
    			{
    				System.out.println(e.getMessage());
    			}
    			break;
    		case 3 :
    				{
    					System.out.println("Delete Car Details.");
    					carid=ap.store_carid();
 		   			   System.out.println("Enter the Car ID  : ");
 		   			   int cid = sc.nextInt();
 		   			   boolean r=true;
 		   			   mainfor:
							while(r)
							{
								for(int i=0;i<carid.length;i++)
								{
									if(cid==carid[i])
										break mainfor;
								}
									System.out.println(cid+" ID Does not Exist.\nEnter the proper ID : ");
									cid=sc.nextInt();
							}
 		   			   carsdetail b1 = s.get(carsdetail.class, cid);
 		   			   s.delete(b1);
 		   			   break;
    					
    				}
    				
    		case 4 :
    		 		{
    		 			System.out.println("Display Car details.");
    		 			carid=ap.store_carid();
    		   			System.out.println("Enter the ID for fetching the details.");
    		   			int bid=sc.nextInt();
    		   			boolean r=true;
    		   			mainfor:
							while(r)
							{
								for(int i=0;i<carid.length;i++)
								{
									if(bid==carid[i])
										break mainfor;
								}
									System.out.println(bid+" ID Does not Exist.\n\t\tEnter the proper ID : ");
									bid=sc.nextInt();
							}
    		   			carsdetail btt=s.load(carsdetail.class, bid);
    		   			System.out.println(btt.toString());
    		   			break;
    		 		}
    		case 5 :
    				{
    					run=false;
    					break;
    				}
    		}
    	}
    	System.out.println("Thank You");
    	tr.commit();
    	s.close();
    	
    }
}
