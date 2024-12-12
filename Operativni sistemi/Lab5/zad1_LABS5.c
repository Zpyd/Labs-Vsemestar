#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define PIPE1 "pipe1.txt"
#define PIPE2 "pipe2.txt"
#define BUFFER_SIZE 1024

mode_t REZIM = (S_IRWXU | S_IRWXG | S_IRWXO);

int main(int argc, char *argv[])
{

    if(argc==2){
        printf("Nemas tocen broj na argumenti");
        exit(1);
    }

    int proces = argv[1];

    switch (proces)
    {   //goddamnit cases se all in 1 scope pa se redeklarirat
    case '1':{  
        int fd = open(PIPE1, O_RDWR | O_CREAT, REZIM);
        if (fd == -1)
        {
            printf("proces 1 ne go otvori pipe1.txt");
            exit(1);
        }


        char buffer[BUFFER_SIZE];
        printf("Vnesi teskst \n"); //moze i so write 1 ama i aint counting letters
        ssize_t bytesRead;


        //??????? is dis ok????
        while((bytesRead = read(0, buffer, BUFFER_SIZE))>0 && buffer[bytesRead-1]!='\0'){
            
            if(write(fd,buffer,bytesRead)==-1){
                printf("failed to zapise u fajlo");
                exit(1);
            }
        }

        //how do i copy just till then??????
        if(buffer[bytesRead-1]=="\0" && bytesRead>0){
            buffer[bytesRead-1]="";
            if(write(fd,buffer,bytesRead)==-1){
                printf("failed to write u fajlo poslednio msg");
                exit(1);
            }
        }
    
        close(fd);

        break;}

    case '2':{
        int citanjefd = open(PIPE1, O_RDONLY);
        if (citanjefd == -1)
        {
            printf("proces 2 ne go otvori pipe1.txt");
            exit(1);
        }

        int smestuvanjefd = open(PIPE2, O_RDWR | O_CREAT, REZIM);
        if (smestuvanjefd == -1)
        {
            printf("proces 2 ne go otvori pipe2.txt");
        }
        
        
        char buffer[BUFFER_SIZE];
        ssize_t bytesRead;
        while ((bytesRead = read(citanjefd, buffer, BUFFER_SIZE)) > 0)
        {
            write(smestuvanjefd, buffer, bytesRead);
        }

        close(citanjefd);
        close(smestuvanjefd);
        break;
    }
    case '3':{
        int fd = open(PIPE2, O_RDONLY, REZIM);
        if (fd == -1)
        {
            printf("proces 3 ne go otvori pipe2.txt");
            exit(1);
        }

        char buffer[BUFFER_SIZE];
        ssize_t bytesRead;
        int praznoMesto=0;

        while((bytesRead=read(fd,buffer,BUFFER_SIZE))>0){
            for (int i = 0; i < bytesRead; i++)
            {
                if(buffer[i]==' '){
                    praznoMesto++;
                }
            }
            
        }

        printf("ima %d prazni mesta",praznoMesto);
        break;}
    }

    return 0;
}
