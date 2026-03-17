# API Documentation (actualizada)

## ✅ Estado de los cambios (no se ha hecho push)
- **No se ha hecho `git push`**: todas las modificaciones están **locales**.
- Cambios detectados en:
  - `pom.xml`
  - `src/main/java/tecnm/demo/controllers/UsuarioController.java`
  - `src/main/java/tecnm/demo/models/Usuario.java`
  - `src/main/java/tecnm/demo/repositories/UsuarioRepository.java`
  - `src/main/resources/application.properties`
  - Nuevos archivos/directorios:
    - `src/main/java/tecnm/demo/config/*`
    - `src/main/java/tecnm/demo/security/*`
    - `src/main/java/tecnm/demo/controllers/AuthController.java`
    - `src/test/resources/application.properties`

---

## 🔐 Autenticación + seguridad implementada
### 1) JWT (token) + login/register
**Endpoints nuevos:**
- `POST /api/auth/register`
  - Crea un usuario nuevo.
  - El email debe ser único.
  - La contraseña se guarda **hasheada**.

- `POST /api/auth/login`
  - Devuelve un **JWT** si el email/password son correctos.
  - Responde 401 si las credenciales son inválidas.

### 2) Protección de rutas
- Todas las rutas **excepto** `/api/auth/**` requieren un token.
- Debes enviar el token en el header:
  - `Authorization: Bearer <TOKEN>`

---

## 🧩 Endpoints principales de la API (anteriormente existentes)
Estas rutas ya estaban en el proyecto y ahora requieren autenticación:

### Usuarios
- `GET /api/usuarios` – Lista todos los usuarios
- `GET /api/usuarios/{id}` – Obtiene un usuario por id
- `POST /api/usuarios` – Crea un usuario (requiere token)
- `PUT /api/usuarios/{id}` – Actualiza un usuario (requiere token)
- `DELETE /api/usuarios/{id}` – Elimina un usuario (requiere token)

### Productos, Categorías, Pedidos, etc.
- Las demás rutas (`/api/productos`, `/api/categorias`, etc.) ahora también requieren token JWT.

---

## 🔧 Cómo ejecutar la app localmente (con seguridad)
1. Inicia la app normalmente:
   ```bash
   mvn spring-boot:run
   ```
2. Regístrate:
   ```bash
   curl -X POST http://localhost:8080/api/auth/register \
     -H "Content-Type: application/json" \
     -d '{"nombre":"Juan","email":"juan@test.com","telefono":"123","sexo":"M","fechaNacimiento":"1990-01-01","contrasena":"miP4ss"}'
   ```
3. Obtén el token:
   ```bash
   curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"email":"juan@test.com","contrasena":"miP4ss"}'
   ```
4. Usa el token para llamadas autenticadas:
   ```bash
   curl -H "Authorization: Bearer <TOKEN>" http://localhost:8080/api/usuarios
   ```

---

## 📌 Notas de seguridad importantes
- La clave JWT (`app.jwt.secret`) se puede configurar vía `application.properties` o variable de entorno.
- `ddl-auto=validate` en `application.properties` evita cambios automáticos en el esquema.

---

## 🗂️ ¿Qué cambios hice en el código?
### Seguridad y autenticación
- Añadí `spring-boot-starter-security` y JWT (jjwt).
- Añadí filtros y servicios para validar el token y cargar usuario.
- Separé la configuración de `PasswordEncoder` en `PasswordConfig`.

### Validación de datos
- Añadí validaciones al modelo `Usuario` (`@NotBlank`, `@Email`, `@NotNull`).
- Usé `@Valid` en los endpoints de creación/actualización de usuario.

### Ajustes de configuración
- `application.properties` ahora usa `validate` en lugar de `update` para `ddl-auto`.
- Se agregó configuración de H2 para que `mvn test` corra sin DB externa.

---

## ✅ Próximo paso (opcional)
Si querés, puedo:
- Agregar un endpoint `/api/auth/me` que devuelva el usuario actualmente logueado.
- Implementar roles (`ADMIN`, `USER`) y protección por rol en ciertos endpoints.
- Loggear acceso y eventos de seguridad.

---

*Nota: aún no hay ningún `git push` hecho. Estas modificaciones están en tu copia local (branch `master`).*
