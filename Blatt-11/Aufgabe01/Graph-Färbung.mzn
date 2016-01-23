% Blatt 10, Aufgabe 1b
include "alldifferent.mzn";
int: nc = 8;

var 1..nc: A;  var 1..nc: B;  var 1..nc: C;  var 1..nc: D;
var 1..nc: E;  var 1..nc: F;  var 1..nc: G;  var 1..nc: H;

constraint A != B+1;  constraint A+1 != B;
constraint A != C+1;  constraint A+1 != C;
constraint A != D+1;  constraint A+1 != D;
constraint B != C+1;  constraint B+1 != C;
constraint B != E+1;  constraint B+1 != E;
constraint B != F+1;  constraint B+1 != F;
constraint C != E+1;  constraint C+1 != E;
constraint C != F+1;  constraint C+1 != F;
constraint C != G+1;  constraint C+1 != G;
constraint D != C+1;  constraint D+1 != C;
constraint D != F+1;  constraint D+1 != F;
constraint D != G+1;  constraint D+1 != G;
constraint E != F+1;  constraint E+1 != F;
constraint E != H+1;  constraint E+1 != H;
constraint F != G+1;  constraint F+1 != G;
constraint F != H+1;  constraint F+1 != H;
constraint G != H+1;  constraint G+1 != H;

constraint alldifferent([A,B,C,D,E,F,G,H]);
solve satisfy;

output [" A=", show(A), " B=", show(B), " C=", show(C), " D=", show(D),
" E=", show(E), " F=", show(F), " G=", show(G), " H=", show(H)]