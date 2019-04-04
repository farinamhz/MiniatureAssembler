lui 1,16		
lw 2,0,thousand		
lw 4,0,one		
loop sub 1,1,2		
slti 3,1,10000		
beq 3,4,finish		
j loop		
finish halt		
thousand .fill 1000
thousand .fill 1001
one .fill 1			