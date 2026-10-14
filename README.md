# Monitor E-Commerce - API Administrativa

Projeto desenvolvido para a disciplina de **Tópicos em Programação I** (UNITINS - Ciência da Computação / Sistemas de Informação), ministrada pelo **Prof. Jânio Elias Teixeira Júnior**.

O sistema consiste em um web service REST construído com **Java 25**, **Quarkus 3.38.3**, **Hibernate ORM com Panache** e **PostgreSQL**, seguindo o padrão arquitetural em camadas ensinado pelo professor.

---

## 🎯 Requisitos da Avaliação A1 Atendidos

| Requisito Avaliado | Como foi implementado no projeto | Entidades Envolvidas |
| :--- | :--- | :--- |
| **Herança (Inheritance)** | `@Inheritance(strategy = InheritanceType.JOINED)` com classe base e tabelas filhas compartilhando PK | `Monitor` (base) $\rightarrow$ `GamingMonitor` e `ProfessionalMonitor` |
| **Composição (1:1)** | `@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)` onde a parte depende do ciclo de vida do todo | `Monitor` (1) $\longleftrightarrow$ (1) `HardwareSpec` |
| **Associação (1:N)** | `@ManyToOne @JoinColumn(name = "id_brand")` entre entidades independentes | `Brand` (1) $\longleftrightarrow$ (N) `Monitor` |
| **Agregação (N:N)** | `@ManyToMany` via tabela `monitor_accessory` para itens agregados que existem independentemente | `Monitor` (N) $\longleftrightarrow$ (N) `Accessory` |
| **Muitos para Muitos (N:N)** | `@ManyToMany` via tabela `monitor_feature` com chave composta e `@JoinTable` | `Monitor` (N) $\longleftrightarrow$ (N) `Feature` |
| **Cardinalidade 1:1** | Relação unívoca entre o monitor e sua especificação técnica | `Monitor` (1) $\longleftrightarrow$ (1) `HardwareSpec` |
| **Cardinalidade 1:N** | Uma marca possui vários monitores; o monitor pertence a uma marca | `Brand` (1) $\longleftrightarrow$ (N) `Monitor` |
| **Cardinalidade N:N** | Monitores possuem múltiplos recursos/tecnologias; o mesmo recurso está em vários monitores | `Monitor` (N) $\longleftrightarrow$ (N) `Feature` |
| **Mínimo de 5 CRUDs** | **6 CRUDs Administrativos completos** com POST, GET por ID, GET busca/filtro, PUT e DELETE | `Brand`, `Feature`, `Accessory`, `Monitor`, `GamingMonitor`, `ProfessionalMonitor` |

---

## 🏛️ Padrão Arquitetural das Camadas

```
src/main/java/br/unitins/top1/
├── model/                  # Entidades JPA (DefaultEntity, Brand, Feature, Accessory, HardwareSpec, Monitor, ...)
│   └── converterjpa/       # Conversores de atributo JPA (TipoPainelConverter)
├── dto/                    # Java Records de entrada (validações Jakarta) e saída (fromEntity)
├── repository/             # PanacheRepository<T> com queries personalizadas (findByName, etc.)
├── service/                # Interfaces de serviço e implementações @ApplicationScoped com @Transactional
└── resource/               # Endpoints REST (JAX-RS / Quarkus REST) com status codes HTTP padronizados
```

---

## 🚀 Endpoints da API Administrativa

A documentação interativa Swagger UI pode ser acessada em: `http://localhost:8080/q/swagger-ui`

### 1. Brands (Marcas / Fabricantes)
- `GET /brands` - Listar todas as marcas
- `GET /brands/{id}` - Buscar marca por ID
- `GET /brands/search?name={name}` - Buscar marcas por nome
- `POST /brands` - Cadastrar nova marca (201 Created)
- `PUT /brands/{id}` - Atualizar marca existente (204 No Content)
- `DELETE /brands/{id}` - Excluir marca (204 No Content)

### 2. Features (Recursos / Tecnologias: G-Sync, HDR10, FreeSync)
- `GET /features` - Listar todos os recursos
- `GET /features/{id}` - Buscar recurso por ID
- `GET /features/search?name={name}` - Buscar recurso por nome
- `POST /features` - Cadastrar recurso (201 Created)
- `PUT /features/{id}` - Atualizar recurso (204 No Content)
- `DELETE /features/{id}` - Excluir recurso (204 No Content)

### 3. Accessories (Acessórios de Monitores: Braço VESA, Cabos, ScreenBar)
- `GET /accessories` - Listar todos os acessórios
- `GET /accessories/{id}` - Buscar acessório por ID
- `GET /accessories/search?name={name}` - Buscar por nome
- `GET /accessories/search/category?category={category}` - Buscar por categoria
- `POST /accessories` - Cadastrar acessório (201 Created)
- `PUT /accessories/{id}` - Atualizar acessório (204 No Content)
- `DELETE /accessories/{id}` - Excluir acessório (204 No Content)

### 4. Monitors (Monitores Base)
- `GET /monitors` - Listar monitores
- `GET /monitors/{id}` - Buscar monitor por ID
- `GET /monitors/search?name={name}` - Buscar monitores por nome
- `GET /monitors/search/brand?brandId={brandId}` - Buscar monitores por marca
- `POST /monitors` - Cadastrar monitor com especificação técnica, recursos e acessórios (201 Created)
- `PUT /monitors/{id}` - Atualizar monitor (204 No Content)
- `DELETE /monitors/{id}` - Excluir monitor e sua HardwareSpec em cascata (204 No Content)

### 5. Gaming Monitors (Monitores Gamer - Herança)
- `GET /gaming-monitors` - Listar monitores gamer
- `GET /gaming-monitors/{id}` - Buscar por ID
- `GET /gaming-monitors/search?name={name}` - Buscar por nome
- `POST /gaming-monitors` - Cadastrar monitor gamer (refreshRate, responseTime, gsyncSupport)
- `PUT /gaming-monitors/{id}` - Atualizar monitor gamer (204 No Content)
- `DELETE /gaming-monitors/{id}` - Excluir monitor gamer (204 No Content)

### 6. Professional Monitors (Monitores Profissionais - Herança)
- `GET /professional-monitors` - Listar monitores profissionais
- `GET /professional-monitors/{id}` - Buscar por ID
- `GET /professional-monitors/search?name={name}` - Buscar por nome
- `POST /professional-monitors` - Cadastrar monitor profissional (colorAccuracy, heightAdjustment)
- `PUT /professional-monitors/{id}` - Atualizar monitor profissional (204 No Content)
- `DELETE /professional-monitors/{id}` - Excluir monitor profissional (204 No Content)

---

## 🛠️ Como Executar

```bash
# Executar em modo desenvolvimento
./mvnw quarkus:dev

# Executar os testes
./mvnw test
```
