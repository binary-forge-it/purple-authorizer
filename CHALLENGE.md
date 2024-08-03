# Challenge description

In this challenge, let's assume we are looking to engage in social networking analysis for prospective customers. We aim to extract a metric known as "closeness centrality" from their social network.

Centrality metrics aim to approximate an individual's level of influence within a social network. The distance between any two vertices is their shortest path. A given vertex (v) has a *farness*. The sum of all distances from each vertex to *v* is the result. Finally, a vertex's *closeness*, *v*, is the inverse of *farness*.
      
The first part of the challenge involves ranking the vertices within a given undirected graph by their *closeness*. The attached file provides the graph, with two vertex names on each line. A single space separates the two, representing an edge between the nodes. 

The second part of the challenge is to create a RESTful web server. We register edges with endpoints and create a ranking of vertexes sorted by centrality. We can think of a node's centrality value. This serves as an initial "score" for that customer. 

The third and final part is to add another endpoint for flagging a customer node as "fraudulent." The system should retrieve a vertex ID and update the internal customer score as follows: The fraudulent customer score should be zero. Direct relatives of the "fraudulent" customer should see their score halved. Generally, the company indirectly refers to the scores of its customers. We should multiply the "fraudulent" customer by a coefficient F. 

F(k) = (1 - (1/2) k)  
      
The variable k represents the length of the shortest path from the "fraudulent" customer to the customer in question.  
      
You should provide a git repository, or a link to a shared private repository on github, Bitbucket, or something similar, with your code and a short README file outlining the solution and explaining how to build and run the code. You should submit your code in a functional programming language, such as Clojure, Common Lisp, Scheme, Haskell, ML, F#, or Scala. We will then analyze the structure and readability of the codebase. We expect production-grade code. There is no problem with using libraries for testing or network interaction, but please avoid using a library that already implements the core graph or social network algorithms.  
      
Don't shy away from asking questions whenever you encounter a problem. Also, please do get in touch at any moment if you believe the timeframe is unrealistic.   
      
References:   
- Closeness Centrality: [http://en.wikipedia.org/wiki/Centrality#Closeness_...] (http://en.wikipedia.org/wiki/Centrality#Closeness_centrality)   
- Shortest path: [http://en.wikipedia.org/wiki/Shortest_path_problem] (http://en.wikipedia.org/wiki/Shortest_path_problem)