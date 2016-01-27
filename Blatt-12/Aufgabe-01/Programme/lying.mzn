% Blatt 11, Aufgabe 1b

var 0..1: tick; 
var 0..1: trick;
var 0..1: track;

constraint trick = track;
constraint tick <= trick;
constraint trick + track < 2;

solve satisfy;

output ["1 = schuldig, 0 = unschuldig: Tick ", show(tick), ", Trick ", show(trick), ", Track ", show(track)]