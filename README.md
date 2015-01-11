ENGR0712
========

To compile, in a given lab subdirectory:

    javac *.java

To view the demonstration:

    appletviewer Lab<#>.html

As a student at the University of Pittsburgh, I took a class called
"Introduction to High Performance Computing and Modeling,"
though the class was basically an open-ended exploration into the
computational implementation of physical and scientific models.

The professor described some physical process, and we were told to
model it. We could use whichever language we preferred as long as
figures show the model in action. After taking this class,
I was invited to research with her that summer.

Topics modeled:

- [Diffusion Limited Aggregation](http://en.wikipedia.org/wiki/Diffusion-limited_aggregation)
- [Percolation Theory](http://en.wikipedia.org/wiki/Percolation_theory)
- [Ising Model](http://en.wikipedia.org/wiki/Ising_model)
- Random Walks ([self-avoiding](http://en.wikipedia.org/wiki/Self-avoiding_walk) and [non-self-avoiding](http://en.wikipedia.org/wiki/Random_walk))
- [Metropolis Algorithm](http://en.wikipedia.org/wiki/Metropolis%E2%80%93Hastings_algorithm)
for modeling phase separation of oil, water and soap
- [Conway's Game of Life](http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

Implementations are given in Java applets.

Java applets do not work when embedded in a browser, as they
did years ago (see [link](https://weblogs.java.net/blog/cayhorstmann/archive/2014/01/16/still-using-applets-sign-them-or-else)).
They do work when run with [`appletviewer`](http://docs.oracle.com/javase/8/docs/technotes/tools/windows/appletviewer.html).

These demonstrations should see a reimplementation in Javascript for
easier viewing.
