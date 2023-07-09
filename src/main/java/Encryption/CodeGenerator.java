package Encryption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CodeGenerator
{
	public String obString[] = new String[94];	
	
	public String[] getString()
	{
		return obString;
	}


	public String Generator(String sc)
	{
		String user = sc;
		char[] user_char = user.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < obString.length; i++)
		{
			String code = "";

			for (int j = 0; j < 2; j++)
			{
				int ran = (int) (Math.random() * 5);
				
				char randomChar = 0, randomsymbol;
				int randomNumber = 0;

				if (ran == 0)
				{
					randomChar = (char) ('a' + (int) (Math.random() * 15));
					code += randomChar;
					randomsymbol = (char) ((char) (int) (Math.random() * 32) + 33);
					code += randomsymbol;
					randomsymbol = (char) ((char) (int) (Math.random() * 32) + 33);
					code += randomsymbol;
				}
				else if (ran == 1)
				{
					randomNumber = (int) (Math.random() * (9 - 1 + 1) + 1);
					code += randomNumber;
					randomsymbol = (char) ((char) (int) (Math.random() * 32) + 33);
					code += randomsymbol;
					randomChar = (char) ('a' + (int) (Math.random() * 5));
					code += randomChar;
					randomsymbol = (char) ((char) (int) (Math.random() * 32) + 33);
					code += randomsymbol;
				}
				else if (ran == 2) 
				{
					randomNumber = (int) (Math.random() * (9 - 1 + 1) + 1);
					code += randomNumber;
					randomChar = (char) ('a' + (int) (Math.random() * 11));
					code += randomChar;
					randomsymbol = (char) ((char) (int) (Math.random() * 30) + 36);
					code += randomsymbol;
				
				}
				else if (ran == 3) 
				{
					randomNumber = (int) (Math.random() * (9 - 1 + 1) + 1);
					code += randomNumber;
					randomChar = (char) ('a' + (int) (Math.random() * 11));
				
				
				}else if (ran == 4) 
				{	
					randomChar = (char) ('a' + (int) (Math.random() * 11));
					code += randomChar;
					randomsymbol = (char) ((char) (int) (Math.random() * 30) + 36);
					code += randomsymbol;	
				}

			}
			obString[i] = code;
		}

		for (int i = 0; i < user_char.length; i++)
		{
			sb.append(obString[(int) user_char[i] - 32]);
		}
		System.out.println("Encripted Code : "+sb);

		return sb.toString();
	}

}

