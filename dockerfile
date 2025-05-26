FROM eclipse-temurin:17-jdk-alpine
#this is the image which is low size jdk with minimal but sufficent linux

WORKDIR /src
# Copy the Maven build output to the Docker image

COPY target/usermanagment-0.0.1-SNAPSHOT.jar /usermanagment.jar
#specify the the final jar file with the final root file

EXPOSE 8080
#expose the rout which could the mapping through docker vm to the windows to see the changes

ENTRYPOINT [ "java", "-jar","/usermanagment.jar" ]
