# Android-Intermediate-BCP

Android Intermediate Course -Belatrix BCP - February/March 2018

## Lesson 4

  - Network Connection

## Tools

Vamos a utilizar algunas herramientas que nos permitan realizar pruebas de los servicios que vamos a consumir desde nuestra app.

- Postman
Esta herramienta nos permite conectarnos a cualquier API Rest
https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop

- Visores de Json
JsonEditor , nos permite ver de una manera más ordenada la tramas que recibimos de los servicios, que normalmente son JSON. http://jsoneditoronline.org/

## Configuración del proyecto

- Primer paso:

Para conectarnos a la nube , necesitamos habilitar el permiso de internet. Para esto nos vamos a AndroidManifest y agregarmos el permiso :

```
...
	<manifest xmlns:android="http://schemas.android.com/apk/res/android"
package="com.isil.am2template">
    <uses-permission android:name="android.permission.INTERNET"/>
    <application
	android:allowBackup="true"
	android:icon="@mipmap/ic_launcher"
	android:label="@string/app_name"
	android:roundIcon="@mipmap/ic_launcher_round"
	android:supportsRtl="true"
	android:theme="@style/AppTheme">
...
```

- Segundo Paso :

Vamos a agregar la dependencias de algunas librerias que nos ayuden para la conexión y procesar los datos que nos llega de la nube.

Retrofit : Es un cliente http diseñado para consumir servicios RESTFul , usa anotaciones para declarar las llamadas POST, GET, PUT , etc y tambien cuenta con callbacks para las respuesta a las peticiones hechas al servicio.

Gson : Esta librería te permite procesar las tramas del respuesta de un servicio(JSON) y poder convertirlas en clases.

OkHtttp : Sobre esta librería se construyo retrofit y es una librería general para realizar cualquier tipo de conección con un servicio RestFul o SOAP. Tambíen cuenta con ciertos utilitarios para poder visualizar en consola las tramas de envío y de respuesta . 

En el file build.gradle de la app realizamos lo siguiente :
		
```
//RETROFIT https://github.com/square/retrofit
    //compile 'com.squareup.retrofit:retrofit:1.9.0'
    compile "com.squareup.retrofit2:retrofit:$rootProject.retrofit2"

    //GSON https://github.com/google/gson
    compile 'com.google.code.gson:gson:2.8.0'
    compile "com.squareup.retrofit2:converter-gson:$rootProject.gson"

    //INTERCEPTOR
    compile "com.squareup.okhttp3:logging-interceptor:$rootProject.okhttp3"
```

y las versiones son declaradas en el file build.gradle del proyecto:
		
```
ext {
    // Sdk and tools
    minSdkVersion = 15
    targetSdkVersion = 26
    compileSdkVersion = 26
    buildToolsVersion = '26.1.0'
    constraintLayoutVersion='1.0.2'

    // App dependencies
    supportLibraryVersion = '26.1.0'
    junitVersion = '4.12'

    roomVersion= '1.0.0'
    gsonVersion='2.8.0'

    retrofit2='2.3.0'
    gson='2.3.0'
    okhttp3='3.4.1'
}
```
## Probando los servicios 
	
Antes de realizar las llamadas a los servicios desde la app , es saludable revisar que los servicios estén operativos y corroborar cuales son las tramas de envío ( request) y de respuesta (response).
Para esto, vamos a usar POSTMAN

- Rest API

En esta oportunidad se ha construido un API Rest usando node.js y mongo.db
La url base es :

```
	https://obscure-earth-55790.herokuapp.com/
```

- Login

	Método : POST
	Path : api/login
	Url : https://obscure-earth-55790.herokuapp.com/api/login
	Request :
```
	{
		"username":"admin@admin.com",
		"password": "123456"
	}
```
	Response :

```
	{
	    "msg": "success",
	    "status": 200,
	    "data": {
		"_id": "59e0540d429d3f501d6493de",
		"id": "59e0540d429d3f501d6493de",
		"username": "admin@admin.com",
		"password": "123456",
		"firstname": "Admin",
		"lastname": "Admin",
		"__v": 0
	    }
	}
```

- Registro :

	Método : POST
	Path : api/users/register
	Url : https://obscure-earth-55790.herokuapp.com/api/users/register

	Request :
```
{
	"username":"demo@admin.com",
	"password":"123456",
	"firstname": "Demo",
	"lastname": "Demo"
}
```
	Response :

```
	{
	    "__v": 0,
	    "id": "59ea8b0f3ad73212009b314b",
	    "username": "demo@admin.com",
	    "password": "123456",
	    "firstname": "Demo",
	    "lastname": "Demo",
	    "_id": "59ea8b0f3ad73212009b314b"
	}
```

- Usuarios :

	Método : GET
	Path : api/users/
	Url : https://obscure-earth-55790.herokuapp.com/api/users/

	Response :
```
	{
	    "msg": "success",
	    "status": 200,
	    "data": [
		{
		    "_id": "59e0540d429d3f501d6493de",
		    "id": "59e0540d429d3f501d6493de",
		    "username": "admin@admin.com",
		    "password": "123456",
		    "firstname": "Admin",
		    "lastname": "Admin",
		    "__v": 0
		},
		{
		    "_id": "59e17088225f6f7b12d14b07",
		    "id": "59e17088225f6f7b12d14b07",
		    "username": "demo@demo.com",
		    "password": "123456",
		    "firstname": "Demo",
		    "lastname": "Demo",
		    "__v": 0
		},
		{
		    "_id": "59ea8b0f3ad73212009b314b",
		    "id": "59ea8b0f3ad73212009b314b",
		    "username": "demo@admin.com",
		    "password": "123456",
		    "firstname": "Demo",
		    "lastname": "Demo",
		    "__v": 0
		}
	    ]
	}
```    
    
## References 

- Connecting to the Network https://developer.android.com/training/basics/network-ops/connecting.html

- Transmittin Network Data Using Volley https://developer.android.com/training/volley/index.html

- Retrofit http://square.github.io/retrofit/

- Retrofit 2 tutoriales https://futurestud.io/

