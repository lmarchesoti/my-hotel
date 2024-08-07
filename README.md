# desafio-senior-backend

Projeto desenvolvido como desafio técnico.

Para rodar o projeto, basta utilizar o comando `docker-compose up --build -d` na raiz do projeto.

## Endpoints

### Hospedes

#### Listagem de Hóspedes

`curl --location 'http://localhost:5000/hospedes/'`

#### Busca de Hóspedes (por id)

`curl --location 'http://localhost:5000/hospedes/:idHospede'`

#### Criação de Hóspedes

```
curl --location 'http://localhost:5000/hospedes/' \
--header 'Content-Type: application/json' \
--data '{
"nome": "jkl",
"documento": "7777777",
"telefone": "(34) 8888888888"
}'
```

#### Atualização de Hóspedes

```
curl --location --request PUT 'http://localhost:5000/hospedes/:idHospede' \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Lucas M",
    "documento": "666666666",
    "telefone": "(34) 555555555"
}'
```

#### Exclusão de Hóspedes

`curl --location --request DELETE 'http://localhost:5000/hospedes/:idHospede'`

### CheckIn

#### Criação de CheckIn

```
curl --location 'http://localhost:5000/checkin?hospedeId=:idHospede' \
--header 'Content-Type: application/json' \
--data '{
    "dataEntrada": "2024-06-01T10:50:00",
    "dataSaida": null,
    "adicionalVeiculo": false
}'
```

#### Busca de Cobrança de CheckIn por Hóspedes

`curl --location 'http://localhost:5000/checkin/billing-by-customer?emHospedagem=:emHospedagem'`

## Observações

1. Ao subir o projeto, ele popula uma tabela de configuração com os valores de diárias, adicionais de garagem e
   horário limite para a cobrança de uma diária adicional no checkout.

2. Sobre o endpoint das consultas, eu entendi que seriam 2 endpoints distintos, um para listagem de clientes, e 
   outro para listagem dos clientes com cálculo de valor gasto, incluindo também os filtros de cliente presente ou 
   já ausente do hotel. Fazer a junção ou ajuste dessa interface dos endpoints seria simples depois da lógica desenvolvida.

3. Ainda sobre o endpoint de consultas, fiquei entre 2 possibilidades para este desenvolvimento, e normalmente eu
   traria como pontos de discussão para a equipe, pois envolve tradeoffs de performance e usabilidade.

    3.1 Na primeira abordagem (que é a presente no código atualmente, porém não finalizada) eu busco a lista de clientes
        com paginação e depois disso calculo os valores gastos no java, aplicando as regras de data.

    3.2 Na segunda abordagem, utilizaria uma query nativa para ter acesso às funções de data do sql e passaria os
        valores praticados como parâmetros na consulta. Essa abordagem tem o benefício de permitir a ordenação da
        query pelos valores mantendo a paginação, porém o reuso do código é reduzido pela criação de código nativo
        do banco.

    Essa decisão de design é necessária pois em jpql não tenho acesso a funções de datas complexas, por exemplo para
    fazer os cálculos de dias da semana, que são necessários para gerar os valores corretos da consulta. Pensando em
    extensibilidade do sistema e reuso de código, sou favorável à primeira abordagem, porém pensando em performance e
    na necessidade de ordenação pelos valores, a segunda se faz necessária e atende aos requisitos.

4. Como estou desenvolvendo o código sozinho e para o desafio apenas, optei por desenvolver o código direto na `main`. 

## Conclusão

Espero que gostem! Tive a oportunidade de utilizar vários conhecimentos e adquirir alguns novos também, foi bastante
proveitoso para mim realizar este projeto!
