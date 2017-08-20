package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Driver {
	
	public static void main(String[] args) throws IOException{
		
		String roadName;
		String j1;
		String j2;
		int roadLength;
		int nLots;
		RoadEntry newRoad;
		Environment map = new Environment();
		FindPath findPath;
		
		String initialRoad ="";
		String goalRoad ="";
		int startPlot=0;
		int goalPlot=0;
		
		ArrayList<Query> queries = new ArrayList<>();
		
//	      long startTime = System.currentTimeMillis();
		



            File f = new File("src/assignment1/environmentFile.txt");

//            BufferedReader b = new BufferedReader(new FileReader(f));
//
//            String readLine = "";

            
            List<String> list = new ArrayList<String>();
            
            try {

                Scanner sc = new Scanner(f);

                while (sc.hasNextLine()) {
                    list.add(sc.nextLine());
                    
                }
                sc.close();
                for(int i=0;i<list.size();i++){
                	System.out.println(list.get(i));
                	String[] parts = list.get(i).split(" ; ");

                	roadName = parts[0];
                	j1 = parts[1];
                	j2 = parts[2];
                	roadLength = Integer.parseInt(parts[3]);
                	nLots = Integer.parseInt(parts[4]);
                	newRoad = new RoadEntry(roadName,j1,j2,roadLength,nLots);
                	map.addRoad(newRoad);

                }
            } 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }


//        		String[] parts= readLine.split(" ; " );
//        		
		
		
          try{
            File f1 = new File("src/assignment1/query-simple.txt");
            
            List<String> list1 = new ArrayList<String>();

            Scanner sc = new Scanner(f1);

            while (sc.hasNextLine()) {
                list1.add(sc.nextLine());
           
            }
            sc.close();

            for(int i=0;i<list1.size();i++){
            	String [] parts = list1.get(i).split(" ; ");
            	String initial = parts[0];
            	String goal = parts[parts.length-1];


          
            	    for(int i1=0;i1<initial.length();i1++) {
            	        if(Character.isUpperCase(initial.charAt(i1))) {
            	        
            	        	startPlot =  Character.getNumericValue(initial.charAt(i1-1));
            	        	initialRoad = initial.substring(i1);


            	        }
            	        
            	    }
            	    
            	    for(int i2=0;i2<goal.length();i2++){
            	    	if(Character.isUpperCase(goal.charAt(i2))){
            	    		goalPlot = Character.getNumericValue(goal.charAt(i2-1));
            	    		goalRoad = goal.substring(i2);
        
            	    	}
            	    }
            	    
            	    Query newQuery = new Query(initialRoad,goalRoad,startPlot,goalPlot);
            	    queries.add(newQuery);
            	    System.out.println(newQuery.sRoad);
            	
            }


            	
            	    
           }catch (FileNotFoundException e) {
               e.printStackTrace();
           }

            


//		
//
//		
//		findPath = new FindPath(map);
//		map.generateNodes();
//		
//		
//		for(int i=0;i<queries.size();i++){
//			
//			
//			Query newQuery = queries.get(i);
//			
//			map.addGoals(newQuery.sRoad,newQuery.gRoad,newQuery.sPlot,newQuery.gPlot);
//			
//			ArrayList<Node> result = findPath.compute();
//			
//			String pathCost = Double.toString(result.get(0).costFromStart);
//			String answer ="";
//			for(int i1=0;i1<result.size();i1++){
//				
//
//
//				String street = result.get(i1).getParent().findEdge(result.get(i1)).streetName;
//				if(i1!=0){
//					answer = "-"+result.get(i1).junction+"-"+answer;
//				}
//				else{
//					
//				}
//			
//				answer = street+answer;
//				
//				
//			}
//			answer = pathCost+";"+answer;
//			System.out.println(answer);
//			
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


