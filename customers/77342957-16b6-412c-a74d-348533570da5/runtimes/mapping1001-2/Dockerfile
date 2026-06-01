# syntax=docker/dockerfile:1
# GraalVM-native build (Invariant 7). Provider-portable — no Railway/Fly specifics.
FROM ghcr.io/graalvm/native-image-community:21 AS build
WORKDIR /workspace
COPY . .
RUN ./mvnw -q -Pnative native:compile -DskipTests

FROM gcr.io/distroless/base-debian12
WORKDIR /app
COPY --from=build /workspace/target/mapping1001-2 /app/application
EXPOSE 8080
ENTRYPOINT ["/app/application"]
