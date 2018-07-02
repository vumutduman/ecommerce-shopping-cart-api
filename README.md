# ecommerce-shopping-cart-api

## NOT: ##
You should use Lombok plugin on your IDE, to build the project

## RUN SCRIPT ##
export PORT=8078
export JAVA_OPTS=-Xmx2048M -Xms2048M

java -Dserver.port=$PORT $JAVA_OPTS -jar target/ecommerce-shopping-cart-api-1.0.0-SNAPSHOT.jar

## SWAGGER URL ##
http://localhost:8078/swagger-ui.html
