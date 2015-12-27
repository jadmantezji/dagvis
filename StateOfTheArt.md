Java graph libraries that are available on the market are:

## [JGraph](http://jgraph.com) ##

JGraph is a powerful, feature-rich, and well documented open-source (BSD license since 25th September 2009) graph component available for Java. With the JGraph zoomable component, you can display objects and relations (networks) in any Swing UI. JGraph provides a range of graph drawing functionality for client-side or server-side applications. JGraph has a simple, yet powerful API enabling you to visualize, interact with, automatically layout and perform analysis of graphs. The following sections define these terms in more detail.

Example applications for a graph visualization library include; process diagrams, workflow and BPM visualization, flowcharts, traffic or water flow, database and WWW visualization, networks and telecoms displays, mapping applications and GIS, UML diagrams, electronic circuits, VLSI, CAD, financial and social networks, data mining, biochemistry, ecological cycles, entity and cause-effect relationships and organisational charts.

JGraph, through it's programming API, provides the means to configure how the graph or network is displayed and the means to associate a context or metadata with those displayed elements. User objects can stored on nodes and edges (in graph model), so it is easy to retrieve user-defined attributes.

This is the main library which project dagvis uses. A very good integration with Swing is an advantage. Uses MVC design pattern to store and display a graph. There is a 136 page tutorial on how to use it, thoroughly describining all the important matters.

The API (especially abstract hierarchy of graph elements and updating view/model) can be a little twisted at the beginning, but it does make sense in the end, especially when you consider undo mechanism. It is possible to implement custom layouts, node views. There is only a box view as a default, dagvis implements ellipse, circle and triangle views.

From the layouts point of view, there is a fantastic hierarchical algorithm implemented, which can help present a workflow in the usable way (simmilar to graphviz).

Image saving, XML persistence, SVG export and printing features are present as well as drag&drop and zooming (which is used by dagvis).

## [JUNG](http://jung.sourceforge.net) ##

Java Universal Network/Graph Framework is a software library that provides a common and extendible language for the modeling, analysis, and visualization of data that can be represented as a graph or network. The JUNG architecture is designed to support a variety of representations of entities and their relations, such as directed and undirected graphs, multi-modal graphs, graphs with parallel edges, and hypergraphs. It provides a mechanism for annotating graphs, entities, and relations with metadata. The current distribution of JUNG includes implementations of a number of algorithms from graph theory, data mining, and social network analysis, such as routines for clustering, decomposition, optimization, random graph generation, statistical analysis, and calculation of network distances, flows, and importance measures (centrality, PageRank, HITS, etc.).

A bit immature on the visualization side, although a very good general-purpose graph manipulation library. Layout algorithms are not good enough to present a complex graph in a clear manner. Uses the idea of transformers to present a graph - you can apply a transformer to node or edge to change its appearance. The project is led by a Google employee ans is open source.

Not that good documentation.

## [JPowerGraph](http://sourceforge.net/projects/jpowergraph) ##

JPowerGraph is a Java library for creating directed graphs for both Swing and SWT. It supports graph movement, selection, context menus, tooltips and dynamic edge creation.

Unfortunately the project seems to be dead for quite a while. The last version (0.2) was released on 7th August 2007 and there is no homepage or developer activity since then. However, there is SWT version of library which enables eclipse development (plugins, RCP applications).

No documentation available. It seems that there is no home page other than sourceforge project page.

## [GINY](http://csbi.sourceforge.net) ##

A graph visualization library focused on graph operations. The public API of GINY defines only interfaces.  This is intentional so that a new implementation can be used seamlessly.  One of the main focuses of this project is to make a "headless" mode available for graphing projects, and be able to move effortlessly to a visual mode.

The back-end of the package is currently using libraries from the CERN Colt project. Colt provides a high-quality, fast, set of data structures that seem to be missing from the regular Java libraries.

On the visualization side [Piccolo](http://code.google.com/p/piccolo2d/) is used. It provides tools to create ZUI (zoomable user interface), which seems to be a different paradigm as far as user interface is concerned.

Not too many layout algorithms implemented, especially a hierarchical algorithm (which was mentioned in JGraph description).

Architecture:

![http://wiki.dagvis.googlecode.com/hg/pics/giny.jpg](http://wiki.dagvis.googlecode.com/hg/pics/giny.jpg)

## [mxGraph](http://www.jgraph.com/mxgraph.html) ##

mxGraph is a Javascript library that uses built-in browser capabilities to provide an interactive drawing and diagramming solution. mxGraph outperforms all existing solutions in startup time, interactivity and functionality.

The library is developed by the same team that is responsible for JGraph. Unlike JGraph, it's double-licensed: there is evaluation version for non-commercial use and the commercial one.

Feeature wise, the library aims to be simmilar to JGraph.

[Demo](http://www.mxgraph.com/demo/mxgraph/editors/grapheditor.html)

## [ZGRViewer](http://zvtm.sourceforge.net/zgrviewer.html) ##

ZGRViewer is a 2.5D graph visualizer implemented in Java and based upon the Zoomable Visual Transformation Machine by Xerox. It is specifically aimed at displaying graphs expressed using the DOT language from AT&T GraphViz and processed by programs dot, neato or others such as twopi.

ZGRViewer is designed to handle large graphs, and offers a zoomable user interface (ZUI), which enables smooth zooming and easy navigation in the visualized structure.

This project can be considered because of its support for DOT language (to replace current implementation). However, it is a standalone application rather than a library. Although it has plugin-enabled architecture, it can involve excessive hacking in order to highlight particular nodes which is one of the main tasks of the project. It is because it doesn't expose the graph instance in runtime (it simply passes SVG data to viewing library - ZVTM).


## References and additional libraries descriptions ##

  * http://networkviz.sourceforge.net/
  * http://www.manageability.org/blog/stuff/open-source-graph-network-visualization-in-java/view