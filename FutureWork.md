The future work will involve inter-thread and inter-socket communication (possibly http and web service, see a RESTful web service mockup below) to make workflow DAGs interactive and useful in parallel execution environment.

Another possibility is to create thin client web interface, which can be possilby done in several ways as JGraph team provide some Javascript and Adobe Flex tools to supplement their mature Swing-only library.

## Idea ##

The idea is to make application available to users via web browser. That involves using javascript and html as a presentation layer which can be done using mxGraph javascript library (see StateOfTheArt page for reference).

Although the library hasn't got any Java bindings, it can be used along with Google Web Toolkit by JSNI (Javascript Native Interface).

This idea involves using technologies such as AJAX and push.

## Google Web Toolkit ##

Given the apparent complexity from a desktop developer's point of view, a common approach is to use the Google Web Toolkit (GWT). GWT provides many commonly required web application features by translating Java into Javascript. This Javascript can be deployed just as natively written Javascript is. GWT also provides the option to obfuscate the resulting Javascript.

The key advantages of GWT are:

  * Reuses Java developer knowledge
  * Allows debugger of the Java source
  * Resolves cross-browser issues
  * Native code can be interleaved with Java
  * Certain compile-time errors can be detected before execution

The key disadvantages of GWT are:

  * Commercial support is not available from the developers
  * The higher the level of abstraction, the more difficult it is to implement features that go against the architecture and to debug when things go wrong
  * It's use may deter from learning and understanding Javascript, which is ultimately necessary to implement certain new features

## RESTful web service ##

In order to change state of nodes (for example highlight them as it is implemented now in the project) we need to have a mean of communication. It can be RESTful web service, which means comprising three aspects:

  1. The base URI for the web service, such as http://example.com/resources/
  1. The MIME type of the data supported by the web service. This is often JSON, XML or YAML but can be any other valid MIME type.
  1. The set of operations supported by the web service using HTTP methods (e.g., POST, GET, PUT or DELETE).

## Mockup ##

Let us try to translate REST concepts to match our application behaviour.

  1. Base URI: http://virolab.cyfronet.pl/dagvis
    * A dynamically created experiment DAG URI: http://virolab.cyfronet.pl/dagvis/{experiment-execution-id}, let's name it dag URI
    * A member (graph node) URI: http://virolab.cyfronet.pl/dagvis/{experiment-execution-id}/{node-name}, let's name it node URI
  1. MIME type: it can JSON or YAML. XML is too verbose for this simple usage. YAML is okay as it is already used as an export format of exp2workflow project.
  1. HTTP Semantics:
    * POST on base URI - creates experiment DAG sub-service, returns dag URI
    * GET on dag URI - gets a graph description (node name - URI mapping for example)
    * DELETE on dag URI - deactivates all nodes
    * POST on node URI - activates a single node (or sends other event)
    * DELETE on node URI - deactivates a single node

  * All the HTTP requests are possibly made by the scheduler

See the sequence diagram below:

![http://wiki.dagvis.googlecode.com/hg/pics/rest.png](http://wiki.dagvis.googlecode.com/hg/pics/rest.png)


# Another idea #

If the application proves not to interact with the user, a servlet which returns rendered graph when requested can be developed. A standard servlet container such as Apache Tomcat can be used.