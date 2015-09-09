#!/usr/bin/env python

import matplotlib.pyplot as plt
import csv
import math

def plot(xs,ys):
    plt.plot(xs, ys, 'ro')
    plt.axis([0, 2500, 0, 2500])
    plt.show()

def semi_osemka():
    X_SCALE = 158.6
    Y_SCALE = 228.0
    X_MAP_SIZE = 2280
    Y_MAP_SIZE = 2380
    A = 400.0
    B = 1.0/267.5
    X = 1150.0
    X_start = 300
    X_end = X_MAP_SIZE - X_start
    Xs = range(X_start, X_end, 50)
    Xs = [y*1.0 for y in Xs]
    points = []
    points += [(x, A*math.sin(B*x+200) + 1000.0 ) for x in Xs]
    points += [(x, -A*math.sin(B*x+200) + 1000.0) for x in Xs[::-1]]
    xs = [x for x,_ in points]
    ys = [y for _,y in points]
    with open('osemka.csv', 'wa') as csvfile:
        spamwriter = csv.writer(csvfile, quoting=csv.QUOTE_MINIMAL)
        for i in range(0,68):
            spamwriter.writerow([ys[i]*1.0, xs[i]*1.0, 90.0])
    #plot(xs,ys)

def bezier():
    x = P(300, 1000, 1400, 1800)
    y = P(600, 2500, 100,  1800)
    xs =[]
    ys=[]
    for t in range(1, 101, 2):
        xs.append(x(t*0.01))
        ys.append(y(t*0.01))
    print len(xs), len(ys)
    with open('bezier.csv', 'wa') as csvfile:
        spamwriter = csv.writer(csvfile, quoting=csv.QUOTE_MINIMAL)
        for i in range(0,50):
            spamwriter.writerow([ys[i]*1.0, xs[i]*1.0, 90.0])
    #plot(xs,ys)

def P(A, B, C, D):
    def xy(t):
        return A*math.pow(1-t,3)+3*B*t*math.pow(1-t,2) + 3*C*t*t*(1-t)+D*t*t*t
    return xy


if __name__ == '__main__':
    semi_osemka()
    bezier()
