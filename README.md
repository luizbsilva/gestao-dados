Desafio
1. Passos para começar
Clonando o Repositório
git clone https://github.com/luizbsilva/gestao-dados.git

Instalando as Dependências
Desntro da pasta do projeto
cd gestao-dados

Caso o sistema Operacional seja Windows abrir o PowerShel
mvn install

Caso o sistema Operacional seja alguma versão Linux abrir o terminal
mvn install

2. Criando Banco de Dados
Criar o banco de Dados no postgresql com o nome gestao_dados
3. Criando arquivo aplication.properties
Alterar o nome do aquivo application.properties.exemplo para application.properties
###Alterar os campos para conexao com o banco ###{{USUARIO_POSTAGRES}} = usuario do banco no qual a aplicação vai conectar ###{{SENHA_USUARIO_POSTGRES}} = senha do usuario do banco no qual a aplicação vai conectar

Neste mesmo projeto já se encontra o projeto front-end
apos estar na pasta do projeto navegar ate cd src/main/angular/

Instalando as Dependências
npm install

Inicializando o Servidor
ng serve
