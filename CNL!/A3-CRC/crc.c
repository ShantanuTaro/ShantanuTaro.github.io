#include <stdio.h>
 #include <string.h>
 int main() {
	int i,j,keylen,msglen;
	char input[100], key[30],temp[30],quot[100],rem[30],key1[30],nmsg[20];
	printf("Enter Data: ");
	scanf("%s",input);
		printf("Enter Key: ");
	scanf("%s",key);
	keylen=strlen(key);
	msglen=strlen(input);
	strcpy(key1,key);
	for (i=0;i<keylen-1;i++) {
		input[msglen+i]='0';
	}
	for (i=0;i<keylen;i++)
	 temp[i]=input[i];        //Dividend Part
	for (i=0;i<msglen;i++) {
		quot[i]=temp[0];
		if(quot[i]=='0')
		 for (j=0;j<keylen;j++)
		 key[j]='0'; else
		 for (j=0;j<keylen;j++)
		 key[j]=key1[j];
		for (j=keylen-1;j>0;j--) {
			if(temp[j]==key[j])//exOR opration perform
			 rem[j-1]='0'; else
			 rem[j-1]='1';
		}
		rem[keylen-1]=input[i+keylen];
		strcpy(temp,rem);
	}
	strcpy(rem,temp);
	printf("\nQuotient is ");
	for (i=0;i<msglen;i++)
	 printf("%c",quot[i]);
	printf("\nRemainder is ");
	for (i=0;i<keylen-1;i++)
	 printf("%c",rem[i]);
	printf("\nFinal data is: ");
	int newmsglen;
		newmsglen=msglen+keylen-1;
	 for(i=msglen;i<(msglen+keylen-1);i++)
	 	input[i]=rem[i-msglen];
	for (i=0;i<newmsglen;i++)
	 printf("%c",input[i]);
	 
	 
//----------------------receiver side code--------------------------------------	
	printf("\nENTER THE RECEIEVED DATA : ");
		scanf("%s",input);
		for (i=0;i<keylen;i++)
	 temp[i]=input[i];
	for (i=0;i<msglen;i++) 
	{
		quot[i]=temp[0];
		
		if(quot[i]=='0')
		 for (j=0;j<keylen;j++)
		 key[j]='0'; 
		 else
		 for (j=0;j<keylen;j++)
		 key[j]=key1[j];
		for (j=keylen-1;j>0;j--) 
		{
			if(temp[j]==key[j])
			 rem[j-1]='0'; else
			 rem[j-1]='1';
		}
		rem[keylen-1]=input[i+keylen];
		strcpy(temp,rem);
	}
	int flag=0;
	strcpy(rem,temp);
	for(i=0;i<keylen-1;i++)
	{
		if(rem[i]=='0')
	 		continue;
		else
			flag=1;
	}
	if(flag==1)
		printf("\n\tSince Remainder Is Not 0 Hence Message Transmitted From Sender To Receriver Contains Error");
	
	else
		printf("\n\tSince Remainder Is 0 Hence Message Transmitted From Sender To Receriver Is Correct\n");
	
	 return 0;
}
