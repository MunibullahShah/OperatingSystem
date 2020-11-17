//MUNIBULLAH SHAH SID:FA18-BSE-038
//SANAN ALI BHATTI SID:FA18-BSE-050

#include<dirent.h>
#include<errno.h>
#include<stdio.h>
#include<stdlib.h>
#include <unistd.h>
#include<time.h>
#include<sys/stat.h>
#include<string.h>




void directory_traversal(char *path){
	DIR * d=opendir(path);
	if(d==NULL){
	perror("\nPermission denied");
	return;
	}
	struct dirent *dir;

	pid_t childpid;


	fprintf(stderr, "\nprocess ID:%ld  parent ID:%ld  child ID:%ld  Going to explore %s\n",
           			 (long)getpid(), (long)getppid(), (long)childpid,path);

	while((dir=readdir(d))!=NULL){

		if(dir->d_type!=DT_DIR){
			struct stat statbuf;
			if (stat(path, &statbuf) == -1)
				perror("Failed to get file status");
			else
				printf("%s  size:%ld  Inode: %ld   Mode: %o    Modified: %s\n",dir->d_name,statbuf.st_size,
					statbuf.st_ino,(statbuf.st_mode & (S_IRWXU | S_IRWXG | S_IRWXO)), ctime(&statbuf.st_mtime) );
				}


		if(dir->d_type==DT_DIR &&strcmp(dir->d_name,".")!=0&& strcmp(dir->d_name,"..")!=0){
			char _path[246];
			sprintf(_path,"%s/%s",path,dir->d_name);
			
			if((childpid=fork())==0){
		
				char arg[300];
				sprintf(arg,"a.out %s",_path);
				execl("./fork_stat.exe", "./fork_stat.exe", _path,  NULL);
				perror("Child failed to exec fork_stat");
			}
		}
	}
		closedir(d);
}

int main(int argc, char **argv)
{
    directory_traversal(argv[1]);
printf("\n");
  return(0);
}
