% Blatt 11, Aufgabe 1a

var 1..100: maenner; 
var 1..100: frauen;
var 1..100: kinder;

constraint maenner + frauen + kinder = 100;
constraint 3*maenner + 2*frauen + 0.5*kinder = 100;
constraint frauen = 5*maenner;

solve satisfy;

output [show(maenner), " Männer, ", show(frauen), " Frauen und ", show(kinder), " Kinder"]