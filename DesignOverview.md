## Overview ##

The big picture of architecture:
  * The entry class is DagViewer.
  * There is IGraph interface - a basic data structure, which has two implementations.
  * Transformer interface basically parses the input and converts it into IGraph. Again, two implementations (DOT, YAML).
  * Viewer interface, which is responsible for converting general IGraph structure to implementation-specific structure of chosen graph library and displaying it to the user.

![http://dagvis.googlecode.com/hg/uml/pics/main.png](http://dagvis.googlecode.com/hg/uml/pics/main.png)

![http://dagvis.googlecode.com/hg/uml/pics/mainseq.png](http://dagvis.googlecode.com/hg/uml/pics/mainseq.png)