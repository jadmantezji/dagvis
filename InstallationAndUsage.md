## Installation ##

No need to install!

In the Downloads section there is a JAR file with all dependencies included, so executing application is as easy as typing

```
java -jar <jarfile> <graphfile>
```

in the command line. Preferred is the YAML format of graph, although DOT is also supported.

Alternatively, you can checkout source code from Mercurial repository and then use Apache Maven (> 2.0) to build project. Unfortunately, you will have to download some jars manually, as in main maven repository either they are absent or old.

You can also download example files to test the application: testfiles.tar.gz.

## Usage ##

This is pretty straightforward. You can use menu to load another file, change layout, set active node or exit the application. On the panel of the left there is graph outline navigator which allows to have a general overview of graph and jump to the part of interest when graph is too complex to fit on the main panel.

Below navigator, there is a list of jobs (nodes which represent actual computation done by GS engine).

It is possible to rearrange nodes in more convenient way by clicking on nodes and dragging them.

Zoom is controlled my mouse wheel.

## User interface ##

![http://wiki.dagvis.googlecode.com/hg/shots/sshot8.png](http://wiki.dagvis.googlecode.com/hg/shots/sshot8.png)

  * Menu is in the top
  * Main panel is on the right in the center (takes majority of window space)
  * Secondary panel is on the left and consists of navigator subpanel and list of jobs