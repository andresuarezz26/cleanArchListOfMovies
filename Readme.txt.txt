
Arquitecture de la App

La app tiene 3 capas:

data: se encarga de traer toda la data de fuentes externas a la aplicación. Básicamente es la que consulta los web services, data bases locales,
archivos del teléfono, shared preferences. Las clases que pertenencen a esta capa son: 
1. Servicios: encargados de consultar los web services
2. Datasources: encargados de consultar la base de datos local (Realm en este caso)
3. Repositorios: encargados de llamar a los servicios y datasources. El esquema que se siguió fue consultar a los servicios, si estos no traen información
o fallan entonces se obtiene la información de la base de datos local. En esta capa se encuentran las implemetaciones, en la capa de domain las
 abstracciones.
4. Responses: son modelos que son los que permiten la serialización de los datos que provienen del servicio web
5. Entidades: son los modelos de base de datos (Realm en este caso)
6. Mappers: son los que se encargan de convertir los modelos de Responses, Entidades y Domain
7. Módulos de Dagger: se definen módulos de Dagger para datasources, servicios y repositorios. Básicamente estos módulos definen cómo debe crear
Dagger las implementaciones de acuerdo con las abstracciones

domain: se encarga de ser el puente entre la clase de presentation y la de data. Es la que contiene todos los casos de usos que son necesarios
para que la apliación funcione. Por ejemplo, en este caso contiene el caso de uso para obtener tv shows o movies y también el caso de uso para 
obtener un tv show o movie por el id. Las clases que pertenecen a esta son:
1. Interactors: son los use cases de la aplicación. Se encargan de llamar a la abstracción de los repositorios. 
2. Repositorios: son las abstracciones, estos son implementados por clases en la capa de datos. 
3. Modelos de domain: son data clases de kotlin que contienen la información de cada show (movies o tv shows)

presentation: se encarga de lidiar con la parte de la presentación de los datos al usuario. Básicamente todas las clases de android relacionadas con 
la interfaz de usuario, como activities, fragments o recycler view, están en esta capa. Se utilizó el patrón MVVM para lograr separar la lógica
de la vista (Debido a que no se puede hacer unit test a la activity porque es un componente de android). En nuestro caso, la vista se comunica con el 
view model a través de android lifecycle events, por lo que la vista no tiene referencia al view model (Como pasa con el MVP).
Se utilizaron lifecycle events debido a que con estos no nos tenemos que preocupar por liberar los recursos, debido a que ellos conocen
sobre el ciclo de vida de los componentes de android. Las clases son:
1. Activities, Adapters, Views, LifeData...: todas las clases relacionadas con la UI de android, están en esta capa.
2. ViewModel: se encargan de consultar a los usecases de la capa de domain y notificar a la vista sobre los cambios que debe tener
3. AppComponent de Dagger: acá se define el grafo que seguirá Dagger, el appComponent contiene todos los módulos de la aplicación
4. ViewModelFactory:  se encarga de inyectar los view models dependendiendo del nombre de la clase

Debido a la importancia que tiene el unit testing, todos los viewmodel, usecases, repositories y mappers tienen
unit tests.


1. Single responsability principle (SRP):
 El propósito del principo de responsabilidad única consiste en que cada clase o componenete se encargue de resolver solo una
funcionalidad y que toda la funcionalidad se encuentre "encapsulada" en esa clase/componente. El propósito de este es hacer el código más mantenible, 
si hay algún cambio o un nuevo requerimiento, fácilmente se puede ver qué clases estarían afectadas por estos nuevos cambios. Esto hace parte fundamental
para lograr que el código sea como Plug-and-play como se habla en el libro Clean Architecture.

2. Para mí un buen código debe cumplir estas características:
- Debe separar las cosas relacionadas con el Framework de la lógica. Con esto logramos hacer un código que sea testeable debido a que extraemos
los componentes del framework en unas clases, y en las otras clases 
- La lógica de negocio (Clases de Java o Kotlin) deben estar totalmente testeadas: si se requiere hacer un refactor, lo primero que debe verse son los tests.
Por lo que si ya se han probado todos los escenarios posibles mediante unit testing, estamos casi seguros que el código funciona bien y que al modificarlo, 
si se pasan los tests, va a seguir funcionando bien.
- Debe basarse en abstracciones y no en implementaciones (Inversion of dependencies principle): Si una implementación cambia y hay objetos que
 dependan de ella, los objetos que dependen de ella también cambien. En cambio, si dependemos de abstracciones cualquier cambio interno en la implementación, 
no va afectar a las clases que dependan de este.
- No debe tener muchos comentarios, sino buenos nombres: Los nombres de las variables, métodos y clases deben ser significativos con respecto
a la responsabilidad que tienen
- Debe seguir el single responsability principle: argumento en la respuesta anterior.
- No debe tener clases con demasiadas líneas de código: mientras más líneas de código tenga una clase, más dificil es de mantener. Por esta razón que una
clase tenga demasiadas líneas de código, significa que no se está cumpliendo el SRP
- Debe primar la composición sobre la herencia.
- Debe hacer uso de patrones de diseño

