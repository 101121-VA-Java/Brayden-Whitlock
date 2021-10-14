class numberTwo{ 
	public static void main(String[] args){
		int n = 10; 
		for(int i = 0; i < n; i++){
			if(i%3 == 0 && i != 0){ 
				System.out.println("The number is: " + i);
			}
		}
		double w = 1;
		while(w < 98){
			System.out.println("This is the next test number: " + w);		
			w = java.lang.Math.random()*100;
		}
	}
}
	 
