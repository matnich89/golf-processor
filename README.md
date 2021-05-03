**Golf-Processor**

Built In Intellij , using java 11 sdk. 

Uses maven `mvn clean install` build and store to local repo

By default, the app needs to be run via docker-compose, go to the route of the project and run `docker-compose up`.

Running docker-compose will provision both a postgres container and build the app docker image and provision it 

The external port for the docker container has been mapped to 8090 so please use localhost:8090/ if
hitting the API``

_Running_

1.  navigate to root of the project
2. `mvn clean install` ( to build the jar )
3. `docker-compose up`

To run without docker-compose the following env variables will have to be passed in
`SPRING_DATASOURCE_URL` (e.g jdbc:postgresql://localhost:5432/postgres)
`SPRING_DATASOURCE_USERNAME` (e.g postgres)
`SPRING_DATASOURCE_PASSWORD` (e.g 12345)

_How to add functionality for future data sources_

1. Add a new dto class under `com.mat.golfprocessor.domain.dto.data` that implements the interface `Golf Data`

2. In `GolfData` (same package) add a new JsonSubType for the new dto class e.g `@JsonSubTypes.Type(value = GolfDataSource3.class)`

3. Under `com.mat.golfprocessor.convertor` package add implement class e.g `Source3Convertor` that implements `Convertor`
   then implement the apply method to transform the source to a GolfTournament Entity as needed
   
4. In `com.mat.golfprocessor.config.AppConfig` put a new map entry within the bean definition method for `formatConvertorMap()` e.g
   ` formatConvertorMap.put(GolfDataSource3.class, new Source3Convertor());`

5. Add a new ENUM to `com.mat.golfprocessor.enums.Source` to represent the new source e.g ` SOURCE3("source3");`

6. Enjoy the magic! (And of course add some tests etc :) ) 


_Assumptions / Thought process_

It was not clear to me if the app should be able to convert between country names and country codes, therefore in the interests of
time I did not implement this 

I have assumed that extra fields in sources that contain data that is not needed can simply be ignored 

I looked into trying to use test-containers for integration tests (https://www.testcontainers.org) 
so that a postgres engine could be used for testing , however ran into 
some setup issues and so reverted to h2 .

As source 1 had only dates present, I parse the epochtimes to dates rather than times.

I have assumed that its ok to store dates as UTC

_Non-Standard Libraries Used_

Awaitility (https://github.com/awaitility/awaitility) used to test async code 
Modelmapper (http://modelmapper.org) used to easily map dtos to entities (when its a trivial property copy)
Lombok (https://projectlombok.org) used extensively to reduce boiler plate code

_Other Things_

Please note that the `@Value` annotation I have used from lombok automatically makes class fields private and final
(So please don't think I missed these out :) 

Cheers

