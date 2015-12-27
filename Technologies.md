Dagvis tool is being developed in Java language. In addition to that, external open source libraries and tools are used:

  * [Apache Maven](http://maven.apache.org/): project build tool and dependency manager
  * [Mercurial](http://mercurial.selenic.com/wiki/): distributed version control system written in Python
  * [log4j](http://logging.apache.org/log4j/1.2/index.html): Java logging library
  * [JavaCC](https://javacc.dev.java.net/): Java Compiler Compiler, a parser generator
  * [SnakeYAML](http://code.google.com/p/snakeyaml/): YAML parser
  * [JGraph](http://www.jgraph.com/): a graph library with strong accent on visualization, see StateOfTheArt
  * [JUNG](http://jung.sourceforge.net/) (framework, as an alternative visualization): great graph library, but still not mature, see StateOfTheArt
  * [JPowerGraph](http://sourceforge.net/projects/jpowergraph) (framework, as an alternative visualization): alternative graph library, see StateOfTheArt
  * Graphviz DOT language specification: for building a parser, link: http://www.graphviz.org/Documentation/dotguide.pdf

Project is designed to be as much independent on external components as possible, you can easily adapt other ones if you wish. For alternatives, see StateOfTheArt page.

The sources include adopted [JPGD](http://www.alexander-merz.com/graphviz/) library by Alexander Merz which parses GraphViz DOT files and provides a convenient API for accessing loaded graph. Although it is a great piece of software, a few bugfixes were and will be needed.

Another project seems to have DOT parser implementation (ANTLR v2 based), ZGRViewer. See StateOfTheArt.