#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#include <sys/types.h>
#include <string.h>

int main(int argc, char *argv[])
{
    int N;

    // 1) citas od tast kolku deca da se sozdadat
    printf("Vnesi kolku pati da se izvrsi naredbata\n");
    scanf("%d", &N);

    for (int i = 1; i < argc; i++) printf("%s ", argv[i]);
    printf("da se izvrsi: %d pati \n", N);

    pid_t pid;
    // 2) sozdacas deca

    for (int i=0; i<N; i++) {
        pid=fork();
        if (pid == 0) break;
    }

    if (pid < 0) {
        printf("Error in fork\n");
    }
        // 2.2) izvrsi programata preku argv i exec familja na naredbi
        else if (pid == 0) {
             execvp(argv[1], &argv[1]);
             printf("child process: %d zavrsi\n", getpid());
            }

             else {

                    // 3) roditelo ceka site od niv da se izvrsat
                    int status;
                    while((pid=wait(&status))>0) {                    //moze i da iskoristeme  for (int i=0; i<N; i++) {wait(NULL);}
                        printf("child process: %d zavrsi\n", getpid());
                    };
                    printf("Ja sum tatkoto i site decinja moi zavrsija");
                }

    return 0;

}
