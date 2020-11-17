#include<stdio.h>
#include<stdlib.h>
#include "simple.h"


int* readFromFile(char *fileName[]){
	FILE *file;
	int memory[100];
	if((file=fopen(fileName[1],"r"))==NULL){
		printf("Could not open the file..\n");
	}
	else{
		int i=0;
		
		while(!feof(file)){
			fscanf(file,"%d",&memory[i]);
			i++;
		}
		fclose(file);
	}
	return memory;
}
