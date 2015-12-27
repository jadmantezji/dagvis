These files can be found in src/main/resources directory in the code repository or in root directory in distribution jar.

## log4j.properties ##

A standard log4j properties file. See http://logging.apache.org/log4j/1.2/manual.html.

## usage.txt ##

A help message displayed when no command line arguments are provided.

## node-types.properties ##

Used when input data lacks presentation description, in particular for YAML files.

Properties to set:

  * {node\_class\_name}.shape - a shape of node. Valid values: circle, ellipse, triangle, box. Default: ellipse.
  * {node\_class\_name}.contour\_count - thickness of node frame. Valid values: integers >= 1. Default: 1.

Node classes provided by exp2workflow: if, loop, p\_loop.

## edge-types.properties ##

Analogous to above.

Properties to set:

  * {edge\_class\_name}.line\_pattern - edge line style. Valid values: continuous, dashed, dotted. Default: continuous.

Edge classes provided by exp2workflow: exit, if\_or\_loop.