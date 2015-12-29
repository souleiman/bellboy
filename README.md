bellboy
====

To build the project

    $> ./gradlew clean # If using gradle first time, it will download gradle
    $> ./gradlew build # builds project and tests code
    $> ./gradlew installDist # If you don't want to use a bundled zip
            executable is in build/install/bellboy/
            

The binary is bundled in build/distributions/bellboy-*.{tar|zip}; 

    $> cd
    $> tar -xvf bellboy/build/distributions/bellboy-*.tar


To run the binary, (Note the same method applies if you skipped bundling and used build/install/bellboy/ instead):

    $> cd bellboy-*/
    # Apply your changes to config.json
    $> java -jar bellboy-*.jar
    
