test: Simpletron.o libsimple.a 
	gcc -o test Simpletron.o -L. -lsimple 

Simpletron.o: Simpletron.c simple.h
	gcc -c Simpletron.c 

util.o: util.c
	gcc -c util.c

libsimple.a: util.o
	ar cr libsimple.a util.o

clean:
	rm *.o libsimple.a 
