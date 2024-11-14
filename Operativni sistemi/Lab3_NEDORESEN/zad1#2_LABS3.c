#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <pthread.h>

/*
 *
 *
 *  ???DOBIVAM GI SITE 3 MOZNOSTI (PWIN,CWIN, NONWIN) AMA FOR SOME REASON MN MN
 *  MN PO VEROJATNO E PARENT PROCESO DA POBEDE ZASO???
 *
 *
 *  ??? OK EVEN IF WE GET A CHILD WIN ISPRINTA GO SAMO 1SKA
 *  DODEKA KOA PARENTO POBEDE 5 PATI SE ISPRINTA
 *      -> IS IT POSSIBLE SAMO 1 THREAD KAJ DETTO DA IMA
 *
 *  NOTE: 5 PATI DA SE PRINTA IS NOT! A WANTED OUTCOME
 *        TREBA KAJ PTHREAD_JOIN DA OPRAVAM RETURN 0 PRI TOCNO NAJDENO
 *        ZA DA GO ZAVRSE PROCESO
 *
 */




int broevi[100];

void *thread_function(void *arg) {
    int N=((int *)arg)[0]; //why does this work????
    int K=((int *)arg)[1];
    int cid=((int *)arg)[2];
    // printf("cid=%d\n",cid);
    // if (cid==0) {
    //     printf("parent of the child id:=%d\n",getppid());
    // }

    srand(time(NULL));
    for (int i = 0; i < N; i++) {
        int j = rand() % N;
        if(broevi[j] == K) {
            printf("NAJDEME GO BEJBEEE %d the victor was: Cid:%d\n", j,cid);

            if(cid == 0) {
                printf("Child is victorious HAZAAAH");
                kill(getppid(),SIGKILL);
            }
            else {
                printf("FUCK THEM KIDS ADULTS 5 LIFE\n");
                kill(cid,SIGKILL);
            }
            return (void*) 1;
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
    // printf("\t%d\n", cid);
    //
    // if(cid==0) {
    //     printf("\t\t%d\n", getpid());
    // }



    int inputs[3]={N,K,cid};
    pthread_t threads[5];
    for(int i=0;i<5;i++) {
        pthread_create(&threads[i], NULL, thread_function, inputs);
    }


    pthread_t p; void* res;
    for(int i=0;i<5;i++) {
        p=pthread_join(threads[i], &res); // DALI U P KE SE SMESTE WHATEVER WE RETURN? AKA RETURN 1 BUM JA TI
        //A BY DEFAULT VRAKJA 0 PRI SUCCESFUL?

        //DALI PREKU RES NAMESTO P SE VRNE TOA RETURN/ PTHREAD_EXIT???
        //      -> AKO DA U IF ZAMENI GO P SO RES
        printf("\n\n\n %d \n\n\n", *(int *)res); //WHY NO PECATE?????????
        if(p!=0 && p!=1) {printf("Error in join\n"); }

        if(p==1){
            return 0;
        }
    }

    if(cid==0) {
        printf("Ja sum detto Tolko vise nema ash nemoze da go najdam\n");
    }
    else
        printf("Apperantly iam the adult in the situation and i still couldnt find it\n");
    pthread_exit(NULL);
}
