# nsp4j-api

#### Network and Service management Planning framework for Java - API

The framework requires three different input files: one describing the network topology, one with all paths listed and one for the specific parameters. All three files must have exactly the same name with different extensions:\*.dgs for the topology, \*.txt for the paths and \*.yml for the parameters.

### Topology file

The desired network topology is described using the GraphStream guidelines in a file with extension \*.dgs. For instance:

.. code-block:: text

	DGS004
	test 0 0

    an n1 x:100 y:150 capacity:1000 serversNode: 1 type: 0
    an n2 x:150 y:100 capacity:1000 serversNode: 1 type: 0
    an n3 x:200 y:100 capacity:1000 serversNode: 1 type: 0
    an n4 x:175 y:200 capacity:1000 serversNode: 1 type: 0
    an n5 x:250 y:150 capacity:1000 serversNode: 1 type: 0
    an n6 x:300 y:150 capacity:1000 serversNode: 1 type: 0
    an n7 x:350 y:100 capacity:1000 serversNode: 1 type: 0
    an n8 x:350 y:200 capacity:1000 serversNode: 1 type: 0
    an n9 x:400 y:150 capacity:1000 serversNode: 1 type: 0

    ae n1n2 n1 > n2 capacity:1000
    ae n1n4 n1 > n4 capacity:1000
    ae n2n1 n2 > n1 capacity:1000
    ae n2n3 n2 > n3 capacity:1000
    ae n3n2 n3 > n2 capacity:1000
    ae n3n5 n3 > n5 capacity:1000
    ae n3n7 n3 > n7 capacity:1000
    ae n4n1 n4 > n1 capacity:1000
    ae n4n5 n4 > n5 capacity:1000
    ae n5n3 n5 > n3 capacity:1000
    ae n5n4 n5 > n4 capacity:1000
    ae n5n6 n5 > n6 capacity:1000
    ae n6n5 n6 > n5 capacity:1000
    ae n6n7 n6 > n7 capacity:1000
    ae n6n8 n6 > n8 capacity:1000
    ae n7n3 n7 > n3 capacity:1000
    ae n7n6 n7 > n6 capacity:1000
    ae n7n9 n7 > n9 capacity:1000
    ae n8n6 n8 > n6 capacity:1000
    ae n8n9 n8 > n9 capacity:1000
    ae n9n7 n9 > n7 capacity:1000
    ae n9n8 n9 > n8 capacity:1000


``an`` adds a node. The command is followed by a unique node identifier, that can be a single word or a string delimited by the double quote character. Values x and y on the server represent the coordinates of the nodes. For each node can be specified the number of servers (i.e serversNode), the capacity of each server (i.e. capacity) and the type of server.

``ae`` adds an link. This command must be followed by a unique identifier of the link, following with the identifiers of two connecting nodes. For each link, the specific capacity can be specified (i.e. capacity)/

For further information, see [Graphstream](http://graphstream-project.org/doc/Advanced-Concepts/The-DGS-File-Format/) documentation.


### Paths file

The admissible paths for the topology must be specified in a file with the same name as the topology file, but with extension \*.txt. For instance:

.. code-block:: text

	[n1, n2, n3, n7, n9]
	[n1, n4, n5, n3, n7, n9]
	[n1, n4, n5, n6, n7, n9]

This paths can be externally generated or generated using the included KShortest Path Generator tool.

### Parameters file

This file describes the default parameters for the optimization model. The name of the file has to be the same as the name of the topology file, but with extension \*.yml. An example of a parameter file is:


.. code-block:: yaml

    # Optimization parameters
    gap: 0
    weights: [0.5, 0.5]
    
    # Network default parameters
    serverCapacityDefault: 1000
    serversNodeDefault: 1
    linkCapacityDefault: 1000
    minPathsDefault: 3
    maxPathsDefault: 3
    minDemandsDefault: 3
    maxDemandsDefault: 3
    minBwDefault: 100
    maxBwDefault: 100
    
    # service parameters
    serviceChains:
      - id: 0
        chain: [0, 1, 2]
        attributes: {
          "minPaths": 1,
          "maxPaths": 2
        }
    
    functions:
      - type: 0
        attributes: {
            "replicable": false,
            "load": 1.0,
            "delay": 10
        }
      - type: 1
        attributes: {
            "replicable": true,
            "load": 1.0,
            "delay": 10
        }
      - type: 2
        attributes: {
            "replicable": false,
            "load": 1.0,
            "delay": 10
        }
    trafficFlows:
      - src: "n1"
        dst: "n9"
        serviceId: 0
        minDemands: 3
        maxDemands: 3
        minBw: 10
        maxBw: 10
      - src: "n2"
        dst: "n9"
        serviceId: 0
        minDemands: 3
        maxDemands: 3
        minBw: 10
        maxBw: 10
    
    # Auxiliary values
    aux: {
        "overhead": 100,
        "iterations": 1000
    }

Description of the parameters (TBA).