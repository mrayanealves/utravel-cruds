# uTravel
Projeto final de Banco de Dados

#### Autores

1. Carlos Eduardo (carloseduardofox8@gmail.com)
2. Gabriel Estevam (gabriel.estevam.narciso@gmail.com)
3. Maria Rayane Alves (mrayalves05@gmail.com)

#### Tecnologias

1. Java
2. Spring
3. MySQL
4. JDBC Template

#### Informações gerais

O **uTravel** é uma plataforma para os registros dos planejamentos das viagens. 
A ideia geral é oferecer aos usuários uma **forma dinâmica de anotar o andamento da viagem, desde a concepção inicial dela, 
o momento em que foi decidido que a viagem seria feita, até o momento final**; funcionando, ademais, como uma rede social 
de viagens com a qual seus usuários podem se inspirar nas experiências de outras pessoas, compartilhar informações 
e as suas próprias experiências.

Nesse repositório, porém, temos apenas uma versão bem básica da concepção inicial do modelo e a implementação dos CRUDS do
sistema. Como a disciplina era de Banco de Dados, tivemos uma restrição na tecnologia de acesso ao banco pela aplicação exclusiva
a JDBC Templete. 


#### Arquitetura do projeto

A organização da arquitetura do projeto foi pensada em uma arquitetura em camadas com os seguintes componentes:

* `Model`: contém as classes de domínio do projeto;

* `Repository`: classes de repositório que manipulam as modificações dos registros das entidades no banco de dados; 

* `Service`: classes que manipulam as regras de negócio do projeto;

* `Controller`: classes de controle que recebe as requisições, enviam para processamento nos services e retornam as 
respectivas respostas.

Esse projeto é uma API. Assim, a interface de visualização, ou documentação nesse caso, foi feita com a inclusão do Swagger. 

#### Executando o projeto

Para executar o projeto, primeiramente clone esse repositório em sua máquina.

Posteriormente, abra o repositório na sua IDE de preferência. 
Nós desenvolvedores desse projeto preferimos O **Intellij IDEA**, mas você pode usar a que você achar melhor de acordo 
com sua experiência.

Em seguida, crie um banco de dados MySQL chamado `utravel` e execute a query de criação das tabelas no schema que se 
encontra em `src/main/resources/database` chamada `utravel.sql`.

Depois, abra o arquivo `application.properties` em `src/main/resources` e configure as informações de conexão com o 
banco de dados de acordo com a seguinte descrição:

> spring.datasource.url=jdbc:mysql://[Host do seu banco]:3306/utravel  
> spring.datasource.username=[Usuário do seu banco]  
> spring.datasource.password=[Senha do seu banco]

Feito isso, execute o arquivo `UtravelApplication` como um projeto java e pronto!

Acesse o link **localhost:8080/swagger-ui.html#/**  no seu navegador é isso.