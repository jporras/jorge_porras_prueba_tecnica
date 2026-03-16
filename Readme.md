Requisitos: Java 17+, Maven

Cómo ejecutar: mvn spring-boot:run

Header requerido: x-api-key: 123456

Endpoints de prueba con ejemplos curl

Ejemplo:

```
curl -H "x-api-key: 123456" "http://localhost:8080/polizas?tipo=COLECTIVA&estado=ACTIVA"
```