# Freelance-Tracker 🧾

Migración del proyecto Autotrack a SpinrgBoot. 

Herramienta para autónomos que permite analizar el ciclo de vida de sus facturas y calcular métricas económicas clave.


## Arquitectura

Proyecto estructurado en capas con responsabilidades separadas:

```
model/        → Entidades JPA
repository/   → Acceso a datos con Spring Data JPA y JPQL
service/      → Lógica de negocio y cálculos
controller/   → Endpoints REST
```

---

## Endpoints

### Empresas `/api/empresas`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/empresas` | Listar todas las empresas |
| GET | `/api/empresas/{cif}` | Buscar empresa por CIF |
| POST | `/api/empresas` | Crear empresa |
| PUT | `/api/empresas/{cif}` | Actualizar empresa |
| DELETE | `/api/empresas/{cif}` | Eliminar empresa |

### Facturas `/api/facturas`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/facturas` | Listar todas las facturas |
| GET | `/api/facturas/{id}` | Buscar factura por ID |
| GET | `/api/facturas/empresa/{cif}` | Facturas de una empresa |
| GET | `/api/facturas/{id}/demora` | Días entre expedición y cobro |
| POST | `/api/facturas` | Crear factura |
| PUT | `/api/facturas/{id}/estado?estado=COBRADA` | Actualizar estado |
| DELETE | `/api/facturas/{id}` | Eliminar factura |

### Jornadas `/api/facturas/{id}/jornadas`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/facturas/{id}/jornadas` | Jornadas de una factura |
| GET | `/api/facturas/{id}/jornadas/{idJornada}` | Buscar jornada por ID |
| POST | `/api/facturas/{id}/jornadas` | Registrar jornada |
| DELETE | `/api/facturas/{id}/jornadas/{idJornada}` | Eliminar jornada |

### Informes `/api/informes`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/informes/mes?anio=2025&mes=1&neto=true` | Total cobrado en un mes |
| GET | `/api/informes/trimestre?anio=2025&mesInicio=1&mesFinal=3&neto=true` | Total cobrado en un trimestre |
| GET | `/api/informes/jornadamedia?cif=B12345678` | Importe medio por jornada de una empresa |


## Roadmap

- [ ] Documentación con Swagger
- [ ] Validaciones de entrada
- [ ] Frontend
- [ ] Tests unitarios con JUnit 5
- [ ] Despliegue

---

## Autor

**Gracia Fernández**
