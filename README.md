# Algolab - Java Server

Algolab es un laboratorio interactivo enfocado en comparar algoritmos de búsqueda y ordenamiento, midiendo tiempo de ejecución, uso de memoria, número de operaciones y número de iteraciones para cada implementación disponible. Esta es la versión Java del servidor.

## 🎯 Configuración Local Automática

Este proyecto soporta **configuración automática**. Esta rutina se ejecuta automáticamente al iniciar la aplicación **solo si no existe ninguna configuración previa** (es decir, si no se detecta el archivo `.env`). En ese caso, el sistema generará el archivo `.env` necesario y configurará el entorno con valores por defecto para facilitar el despliegue local.

## 📊 Métricas Registradas

Cada ejecución de un algoritmo reporta las siguientes métricas para facilitar el análisis comparativo:

- **Tiempo (ns):** Duración total medida con `System.nanoTime()`.
- **Memoria (bytes):** Calculada como `Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()`.
- **Operaciones:** Conteo acumulado de comparaciones, asignaciones y operaciones matemáticas relevantes dentro del algoritmo.
- **Iteraciones:** Total de ciclos ejecutados en bucles y llamadas recursivas, útil para dimensionar el esfuerzo estructural del algoritmo.

## 📋 Requerimientos del Sistema

- **Java**: 21 o superior
- **PostgreSQL**: 13 o superior (Recomendado, también soporta SQLite)

## 🚀 Instalación y Configuración

### 1. Navegar al Proyecto
Clonar el repositorio o descargarlo y luego ingresar a la carpeta:
```bash
cd algolab-java-server
```

### 2. Compilar el Proyecto
Para descargar dependencias y compilar el proyecto, utiliza el wrapper de Maven incluido:

**Windows:**
```bash
.\mvnw.cmd clean install
```

**macOS/Linux:**
```bash
./mvnw clean install
```

### 3. Configurar Variables de Entorno

Crear archivo `.env` en la raíz del proyecto (puedes copiar `.env.example`) con las siguientes variables:

```env
# Configuración del Servidor
SERVER_PORT=8080
APP_PROFILE=dev

# Base de datos (REQUERIDA)
# Ejemplo para PostgreSQL:
DATABASE_URL=jdbc:postgresql://localhost:5432/algolab
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=password

# Ejemplo para SQLite:
# DATABASE_URL=jdbc:sqlite:algolab.db

# Configuración de Seguridad
AUTH_PASSWORD=35772
AUTH_SECRET=auth_secret
AUTH_JWT_EXPIRATION_MS=2592000000

# Límites y Configuraciones
MOVIES_SORT_LIMIT=10000
WARMUP_ITERATIONS=25000
```

### 4. Configurar Base de Datos

Este proyecto utiliza Spring Data JPA y puede conectarse a PostgreSQL o SQLite.
- **Para PostgreSQL:** Asegúrate de crear la base de datos vacía (ej. `algolab`) en tu servidor PostgreSQL y configura las credenciales en el `.env`.
- **Para SQLite:** Configura la URL como `jdbc:sqlite:algolab.db` (asegúrate de que el archivo exista o deja que Hibernate lo cree si `ddl-auto` lo permite, aunque para este proyecto se recomienda usar el generador de datos).

### 5. (Opcional) Frontend Local

Si deseas servir la interfaz de usuario localmente, compila el proyecto cliente y coloca los archivos estáticos en la carpeta `src/main/resources/static`. Spring Boot servirá estos archivos automáticamente en la raíz.

## 🏃‍♂️ Ejecutar la Aplicación

**Windows:**
```bash
.\mvnw.cmd spring-boot:run
```

**macOS/Linux:**
```bash
./mvnw spring-boot:run
```

Una vez levantado el servidor podrás acceder a la aplicación en http://localhost:8080/ (si el frontend está configurado).

### 🖥️ Guía rápida de la interfaz web

1. Abre http://localhost:8080/
2. Selecciona cualquier usuario.
3. Ingresa como contraseña el valor de la variable `AUTH_PASSWORD` (por defecto `35772`).
4. Haz click en “Iniciar Sesión”.

### 🔍 Endpoints y Swagger

La documentación interactiva de la API está disponible en:
**http://localhost:8080/api/docs**

Para probar los endpoints protegidos en Swagger:
1. Usa el endpoint `POST /api/users/login` con:
   ```json
   {
     "user_id": 1,
     "password": "YOUR_AUTH_PASSWORD"
   }
   ```
2. Copia el token devuelto.
3. Haz clic en el botón **Authorize** en la parte superior e ingresa `Bearer <TU_TOKEN>`.

### 🔍 Endpoints Principales

- `GET /api/movies/sort/data-structures` - Algoritmos de ordenamiento disponibles
- `GET /api/movies/sort` - Ejecutar ordenamiento
- `GET /api/movies/search/data-structures` - Algoritmos de búsqueda disponibles
- `GET /api/movies/search` - Ejecutar búsqueda
- `GET /api/movies` - Listar películas
- `GET /api/users` - Listar usuarios
- `POST /api/users/login` - Autenticación
