# RSA
For IT security basics course

#About the algorithm:  
Two prime number (p and q) needed. This program will generate these numbers randomly.  
N=p*q and fiN=(p-1)*(q-1)
e should be a relative prime for fiN, that generate randomly this code.
We using the Euripides algorithm so we know that 1=e*d-fiN*k is equal with e*d=k*fiN+1.
The encoding itself is working like this: number^e=x (mod N) 
where number should be coded and x is the coded value of number.
