#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>


int main(int argc, char *argv[])
{
    if (argc < 2)
    {
        printf("greska vneseni arg sin");
        return 1;
    }

    char flag=argv[1];

    int pipe1[2], pipe2[2];
    pid_t komanda1, komanda2;

    if (pipe(pipe1) == -1 || pipe(pipe2) == -1)
    {
        printf("failed to pipe");
        return 1;
    }

    komanda1 = fork();
    if (komanda1 == 0)
    {
        close(pipe1[0]);

        char comm[100];
        printf("komanda:\n");
        scanf("%s",comm);
        dup2(pipe1[1],STDOUT_FILENO);
        close(pipe1[1]);
        execlp(comm,comm,flag,NULL);
    }
    else
    {
        
        komanda2 = fork();
        if (komanda2 == 0)
        {
            waitpid(komanda1, NULL, 0);

            close(pipe1[1]);
            close(pipe2[0]);
            dup2(pipe1[0],STDIN_FILENO);
            dup2(pipe2[1],STDOUT_FILENO);

            char comm[100];
            printf("komanda:\n");
            
            close(pipe1[0]);
            close(pipe2[1]);
            execlp(comm,comm,flag,NULL);
        
        }
        else
        {
            // komanda 3:

            waitpid(komanda2, NULL, 0);
            close(pipe2[1]); 

            char comm[100];
            printf("komanda:\n");

            execlp(comm,comm,flag,NULL);
           
             
        }
    }
}