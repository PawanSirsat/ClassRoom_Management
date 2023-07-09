package Encryption;

public class Encrypt
{
	public static String Generator(CodeGenerator CodeGen, String sc)
	{

		CodeGenerator random_Value = CodeGen;

		char ch[] = sc.toCharArray();
		String[] strArray = random_Value.getString();

		int count = 0;

		String strfinal = new String();

		for (int i = 0; i < ch.length; i++)
		{
			StringBuffer temp = new StringBuffer();

			for (int j = i; j < ch.length; j++)
			{
				temp.append(ch[j]);

				for (int j2 = 0; j2 < strArray.length; j2++)
				{
					if (temp.toString().equals(strArray[j2].toString()))
					{
						int a = j2 + 32;
						System.out.print((char) a);
						strfinal += (char) a;
						j2 = strArray.length;
						i = j;
						j = ch.length;
					}
				}

			}
		}
		return strfinal;
	}

}
