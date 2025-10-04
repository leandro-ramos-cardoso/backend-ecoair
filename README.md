# 🌬️ EcoAir - Sistema de Monitoramento de Qualidade do Ar

Sistema IoT para monitoramento de qualidade do ar em tempo real, desenvolvido com Spring Boot para gerenciar dispositivos sensores e coletar dados ambientais.

## 📋 Índice

- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Configuração e Instalação](#configuração-e-instalação)
- [API Endpoints](#api-endpoints)
- [Modelos de Dados](#modelos-de-dados)
- [Exemplos de Uso](#exemplos-de-uso)
- [Contribuição](#contribuição)

## 🎯 Visão Geral

O EcoAir é uma plataforma completa para monitoramento de qualidade do ar que permite:

- ✅ Cadastro e gerenciamento de dispositivos sensores
- ✅ Coleta de dados em tempo real via IoT
- ✅ Monitoramento de diferentes tipos de gases
- ✅ Geolocalização dos dispositivos
- ✅ API RESTful para integração
- ✅ Validação de dados e segurança

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Hibernate**
- **MapStruct** (Mapeamento de objetos)
- **Bean Validation**
- **Lombok**
- **Maven** (Gerenciamento de dependências)

## 📁 Estrutura do Projeto

```
src/main/java/com/ecoair/ecoair/
├── controller/          # Controladores REST
│   ├── DeviceController.java
│   └── SensorDataController.java
├── dtos/               # Data Transfer Objects
│   ├── DeviceRequestDTO.java
│   ├── DeviceResponseDTO.java
│   ├── SensorDataRequestDTO.java
│   └── SensorDataResponseDTO.java
├── enums/              # Enumerações
│   └── UserType.java
├── mapper/             # Mapeadores MapStruct
│   ├── DeviceMapper.java
│   └── SensorDataMapper.java
├── model/              # Entidades JPA
│   ├── Device.java
│   ├── SensorData.java
│   └── User.java
├── repository/         # Repositórios JPA
│   ├── DeviceRepository.java
│   ├── SensorDataRepository.java
│   └── UserRepository.java
└── service/            # Camada de Serviços
    ├── DeviceService.java
    ├── DeviceServiceImpl.java
    ├── SensorDataService.java
    ├── SensorDataServiceImpl.java
    ├── UserService.java
    └── UserServiceImpl.java
```

## ⚙️ Configuração e Instalação

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Banco de dados (H2, MySQL, PostgreSQL)

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd EcoAir
```

### 2. Execute o projeto

```bash
# Compilar e executar
./mvnw spring-boot:run

# Ou usando Maven
mvn spring-boot:run
```

### 3. Acesse a aplicação

- **API**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console

## 🔌 API Endpoints

### Dispositivos (Devices)

| Método | Endpoint       | Descrição                    |
| ------ | -------------- | ---------------------------- |
| `POST` | `/device`      | Criar novo dispositivo       |
| `GET`  | `/device`      | Listar todos os dispositivos |
| `GET`  | `/device/{id}` | Buscar dispositivo por ID    |
| `PUT`  | `/device/{id}` | Atualizar dispositivo        |

### Dados dos Sensores (Sensor Data)

| Método | Endpoint                     | Descrição                     |
| ------ | ---------------------------- | ----------------------------- |
| `POST` | `/sensor-data`               | Criar novo registro de dados  |
| `POST` | `/sensor-data/data`          | Receber dados do sensor (IoT) |
| `GET`  | `/sensor-data`               | Listar todos os dados         |
| `GET`  | `/sensor-data/{id}`          | Buscar dados por ID           |
| `GET`  | `/sensor-data/mac/{mac}`     | Buscar dados por MAC          |
| `GET`  | `/sensor-data/gas/{gasType}` | Buscar dados por tipo de gás  |

### Usuários (Users)

| Método | Endpoint     | Descrição                |
| ------ | ------------ | ------------------------ |
| `POST` | `/user`      | Criar novo usuário       |
| `GET`  | `/user`      | Listar todos os usuários |
| `GET`  | `/user/{id}` | Buscar usuário por ID    |
| `PUT`  | `/user/{id}` | Atualizar usuário        |

## 📊 Modelos de Dados

### Device (Dispositivo)

```json
{
  "id": 1,
  "deviceName": "Sensor Sala 101",
  "mac": "78:21:84:8C:B4:F8",
  "latitude": -23.5505,
  "longitude": -46.6333,
  "gasType": "CO2",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### SensorData (Dados do Sensor)

```json
{
  "id": 1,
  "mac": "78:21:84:8C:B4:F8",
  "sensorValue": 450.5,
  "gasType": "CO2",
  "latitude": -23.5505,
  "longitude": -46.6333,
  "timestamp": "2024-01-15T10:30:00",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### User (Usuário)

```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@example.com",
  "password": "senha123"
}
```

## 💡 Exemplos de Uso

### 1. Cadastrar um Dispositivo

```bash
curl -X POST http://localhost:8080/device \
  -H "Content-Type: application/json" \
  -d '{
    "deviceName": "Sensor Sala 101",
    "mac": "78:21:84:8C:B4:F8",
    "latitude": -23.5505,
    "longitude": -46.6333,
    "gasType": "CO2"
  }'
```

### 2. Enviar Dados do Sensor

```bash
curl -X POST http://localhost:8080/sensor-data/data \
  -H "Content-Type: application/json" \
  -d '{
    "mac": "78:21:84:8C:B4:F8",
    "sensorValue": 450.5,
    "gasType": "CO2",
    "latitude": -23.5505,
    "longitude": -46.6333,
    "timestamp": "2024-01-15T10:30:00"
  }'
```

### 3. Buscar Dados por MAC

```bash
curl -X GET http://localhost:8080/sensor-data/mac/78:21:84:8C:B4:F8
```

### 4. Listar Todos os Dispositivos

```bash
curl -X GET http://localhost:8080/device
```

## 🔒 Validações

### Device

- **deviceName**: Obrigatório, máximo 150 caracteres
- **mac**: Obrigatório, formato válido (XX:XX:XX:XX:XX:XX), único
- **latitude**: Obrigatório, entre -90 e 90
- **longitude**: Obrigatório, entre -180 e 180
- **gasType**: Obrigatório, máximo 50 caracteres

### SensorData

- **mac**: Obrigatório, formato válido
- **sensorValue**: Obrigatório, entre 0.0 e 1000.0
- **gasType**: Obrigatório
- **latitude**: Obrigatório, entre -90 e 90
- **longitude**: Obrigatório, entre -180 e 180
- **timestamp**: Obrigatório

## 🚀 Funcionalidades Principais

### Para Dispositivos IoT

- ✅ Autenticação por MAC address
- ✅ Validação de formato de dados
- ✅ Geolocalização automática
- ✅ Timestamp de leitura

### Para Administradores

- ✅ CRUD completo de dispositivos
- ✅ Monitoramento de dados em tempo real
- ✅ Consultas por período, localização e tipo de gás
- ✅ Relatórios e análises

### Para Desenvolvedores

- ✅ API RESTful documentada
- ✅ Validações robustas
- ✅ Tratamento de erros
- ✅ Logs detalhados

## 🧪 Testes

```bash
# Executar todos os testes
./mvnw test

# Executar testes com relatório de cobertura
./mvnw test jacoco:report
```

## 📈 Monitoramento

- **Health Check**: `/actuator/health`
- **Métricas**: `/actuator/metrics`
- **Info**: `/actuator/info`

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 📞 Suporte

Para dúvidas ou suporte, entre em contato:

- **Email**: suporte@ecoair.com
- **Issues**: [GitHub Issues](https://github.com/seu-usuario/ecoair/issues)

---

**EcoAir** - Monitorando a qualidade do ar para um futuro mais sustentável 🌱
