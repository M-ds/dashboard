FROM postgres:13.2
ENV POSTGRES_USER adminPostgres
ENV POSTGRES_PASSWORD superSexyWachtwoord
ENV POSTGRES_DB dashboard-dev
COPY create-db-script.sql/ /docker-entrypoint-initdb.d/