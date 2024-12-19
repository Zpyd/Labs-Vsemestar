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

        char input[1024];

        printf("Vnesi tekst za kom 1 (pretisni samo enter vo newline ako sakas za kraj):\n");
        while (fgets(input, sizeof(input), stdin))
        {
            if (input[0] == '\0' || input[0] == '\n')
            {
                break;
            }
            write(pipe1[1], input, strlen(input));
        }
        close(pipe1[1]);
    }
    else
    {
        
        komanda2 = fork();
        if (komanda2 == 0)
        {
            waitpid(komanda1, NULL, 0); 
            close(pipe1[1]);
            close(pipe2[0]);
            char buffer[1024];
            int bytes_read;
            while ((bytes_read = read(pipe1[0], buffer, sizeof(buffer))) > 0)
            {
                write(pipe2[1], buffer, bytes_read);
            }
            close(pipe1[0]);
            close(pipe2[1]);
        }
        else
        {
            // komanda 3:

            waitpid(komanda2, NULL, 0);
            close(pipe2[1]); 

            char buffer[1024];
            int bytes_read;
            int spaces_count = 0;

            while ((bytes_read = read(pipe2[0], buffer, sizeof(buffer))) > 0)
            {
                for (int i = 0; i < bytes_read; i++)
                {
                    if (buffer[i] == ' ')
                    {
                        spaces_count++;
                    }
                }
            }
            printf("br prazni mesta: %d\n", spaces_count);
            close(pipe2[0]); 
        }

            return 0;
    }
}
