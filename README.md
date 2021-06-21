# Spring-Cloud-Examples

Repositório com todo um ecossistema em Spring Cloud

## Folder-Config

Pasta com todos os arquivos de propriedades usado pelo "Config-Server"

## Config-Server

Projeto que disponibiliza configurações para os projetos spring e usa como pasta base a pasta "Folder-Config"

## Eureka

Projeto de Discoberta de projetos.

## pessoasApi

Projeto simples CRUD de Pessoas

## usuariosApi

Projeto simples CRUD de Usuarios que faz busca no "pessoasApi" para trazer o usuario com pessoa

## zuul-gateway

Projeto de api Gateway e trabalha como um proxy que busca projetos através do projeto "Eureka"



