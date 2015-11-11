# VoronoiDiagram

This code is an JAVA implementation of Fortune's Algorithm to compute voronoi diagram 2D. 

Features:
===
This code can output voronoi edges as ArrayList. each edges has built in functions that allows:
1. get points that share this edge.<br >
2. get direction of this edge.<br>
<br>
There is an function to compute polygon for each input point.<br>
![alt tag](https://github.com/wuga214/VoronoiDiagram/blob/master/code/figure.png)

How to use:
===
Please see example in src/Main.java<br>

Glitch:
===
This code has a glitch that can cause voronoi graph draw some unreasonable lines for data points that share similary y-axis values.<br>
But for small data set (less than 1000 points), it is very unlikely happen.<br>
If you can fix it, please contact me.<br>

About Fortune's Algorithm:
===
Tutorial Slides [here](http://www.cs.sfu.ca/~binay/813.2011/Fortune.pdf)<br>
Someone's Blog [here](http://blog.ivank.net/fortunes-algorithm-and-implementation.html)<br>
