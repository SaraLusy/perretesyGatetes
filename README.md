# Arquitectura
* Módulos del proyecto
	- *Web*: Módulo destinado a la capa web para proveer los estáticos de la página web. También implementa la seguridad.
	- *Rest*: Módulo destinado a implementar una capa de servicios web de tipo REST en JSON.
			Depende del módulo `Business`.
	- *Persistence*: Módulo destinado a implementar la capa de persistencia de datos.
			Depende del módulo `Business` para tener acceso a las interfaces que requiere implementar en la persistencia.
			Favorecemos el principio de inversión de dependencias en la arquitectura.
	- *Domain*: Módulo destinado a implementar las entidades de datos, así como cualquier otra clase de utilidad, constantes, exceptions, ... .
	- *Business*: Módulo destinado a implementar todos los aspectos relativos a la lógica de la aplicación.
			    Depende del módulo `Domain`.

# Instalación en Spring Tools Suite (STS)
* Clonar repositorio
* Importar proyecto en Spring Tool Suite
* Descargar dependencias de librerías.

# Instalación en IntelliJ IDEA
* Clonar repositorio e importar proyecto
    - Si acabamos de instalar el programa, aparece directamente la opción de importar proyecto de Version Control. Si ya lo estabamos usando simplemente tenemos que ir a la barra de menú superior: FILE -> NEW -> Project From Version Control... y ponemos la URL del proyecto en HTTPS.
* Descargar dependencias de librerías.
    - El mismo IDE tiene que detectar que es un proyecto MAVEN, cuando lo haga, automaticamente descargará todas las dependencias que no tenga. Si por lo que sea no lo hace, en la barra lateral derecha hay una pestaña de Maven, es desplegarla y pulsar la opción de INSTALL.

# Configuración
* Profiles de Maven vs Spring Boot
    - Se han implementado 2 profiles `dev` y `prod` en Maven.
    - Estos profiles se encargan de:
        1. No añadir el Tomcat embebido porque hay un servidor standalone de Tomcat para cada entorno.
        2. Por otro lado, también se encarga de activar el application-xxx.properties que usará Spring Boot al iniciar la aplicación usando la propiedad `<activatedProperties>{entorno}</activatedProperties>`.
    - Fichero pom.xml de /edugestor
    ```
        <profiles>
		<profile>
			<id>dev</id>
			<dependencies>
				<dependency>
					<groupId>javax.servlet</groupId>
					<artifactId>javax.servlet-api</artifactId>
					<version>${servlet.version}</version>
					<scope>provided</scope>
				</dependency>
				<dependency>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-web</artifactId>
					<exclusions>
						<exclusion>
							<groupId>org.springframework.boot</groupId>
							<artifactId>spring-boot-starter-tomcat</artifactId>
						</exclusion>
					</exclusions>
				</dependency>
			</dependencies>
			<properties>
				<activatedProperties>dev</activatedProperties>
			</properties>
		</profile>
		<profile>
			<id>prod</id>
			<dependencies>
				<dependency>
					<groupId>javax.servlet</groupId>
					<artifactId>javax.servlet-api</artifactId>
					<version>${servlet.version}</version>
					<scope>provided</scope>
				</dependency>
				<dependency>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-web</artifactId>
					<exclusions>
						<exclusion>
							<groupId>org.springframework.boot</groupId>
							<artifactId>spring-boot-starter-tomcat</artifactId>
						</exclusion>
					</exclusions>
				</dependency>
			</dependencies>
			<properties>
				<activatedProperties>prod</activatedProperties>
			</properties>
		</profile>
	</profiles>
    ```
* Ficheros properties de configuración, parámetros y para que sirven.
* Configuración seguridad.

# Seguridad
* Consola de administración de dominios:
    - Test: `https://dev-05350629-admin.okta.com/`
* Configuración OKTA en Spring Boot
    1. En el fichero `aplication.properties` disponemos de las siguientes variables:
    ```
    app.security.enable [true | false]  // Desactiva la seguridad completa de la aplicación.
    okta.oauth2.issuer                  // Dominio Okta para identificar a los usuarios.
    okta.oauth2.clientId                // Identificador del cliente OKTA de la aplicación.
    okta.oauth2.clientSecret            // Clave secreta del cliente OKTA de la aplicación.
    ```

    2. En el fichero `pom.xml` del módulo padre se ha añadido el `starter-okta-spring-boot`
    ```
    <dependency>
        <groupId>com.okta.spring</groupId>
        <artifactId>okta-spring-boot-starter</artifactId>
        <version>${okta.springboot.version}</version>
    </dependency>
    ```

    3. Se ha creado un servicio REST para obtener la información del usuario una vez se identifique.
    ```
    @RequestMapping("usuario")
    public interface ISecurityController {
        @GetMapping("getUsuarioAutenticado")
        UsuarioDTO getUsuarioAutenticado(@AuthenticationPrincipal OidcUser userAuth);
    }
    ```

    4. La estructura del modelo de usuario en JSON es:
    ```
    {
        "preferredUsername": "sara@perez.com",
        "nickName": null,
        "name": "00u696nwp1D7gtabK5d7",
        "middleName": null,
        "fullName": "Sara Pérez Roldán",
        "givenName": "Sara",
        "address": {
            "country": null,
            "formatted": null,
            "locality": null,
            "postalCode": null,
            "region": null,
            "streetAddress": null
        },
        "gender": null,
        "birthdate": null,
        "picture": null,
        "profile": null,
        "website": null,
        "email": "sara@perez.com",
        "phoneNumber": null,
        "authenticatedAt": 1661341128,
        "updatedAt": 1661240213,
        "issuedAt": 1661349187,
        "expiresAt": 1661352787,
        "zoneInfo": null,
        "emailVerified": true,
        "phoneNumberVerified": false
    }
    ```
    - Documentación: [Más info](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/oauth2/core/oidc/StandardClaimAccessor.html)

# Configuración POSTMAN para acceder a los recursos protegidos.
    
1. **Introducción**:
    - Para poder utilizar los servicios REST protegidos por OKTA es necesario obtener un token de acceso a través de la API que proporciona OKTA.
    - El token de acceso sigue una serie de pasos para ser obtenido por lo que para facilitar su generación se ha automatizado todo el proceso en la siguiente colección.
2. **Ejecutar la API protegida por Okta:**
    - Configurar `Authorization > Bearer Token` en el recurso a acceder.
    - Como valor escriba el nombre de la variable del entorno {{ accessToken }}
    - Seleccione el entorno `PERRETESGATETES-LOCAL`.
    - Indicar usuario / password en el entorno `PERRETESGATETES-LOCAL`.
3. **Generar un token:**
    - Para setear un nuevo token a la variable `{{ accessToken }}`
    - Abra la colección de `SGVD > Okta Access Token`.
    - Pulsar en el botón "Run" de la colección para ejecutar en secuencia las 3 llamadas de la API de Okta.
4. **Obtener info del usuario:**
    - Se ha diseñado un servicio `GET /getUsuarioIdentificado` para devolver información del usuario autenticado que se encuentra en la carpeta `Usuario > getUserInfo`.
    - Si la llamada se hace desde un navegador se obtiene la información más completa y no es necesario transmitir el token.
    - De todas maneras, si se requiere ver la información del usuario por Postman, mejor invocar a la llamada propia de okta con el valor de `{{ accessToken }}`
    - Más info en: (https://developer.okta.com/docs/reference/api/oidc/#userinfo)

## Desconexión de la aplicación (logout)

1. **Funcionamiento**
    - El starter de Okta para Spring Boot se integra directamente con Spring Security.
    - Para desconectar al usuario de la sesión con Okta, será necesario acceder a la URL:
    `{domain}:{puerto}/sgvd/logout`
    - Ejemplo: `http://localhost:8080/perretesGatetes/logout`
    - La aplicación realizará una redirección a la página de identificación de nuevo y ya no se podrá aceder a ningún recurso de la web ni los endpoint de la API hasta que el usuario no se vuelva a identificar.
