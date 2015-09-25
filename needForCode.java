import java.io.*;

class needForCode
{
	public static void main(String[] args) throws IOException
	{
		System.out.print("Key: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String key = br.readLine().trim().toLowerCase();
		System.out.print("Is message plaintext (Y/N): ");
		char textType = br.readLine().toLowerCase().charAt(0);
		if(textType=='y')
			System.out.print("Plaintext: ");
		else if(textType=='n')
			System.out.print("Ciphertext: ");
		else
		{
			System.out.print("Invalid entry");
			System.exit(0);
		}

		String text = "",textCopy = "";
		text = br.readLine();
		textCopy = text;
		text = text.trim().toLowerCase();
		text = text.replace(" ", "");
		//System.out.print("\nnew text = "+text+", original = "+textCopy);
		
		int origSize, textSize, keySize;
		origSize = textCopy.length();
		textSize = text.length();
		keySize = key.length();
		if(keySize<textSize)
		{
			int diff = textSize-keySize;
			int q = diff/keySize;
			if(q>0)
			{
				while(q!=0)
				{
					key = key.concat(key);
					q--;
				}
				keySize = key.length();
				if(keySize<textSize)
				{
					diff = textSize - keySize;
					key = key.concat(key.substring(0, diff));
				}
				keySize = key.length();
			}
			else
			{
				diff = textSize - keySize;
				key = key.concat(key.substring(0, diff));
				keySize = key.length();
			}
		}
		else if(keySize>textSize)
		{
			int diff = keySize - textSize;
			key = key.substring(0, keySize-diff);
			keySize = key.length();
		}
		//modified key
		//System.out.print("\nnew key: "+key);
		
		//System.out.print("\ntext length: "+textSize+", key length: "+keySize+"\n");
		
		int arr1[] = new int[textSize];		//for message
		int arr2[] = new int[textSize];		//for key
		char message[] = new char[origSize];
		int i,space;
		//processing the text
		int alphabet[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
		for(i=0;i<textSize;i++)
		{
			arr1[i]=alphabet[text.charAt(i)-97];
			arr2[i]=alphabet[key.charAt(i)-97];
			//System.out.println("values: "+arr1[i]+", "+arr2[i]);
		}
		
		if(textType == 'y')
		{
			for(i=0;i<textSize;i++)
			{
				arr1[i]=arr1[i]+arr2[i];		//message+key
				arr1[i]=(arr1[i]%26)+1;
				//System.out.print("\n"+arr1[i]+" ");
				message[i]=(char) (arr1[i]+97-1);
				//System.out.print(message[i]+" ");
			}
			message[0]=Character.toUpperCase(message[0]);
			text = new String(message);
			System.out.print("Ciphertext: "+text);
			
			/*
			 * for plaintext: the enemy will attack at dawn
			 * and key: venturesity
			 * 
			 * ciphertext: qntzjxssgdligiowvqudyatt
			 */
		}
		else if(textType == 'n')
		{
			for(i=0;i<textSize;i++)
			{
				arr1[i]=arr1[i]-arr2[i];		//cipher-key
				//System.out.print("\n"+arr1[i]+" ");
				arr1[i]=(arr1[i]%26)-1;
				if(arr1[i]<=0)
					arr1[i]+=26;
				//System.out.print("\n"+arr1[i]+" ");
				if(textCopy.charAt(i)==' ')
				{
					//System.out.print("\n entered space if");
					message[i]=' ';
					i++;
				}
				message[i]=(char)(arr1[i]-1+97);
				//System.out.print(message[i]+" ");
			}
			message[0]=Character.toUpperCase(message[0]);
			text = new String(message);
			System.out.print("Plaintext: "+text);
			
			/*
			 * for ciphertext: Qntzjxssgdligiowvqudyatt
			 * and key: venturesity
			 * 
			 * plaintext: Theenemywillattackatdawn
			 */
		}
	}
}
