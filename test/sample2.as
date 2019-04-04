lui 1,16			#(0)	134283280
lw 2,0,thousand		#(1)	151126024
lw 4,0,one			#(2)	151257097
loop sub 1,1,2		#(3)	17960960
slti 3,1,10000		#(4)	101918480
beq 3,4,finish		#(5)	188940295
j loop				#(6)	218103811
finish halt			#(7)	234881024
thousand .fill 1000	#(8)	1000
one .fill 1			#(9)	1