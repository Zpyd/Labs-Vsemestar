import os
import sys

if len(sys.argv) < 2:
    print("nema dovolno arg")
    sys.exit(1)
else:
    for i in sys.argv[1:]:
        if os.path.isfile(i):
            with open(i, "r") as f:
                text=f.read()

            text=text.split()
            print(text)

            for j in range(int(len(text)/2)):
                if text[j]==text[-(j+1)]:
                    continue
                else:
                    temp=text[j]
                    text[j]=text[-(j+1)]
                    text[-(j+1)]=temp
                    # print(f"text[{j}]: {text[j]}")
                    # print(f"text[-{j}]: {text[-(j + 1)]}")
                    # print("")

            with open(i, "w") as f:
                f.write(" ".join(text))
                #OVOA GO PRAE AKO IMALO NEWLINE CHAR DA SE IZBRISAT HOW DO I ADJUST THAT
        else:
            print(f"abe johny {i} ne e fajl u tekovnio dir")
