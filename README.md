TRABALHO DE DESENVOLVIMENTO DE SOFTWARE_PARTE1
ALUNOS: Leonardo Mulinari, Murilo Schreiner Ortiz e Pedro Ferreira Taborda

Os seguintes métodos estão inocrporados, seguem alguns testes que podem ser realizados:

### Listar todos os pedidos
GET http://localhost:8080/ped/pedido
Content-Type: application/json

{
  "quantidade": 0,
  "precoUnitario": 0,
  "desconto": 0,
  "produto": {},
  "pedido": {}
}

### Cadastrar novo pedido
POST http://localhost:8080/ped/pedido
Content-Type: application/json

{
  "id_funcionario": "1",
  "id_cliente": "2",
  "data_pedido": "2024-05-09",
  "data_remessa": "2024-05-10"
}

### Listar pedido por id
GET http://localhost:8080/ped/pedidoid/1

### Atualizar pedido baseado no id_pedido
PUT http://localhost:8080/ped/pedido/1
Content-Type: application/json

{
  "id_funcionario": "1",
  "id_cliente": "3",
  "data_pedido": "2024-05-09",
  "data_remessa": "2024-05-11"
}

### Deletar pedido baseado no id_pedido
DELETE http://localhost:8080/ped/pedido/1

### Deletar todos os pedidos
DELETE http://localhost:8080/ped/pedido

### Listar todos os produtos
GET http://localhost:8080/prod/produto

### Cadastrar novo produto
POST http://localhost:8080/prod/produto
Content-Type: application/json

{
  "codigo": "P001",
  "descricao": "Produto 1",
  "valor_custo": 10.50,
  "valor_venda": 15.00
}

### Listar produto por código
GET http://localhost:8080/prod/produtoCod/P001

### Atualizar produto baseado no código
PUT http://localhost:8080/prod/produto/P001
Content-Type: application/json

{
  "codigo": "P001",
  "descricao": "Produto 1 Atualizado",
  "valor_custo": 12.50,
  "valor_venda": 17.00
}

### Deletar produto baseado no código
DELETE http://localhost:8080/prod/produto/P001

### Deletar todos os produtos
DELETE http://localhost:8080/prod/produto

### Listar todos os produtos-pedido
GET http://localhost:8080/produto-pedido

### Listar produto-pedido por id
GET http://localhost:8080/produto-pedido/1

### Cadastrar novo produto-pedido
POST http://localhost:8080/produto-pedido
Content-Type: application/json

{
  "quantidade": 5,
  "precoUnitario": 10.50,
  "desconto": 2.00,
  "produto": {
    "codigo": "P001",
    "descricao": "Produto 1",
    "valor_custo": 10.50,
    "valor_venda": 15.00
  },
  "pedido": {
    "id_funcionario": "1",
    "id_cliente": "2",
    "data_pedido": "2024-05-09",
    "data_remessa": "2024-05-10"
  }
}

### Atualizar produto-pedido baseado no id
PUT http://localhost:8080/produto-pedido/1
Content-Type: application/json

{
  "quantidade": 7,
  "precoUnitario": 10.50,
  "desconto": 2.00,
  "produto": {
    "codigo": "P001",
    "descricao": "Produto 1 Atualizado",
    "valor_custo": 12.50,
    "valor_venda": 17.00
  },
  "pedido": {
    "id_funcionario": "1",
    "id_cliente": "3",
    "data_pedido": "2024-05-09",
    "data_remessa": "2024-05-11"
  }
}

### Deletar produto-pedido baseado no id
DELETE http://localhost:8080/produto-pedido/1

### Deletar todos os produtos-pedido
DELETE http://localhost:8080/produto-pedido

