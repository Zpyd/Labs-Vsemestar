#include <sys/fcntl.h>
#include <sys/mman.h>

#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <time.h>

/*pretpostavka:
    se izvrsuvat programite vaka od terminal:
        ./run 1 123 [123 e kluco]
        ./run 2 123
        ./run 3 123

        MEGUTOA DALI NA VAJ NACIN SE PRVICNO ZACUVUVA REDOSLEDO SO CEL DA DOJDE
        DO CASE 1 ZA DA SE SOZDADE OGLASNATA TABELA???

        KAJ TREBA DA SE DEALOCIRA POKAZUVACITE I MEMORIJATA???

*/
int main(int argc,char* argv[]){

    if(argc<3){
        printf("premal broj arg vnesi slednio tip:\n ./exec tip kluc" );
            return 1;

        }
    
    key_t kluc = (key_t) argv[2];
    char *mem;

    int mem_adresa=mmap();    
        if(mem_adresa==-1){
            printf("failed pri sozdavanje na memory adresa");
            return 1;
            }
    mem=mmap(NULL, 1000 * (sizeof(int)), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if(mem==MAP_FAILED){
        printf("neuspesno prikacuvanje na memorijata");
        return 0;
    }

    char tip=argv[1];

    switch(tip){
        case '1':
                char* tekst;
                while(1){
                    if(mem[0]=='0'){
                        printf("vnesi nekoja poraka na oglasnata tabela(and NO rascism this time)");
                        scanf("%s",&tekst);
                        strcpy(mem+1,tekst);
                        mem[0]="1";
                        sleep(rand()%10+1);
                    }
                    while(mem[0]=='1'){sleep(1);}
                }

            break;
        
        case '2':
                char erase='00';
                    while(1){
                      sleep(5);      
                      strcpy(mem,erase);  
                    }       
            break; 

        case '3':
                char* tekst;
                while(1){
                    if(mem[0]=='1'){
                        strcpy(tekst,mem+1);
                        printf("%s/n",tekst);
                        sleep(rand()%10+1);
                    }
                    while(mem[0]=='0'){sleep(1);}
                }

            break; 
        
        default:
            printf("ima samo 3 tipa na vaa programa taka da izberi od 1-3");
            return 1;
    }




}