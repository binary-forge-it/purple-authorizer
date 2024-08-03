# Challenge description

- Company: Nubank
- Role: Junior Software Engineer
- Date: 26/04/2016

[pt-BR]

Neste desafio, vamos supor que estamos procurando nos envolver em análises de redes sociais para clientes em potencial. Nosso objetivo é extrair uma métrica conhecida como "centralidade de proximidade" de sua rede social.

As métricas de centralidade visam aproximar o nível de influência de um indivíduo em uma rede social. A distância entre quaisquer dois vértices é seu caminho mais curto. Um determinado vértice (v) tem uma *distancia*. A soma de todas as distâncias de cada vértice para *v* é o resultado. Finalmente, a *proximidade* de um vértice, *v*, é o inverso de *distancia*.

A primeira parte do desafio envolve classificar os vértices dentro de um determinado gráfico não direcionado por sua *proximidade*. O arquivo em anexo fornece o gráfico, com dois nomes de vértice em cada linha. Um único espaço separa os dois, representando uma aresta entre os nós.

A segunda parte do desafio é criar um servidor web RESTful. Registramos arestas com pontos finais e criamos uma classificação de vértices classificados por centralidade. Podemos pensar no valor de centralidade de um nó. Isso serve como uma "pontuação" inicial para esse cliente.

A terceira e última parte é adicionar outro ponto final para sinalizar um nó de cliente como "fraudulento". O sistema deve recuperar um ID de vértice e atualizar a pontuação interna do cliente da seguinte forma: A pontuação do cliente fraudulento deve ser zero. Parentes diretos do cliente "fraudulento" devem ver sua pontuação reduzida pela metade. Geralmente, a empresa se refere indiretamente às pontuações de seus clientes. Devemos multiplicar o cliente "fraudulento" por um coeficiente F.

F(k) = (1 - (1/2) k)

A variável k representa o comprimento do caminho mais curto do cliente "fraudulento" para o cliente em questão.

Você deve fornecer um repositório git, ou um link para um repositório privado compartilhado no github, Bitbucket ou algo semelhante, com seu código e um pequeno arquivo README descrevendo a solução e explicando como construir e executar o código. Você deve enviar seu código em uma linguagem de programação funcional, como Clojure, Common Lisp, Scheme, Haskell, ML, F# ou Scala. Em seguida, analisaremos a estrutura e a legibilidade da base de código. Esperamos código de nível de produção. Não há problema em usar bibliotecas para testes ou interação de rede, mas evite usar uma biblioteca que já implemente os algoritmos de gráfico ou rede social principais.

Não tenha medo de fazer perguntas sempre que encontrar um problema. Além disso, entre em contato a qualquer momento se achar que o prazo não é realista.

Referências:
- Closeness Centrality: [http://en.wikipedia.org/wiki/Centrality#Closeness_...] (http://en.wikipedia.org/wiki/Centrality#Closeness_centrality)   
- Shortest path: [http://en.wikipedia.org/wiki/Shortest_path_problem] (http://en.wikipedia.org/wiki/Shortest_path_problem)

[en]

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