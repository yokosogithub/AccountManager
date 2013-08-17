import java.math.*;
public class Compile {
	public Compile(){
		
	}
	public BigInteger transport(String str){
		int[] arr = new int[str.length()];
		String newstr="";
		for (int i = 0 ; i < str.length() ; i ++ ){                    
			arr[i] = str.charAt(i);
			newstr=newstr+arr[i];
		}
		BigInteger code = new BigInteger(newstr);
		return code;
	}
	public BigInteger add(BigInteger code,BigInteger secCode){
		BigInteger newcode=new BigInteger("0");
		newcode=newcode.add(code);
		newcode=newcode.add(secCode);
		return newcode;
	}
	public String secCompile(BigInteger code){
		String newcode = code.toString(2);
		return newcode;
	}
	
	public String thiCompile(String code){
		String newcode="";
		int length = code.length();
		int i;
		for(i=0;i<48;i=i+6){
			if(length>50){
				if(code.substring(i+0, i+6).equals("000000")){
					newcode=newcode+"a";
				}
				else if(code.substring(i+0,i+6).equals("000001")){
					newcode=newcode+"b";
				}
				else if(code.substring(i+0, i+6).equals("000010")){
					newcode=newcode+"c";
				}
				else if(code.substring(i+0,i+6).equals("000011")){
					newcode=newcode+"d";
				}
				else if(code.substring(i+0, i+6).equals("000100")){
					newcode=newcode+"e";
				}
				else if(code.substring(i+0,i+6).equals("000101")){
					newcode=newcode+"f";
				}
				else if(code.substring(i+0, i+6).equals("000110")){
					newcode=newcode+"g";
				}
				else if(code.substring(i+0,i+6).equals("000111")){
					newcode=newcode+"h";
				}
				else if(code.substring(i+0, i+6).equals("001000")){
					newcode=newcode+"i";
				}
				else if(code.substring(i+0,i+6).equals("001001")){
					newcode=newcode+"j";
				}
				else if(code.substring(i+0, i+6).equals("001010")){
					newcode=newcode+"k";
				}
				else if(code.substring(i+0,i+6).equals("001011")){
					newcode=newcode+"l";
				}
				else if(code.substring(i+0, i+6).equals("001100")){
					newcode=newcode+"m";
				}
				else if(code.substring(i+0,i+6).equals("001101")){
					newcode=newcode+"n";
				}
				else if(code.substring(i+0, i+6).equals ("001110")){
					newcode=newcode+"o";
				}
				else if(code.substring(i+0,i+6).equals("001111")){
					newcode=newcode+"p";
				}
				else if(code.substring(i+0, i+6).equals("010000")){
					newcode=newcode+"q";
				}
				else if(code.substring(i+0,i+6).equals("010001")){
					newcode=newcode+"r";
				}
				else if(code.substring(i+0, i+6).equals("010010")){
					newcode=newcode+"s";
				}
				else if(code.substring(i+0,i+6).equals("010011")){
					newcode=newcode+"t";
				}
				else if(code.substring(i+0, i+6).equals("010100")){
					newcode=newcode+"u";
				}
				else if(code.substring(i+0,i+6).equals("010101")){
					newcode=newcode+"v";
				}
				else if(code.substring(i+0, i+6).equals ("010110")){
					newcode=newcode+"w";
				}
				else if(code.substring(i+0,i+6).equals("010111")){
					newcode=newcode+"x";
				}
				else if(code.substring(i+0, i+6).equals("011000")){
					newcode=newcode+"y";
				}
				else if(code.substring(i+0,i+6).equals("011001")){
					newcode=newcode+"z";
				}
				else if(code.substring(i+0, i+6).equals("011010")){
					newcode=newcode+"0";
				}
				else if(code.substring(i+0,i+6).equals("011100")){
					newcode=newcode+"1";
				}
				else if(code.substring(i+0, i+6).equals("011101")){
					newcode=newcode+"2";
				}
				else if(code.substring(i+0,i+6).equals("011110")){
					newcode=newcode+"3";
				}
				else if(code.substring(i+0, i+6).equals("011111")){
					newcode=newcode+"4";
				}
				else if(code.substring(i+0, i+6).equals("100000")){
					newcode=newcode+"5";
				}
				else if(code.substring(i+0, i+6).equals("100001")){
					newcode=newcode+"6";
				}
				else if(code.substring(i+0, i+6).equals("100010")){
					newcode=newcode+"7";
				}
				else if(code.substring(i+0, i+6).equals("100011")){
					newcode=newcode+"8";
				}
				else if(code.substring(i+0, i+6).equals("100100")){
					newcode=newcode+"9";
				}
				else if(code.substring(i+0, i+6).equals("100101")){
					newcode=newcode+"A";
				}
				else if(code.substring(i+0, i+6).equals("100110")){
					newcode=newcode+"B";
				}
				else if(code.substring(i+0, i+6).equals("100111")){
					newcode=newcode+"C";
				}
				else if(code.substring(i+0, i+6).equals("101000")){
					newcode=newcode+"D";
				}
				else if(code.substring(i+0, i+6).equals("101001")){
					newcode=newcode+"E";
				}
				else if(code.substring(i+0, i+6).equals("101010")){
					newcode=newcode+"F";
				}
				else if(code.substring(i+0, i+6).equals("101011")){
					newcode=newcode+"G";
				}
				else if(code.substring(i+0, i+6).equals("101100")){
					newcode=newcode+"H";
				}
				else if(code.substring(i+0, i+6).equals("101101")){
					newcode=newcode+"I";
				}
				else if(code.substring(i+0, i+6).equals("101110")){
					newcode=newcode+"J";
				}
				else if(code.substring(i+0, i+6).equals("101111")){
					newcode=newcode+"K";
				}
				else if(code.substring(i+0, i+6).equals("110000")){
					newcode=newcode+"L";
				}
				else if(code.substring(i+0, i+6).equals("110001")){
					newcode=newcode+"M";
				}
				else if(code.substring(i+0, i+6).equals("110010")){
					newcode=newcode+"N";
				}
				else if(code.substring(i+0, i+6).equals("110011")){
					newcode=newcode+"O";
				}
				else if(code.substring(i+0, i+6).equals("110100")){
					newcode=newcode+"P";
				}
				else if(code.substring(i+0, i+6).equals("110101")){
					newcode=newcode+"Q";
				}
				else if(code.substring(i+0, i+6).equals("110110")){
					newcode=newcode+"R";
				}
				else if(code.substring(i+0, i+6).equals("110111")){
					newcode=newcode+"S";
				}
				else if(code.substring(i+0, i+6).equals("111000")){
					newcode=newcode+"T";
				}
				else if(code.substring(i+0, i+6).equals("111001")){
					newcode=newcode+"U";
				}
				else if(code.substring(i+0, i+6).equals("111010")){
					newcode=newcode+"V";
				}
				else if(code.substring(i+0, i+6).equals("111011")){
					newcode=newcode+"W";
				}
				else if(code.substring(i+0, i+6).equals("111100")){
					newcode=newcode+"X";
				}
				else if(code.substring(i+0, i+6).equals("111101")){
					newcode=newcode+"Y";
				}
				else if(code.substring(i+0, i+6).equals("111110")){
					newcode=newcode+"Z";
				}
				else if(code.substring(i+0, i+6).equals("111111")){
					newcode=newcode+"J";
				}
				
				
				}
			   else return code;
			}
		
		return newcode;
	
}
}
