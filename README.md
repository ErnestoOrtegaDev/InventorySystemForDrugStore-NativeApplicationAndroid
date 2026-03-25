# 🏥 FarmaVida - Sistema de Gestión de Inventario Farmacéutico

FarmaVida es una aplicación móvil moderna desarrollada en Android nativo con **Jetpack Compose**. El sistema permite a los usuarios gestionar el inventario de medicamentos, controlar fechas de caducidad, recibir notificaciones de stock y personalizar su perfil de usuario, todo bajo una interfaz intuitiva y minimalista.

---

## 🚀 Características Principales

### 🔐 Autenticación y Perfil
*   **Pantalla de Acceso:** Inicio de sesión y registro con validación visual.
*   **Gestión de Perfil:** Permite cambiar el nombre, correo, teléfono y subir una foto de perfil desde la galería del teléfono. El encabezado del sistema se actualiza dinámicamente con los datos del usuario.

### 📊 Dashboard (Home)
*   **Banner de Alerta:** Visualización destacada de productos agotados (ej. Aspirina).
*   **Categorización:** Clasificación de productos por categorías (Bebés, Farmacia, Cuidado).
*   **Listas Inteligentes:** Carruseles de productos "Próximos a caducar" y "Por agotarse" con acceso directo a detalles.

### 📦 Inventario y CRUD Local
*   **Control Total:** Lista detallada de medicamentos con stock y fechas de vencimiento.
*   **Búsqueda Avanzada:** Buscador con filtrado en tiempo real y etiquetas populares (Tags).
*   **Gestión de Productos:**
    *   **Crear:** Formulario para añadir nuevos medicamentos con carga de imagen desde el dispositivo.
    *   **Leer:** Vista de detalles completa (Instrucciones, Advertencias, Precio).
    *   **Actualizar:** Modificación de datos existentes e imágenes.
    *   **Eliminar:** Borrado lógico de productos de la lista.

### 🔔 Notificaciones
*   **Buzón de Avisos:** Centro de mensajes categorizado por estado de medicamentos, confirmación de pedidos y alertas de caducidad.

---

## 🛠️ Stack Tecnológico

*   **Lenguaje:** [Kotlin](https://kotlinlang.org/)
*   **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material Design 3)
*   **Arquitectura:** State Hoisting y Manejo de Estados Reactivos.
*   **Carga de Imágenes:** [Coil 3](https://coil-kt.github.io/coil/) (Soporte para URIs locales y recursos).
*   **Navegación:** Gestión de estados mediante `rememberSaveable` para transiciones fluidas entre pantallas.

---

## 📸 Capturas de Pantalla

| Dashboard | Inventario | Detalles | Búsqueda |
| :---: | :---: | :---: | :---: |
| ![Home](https://via.placeholder.com/150) | ![Inventario](https://via.placeholder.com/150) | ![Detalles](https://via.placeholder.com/150) | ![Search](https://via.placeholder.com/150) |


---

## ⚙️ Instalación y Configuración

1.  **Clonar el repositorio:*
git clone https://github.com/ErnestoOrtegaDev/InventorySystemForDrugStore-NativeApplicationAndroid.git

### Abrir el proyecto: 
Inicia Android Studio y selecciona la opción Open. Navega hasta la carpeta donde clonaste el repositorio y ábrela.

### Sincronizar Gradle: 
Espera a que Android Studio descargue las dependencias necesarias y termine la sincronización del proyecto. Esto puede tomar unos minutos dependiendo de tu conexión a internet.

### Configurar el Emulador (Opcional): 
Si no usas un dispositivo físico, abre el Device Manager en Android Studio y crea un dispositivo virtual (AVD) con una API reciente (se recomienda API 24 o superior).

### Ejecutar la aplicación: 
Conecta tu dispositivo o inicia el emulador, y presiona el botón verde de Run (o usa el atajo Shift + F10).

## 📂 Estructura del Proyecto
La arquitectura del código está pensada para ser escalable y fácil de mantener:

`ui/screens/`: Contiene las pantallas principales del sistema (Home, Inventario, Detalles, Perfil, Login).

`ui/components/`: Elementos visuales reutilizables (Botones, Tarjetas de Medicamentos, Barras de Búsqueda, TopBars).

`model/`: Clases de datos (Data Classes) que representan la estructura de los medicamentos, categorías y usuarios.

`navigation/`: Gestión de las rutas y el estado de la navegación para permitir transiciones fluidas sin perder la información en pantalla.

## 🌱 Próximas Mejoras (Roadmap)
[ ] Integración con base de datos remota (Firebase / PostgreSQL) para sincronización en la nube.

[ ] Escáner de código de barras para registro rápido de medicamentos.

[ ] Implementación de notificaciones Push reales para alertas de caducidad.

[ ] Soporte para modo oscuro/claro nativo.

## 🎓 Contexto Académico / Autor
Desarrollado por Ernesto como parte del portafolio de proyectos para la carrera de Ingeniería en Tecnologías de la Información e Innovación Digital en la Universidad Politécnica de Durango (UNIPOLI DGO).
