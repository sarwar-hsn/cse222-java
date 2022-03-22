
import infrastructure.Office;
import infrastructure.Side;
import infrastructure.Street;

public class TimeComplexity {
	public static void main(String[] args) {
		
		
		/**
		 * time analysis for version one using ArrayList
		 */
		{
			
			{
				Street street = new Street(55);
				int bulkSize = 1000;
				long start,end;
				//adding 100 building
			
				start = System.nanoTime();
				for(int i = 0; i < bulkSize; i++) {
					int randomLength =(int) Math.round(Math.random()*10); 
					int randomHeight = (int) Math.round(Math.random()*10); 
					int randomPosition = (int) Math.round(Math.random()*50); 
					street.add(new Office("owner", randomLength,randomHeight,Side.left, randomPosition, "jobtype"));					
				}
				end = System.nanoTime();
				System.out.println(street.left().size() + " building added in : "+(end - start)*Math.pow(10,-6)+" millisecond");
				
				start = System.nanoTime();
				street.silhouetteLeft();
				end = System.nanoTime();
				System.out.println(street.left().size()  +  " building skyline printed in : "+(end - start)*Math.pow(10,-6)+" millisecond");
				
				start = System.nanoTime();
				int size = street.left().size();
				street.left().clear();
				end = System.nanoTime();		
				System.out.println(size  +" building removed in : "+(end - start)*Math.pow(10,-6)+" millisecond ");
			}
			
			{
				Street street = new Street(55);
				int bulkSize = 100;
				long start,end;
				//adding 100 building
			
				start = System.nanoTime();
				for(int i = 0; i < bulkSize; i++) {
					int randomLength =(int) Math.round(Math.random()*10); 
					int randomHeight = (int) Math.round(Math.random()*10); 
					int randomPosition = (int) Math.round(Math.random()*50); 
					street.add(new Office("owner", randomLength,randomHeight,Side.left, randomPosition, "jobtype"));					
				}
				end = System.nanoTime();
				System.out.println(street.left().size() + " building added in : "+(end - start)*Math.pow(10,-6)+" millisecond");
				
				start = System.nanoTime();
				street.silhouetteLeft();
				end = System.nanoTime();
				System.out.println(street.left().size()  +  " building skyline printed in : "+(end - start)*Math.pow(10,-6)+" millisecond");
				
				start = System.nanoTime();
				int size = street.left().size();
				street.left().clear();
				end = System.nanoTime();		
				System.out.println(size  +" building removed in : "+(end - start)*Math.pow(10,-6)+" millisecond ");
			}
			{
				Street street = new Street(55);
				int bulkSize = 10;
				long start,end;
				//adding 100 building
			
				start = System.nanoTime();
				for(int i = 0; i < bulkSize; i++) {
					int randomLength =(int) Math.round(Math.random()*10); 
					int randomHeight = (int) Math.round(Math.random()*10); 
					int randomPosition = (int) Math.round(Math.random()*50); 
					street.add(new Office("owner", randomLength,randomHeight,Side.left, randomPosition, "jobtype"));					
				}
				end = System.nanoTime();
				System.out.println(street.left().size() + " building added in : "+(end - start)*Math.pow(10,-6)+" millisecond");
				
				start = System.nanoTime();
				street.silhouetteLeft();
				end = System.nanoTime();
				System.out.println(street.left().size()  +  " building skyline printed in : "+(end - start)*Math.pow(10,-6)+" millisecond");
				
				start = System.nanoTime();
				int size = street.left().size();
				street.left().clear();
				end = System.nanoTime();		
				System.out.println(size  +" building removed in : "+(end - start)*Math.pow(10,-6)+" millisecond ");
			}
			
		}
		
	}

}
