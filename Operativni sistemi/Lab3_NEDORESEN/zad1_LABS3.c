#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <pthread.h>


int broevi[100];

void *thread_function(void *arg) {
    int *result= malloc(sizeof(int));
    int N=((int *)arg)[0];f
    int K=((int *)arg)[1];

    srand(time(NULL));
    for (int i = 0; i < N; i++) {
        int j = rand() % N + 1;
        if(broevi[j] == K) {
            result= j;
            return (void*) result;
        }
    }
    pthread_exit(NULL);
}


int main(int argc, char *argv[]) {
    if(argc != 3) {
        printf("Incorrect number of arguments\nVnesi 1)N-broj na elementi u filo, 2)K-brojo so sakas da go najdes");
        exit(1);
    }

    //N broevi u nizata i K element za trazenje
    int N=atoi(argv[1]), K=atoi(argv[2]);

    char *filename="data.txt";
    FILE *f=fopen(filename,"r");
    if(f==NULL) {
        printf("Error opening file");
        return 1;
    }

    int i=0; int br;
    while(fscanf(f,"%d",&br)==1) {
        broevi[i++]=br;
    }

    fclose(f);

    // printf("Numbers in the array:\n"); //GOOD
    // for (i = 0; i < N; i++) {
    //     printf("%d ", broevi[i]);
    // }
    // printf("\n");
    //
    //


    pid_t cid=fork();
    if(cid<0) {
        printf("Error in fork");
    }

    int* indeks;

    int inputs[2]={N,K};
    pthread_t threads[5];
    for(int i=0;i<5;i++) {
        pthread_create(&threads[i], NULL, thread_function, inputs);
    }

    for(int i=0;i<5;i++) {
        if((pthread_join(threads[i], &indeks))==0){
            if(*indeks==K) {
               if(cid==0) {
               printf("Child is victorious HAZAAAH");
                kill(getppid(),SIGKILL);
                break;
               }
                else {
                printf("FUCK THEM KIDS ADULTS 5 LIFE");
                kill(cid,SIGKILL); break;
                }
            }
        }
        else { printf("Error in join"); }
    }

    free(res);

    
    printf("Tolko vise nema ash nemoze da go najdam\n");
    pthread_exit(NULL);
}
