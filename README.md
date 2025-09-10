# NamesNetworkClientService-ARSW

## Andrés Jacobo Sepúlveda Sánchez

### Instrucciones

#### Para correr los puntos del 1 al 6 simplemente hay que pararse en el directorio con su nombre y ejecutar las clases que esten dentro. 

**Para el punto 2** 

Asegurarse de escribir una URL valida en consola como por ejemplo ```https://google.com```. Los datos se guardaran automaticamente en el archivo ```resultado.html``` que se encuentra en la raiz de proyecto.

**Para el punto 3** 

Primero toca prender el servidor ```EchoServer``` antes que el cliente ```EchoClient```, ademas la entrada deben ser numeros para que se pueda sacar y devolver el cuadrado del mismo.

**Para el punto 4** 

Similar al punto 3 se debe prender antes el servidor ```MathServer``` y luego el cliente ```MathClient```, si desea cambiar de funcion debe escrbir por consola ```fun:cos``` para coseno, ```fun:sin``` para seno y ```fun:tan``` para tangente. La operacion por defecto siempre es coseno.

**Para el punto 5** 

Luego de prender la clase ```HttpServer``` desde el navegador web ingresando a la siguiente URL: ```http://localhost:35000/``` podra ver la pagina web la cual cuenta con dos funciones, mostrar la hora actual y mandar mensaje al servidor el cual mostrara por pantalla el texto: 
```
{
  "message": "Hola servidor desde el cliente <3"
}
```
Todos los archivos referentes a la pagina se encuentran en el directorio ```resources``` dentro del directorio ```main```.

**Para el punto 6** 

Debera ejecutar ambas clases, pero en este caso no importa si no ejecuta primero a la clase servidor ```DatagramTimeServer``` y luego a la clase cliente ```DatagramTimeClient``` dado que el ejercicio solicitaba que esta ultima permaneciera activa aun sin servidor. Es por esto que si usted apaga el servidor mientras que el usuario esta prendido no ocurrira nada, solamente se mostrara este mensaje por consola indicando que no se esta recibiendo datos del servidor: ```No response from server, retrying...```.

**Para el ultimo punto debera seguir los siguientes pasos para que todo funcione correctamente:**

1. Parece en la direccion en la que este el repositorio, por ejemplo: ```C:\desktop\works\NamesNetworkClientService-ARSW```.
   
2. Ejecute desde esa ruta el siguiente comando para tener los .class: ```javac -d target\classes src\main\java\org\escuelaing\edu\co\ejercicio7\*.java```.
   
3. Verifique que esten los archivos con: ```dir target\classes\org\escuelaing\edu\co\ejercicio7```, deberian aparer ```ChatApp.class``` y ```ChatServiceImpl.class```.
   
4. Abra una nueva ventana de CMD y ponga estos comandos:
     ```
     cd /d C:\desktop\works\ARSW\NamesNetworkClientService-ARSW
     java -Djava.rmi.server.hostname=127.0.0.1 -cp target\classes org.escuelaing.edu.co.ejercicio7.ChatApp
    ```
    
5. Luego responda a los prompts. Ejemplo:
   ```
   Tu nombre: Andres
   Puerto local para publicar tu servicio: 25600
   ```
  
6. Cuando pregunte por la IP del otro usuario es importante no escribir nada y abrir una nueva pestaña de CMD, en esta se debe poner los siguientes comandos:
   ```
   cd /d C:\desktop\works\Desktop\ARSW\NamesNetworkClientService-ARSW
   java -Djava.rmi.server.hostname=127.0.0.1 -cp target\classes org.escuelaing.edu.co.ejercicio7.ChatApp
   ```
  
7. Luego responda a los prompts. Ejemplo:
   ```
   Tu nombre: Daniel
   Puerto local para publicar tu servicio: 34000
   IP del otro usuario: 127.0.0.1
   Puerto remoto del otro usuario: 25600
   ```

8. Finalmente vuelva a la pestaña 1 y termine de responder al prompt con:
   ```
   IP del otro usuario: 127.0.0.1
   Puerto remoto del otro usuario: 34000
   ```

Haciendo esto deberia poder enviar un mensaje desde la consola 1 y este se mostrara en la consola 2, de igual forma desde la consola 2 deberian llegar los mensajes a la consola 1.








