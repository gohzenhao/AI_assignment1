package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Driver {
	
	public static void main(String[] args) throws IOException{
		
		String roadName;
		String j1;
		String j2;
		int roadLength;
		int nLots;
		String[] parts;
		RoadEntry newRoad;
		Environment map = new Environment();
		FindPath findPath;
		
		
		
		try {

            File f = new File("src/assignment1/environmentFile.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            System.out.println("Reading file using Buffered Reader");

            while ((readLine = b.readLine()) != null) {
            	parts = readLine.split(" ; " );
            	roadName = parts[0];
            	j1 = parts[1];
            	j2 = parts[2];
            	roadLength = Integer.parseInt(parts[3]);
            	nLots = Integer.parseInt(parts[4]);
            	newRoad = new RoadEntry(roadName,j1,j2,roadLength,nLots);
            	map.addRoad(newRoad);


            }
        

        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		String initialNode = "2Warren";
		String goalNode = "3Durham";
		findPath = new FindPath(map,initialNode,goalNode);
		map.generateNodes();
		map.addGoals("Warren","Durham");
		findPath.compute();
//		for(int i=0;i<map.getNodes().size();i++){
//			System.out.println("Main node : "+map.getNodes().get(i).junction);
//			System.out.println("Children:");
//			for(int u=0;u<map.getNodes().get(i).getChildren().size();u++){
//				System.out.println(map.getNodes().get(i).getChildren().get(u).junction);
//				
//
//			}
//		}
		

    }

		
		
		
	}


