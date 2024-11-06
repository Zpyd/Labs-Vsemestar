import subprocess
import sys

# 1) 2 argumenti preku comandna linija : 1. ime na datoteka 2. flag

if len(sys.argv) <3:
    print("treba da vneses 2 arg: filename.txt flag(-f ili -d")
    exit(1)
else:
    data_ls = subprocess.run(['ls', '-al'], capture_output=True, text=True)
    result_ls = data_ls.stdout.splitlines()
    del result_ls[0] #da go trgne toa total
    #print(result_ls)
    if data_ls.returncode != 0:
        print("problem pri subprocess run")
        exit(1)

    # 1.1) -d razlicni datumi kreirani ili modified
    else:
        if (sys.argv[2] == "-d"):
            #imam opcija da gi izeteriram i sporeduvam ama shablon e ls taka da od 5: pa natam
            with (open(sys.argv[1], "a")) as out:
                for line in result_ls:
                    line = line.split()
                    out.write(" ".join(line[5:])+"\n") #[5:8] ako se traze samo datata without the name

        # 1.2) -f ispecati za sekoj datum kolku datoteki bile kreirani/modified
        elif (sys.argv[2] == "-f"):
            dates={}
            for line in result_ls:
               # print(line)
                line = line.split()
                datum=" ".join(line[5:7])
                if datum not in dates:
                    dates[datum] = 1

                elif datum in dates:
                    dates[datum] += 1

            with open(sys.argv[1], "w") as out:
                for key, value in dates.items():
                    out.write(key + ": " + str(value) + "\n")

