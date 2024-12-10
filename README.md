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
cozinha, horários de funcionamento e capacidade total.
  - [ ] Cadastro / Exclusão de restaurantes


* **Reserva de Mesas:** Os usuários podem fazer reservas para datas e horários específicos.
  - [ ] Listar horarios disponiveis para reserva
  - [ ] Fazer reserva
  - [ ] Cancelar reserva


* **Avaliações e Comentários:** Após a visita, os usuários podem avaliar o restaurante e deixar comentários sobre sua experiência.
  - [ ] **POST** - /avaliar/{id-reserva} 
  - [ ] **GET** - /restaurantes/{id-restaurante}/avaliacoes (paginada)


* **Busca de Restaurantes:** Os usuários podem buscar restaurantes por nome, localização ou tipo de cozinha.
  - Criar índices para esses campos 
  - Ordenação não necessária


* **Gerenciamento de Reservas:** Os restaurantes podem gerenciar as reservas, visualizando e atualizando o status das mesas
    - [ ] Registrar horários disponíveis para reserva
    - [ ] Listar reservas (retornar o ID)
    - [ ] Registrar liberação da mesa após saída do cliente
    - [ ] ...

