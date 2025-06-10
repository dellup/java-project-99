FROM gradle:8.6-jdk21

WORKDIR /

COPY / .

RUN chmod +x gradlew && \
    ./gradlew installDist

CMD ./build/install/demo/bin/demo