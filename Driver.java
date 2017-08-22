package assignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Driver {
	
	public static void main(String[] args) throws IOException{
		
//		File environmentFile = new File(args[0]+".txt");
//		File queryFile = new File(args[1]+".txt");
//		File outputFile = new File(args[2]+".txt");
		
		File environmentFile = new File("src/assignment1/test2.txt");
		File queryFile = new File("src/assignment1/query2.txt");
		File outputFile = new File("src/assignment1/answer.txt");
		
//	      long startTime = System.currentTimeMillis();
		FileReader fr = null;
		BufferedReader br = null;
		
		String roadName,j1,j2;
		int roadLength,nLots;
		RoadEntry newRoad;
		Environment map = new Environment();
		FindPath findPath;
		
		String initialRoad ="";
		String goalRoad ="";
		int startPlot=0;
		int goalPlot=0;
		
		ArrayList<Query> queries = new ArrayList<>();
		

		try {
			//read environment file
			if(environmentFile.exists())
			{

				fr = new FileReader(environmentFile);
				br = new BufferedReader(fr);
				String line = br.readLine();
				try
				{
					while(line!=null)
					{
						StringTokenizer st = new StringTokenizer(line," ; ");
						roadName = st.nextToken();
						j1 = st.nextToken();
						j2 = st.nextToken();
						roadLength = Integer.parseInt(st.nextToken());
						nLots = Integer.parseInt(st.nextToken());
						newRoad = new RoadEntry(roadName,j1,j2,roadLength,nLots);
						map.addRoad(newRoad);
						line = br.readLine();
						
					}

				}
				catch (IOException e)
				{
					System.out.println(e.getMessage());
				}
				finally
				{
					try// if the file was opened, close it
					{
					if (fr != null)
					fr.close();
					}
					catch (IOException ioe)
					{
						System.out.println(ioe.getMessage());
					}

				}

			}
			//read query file
			if(queryFile.exists())
			{
				fr = new FileReader(queryFile);
				br = new BufferedReader(fr);
				String line = br.readLine();
				try
				{
					while(line!=null)
					{
						StringTokenizer st = new StringTokenizer(line," ; ");
						String initial = st.nextToken();
						String goal = st.nextToken();
						//get initial node
						for(int i=0;i<initial.length();i++) 
						{
							if(Character.isUpperCase(initial.charAt(i))) {
								
								
								startPlot =  Integer.parseInt(initial.substring(0,i));
//								System.out.println(startPlot);
								initialRoad = initial.substring(i);
							}
						}
						//get goal node
						for(int i=0;i<goal.length();i++)
						{
							if(Character.isUpperCase(goal.charAt(i))){
								goalPlot = Integer.parseInt(goal.substring(0, i));
//								System.out.println(goalPlot);
								goalRoad = goal.substring(i);
							}
						}
						Query newQuery = new Query(initialRoad,goalRoad,startPlot,goalPlot);
						queries.add(newQuery);
						line = br.readLine();
					}
				}
				catch (IOException e)
				{
					System.out.println(e.getMessage());
				}
				finally
				{
					try// if the file was opened, close it
					{
					if (fr != null)
					fr.close();
					}
					catch (IOException ioe)
					{
						System.out.println(ioe.getMessage());
					}

				}

			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try// if the file was opened, close it
			{
				if (fr != null)
				fr.close();
			}
			catch (IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}

		}
		

		
		findPath = new FindPath(map);
		map.generateNodes();
		
//		map.addGoals("Road-28","Road-6",1,20);
//		ArrayList<Node> result = findPath.compute();
//		
//		String answer ="";
//		String pathCost = Double.toString(result.get(0).costFromStart);
//		for(int i1=0;i1<result.size();i1++){
//
////			System.out.println(result.get(i1).costFromStart);
//
//			String street = result.get(i1).getParent().findEdge(result.get(i1)).streetName;
//			if(i1!=0){
//				answer = "-"+result.get(i1).junction+"-"+answer;
//			}
//			else{
//
//			}
//
//			answer = street+answer;
//
//		}
//		answer = pathCost+";"+answer;
//		System.out.println(answer);
		
		

		try
		{
			FileWriter fw=new FileWriter(outputFile);
			BufferedWriter bw=new BufferedWriter(fw);
			
			for(int i=0;i<queries.size();i++){
				
				System.out.println(map.nodes.size());
				String answer ="";
				
				Query newQuery = queries.get(i);
				
				if(map.addGoals(newQuery.sRoad,newQuery.gRoad,newQuery.sPlot,newQuery.gPlot)){

					

				ArrayList<Node> result = findPath.compute();

				String pathCost = Double.toString(result.get(0).costFromStart);
				
				for(int i1=0;i1<result.size();i1++){


					String street = result.get(i1).getParent().findEdge(result.get(i1)).streetName;
					if(i1!=0){
						answer = "-"+result.get(i1).junction+"-"+answer;
					}
					else{

					}

					answer = street+answer;


				}
				answer = pathCost+";"+answer;
				System.out.println(answer);
				}
				else{
					answer = "no-path";
					System.out.println(answer);
				}
				bw.write(answer);
				bw.newLine();
			}
			bw.flush();
			bw.close();
			
			
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		

		
//			
////		finally
////		{
////			try// if the file was opened, close it
////			{
////				if (fw != null)
////					fw.close();
////			}
////			catch (IOException ioe)
////			{
////				System.out.println(ioe.getMessage());
////			}
//		}

//	      long end = System.currentTimeMillis();
//	      System.out.println("Total Time: "+(end-startTime));
	     
		
		
		


//		for(int i=0;i<map.getNodes().size();i++){
//			System.out.println("Main node : "+map.getNodes().get(i).junction);
//			System.out.println("Children:"); 
//			for(int u=0;u<map.getNodes().get(i).getAdjencies().size();u++){
//				System.out.println(map.getNodes().get(i).getAdjencies().get(u).target.junction);
//				System.out.println("Cost to get there : "+map.getNodes().get(i).getAdjencies().get(u).getCost());
//				
//
//			}
//		}
		

    }

		
		
		
	}


