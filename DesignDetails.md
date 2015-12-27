The application is divided into parts (basing on package names):

## pl.cyfronet.virolab.dagvis.structure ##

![http://dagvis.googlecode.com/hg/uml/pics/structure.png](http://dagvis.googlecode.com/hg/uml/pics/structure.png)

Abstract data structures.

### Important classes ###

#### interfaces _IGraph, INode and IEdge_ ####
are meant to be implemented by data structure wrappers of imported DAGs (Directed Acyclic Graphs). They include all the methods that are needed to access and to display a DAG.

#### AbstractGraph ####
is the base abstract class for IGraph implementations. It provides the basic implementation for listener framework. Implemented methods:

  * void addGraphEventListener(GraphEventListener listener)
  * void removeGraphEventListener(GraphEventListener listener)
  * GraphEventListener[.md](.md) getGraphEventListeneres()
  * void deactivateAllNodes()
  * void setNodeState(String nodeName, boolean h)

The remaining methods are abstract.

#### GraphEventListener ####
is a listener interface.

```
/**
 * Listener interface, listens to graph state events.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface GraphEventListener {

	/**
	 * Called when an event occured.
	 * @param e Graph event.
	 */
	void stateChanged(AbstractGraphEvent e);

}
```

See [EventAPI](EventAPI.md) for details.

#### AbstractGraphEvent ####

is the base class for all graph events. See [EventAPI](EventAPI.md) for details.

#### NodeChangedStateEvent ####

is currently the only implemented event. See [EventAPI](EventAPI.md) for details.


## com.alexmerz.graphviz ##

![http://dagvis.googlecode.com/hg/uml/pics/parser.png](http://dagvis.googlecode.com/hg/uml/pics/parser.png)

The sources include adopted [JPGD](http://www.alexander-merz.com/graphviz/) library by Alexander Merz which parses GraphViz DOT files and provides a convenient API for accessing loaded graph. It is based on generated code by JavaCC parser generator.

The changes were made solely to the file http://dagvis.googlecode.com/hg/src/main/javacc/graphviz.jj to fix some parsing bugs.

To see the detailed documentation please follow these links: [documentation](http://www.alexander-merz.com/graphviz/doc.html) and [javadoc](http://www.alexander-merz.com/graphviz/doc/index.html)


## pl.cyfronet.virolab.dagvis.input ##

![http://dagvis.googlecode.com/hg/uml/pics/input.png](http://dagvis.googlecode.com/hg/uml/pics/input.png)

![http://dagvis.googlecode.com/hg/uml/pics/wrappers.png](http://dagvis.googlecode.com/hg/uml/pics/wrappers.png)

Classes that are responsible for loading and converting input data to general data structure (IGraph). Each loader has its own package. There are two:

  * DOT loader based on Alexander's parser (see previous chapter)
  * YAML parser

Additionaly each package has own wrapper subpackage, which consists of implementations of IGraph, INode and IEdge interfaces.

### Important classes ###

#### Transformer ####
Base input-related interface.

```
/**
 * Transformers: more than meets the eye.
 * Interface for input data to abstract data structure transformer.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface Transformer {
	
	/**
	 * Transforms source to IGraph instance.
	 * 
	 * @param source Stream of input data.
	 * @return Abstract Graph representation.
	 * @throws TransformationException When the input contains error. 
	 */
	IGraph getGraph(InputStream source) throws TransformationException;
	
}
```

#### TransformationException ####
Inherits from java.lang.Exception. Thrown when Transformer can't do its job.

#### dot.DOTTransformer, yaml.YAMLTransformer ####
Transformer implementations.

#### dot.wrapper and yaml.wrapper packages ####
They consist of IGraph, INode and IEdge implementations used by appropriate transformer classes.


## pl.cyfronet.virolab.dagvis.view ##

This part of the code is the one that make the experiment DAG actually visible to the user.

Three implementations: JGraph (main, and the only usable), JUNG and JPowergraph (just stubs, although there are some transformers implemented for JUNG).

### Important classes ###

#### Viewer ####
Basic interface for GUI.

```
/**
 * Interface for a graph viewer, which displays given graph to the user.
 * 
 * @author Krzysztof Nirski
 *
 */
public interface Viewer {
	
	/**
	 * Prepares a graph that user wants to display. 
	 * 
	 * @param in Input graph.
	 */
	void setGraph(IGraph in);
	
	/**
	 * Gets (possibly modified by user interaction) the graph.
	 * 
	 * @return The graph.
	 */
	IGraph getGraph();
	
	/**
	 * Displays the graph to the user.
	 */
	void view();
	
}
```

All of the implementations use Swing which is available in Java standard library that comes along with Java SDK and JRE.


## pl.cyfronet.virolab.dagvis.view.jgraph ##

![http://dagvis.googlecode.com/hg/uml/pics/jgraph.png](http://dagvis.googlecode.com/hg/uml/pics/jgraph.png)

The primary viewer implementation. Uses JGraph library (see StateOfTheArt).

### Important classes ###

#### JGraphViewer ####
Viewer interface implementation, intantiates JGraphViewerFrame on _view_ call.

#### JGraphViewerFrame ####
Extends javax.swing.JFrame. Shows the main window of GUI.

#### CustomGraphConstants ####
Extends **org.jgraph.graph.GraphConstants**, the class which is used to set attributes for graph objects. Some custom attributes setters are added. This is a very important class, for details see [JGraph manual](http://www.jgraph.com/pub/jgraphmanual.pdf).

#### GraphTreeModel ####
Implementation of _javax.swing.tree.TreeModel_ used by **javax.swing.JTree**. It is a proxy class which is limited to handle only job nodes.

#### MouseHandler ####
Implementation of _java.awt.event.MouseWheelListener_. Allows to zoom displayed graph with mouse wheel.

#### ViewFactory ####
Implementation of _org.jgraph.graph.CellViewFactory_. Used to instantiate custom-shaped nodes.

#### BoxVertexView, EllipseVertexView, TriangleVertexView ####
Classes that extend **org.jgraph.graph.VertexView**. They provide custom node shapes.


## pl.cyfronet.virolab.dagvis.view.jung ##

![http://dagvis.googlecode.com/hg/uml/pics/jung.png](http://dagvis.googlecode.com/hg/uml/pics/jung.png)

This is the framework implementation that uses JUNG library (see StateOfTheArt). Not usable, although proposes the way to use the library.

Additionally, the package **transformers** includes some visual transformers (shape, paint, node label).

Please refer to documentation [here](http://www.grotto-networking.com/JUNG/JUNG2-Tutorial.pdf), [here](http://sourceforge.net/apps/trac/jung/wiki/JUNGManual) and [javadoc](http://jung.sourceforge.net/doc/api/index.html) for details.

## pl.cyfronet.virolab.dagvis.view.jpowergraph ##

![http://dagvis.googlecode.com/hg/uml/pics/jpowergraph.png](http://dagvis.googlecode.com/hg/uml/pics/jpowergraph.png)

This is the framework implementation that uses JPowergraph library (see StateOfTheArt). Not usable, although proposes the way to use the library.

## pl.cyfronet.virolab.dagvis.util ##

![http://dagvis.googlecode.com/hg/uml/pics/util.png](http://dagvis.googlecode.com/hg/uml/pics/util.png)

Utility classes. Mainly enums used by views. StringUtil class with helper methods for strings.

### Enums ###

  * **ArrowStyle** - edge's arrow shape
  * **LinePattern** - edge's line pattern
  * **Shape** - node's shape

### Concrete classes ###

#### StringUtil ####

Various static methods for string manipulation and measurment (handy in viewer implementations).

#### CustomColour ####

Class that adds some custom colour definitions and has utility method to parse strings containing colour name.