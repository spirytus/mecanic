#!/usr/bin/env python

import matplotlib.pyplot as plt
import csv
import math

X_SCALE = 158.6
Y_SCALE = 228.0

X_MAP_SIZE = 2280
Y_MAP_SIZE = 2380

#x = [5.0, 6.0, 6.6, 7.0, 7.3, 7.0, 5.0, 4.0,3.4,3.0,2.7,3.0,5.0,6.0,6.6,7.0,7.3,7.0,5.0,4.0,3.4,3.0,2.7,3.0]
#y = [2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5]
#xs = [x1*X_SCALE for x1 in x]
#ys = [y1*Y_SCALE for y1 in y]

A = 400.0
B = 1.0/300.0

X = 1150.0
X_start = 300
X_end = X_MAP_SIZE - X_start

Xs = range(X_start, X_end, 50)
Xs = [y*1.0 for y in Xs]

points = []
points += [(x, A*math.sin(B*x+50.) + 1000.0 ) for x in Xs]
points += [(x, -A*math.sin(B*x+50.) + 1000.0) for x in Xs[::-1]]

print points

xs = [x for x,_ in points]
ys = [y for _,y in points]

with open('track.csv', 'wa') as csvfile:
    spamwriter = csv.writer(csvfile, quoting=csv.QUOTE_MINIMAL)
    for i in range(0,68):
        spamwriter.writerow([ys[i]*1.0, xs[i]*1.0, 90.0])

plt.plot(xs, ys, 'ro')
plt.axis([0, 2500, 0, 2500])
plt.show()
