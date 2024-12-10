#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <time.h>
#define shmSize 1025 //shared memory size
#define flag 0
#define messageOffset 1 //offset na pochetok na porakata

//flag sostojbi, 0 za rdy to write, 1 za msg rdy, 2 za msg read
void process_type_1(int shmid) {
    //prikachuvanje na shm
    char *shared_memory = (char *)shmat(shmid, NULL, 0);
    if (shared_memory == (char *)-1) {
        perror("shmat");
        exit(1);
    }

    while (1) {
        if (shared_memory[flag] == 0) {
            printf("Enter a message: ");
            char bla[120];
            scanf("%s",&bla);
            strcpy(shared_memory[1],bla);
//citanje i zapishuvanje
            shared_memory[strcspn(&shared_memory[messageOffset], "\n")] = 0;
//ostranuvanje na \n od vlezot
            shared_memory[flag] = 1; //1 ako e evailable
        }

        sleep(rand() % 10 + 1);
        //sleep dodeka seushe ima poraka
        while (shared_memory[flag] == 1) {
            sleep(1);
        }
    }
}

void process_type_2(int shmid) {
    char *shared_memory = (char *)shmat(shmid, NULL, 0);
    if (shared_memory == (char *)-1) {
        perror("shmat");
        exit(1);
    }

    while (1) {
        sleep(5);
        if (shared_memory[flag] == 1 || shared_memory[flag] == 2) {
            memset(&shared_memory[messageOffset], 0, shmSize - 1);
//chistenje na memorija
            shared_memory[flag] = 0; //flag na 0
        }
    }
}

void process_type_3(int shmid) {
    char *shared_memory = (char *)shmat(shmid, NULL, 0);
    if (shared_memory == (char *)-1) {
        perror("shmat");
        exit(1);
    }

    while (1) {
        if (shared_memory[flag] == 1) {
            printf("Memorijata prochitana e: %s\n", &shared_memory[
messageOffset]);
            shared_memory[flag] = 2; //prochitana poraka

            int sleep_time = rand() % 10 + 1;
            sleep(sleep_time);
        }

        while (shared_memory[flag] != 1) {
            sleep(1);
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Pogreshno vnesena bla bla");
        exit(1);
    }

    int process_type = atoi(argv[1]);
    key_t key = atoi(argv[2]);

    srand(time(NULL));

    int shmid = shmget(key, shmSize, IPC_CREAT | 0666); //kreiranje ilidobivanje pristap na shmseg
    if (shmid == -1) {
        perror("shmget");
        exit(1);
    }

    switch (process_type) {
        case 1:
            process_type_1(shmid);
            break;
        case 2:
            process_type_2(shmid);
            break;
        case 3:
            process_type_3(shmid);
            break;
        default:
            fprintf(stderr, "nemat takov proces.\n");
            exit(1);
    }

    return 0;
}

