import numpy as np
import matplotlib.pyplot as plt
import math

h = np.arange(0.0001, 0.9999, 0.0001)

y = - h* (np.log(h)/np.log(2)) - (1-h)*(np.log(1-h)/np.log(2))

plt.ylim(0,1)
plt.xlim(0,1)

plt.plot(h,y, lw=1)
plt.show()

HIER NOCH LOESUNG ZU AUFGABE 3 DAZUSCHREIBEN