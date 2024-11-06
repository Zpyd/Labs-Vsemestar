import sys
import os

if len(sys.argv) < 2:
    exit("nema dovolno argumenti ya peanut brain")
else:
    for i in sys.argv[1:]:
        if os.path.isfile(i):
            with open(i, "r") as f:
                lines = f.readlines()

            with open(i, "w") as f2:
                for line in reversed(lines):
                    end_words = " ".join(reversed(line.split()))
                    #print(f"od krajo zborovi: {end_words}")
                    f2.write(end_words + "\n")
        else:
            print(f"abe johny {i} ne e fajl u tekovnio dir")