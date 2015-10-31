# -*- coding: utf-8 -*-
# <nbformat>3.0</nbformat>

# <codecell>

# Shows basic statistical test ( student t-test )
import numpy as np
import matplotlib.pyplot as plt
import pylab as P
import scipy.stats as st

# just eye candy
isseorange = (1.0, 0.57647, 0.039216)

# <codecell>

# read sample data (obtained from Netlogo runs
whites = np.genfromtxt("whites.csv", dtype=float)
blacks = np.genfromtxt("blacks.csv", dtype=float)

# <codecell>

# calculate standard deviations and means
print("Mean whites: ", np.mean(whites))
print("Std whites: ", np.std(whites))
print("N whites: ", len(whites))
print('----------------')
print("Mean blacks: ", np.mean(blacks))
print("Std blacks: ", np.std(blacks))
print("N blacks: ", len(blacks))

# <codecell>

print("Median whites: ", np.median(whites))
print("Median blacks: ", np.median(blacks))

# <codecell>

mu = np.mean(whites)
sigma = np.std(whites)
n, bins, patches = P.hist(whites, 20, normed=1, histtype='bar')
P.setp(patches, 'facecolor', 'w', 'alpha', 0.75)
    
# add a line showing the expected distribution
y = P.normpdf( bins, mu, sigma)
l = P.plot(bins, y, 'k--', linewidth=1.5)

mu = np.mean(blacks)
sigma = np.std(blacks)
n, bins, patches = P.hist(blacks, 20, normed=1, histtype='bar')
P.setp(patches, 'facecolor', 'black', 'alpha', 0.75)
    
# add a line showing the expected distribution
y = P.normpdf( bins, mu, sigma)
l = P.plot(bins, y, 'k--', linewidth=1.5)
plt.xlabel('Winning Frequency')
plt.ylabel('Rel frequency')
plt.title('Comparing "Believers and Non-Believers"')
plt.savefig("histogram.pdf")
plt.show()

# <codecell>

# now for statistical testing
[t, prob] = st.ttest_ind(whites, blacks)
    
if prob < 0.01:
    print(' SIGNIFICANT t=', t, ' prob = ', prob)
else:
    print(' insignificant t=', t, ' prob = ', prob)
        

# <codecell>

blacks1338 = np.genfromtxt("blacks1338.csv", dtype=float)
blacks = np.genfromtxt("blacks.csv", dtype=float)

mu = np.mean(blacks1338)
sigma = np.std(blacks1338)
n, bins, patches = P.hist(blacks1338, 20, normed=1, histtype='bar')
P.setp(patches, 'facecolor', 'gray', 'alpha', 0.75)
    
# add a line showing the expected distribution
y = P.normpdf( bins, mu, sigma)
l = P.plot(bins, y, 'k--', linewidth=1.5)

mu = np.mean(blacks)
sigma = np.std(blacks)
n, bins, patches = P.hist(blacks, 20, normed=1, histtype='bar')
P.setp(patches, 'facecolor', 'black', 'alpha', 0.75)
    
# add a line showing the expected distribution
y = P.normpdf( bins, mu, sigma)
l = P.plot(bins, y, 'k--', linewidth=1.5)
plt.xlabel('Winning Frequency')
plt.ylabel('Rel frequency')
plt.title('Comparing "Non-Believers" from random seeds 1337 and 1338')
plt.savefig("histogram2.pdf")
plt.show()

# <codecell>

# now for statistical testing
[t, prob] = st.ttest_ind(blacks1338, blacks)
    
if prob < 0.01:
    print(' SIGNIFICANT t=', t, ' prob = ', prob)
else:
    print(' insignificant t=', t, ' prob = ', prob)
        

# <markdowncell>

# To calculate the test statistic $t$ for independent two sample t-test, use the formula
# $$ t = \frac{\bar {X} - \bar{Y}}{s_{XY} \cdot \sqrt{\frac{1}{N_X}+\frac{1}{N_Y}}} $$ 
# $$s_{XY} = \sqrt{\frac{(N_X-1)s_{X}^2+(N_Y-1)s_{Y}^2}{N_X+N_Y-2}}$$

# <codecell>

# manual computation of Welch's t test
mu1 = np.mean(whites)
mu2 = np.mean(blacks)
var1 = np.var(whites, ddof=1)
var2 = np.var(blacks, ddof=1)
N1 = len(whites)
N2 = len(blacks)

sxy = np.sqrt ( ( (N1 - 1)*var1 + (N2 - 1)*var2 ) / float(N1 + N2 - 2)  ) 
t = np.divide ((mu1-mu2), (sxy * np.sqrt(1./N1 + 1./N2) ))  
df = N1+N2-2
print(t)
print(df)

# <codecell>


# use np.abs to get upper tail
p = st.distributions.t.sf(np.abs(t), df) * 2  
print("Probability of sample outcome by chance: ", p)

alpha = 0.05
if p < alpha:
    print("Significant")
else:
    print("Not signficant")

# <codecell>

from scipy.stats import t

x = np.linspace(t.ppf(0.0001, df), t.ppf(0.9999, df), 100)
plt.plot(x, t.pdf(x, df), color=isseorange, alpha=0.9, label='t pdf')
plt.fill_between(x, t.pdf(x, df), facecolor=isseorange, alpha = 0.4)
plt.xlabel('Probabilty distribution over t values')
plt.legend(loc='best', frameon=False)#
plt.title('Degrees of freedom 298')
plt.savefig('student-t.pdf')

# <codecell>

x = np.linspace(t.ppf(9.92242823716e-161, df), t.ppf(0.999999999, df), 100)
plt.plot(x, t.pdf(x, df), color=isseorange, alpha=0.9, label='t pdf')
plt.fill_between(x, t.pdf(x, df), facecolor=isseorange, alpha = 0.4)
plt.xlabel('Probabilty distribution over t values')
plt.legend(loc='best', frameon=False)
plt.savefig('student-t2.pdf')

# <codecell>

x = np.linspace(t.ppf(0.0001, df), t.ppf(0.9999, df), 100)
ix = np.linspace(t.ppf(0.05, df), t.ppf(0.95, df), 100)
plt.plot(x, t.pdf(x, df), color=isseorange, alpha=0.9, label='t pdf')

plt.fill_between(x, t.pdf(x, df), facecolor=isseorange, alpha = 0.7)

plt.fill_between(ix, t.pdf(ix, df), facecolor='w', alpha = 1)
plt.xlabel('Probabilty distribution over t values')
plt.legend(loc='best', frameon=False)#
plt.title('Degrees of freedom 298')
plt.savefig('student-t-two-tailed.pdf')

# <codecell>

x = np.linspace(t.ppf(0.0001, df), t.ppf(0.9999, df), 100)
ix = np.linspace(t.ppf(0.0001, df), t.ppf(0.90, df), 100)
plt.plot(x, t.pdf(x, df), color=isseorange, alpha=0.9, label='t pdf')

plt.fill_between(x, t.pdf(x, df), facecolor=isseorange, alpha = 0.7)

plt.fill_between(ix, t.pdf(ix, df), facecolor='w', alpha = 1)
plt.xlabel('Probabilty distribution over t values')
plt.legend(loc='best', frameon=False)#
plt.title('Degrees of freedom 298')
plt.savefig('student-t-one-tailed.pdf')

# <codecell>

x = np.linspace(t.ppf(0.0001, df), t.ppf(0.9999, df), 100)
ix = np.linspace(t.ppf(0.10, df), t.ppf(0.9999, df), 100)
plt.plot(x, t.pdf(x, df), color=isseorange, alpha=0.9, label='t pdf')

plt.fill_between(x, t.pdf(x, df), facecolor=isseorange, alpha = 0.7)

plt.fill_between(ix, t.pdf(ix, df), facecolor='w', alpha = 1)
plt.xlabel('Probabilty distribution over t values')
plt.legend(loc='best', frameon=False)#
plt.title('Degrees of freedom 298')
plt.savefig('student-t-one-tailed-lt.pdf')

# <codecell>