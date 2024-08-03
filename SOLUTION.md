# Purple Graph Server

```bash
Fellipe Souto Sampaio
f.souto@outlook.com
(Github)[https://github.com/fllsouto]
```

### Sobre a solução
O desafio foi escrito utilizando a linguagem multiparadigma Scala. Na confecção do servidor RESTful foi utilizado o Scalatra, framework baseado no Sinatra. Foi criado um cliente utilizando nodeJS para facilitar os testes da API.

O problema central consistia em achar todas a combinações de distâncias mínimas em um grafo não dirigido e não valorado. A representação escolhida para o grafo foi uma matriz NxN, onde N é o número de vértices. Isso pode ocupar um tamanho proporcional a N^2 de espaço em memória.

Para resolução do problema implementei o algoritmo [Floyd-Warshall](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm), ele tem complexidade O(N^3) no pior dos casos, por ter três for's aninhados. Entretando por ser um grafo não dirigido e poder considerar que as areastas tem peso 1 as distâncias d(A,B) == d(B,A). Isso diminui o número de operações necessárias, fazendo com que apenas uma parte da matriz de adjacencia tenha que ser operada, a outra parte tem seu resultado refletido. Realizando vários testes verifiquei que na média preciso de (n^2)(n - 1)/2 operações para calcular todos os caminhos mínimos no grafo. 

Pegando como exemplo a entrada fornecida por vocês, que tem um **N = 100**, isso resulta em 495 000 iterações, bem menor das 1 000 000 iterações da implementação sem otimizações. Porém como qualquer algoritmo cúbico, para um N muito grande essa diferença torna-se insignificante e o desempenho degrada-se.

A escolha desse algoritmo se deu por ser um dos melhores para se calcular todas as distâncias mínimas de um grafo, de maneira simples e com uma boa eficiência.

Escreve testes unitários para a classe **Vertex.scala** e **Graph.scala**, onde tentei cobrir todos os casos possíveis de uso.

O cálculo da proximidade e do distânciamento de um vértice sai de forma natural ao usar uma matrix de adjacencia como estrutura de dados. Além disso facilitou o cálculo dos nós fraudolentos, sendo necessário apenas pegar a linha pertencente ao tal vértice, aplicar a formula e mapear nos respectivos scores dos vértices.

### Como instalar/compilar?

```bash
# clonar repositório ou descompactar arquivo
cd purple-graph-node

# Versao do node indicada >= v0.10.37
# Versao do npm indicado >= 1.4.28
# Esse comando irá instalar as dependências do NodeJs usados no cliente
npm install

cd ../purple-graph-server

#Versao do java indicado >= 1.7.0_80
#Versao do javac indicado >= 1.7.0_80

# Esse comando irá baixar a versão utilizada do Scala e suas respectivas dependências (pode demorar alguns minutos)
./sbt
```

### Como executar?

```bash
# Ao terminal o download das depedências do sbt feche execute novamente
./sbt

# Dentro do terminal do sbt execute (pode ter que baixar mais alguma dependência, espere mais um pouco)
jetty:start

# Isso irá subir um servidor jetty Scalatra local na porta 8080

# É possível testar o servidor utilizando o 'curl', mas com o cliente node a montagem dos request fica muito mais simples

cd purple-graph-node
node index.js <host> <porta> <input-file> <action>

#Cada action do cliente node está mapeada para um endpoint do servidor Scalatra
#
# Cria um novo grafo, dado a entrada fornecida
# new -> POST /purplegraph/api/rank/
#
# Insere nós fraudulentos, dado a entrada fornecida
# fraud -> POST /purplegraph/api/fraud/
#
# Reseta as configurações do sistema para receber uma nova entrada
# reset -> POST /purplegraph/api/rank/reset
#
# Mostra o ranking de proximidade, mostrando o identificador do vértice e seu score 
# shot -> GET /purplegraph/api/rank/
```