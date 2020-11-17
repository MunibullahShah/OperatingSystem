#include<stdio.h>
#include "simple.h"

int* readFromFile(char *fileName[]);

int main( char *fileName[]){
int* memory;//memory of simpletron
memory=readFromFile(fileName);
int accumulator;//register
int flag=0;

printf("\n");

int counter=0;//counter for memory
int temp;
int i;

do{

		int instruction=memory[i];
	printf("instruction= %d",instruction);
	int operation=(instruction/100);
	printf("operation= %d",operation);


	int operand=(instruction%100);
	printf("operand=........................................");


	switch(operation){
		case READ:
			{
			printf("Enter the number\n");
			int temp;
			scanf("%d",&temp);
			memory[operand]=temp;
			break;
		}
		
		case WRITE:
		{
			printf("writing from memory\n%d\n",memory[operand]);
			break;
		}
	
		case LOAD:{
			accumulator=memory[operand];
			printf("Accumulator has %d\n",accumulator);
			break;
		}
	
	
		case STORE:{
			memory[operand]=accumulator;
			printf("Stored in Memory\n");
			break;
		}


		case ADD:{
			accumulator=accumulator+memory[operand];
			printf("Accumulator has %d\n",accumulator);
			break;
		}

	
		case SUBTRACT:{
			accumulator=accumulator-memory[operand];
			printf("Accumulator has %d\n",accumulator);
			break;
		}
	
		case DIVIDE:{
			accumulator=accumulator/memory[operand];
			printf("Accumulator has %d\n",accumulator);
			break;
		}

	
		case MULTIPLY:{
			accumulator=accumulator*memory[operand];
			printf("Accumulator has %d\n",accumulator);
			break;
		}

	
		case BRANCH:{
			temp=i;
			instruction=memory[temp];
			continue;
			break;
		}

	
		case BRANCHNEG:{
			if(accumulator<0){
			temp=i;
			instruction=memory[temp];
			continue;
		}
		break;
		}

	
		case BRANCHZERO:{
			if(accumulator==0){
				temp=i;
			instruction=memory[temp];
				continue;
			}
			break;
	}

	
	case HALT:{
		flag=43;
		printf("program halted\n");
		break;
	}

		
	default:{
		printf("No Such Operation\n");
		printf("Enter your instruction manually\n");
		scanf("%d",&instruction);
		continue;
		break;
	}
	}
	
	i++;
	
	}while(flag!=43);
}


