public class RRPSSapp {
	public static void main(String[] args) 
	{
		Restaurant r = new Restaurant("/staffData.txt", "/menuData.txt", "/tableData.txt");
		r.runRRPSS();
	}
}