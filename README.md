```text
                                                          _  (MVP)
 _ __ ___  ___  ___ _ ____   ____ _            __ _ _ __ (_)
| '__/ _ \/ __|/ _ \ '__\ \ / / _` |  _____   / _` | '_ \| |
| | |  __/\__ \  __/ |   \ V / (_| | |_____| | (_| | |_) | |
|_|  \___||___/\___|_|    \_/ \__,_|          \__,_| .__/|_|
                                                   |_|      
```

## Funcionalidades Básicas:
* **Cadastro de Restaurantes:** Os restaurantes podem se cadastrar no
sistema, fornecendo informações como nome, localização, tipo de
cozinha, horários de funcionamento e capacidadeEmMesas total.
  - [X] Cadastro / Exclusão de restaurantes


* **Reserva de Mesas:** Os usuários podem fazer reservas para datas e horários específicos.
  - [X] Fazer reserva
  - [X] Cancelar reserva
  - [X] Concluir reserva


* **Avaliações e Comentários:** Após a visita, os usuários podem avaliar o restaurante e deixar comentários sobre sua experiência.
  - [x] **POST** - /avaliacao/{id-reserva} 
  - [X] **GET** - /avaliacao/{cnpj} (paginada)


* **Busca de Restaurantes:** Os usuários podem buscar restaurantes por nome, localização ou tipo de cozinha.
  - [X] Criar índices para esses campos 
    - Ordenação não necessária


* **Gerenciamento de Reservas:** Os restaurantes podem gerenciar as reservas, visualizando e atualizando o status das mesas
    - [X] Listar reservas (retornar o ID)
    - [X] Registrar liberação da mesa após saída do cliente

## Rodar localmente
Para rodar o projeto localmente, é necessário seguir o passo a passo abaixo.
### Banco de Dados
```shell
docker run -d -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=reservadb_dev -p5432:5432 postgres
```

### Configurar variáveis de ambiente:
```.dotenv
RESERVA_PROFILE=dev
RESERVA_DB_NAME=reservadb_dev
RESERVA_DB_USER=postgres
RESERVA_DB_PASSWORD=postgres
RESERVA_DB_HOST=postgresql://localhost
RESERVA_DB_PORT=5432
```

### Executar Aplicação
```shell
mvn spring-boot:run
```

## Compose
Alternativamente pode-se executar o projeto com o Docker Compose:
```shell
docker compose up -d
```
ou

```shell
docker-compose up -d
```