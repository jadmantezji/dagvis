## Description ##

Dagvis is a prototype application that allows you to open and view workflow DAGs produced by Virolab's exp2workflow project. It is developed with execution workflows in mind, although in theory it can read and present any GraphViz file and provide some interactivity. A computer program (the one without infinite loops) is a great example of a DAG, so no matter how complex it is, the execution always follows one of the paths of flow. A concept of visualization of this flow can be imagined, where computation intensive tasks are represented as large vertices and data flow as edges. That leads us to new ideas: monitoring bottlenecks, interactive manual scheduling, or even bringing some real-world concepts into a mathematical creature called a DAG - like annealing, diffusion, mapping weights of edges to network connections based on bandwidth automatically with a help from router infrastructure, state management.

### Important udpate ###

JGraphX - the next version (and rewrite) of JGraph library, which is used in implementation of view layer was released on **20 X 2009**. If a future developer wants to use it, he/she has to reimplement _pl.cyfronet.virolab.dagvis.view.Viewer_ interface. See [DesignOverview](DesignOverview.md) and [DesignDetails](DesignDetails.md) wiki pages.

## Related projects ##

  * [Virolab](http://virolab.cyfronet.pl)
  * [exp2workflow](https://gforge.cyfronet.pl/projects/exp2workflow/)

## Documentation ##

See wiki section.

  * [InstallationAndUsage](InstallationAndUsage.md) - Tutorial on using the application.
  * [Configuration](Configuration.md)
  * [Technologies](Technologies.md)
  * [Requirements](Requirements.md)
  * [DesignOverview](DesignOverview.md)
  * [DesignDetails](DesignDetails.md)
  * [EventAPI](EventAPI.md)
  * [FutureWork](FutureWork.md)
  * [ClassDiagrams](ClassDiagrams.md)
  * [SequentialDiagrams](SequentialDiagrams.md)
  * [UseCase](UseCase.md)
  * [Screenshots](Screenshots.md)
  * [ScreenshotsOld](ScreenshotsOld.md)

## A screenshot ##

![http://wiki.dagvis.googlecode.com/hg/shots/sshot8.png](http://wiki.dagvis.googlecode.com/hg/shots/sshot8.png)