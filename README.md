
# Prueba tecnica de tipo de cambio

A brief description of what this project does and who it's for


## Deployment

Generar Jar

```bash
  mvnw.cmd clean package -DskipTests
```

Generar build Msvc-Payments

Debes colocarte dentro del proyector:
```bash
   docker build -t payments . -f -\Dockerfile
```

Crear una red en docker 

```bash
  docker network create spring 
```

Lenvantar contenedor mysql con volumen

```bash
docker run -d -p 3307:3306 --name mysql8 --network spring  -e  MYSQL_ROOT_PASSWORD=sasa  -e MYSQL_DATABASE=msvc_usuarios -v data-mysql:/var/lib/mysql  --restart=always mysql:8
```

Generar build Msvc-balance

Debes colocarte dentro del proyector:
```bash
   docker build -t balances . -f -\Dockerfile
```


Levantar proyecto Msvc-Payments en docker

Debes colocarte dentro del proyector:
```bash
   docker run -p 8081:8081 --name msvc-payments --rm --network spring payments
```


Levantar proyecto Msvc-balances en docker

Debes colocarte dentro del proyector:
```bash
   docker run -p 8082:8082 --name msvc-balances --rm --network spring balances
```




## API Reference

#### Listar todas las monedas

```http
  GET /api/payments/currency
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | id de la moneda |
| `code` | `string` | Codigo de moneda |
| `name` | `string` | Nombre de la moneda |

#### Guardar moneda

```http
  POST /api/payments/currency
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `code` | `string` | **Required**. Codigo de moneda |
| `name` | `string` | **Required**. Nombre de la moneda | 

 #### Listar tipo de cambios

```http
  GET /api/payments/exchangeRate
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id` | `number` | id de la moneda |
| `amount` | `number` | Cantidad de cambio |
| `id_currency_to` | `number` | Id de la moneda destino |
| `currency_to` | `Currency` | Id de la moneda destino |
| `id_currency_from` | `number` | Id de la moneda origen |
| `currency_from` | `Currency` | Id de la moneda origen |


 #### Guardar tipo de cambio

```http
  POST /api/payments/exchangeRate
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `amount` | `number` | Cantidad de cambio |
| `id_currency_to` | `number` | Id de la moneda destino |
| `id_currency_from` | `number` | Id de la moneda origen |
| `date_registry` | `date` | fecha format: AAAA-MM-DD |


 #### Consultar tipo de cambio

```http
  POST /api/payments/exchangeRate/consult
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `amount` | `number` | Cantidad de cambio |
| `to` | `string` | codigo de  moneda destino- Ejemplo: USD |
| `from` | `string` | codigo de la  moneda origen- Ejemplo: PEN | 



 #### Pagos

```http
  POST /api/balances/balance
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `amount` | `number` | Cantidad de cambio |
| `to` | `string` | codigo de  moneda destino- Ejemplo: USD |
| `from` | `string` | codigo de la  moneda origen- Ejemplo: PEN | 

 #### Crear usuario

```http
  POST /api/payments/users
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `username` | `string` | Usuarios |
| `password` | `string` | password |
| `email` | `string` | correo electronico | 
| `locked` | `boolean` | Si el usuario esta bloqueado | 
| `disabled` | `boolean` | Si el usuario esta desactivado | 


 #### Login

```http
  POST /api/payments/auth/login
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `username` | `string` | Usuarios |
| `password` | `string` | password |

 #### Actualizar tipo de cambios

```http
  POST /api/payments/exchangeRate/change
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `amount` | `number` | Cantidad de cambio |
| `to` | `string` | codigo de  moneda destino- Ejemplo: USD |
| `from` | `string` | codigo de la  moneda origen- Ejemplo: PEN | 

${id}
## Authors

- [@Ing.KevinCaderon](https://github.com/nevada25)

