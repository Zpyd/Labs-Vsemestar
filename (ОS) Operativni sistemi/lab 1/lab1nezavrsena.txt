#!/usr/bin/bash

#isprobaj ?<za naming things za replace> u smisla s/?<hmm>(something)/hmm instead of writing 1

#Za cas:
# 1.1) da se najdat site procesi od root
#      ps -u root or ps -elf | grep root

# 1.2) da se najdat site c fajlovi u dir i prethodnio dir so grep
#      ls | grep -e ".*\.txt"

# 1.3 da se najde so sed site filovi u tekovnio dir i da se isprintat site  
#find . -type f -exec stat --format="%n %y" {} \; this works ama ne so se bara :P
#find . -maxdepth 1 -print0 | xargs -0 stat --format "%" isto so exec basically

#or with ls 

#ovoa najblisku ama still so far 
#ls -l | sed '1d; s/^.* [0-9][0-9]* \([A-Za-z]* [0-9]* [0-9:]*\) \(.*\)/\2 \1/' neam pojma

#find . -maxdepth 1 -print0 | xargs -0 stat primer za stat kako rabote u za da pokaze za razni jazli u cur dir info


#1.4 da se najdat site datoteki promeneti minatata nedela

# 2 koristejki awk find -maxdepth 2 (directly za 2te dir da gi najdemem) 

#FAILED EXPERIMENT #1
# imefile=$1

# if [-n  $imefile] then imefile=$(find . -name "naredbi.txt") 
#     else
#     if [[ ! "$imefile" =~ \.txt$ ]]; then #ako se misle bilo koj tip na fajl togaj mozeme if [-f $ime] toa i direktno proveruva dali e taj file u dir msm
#         imefile=$(find . -name "$imefile")

#         if [ -z "$imefile" ]; then 
#             echo "nema ja nikade batka tezok e zivoto"
#         else 
#             if [ "./naredbi.txt" != "$imefile" ] || ; then #kako da napraveme sporedba da videme dali e u segasnio dir na drug nacin???
#                 cp "$imefile" .  # ZASO SEKAD GO IZVRSUVA VOA DURI I KOA NEMA GO???????
#                 echo "'naredbi.txt' has been copied to the current directory." 
#             else echo "naredbi e veke u cur dir" 
#             fi
#         fi
#     fi
# fi

# echo "$ime"


#FAILED EXPERIMENT #2
# SHO PRAEME U SLUCAJ KOGA IMAME POVISE INSTANCI NA FAJL SO ISTO IME???? KAKO DA GO TRETIRAME FIND??
# OVOA E SO PRETPOSTAVKA DEKA SAMO 1 FILE PO TOA IME POSTOE
#I ZASO UOPSTE NISO NE PRAE SEA?????????????

# imefile=$1

# if [ -z  $imefile ]; then imefile=$(find . -name "naredbi.txt") 
#     else
#     if [ ! -f $imefile ]; then #ako se misle bilo koj tip na fajl togaj mozeme if [-f $ime] toa i direktno proveruva dali e taj file u dir msm
#         imefile=$(find . -name "$imefile")

#         if [ -z "$imefile" ]; then 
#             echo "nema ja nikade batka tezok e zivoto"
#         else 
#                 cp "$imefile" .  # ZASO SEKAD GO IZVRSUVA VOA DURI I KOA NEMA GO???????
#                 echo "'naredbi.txt' has been copied to the current directory." 
#             fi
#         else echo "eee so mu se sekiras eve ja uste u start e u istio"
#         fi
#     fi



#OVAJ NAJBLIZUK FAILED EXPERIMENT I THINK
imefile=$1

if [ -z  $imefile ]; then imefile=$(find . -name "naredbi.txt") fi

if [ ! -f $imefile ]; then
        imefile=$(find . -name "$imefile")
        if [ -z "$imefile" ]; then 
            echo "nema ja nikade batka tezok e zivoto"
        else 
                cp "$imefile" .  # ZASO SEKAD GO IZVRSUVA VOA DURI I KOA NEMA GO???????
                cat $imefile
            fi
fi














# Za doma:
# 1 i 2) napisi skripta za da ukl firefox da najdes pid i da go kilnesh
# firefox

# echo "Iskl go za: "
# i=$1

# if ! [["$i" =~ ^[0-9]+$ ]] then
#     echo "please enter a valid number idiot"
#     exit 1
# fi

# while [ $i -gt 0 ]
# do 
#     echo "$i"
#     sleep 1 
#     ((i--))
# done

# pgrep firefox | xargs kill

# 2)
# Da se iskoristi datotekata vezba12.txt koja sadrzi kontakt informacii za FEIT. D
# a se iskoristi naredbata SED za da se najdat site validni e-mail adrese i da se cenzuriraat podatocite od 
# e-mail adresite pred znakom @. Primer e zadaden podolu. Da se vnimava deka treba da se najdat validnite e-mail adrese.


# Important e-mail addresses
# Official contact email: contact@feit.ukim.edu.mk
# Dean’s Office contact email: deans.office@feit.ukim.edu.mk
# Dean’s Office Assistant: tehsekretar@feit.ukim.edu.mk
# Public Information Coordinator: informacii@feit.ukim.edu.mk
# Library: biblio@feit.ukim.edu.mk
# Web Administrator: webadmin@feit.ukim.edu.mk
# 
# Rez od cenzuriranjeto:
# Important e-mail addresses
# Official contact email: ?@feit.ukim.edu.mk
# Dean’s Office contact email: ?@feit.ukim.edu.mk
# Dean’s Office Assistant: ?@feit.ukim.edu.mk
# Public Information Coordinator: ?@feit.ukim.edu.mk
# Library: ?@feit.ukim.edu.mk
# Web Administrator: ?@feit.ukim.edu.mk

# sed -E 's/([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+\.[a-zA-Z]{2,})/?@\2/g' vezba12.txt
#   