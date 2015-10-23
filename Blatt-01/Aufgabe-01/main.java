
public class main {

	static float tMin,tMax,tCurrent;

	public static void main(String[] args) {
		
		//args[0] = tMin, args[1] = tMax, args[2] = tCurrent, args[3...i] = tOutside
		
		try{
			tMin = Float.parseFloat(args[0]);
			tMax = Float.parseFloat(args[1]);
			tCurrent = Float.parseFloat(args[2]);
		
		

			for(int i=3; i<args.length-1; i++){
				
				float messung = Float.parseFloat(args[i]);
				System.out.println("Messung Draussen: "+ messung +". Messung Drinnen: "+ tCurrent);
				
				float diff = tCurrent - messung;
				
				//Drinnen ists kälter
				if(diff<0){
					heizen();
				}
				//Drinnen ists wärmer
				else if(diff>0){
					kuehlen();
				}
				//Gleich warm
				else{
					passiv();
				}
				
				System.out.println("Nach Anpassung: Messung Drinnen: "+tCurrent);

			}
			
			System.out.println("Done");
			
			
		}catch(Exception e){
			System.out.println("Falsche Eingabe\nDone");
		}		

	}
	
	private static void heizen(){
		if(Math.abs(tMax-tCurrent)<5){
			//Heizen überschreitet Korridor
			//tue nichts
		}
		else{
			tCurrent += 5.0f;
		}
	}

	private static void kuehlen(){
		if(Math.abs(tMin-tCurrent)<5){
			//Kuehlen unterschreitet Korridor
			//tue nichts
		}
		else{
			tCurrent -= 5.0f;
		}
	}
	
	private static void passiv(){
		//tue nichts
	}
}
