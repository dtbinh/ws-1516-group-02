% Blatt 11, Aufgabe 1c1

var 0..1: A; 
var 0..1: B;
var 0..1: C;

constraint if A==1 then A+B+C = 2 else A+B+C != 2 endif;
constraint if B==1 then A+B+C = 1 else A+B+C != 1 endif;
constraint if C==1 then A+B+C = 0 else A+B+C != 0 endif;

solve satisfy;

output ["1 = Ritter, 0 = Schurke: A ", show(A), ", B ", show(B), ", C ", show(C)]