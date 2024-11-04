# desafio-backend

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
   horário limite para a cobrança de uma diária adicional no checkout. Em um ambiente de produção isso pode ser 
   desabilitado.

2. Sobre o endpoint das consultas, eu entendi que seriam 2 endpoints distintos, um para listagem de clientes, e 
   outro para listagem dos clientes com cálculo de valor gasto, incluindo também os filtros de cliente presente ou 
   já ausente do hotel. Fazer a junção ou ajuste dessa interface dos endpoints seria simples depois da lógica desenvolvida.  

3. Como estou desenvolvendo o código sozinho e para o desafio apenas, optei por desenvolver o código direto na `main`. 

## Conclusão

Espero que gostem! Tive a oportunidade de utilizar vários conhecimentos e adquirir alguns novos também, foi bastante
proveitoso para mim realizar este projeto!
